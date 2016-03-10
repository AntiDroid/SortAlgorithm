import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Person implements Comparable<Object>{

	private String vorname, nachname;
	private int alter;
	
	private static List<Person> personen = new ArrayList<Person>();
	
	public Person(String vn, String nn, int a){
		setVorname(vn);
		setNachname(nn);
		setAlter(a);
	}

	public String getNachname() {
		return nachname;
	}

	public void setNachname(String nachname) {
		this.nachname = nachname;
	}

	public int getAlter() {
		return alter;
	}

	public void setAlter(int alter) {
		this.alter = alter;
	}

	public String getVorname() {
		return vorname;
	}

	public void setVorname(String vorname) {
		this.vorname = vorname;
	}
	
	public static List<Person> getPersonen() {
		return personen;
	}

	public static void setPersonen(List<Person> p) {
		personen = p;
	}

	@Override
	public boolean equals(Object o){
		
		Person p = (Person)o;
		return (this.vorname.equals(p.vorname)) && (this.nachname.equals(p.nachname));// && (this.alter == p.alter);
	}
	
	@Override
	public int compareTo(Object arg0) {
		
		Person o = (Person)arg0;
		
		int nn = this.nachname.compareToIgnoreCase(o.nachname);
		int vn = this.vorname.compareToIgnoreCase(o.vorname);
		
		if(nn == 0){
			if(vn == 0){
				if(this.alter == o.alter)
					return 0;
				else if(this.alter < o.alter)
					return -1;
				else if(this.alter > o.alter)
					return 1;
			}
			else if(vn > 0)
				return 1;
			
			else if(vn < 0)
				return -1;
		}
		else if(nn > 0)
			return 1;
		
		else if(nn < 0)
			return -1;
		
		return 0;
	}

	public String toString(){
		return "Vorname: "+vorname+"\t\tNachname: "+nachname+"\nAlter: "+alter+"\n";
	}
	
	public static void readAndSetUp(String fileName, int maxEntries) throws Exception{
			
		String csv = "emp_"+fileName+".csv";
		BufferedReader br = new BufferedReader(new FileReader(csv));
		
		String str = br.readLine();
		String[] splittedStr;
		
		personen.clear();
		
		for(int i = 0; (i < maxEntries) && (str != null); i++){
			splittedStr = str.split("\\s+");
			personen.add(new Person(splittedStr[0], splittedStr[1], (int)(Math.random()*100)));
			str = br.readLine();
		}
		
		br.close();
	}
}
