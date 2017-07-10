import java.util.*;
import java.lang.*;

// NP sort slows down greatly at around length 12
// Most computation time is spent checking if list is sorted

public class PermutationsSort {
	
	public static void main(String[] args) {
		int length = 1;
		while(true) {
			ArrayList<Comparable> array = new ArrayList<Comparable>();
			for (int i = 0; i < length; i++) {
				array.add((int)(Math.random() * 999));
			}
			
			ArrayList<Comparable> result = new ArrayList<Comparable>(npSort(array));
			printPermutation(result);
			
			length++;
		}
	}
	
	public static ArrayList<Comparable> npSort(ArrayList<Comparable> array) {
		Permutations<Comparable> p = new Permutations<Comparable>(array);
		while(p.hasNext()) {
			ArrayList<Comparable> current = new ArrayList<Comparable>(p.next());
			boolean sorted = true;
			for (int i = 0; i < current.size()- 1; i++) {
				if (current.get(i).compareTo(current.get(i + 1)) >= 0) {
					sorted = false;
					break;
				}
			}
			if (sorted) {
				return current;
			}
		}
		return null;
	}
	
	public static void printPermutation(ArrayList<Comparable> temp) {
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