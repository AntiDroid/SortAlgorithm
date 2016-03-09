import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

	static String str;
	static String[] splittedStr;
	static List<Person> personen = new ArrayList<Person>();
	static int von, bis;
	
	public static void main(String[] args) {

		try {
			Main m = new Main();
			personen = m.readAndSetUp("avg");
			m.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	public void start() throws Exception{ 

		//bubbleSort(personen);
		
		personen = mergeSort(personen);
		System.out.println("\n\n\nAnzahl: "+personen.size());
		
		List<Person> neu = readAndSetUp("best");
		
		for(int i = 0; i < personen.size(); i++){
			if(!personen.get(i).equals(neu.get(i))){
				System.out.println("\n\nFEHLER "+i);
			}
		}
		
		System.out.println("\n\n\nAnzahl: "+personen.size());
	}
	
	public List<Person> readAndSetUp(String fileName) throws Exception{
		
		List<Person> p = new ArrayList<Person>();
		
		File csv = new File(this.getClass().getResource("emp_"+fileName+".csv").toURI());
		BufferedReader br = new BufferedReader(new FileReader(csv));
			
		str = br.readLine();
		
		while(str != null){
			
			splittedStr = str.split("\\s+");
			if(splittedStr[0].equals("Aluzio") && splittedStr[1].charAt(0) == 'B')// && splittedStr[1].charAt(1) == 'a')
				p.add(new Person(splittedStr[0], splittedStr[1], (int)(Math.random()*100)));
			
			str = br.readLine();
		}
		
		br.close();
		
		return p;
	}
	
	public static List<Person> mergeSort(List<Person> pers){
		
		if(pers.size() > 1){
			
			List<Person> p1 = pers.subList(0, pers.size()/2);
			List<Person> p2 = pers.subList((pers.size()/2), pers.size());
			
			return merge(mergeSort(p1), mergeSort(p2));
		}
		else{
			return pers;
		}
	}
	
	public static List<Person> merge(List<Person> m1, List<Person> m2) {

		int posA = 0, posB = 0, pos = 0;
		List<Person> erg = new ArrayList<Person>();
		while(posA < m1.size() && posB < m2.size()){
			if(m1.get(posA).compareTo(m2.get(posB)) <= 0){
				erg.add(m1.get(posA));
				posA++;
				pos++;
			
			}
			else{
				erg.add(m2.get(posB));
				posB++;
				pos++;
			}
		}
		
		while(posA < m1.size()){
			erg.add(m1.get(posA));
			posA++;
			pos++;
		}
		
		while(posB < m2.size()){
			erg.add(m2.get(posB));
			posB++;
			pos++;
		}
		
		return erg;
	}


	static void bubbleSort(List<Person> ar){
		
		boolean sorted = false;
		int upper_border = ar.size();
		while(!sorted){
			
			sorted = true;
			upper_border--;
			for(int i = 0; i < upper_border; i++){
				if(ar.get(i).compareTo(ar.get(i+1)) == 1){
					swap(ar, i, i+1);
					sorted = false;
				}
			}
			
		}
		
	}
	
	static public void swap(List<Person> a, int x, int y){
		
		Person temp = a.get(x);
		//x = y
		a.set(x,  a.get(y));
		//y = x
		a.set(y, temp);
	}
}
