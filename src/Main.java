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
	
	static int counter = 0;
	
	public static void main(String[] args) {

		try {
			Main m = new Main();
			personen = m.readAndSetUp("worst");
			m.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	public void start() throws Exception{ 
		
		bubbleSort(personen);
	}
	
	public List<Person> readAndSetUp(String fileName) throws Exception{
		
		counter = 0;
		List<Person> p = new ArrayList<Person>();
		
		File csv = new File(this.getClass().getResource("emp_"+fileName+".csv").toURI());
		BufferedReader br = new BufferedReader(new FileReader(csv));
			
		str = br.readLine();
		
		while(str != null){
			counter++;
			splittedStr = str.split("\\s+");
			if(splittedStr[1].equals("Aamodt"))
				p.add(new Person(splittedStr[0], splittedStr[1], (int)(Math.random()*100)));
			
			str = br.readLine();
		}
		
		br.close();
		
		return p;
	}
	
	static void quickSort(List<Person> a, int left, int right){
		
		int le, ri;
		le = left; ri = right;
		
		Person pivot = a.get(5);
		
		while(le <= ri){
		
			while(a.get(le).compareTo(pivot) == -1)	le++;
			while(pivot.compareTo(a.get(ri)) == 1)	ri--;

			if(le <= ri){
				swap(a, le, ri);
				le++; ri--;
			}
		}

		if(left < ri)	quickSort(a, left, ri);
		if(le < right)	quickSort(a, le, right);

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
		
		counter++;
		Person temp = a.get(x);
		
		//x = y
		a.set(x,  a.get(y));
		//y = x
		a.set(y, temp);
	}
}
