import java.util.*;
public class FibTest
{
	public static void main(String[] args)
	{
		if (fibIter(6) == 8)
		{
			System.out.println("fibIter(6) returns 8. Test passed!");
		}
		else
		{
			System.out.println("fibIter(6) did not return 8. TEST FAILED!");
		}
		if (fibIter(20) == 6765)
		{
			System.out.println("fibIter(20) returns 6765. Test passed!");
		}
		else
		{
			System.out.println("fibIter(5) did not return 6765. TEST FAILED!");
		}
		
		if (fibRecur(6) == 8)
		{
			System.out.println("fibRecur(6) returns 8. Test passed!");
		}
		else
		{
			System.out.println("fibRecur(6) did not return 8. TEST FAILED!");
		}
		if (fibRecur(20) == 6765)
		{
			System.out.println("fibRecur(20) returns 6765. Test passed!");
		}
		else
		{
			System.out.println("fibRecur(5) did not return 6765. TEST FAILED!");
		}
		System.out.println();
		
		long before = System.currentTimeMillis();
		fibRecur(40);
		long after = System.currentTimeMillis();
		System.out.println("FibRecur(40) took this long to execute: " + (after - before) + " milliseconds.");
		
		before = System.currentTimeMillis();
		fibIter(40);
		after = System.currentTimeMillis();
		System.out.println("FibIter(40) took this long to execute: " + (after - before) + " milliseconds."); 
	}
	
	public static int fibIter(int n)
	{ 
		int[] fibSequence = new int [n+1]; 
		fibSequence[1] = 1;
		fibSequence[2] = 1; 
		for (int i = 2; i < fibSequence.length; i++)
		{
			fibSequence[i] = fibSequence[i - 2] + fibSequence[i - 1];
		}
		return fibSequence[n];
	}
	
	public static int fibRecur(int n)
	{
		if(n == 1 || n == 2)
		{
			return 1;
		}
		else
		{
			return (fibRecur(n-1) + fibRecur(n - 2));
		}
	}
}
// 1 1 2 3 5 8 13 21