import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

	static boolean recursive = true;
	static String[] mode = { "worst", "avg", "best" };
	
	public static void main(String[] args) {

		try {
			Main m = new Main();
			m.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Testsequenz der Sortieralgorithmen unter verschiedenen Bedingungen
	 * @throws Exception
	 */
	public void start() throws Exception{ 
		
		Scanner in = new Scanner(System.in);
		int count = 0;
		
		do{
			System.out.println("How many entries should be used?");
			
			try{
				count = Integer.parseInt(in.next());
				if(count < 2)
					throw new Exception();
			}catch(Exception e){
				continue;
			}
			break;
			
		}while(true);
		
		double[] rec = new double[3];
		double[] normal = new double[3];
			
		System.out.println("Geht");
		for(int i = 0; i < 3; i++){
				
			Person.readAndSetUp(mode[i], count);
			rec[i] = sort(recursive);
				
			Person.readAndSetUp(mode[i], count);
			normal[i] = sort(!recursive);
		}
		
		PrintWriter writer = new PrintWriter("the-file-name.txt", "UTF-8");
		writer.println("\t\t  Recursive\t\t    Normal");
		writer.println("         ----------------------------");
		writer.println(mode[0].toUpperCase()+"\t\t"+rec[0]+"ms \t\t\t"+normal[0]+"ms");
		writer.println("\n"+mode[1].toUpperCase()+" \t\t"+rec[1]+"ms \t\t\t"+normal[1]+"ms");
		writer.println("\n"+mode[2].toUpperCase()+"\t\t"+rec[2]+"ms \t\t\t"+normal[2]+"ms");
		writer.println("         ----------------------------");
		writer.print("Items: "+count);
		writer.close();
		
		in.close();
	}
	
	/**
	 * Sortiermethode, welche die Zeit stoppt und den Algorithmus aussucht
	 * @param r zur Bestimmung des Algorithmuses 
	 * @return Dauer des Vorgangs in ms
	 */
	public static <T extends Comparable<T>> double sort(boolean r) {
		
		double start = System.currentTimeMillis();

		if(r)
			Person.setPersonen((List<Person>)mergeSort((List<T>)Person.getPersonen()));
		else
			selectionSort((List<T>)Person.getPersonen());

		return (System.currentTimeMillis()-start);
	}
	
	/**
	 * Der Kopfabschnitt des Merge-Sortier-Algorithmuses. 
	 * Er teilt die bestehnden Arrays in kleinere und uebergibt sie
	 * zur Verarbeitung an den eigentlichen Sortierer - merge().
	 * Dieser Vorgang geschieht rekursiv, sich selbst wiederholend.
	 * @param pers zu sortierende Liste
	 * @return die sortierte Unterliste
	 */
	public static <T extends Comparable<T>> List<T> mergeSort(List<T> pers){
		
		if(pers.size() > 1){
			
			List<T> p1 = pers.subList(0, pers.size()/2);
			List<T> p2 = pers.subList((pers.size()/2), pers.size());
			
			return merge(mergeSort(p1), mergeSort(p2));
		}
		else
			return pers;
		
	}
	
	/**
	 * Der eigentliche Sortiervorgang des Merge-Sortierens.
	 * Die beiden aufgeteilten Arrays werden zu einem neuen sortierten
	 * umgeschrieben.
	 * @param m1 Array 1
	 * @param m2 Array 2
	 * @return die sortierte Teil oder Hauptliste
	 */
	public static <T extends Comparable<T>> List<T> merge(List<T> m1, List<T> m2) {

		int posA = 0, posB = 0;
		
		List<T> erg = new ArrayList<T>();
		
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

	/**
	 * Einfacher Sortieralgorithmus.
	 * Man sucht für ein Element i ein nachfolgendes groesseres Element
	 * min und tauscht diese dann in der Position.
	 * @param ar die zu sortierende Liste
	 */
	public static <T extends Comparable<T>> void selectionSort(List<T> ar){
		
		for(int i = 0; i < ar.size()-1; i++){
			
			int min = i;
			for(int j = (i+1); j < ar.size(); j++){
				if(ar.get(min).compareTo(ar.get(j)) == 1)
					min = j;
			}
			swap(ar, i, min);
		}
	}
	
	/**
	 * Tauscht zwei Positionen aus einem Array miteinander
	 * @param a Array
	 * @param x Position 1
	 * @param y Position 2
	 */
	public static <T extends Comparable<T>> void swap(List<T> a, int x, int y){
		
		T temp = a.get(x);
		
		a.set(x,  a.get(y));
		a.set(y, temp);
	}

}
