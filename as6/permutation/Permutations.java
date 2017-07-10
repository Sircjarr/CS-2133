// <E> is a generic type
import java.util.*;

// Class works like an Iterator and generates all possible permutations of a list
public class Permutations<E extends Comparable> {
	
	private List<E> list;
	private int listSize;
	
	private E c;
	private Permutations<E> p;
	private List<E> pL;
	
	private int i; // Count variable
	private int combinations;

	public Permutations(List<E> list) {
		this.list = list;
		listSize = list.size();
		
		combinations = factorial(listSize);
		
		// Recursive method
		if (listSize == 0 || list == null) {
			;
		}
		else {
			// Create and remember FIRST ELEMENT from list
			c = list.get(0);
			
			// Create and remember PERMUTATIONS OBJECT with leftover list
			List<E> subList = list.subList(1, list.size());
			p = new Permutations(subList, false);
			
			// Create and remember FIRST PERMUTATION list
			pL = new ArrayList<E>(p.list);
			
			i = 0;
		}
	}
	
	private Permutations(List<E> list, boolean innerCall) { // Second constructor handles subsequent calls, 
		this.list = list;                                   //bypasses factorial(), and sets i to 1;
		listSize = list.size();
		
		if (listSize == 0) {
			;
		}
		else {
			c = list.get(0);
			
			List<E> subList = list.subList(1, list.size());
			p = new Permutations(subList, false);
			
			pL = new ArrayList<E>(p.list);
			
			i = 1;
		}
	}
	
	public boolean hasNext() { 
		if(combinations == 0) { // No combinations left
			return false;
		}
		else {
			combinations--;
			return true;
		}
	}
	
	public ArrayList<E> next() { // Returns a new permutation of list, could also return List<E> if desired
		if(i >= listSize) {
			pL = p.next();
			i = 0;
		}
		
		// Insert Element into index i
		if(i == (listSize - 1)) {
			pL.add(c);
		}
		else {
			pL.add(i, c);
		}
		ArrayList<E> result = new ArrayList<E>(pL);
		pL.remove(i);
		
		i++;
		
		return result;
	}
	
	// Number of combinations based on factorials
	public int factorial(int listSize) {
		if(listSize == 0 || list == null) { //Sets combinations to zero if listSize is 0
			return 0;
		}
		
		int result = 1;
		for (int i = listSize; i > 1; i--) {
			result *= i;
		}
		return result;
	}
	
	// MAIN //////////////////////////////////////////////////////////////////////////////
	public static void main(String[] args) {
		// Create list with any type
		ArrayList<String> list = new ArrayList<>();
		list.add("banana");
		list.add("spoon");
		list.add("wizzcheese");
		list.add("Marrrrrrrrble");
		//list.add(4);
		//list.add(5);
		
		
		// Iterate and print out all possible permutations of the list
		for (Permutations<Integer> p = new Permutations(list); p.hasNext(); ) {
			ArrayList<Integer> temp = p.next();
			
			printPermutation(temp);
		}
	}
	
	public static <E> void printPermutation(ArrayList<E> temp) { // Note <E> before return type
		System.out.print("[");
		for (int i = 0; i < temp.size(); i++) {
			if(i == temp.size() - 1) {
				System.out.print(temp.get(i));
				break;
			}
			System.out.print(temp.get(i) + ", ");
		}
		System.out.print("]\n");
	}
}