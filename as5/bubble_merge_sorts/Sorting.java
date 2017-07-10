// CLASS NOTES
// how long it takes an algorithm to run, how much memory we use,
// how much bandwidth. Expressed in terms of input size (Big-O Notation)
// input size - standard way to measure resource use

// RUNNING TIME DIFFERENCE
// O(n log n) running times are faster and more efficient than O(n^2) running times. This is 
// because O(n^2) relies on nested loops for each element in an array, while O(n log n) 
// breaks the array into smaller, sorted pieces that are merged into the sorted list. 

import java.lang.*;
import java.util.Arrays;

public class Sorting {
	
	static final long twentySeconds = 20000;
	
	static boolean bDone = false;
	static boolean mDone = false;
	
	static int n = 10; // Array length n
	
	public static void main(String[] args) {
		
		double[] bArr; // Bubble Sort array  O(n^2)
		double[] mArr; // Merge Sort array  O(n log n)
		
		// Methods run until they are both considered done
		while (bDone == false || mDone == false) {
			bArr = new double[n];
			makeArray(bArr);
			mArr = new double[n];
			mArr = bArr.clone();
			
			// Bubble sort
			if (bDone == false) {
				long before = System.currentTimeMillis();
				bubbleSort(bArr);
				if (bDone == false) {
					long after = System.currentTimeMillis();
					System.out.printf("Bubble sort:%4slength: %d%4smilliseconds: %d\n"," ", n, " ", (after - before));
				}
			}
			
			// Merge sort
			if (mDone == false) {
				long before = System.currentTimeMillis();
				mergeSort(mArr);
				if (mDone == false) {
					long after = System.currentTimeMillis();
					System.out.printf("Merge sort:%5slength: %d%4smilliseconds: %d\n"," ", n, " ", (after - before));
				}
			}
			
			n *= 10;
			if (n >= 100000000) { // OutOfMemoryError is thrown when trying to make an array of this length.
				System.out.println("Merge sort memory limit reached at length 10^8");
				System.out.println("Program terminating.");
				System.exit(0);
			}
		}
	}
	
	public static double[] makeArray(double[] arr) {
		for (int i = 0; i < arr.length; i++) {
			arr[i] = Math.random();
		}
		return arr;
	}
	
	public static double[] bubbleSort(double[] arr) {
		long before = System.currentTimeMillis();
		int iter = (arr.length - 1);
		
		outer:
		for (int i = 0; iter > 0; i++) {
			for (int x = 0; x < iter; x++) {
				if (arr[x] > arr[x + 1]) {
					double temp = arr[x];
					arr[x] = arr[x + 1];
					arr[x + 1] = temp;
				}
				
				if ((System.currentTimeMillis() - before) > twentySeconds) {
					System.out.println("Bubble sort execution time exceeded twenty seconds at length " + n);
					System.out.println("Bubble sort terminating.");
					bDone = true;
					break outer;
				}
			}
			iter--;
		}
		return arr;
	}
	
	public static double[] mergeSort(double[] arr) {
		if (arr.length == 1) {
			return arr;
		}
		int m = (arr.length / 2);
		
		double[] left = Arrays.copyOfRange(arr, 0, m);	
		double[] right = Arrays.copyOfRange(arr, m, arr.length);
		
		left = mergeSort(left);
		right = mergeSort(right);
		
		double[] result = new double[left.length + right.length];
		
		int i = 0; // left index
		int j = 0; // right index
		int k = 0; // result index
		
		while (i < left.length && j < right.length) {
			
			result[k] = (left[i] < right[j]) ? left[i] : right[j];
			
			if (result[k] == left[i]) {
				k++;
				i++;
			}
			else if (result[k] == right[j]) {
				j++;
				k++;
			}
		}
		while (i < left.length) {
			result[k] = left[i];
			k++;
			i++;
		}
			
		while (j < right.length) {
			result[k] = right[j];
			k++;
			j++;
		}
			
		return result;
	}
}