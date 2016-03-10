import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

	static double dur;
	static boolean recursive = true;
	
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
			System.out.println("How many entries should be used?");
			
			try{
				count = Integer.parseInt(in.next());
			}catch(Exception e){
				count = 0;
				continue;
			}
		
			double[] rec = new double[3];
			double[] nor = new double[3];
			
			//Rekursiv
			Person.readAndSetUp("best", count);
			rec[0] = sort(recursive);
			Person.readAndSetUp("avg", count);
			rec[1] = sort(recursive);
			Person.readAndSetUp("worst", count);
			rec[2] = sort(recursive);
			
			//Normal
			Person.readAndSetUp("best", count);
			nor[0] = sort(!recursive);
			Person.readAndSetUp("avg", count);
			nor[1] = sort(!recursive);
			Person.readAndSetUp("worst", count);
			nor[2] = sort(!recursive);
			
			System.out.println("\n\n\n\tRecursive\tNormal");
			System.out.println("\nBEST\t  "+rec[0]+"\t\t "+nor[0]);
			System.out.println("\nAVG\t  "+rec[1]+"\t\t "+nor[1]);
			System.out.println("\nWORST\t  "+rec[2]+"\t\t "+nor[2]);
			
			System.out.println("\n\n");
			System.out.println("Restart?[0/1]");
			
			try{
				if(Integer.parseInt(in.next()) == 1)
					count = 0;
			}catch(Exception e){
				break;
			}
			
		}while(count < 2);
		
		System.out.println("Test aborted");
		
		in.close();
	}
	
	public static boolean testSorting(boolean isRecursive) throws Exception{
		
		Person.readAndSetUp("best", 999999);
		List<Person> superListe = new ArrayList<Person>(Person.getPersonen());
		
		Person.readAndSetUp("avg", 999999);
		sort(isRecursive);
		
		for(int i = 0; i < Person.getPersonen().size(); i++)
			if(!superListe.get(i).equals(Person.getPersonen().get(i)))
				return false;
		
		return true;
	}
	
	public static double sort(boolean r) {
		
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
