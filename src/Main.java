import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

	static double dur;
	static boolean recursive = true;
	static String[] mode = { "best", "avg", "worst" };
	
	public static void main(String[] args) {

		try {
			Main m = new Main();
			m.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void start() throws Exception{ 
		
		Scanner in = new Scanner(System.in);
		
		int count = 0;
		
		do{
			System.out.println("How many entries should be used? (random input to abort)");
			
			try{
				count = Integer.parseInt(in.next());
				if(count < 2)
					throw new Exception();
			}catch(Exception e){
				break;
			}
			
			double[] rec = new double[3];
			double[] normal = new double[3];
			
			for(int i = 0; i < 3; i++){
				
				Person.readAndSetUp(mode[i], count);
				rec[i] = sort(recursive);
				
				Person.readAndSetUp(mode[i], count);
				normal[i] = sort(!recursive);
			}
			
			PrintWriter writer = new PrintWriter("the-file-name.txt", "UTF-8");
			writer.println("\t\t  Recursive\t\t    Normal");
			writer.println("         ----------------------------");
			writer.println("BEST\t\t"+rec[0]+"ms \t\t\t"+normal[0]+"ms");
			writer.println("\nAVG \t\t"+rec[1]+"ms \t\t\t"+normal[1]+"ms");
			writer.println("\nWORST\t\t"+rec[2]+"ms \t\t\t"+normal[2]+"ms");
			writer.println("         ----------------------------");
			writer.print("Items: "+count);
		
			
			writer.close();
			System.out.println("\n\n\n\n\n\n\n\n\n");
			
		}while(true);
		
		System.out.println("Benchmark aborted");
		
		in.close();
	}
	
	public static boolean testSorting(boolean isRecursive) throws Exception{
		
		Person.readAndSetUp(mode[0], 99999);
		List<Person> superListe = new ArrayList<Person>(Person.getPersonen());
		
		Person.readAndSetUp(mode[1], 99999);
		sort(isRecursive);
		
		for(int i = 0; i < Person.getPersonen().size(); i++)
			if(!superListe.get(i).equals(Person.getPersonen().get(i)))
				return false;
		
		return true;
	}
	
	public static  double sort(boolean r) {
		
		double start = System.currentTimeMillis();

		if(r)
			Person.setPersonen(mergeSort(Person.getPersonen()));
		else
			selectionSort(Person.getPersonen());

		return (System.currentTimeMillis()-start);
	}
	
	public static List<Person> mergeSort(List<Person> pers){
		
		if(pers.size() > 1){
			
			List<Person> p1 = pers.subList(0, pers.size()/2);
			List<Person> p2 = pers.subList((pers.size()/2), pers.size());
			
			return merge(mergeSort(p1), mergeSort(p2));
		}
		else
			return pers;
		
	}
	
	public static List<Person> merge(List<Person> m1, List<Person> m2) {

		int posA = 0, posB = 0;
		
		List<Person> erg = new ArrayList<Person>();
		
		while(posA < m1.size() && posB < m2.size()){
			if(m1.get(posA).compareTo(m2.get(posB)) <= 0){
				erg.add(m1.get(posA));
				posA++;
			}
			else{
				erg.add(m2.get(posB));
				posB++;
			}
		}
		
		while(posA < m1.size()){
			erg.add(m1.get(posA));
			posA++;
		}
		
		while(posB < m2.size()){
			erg.add(m2.get(posB));
			posB++;
		}
		
		return erg;
	}

	public static void selectionSort(List<Person> ar){
		
		for(int i = 0; i < ar.size()-1; i++){
			
			int min = i;
			for(int j = i+1; j < ar.size(); j++){
				if(ar.get(min).compareTo(ar.get(j)) == 1)
					min = j;
			}
			swap(ar, i, min);
		}
	}
	
	public static void swap(List<Person> a, int x, int y){
		
		Person temp = a.get(x);
		//x = y
		a.set(x,  a.get(y));
		//y = x
		a.set(y, temp);
	}

}
