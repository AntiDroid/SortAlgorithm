import java.util.ArrayList;
import java.util.List;

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
		
		List<Person> pe = new ArrayList<Person>();
		
		Person.readAndSetUp("worst", 10);
		sort(!recursive);
		
		pe = Person.getPersonen();
		
		Person.readAndSetUp("avg", 10);
		sort(!recursive);		
		
		Person.readAndSetUp("best", 10);
		sort(recursive);
		
		for(int i = 0; i < pe.size(); i++)
			if(!pe.get(i).equals(Person.getPersonen().get(i)))
				System.out.println("FEHLER");
		
		System.out.println("\n\n\n\nAnzahl der Elemente: "+Person.getPersonen().size());
	}
	
	public static void sort(boolean r) {
		
		double start = System.currentTimeMillis();

		if(r){
			System.out.print("\nRekursiv("+Person.getPersonen().size()+")\t");
			Person.setPersonen(mergeSort(Person.getPersonen()));
		}
		else{
			System.out.println("\nNormal("+Person.getPersonen().size()+")\t");
			bubbleSort(Person.getPersonen());
		}
		System.out.print("Dauer = "+ (System.currentTimeMillis()-start)+"\n");
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

	public static void bubbleSort(List<Person> ar){
		
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
	
	public static void swap(List<Person> a, int x, int y){
		
		Person temp = a.get(x);
		//x = y
		a.set(x,  a.get(y));
		//y = x
		a.set(y, temp);
	}
}
