import java.util.*;
public class Factorial
{
	public static void main(String[] args)
	{
		if(calculate(0) == 1)
		{
			System.out.println("Factorial.calculate(0) returned 1. Test passed!");
		}
		else
		{
			System.out.println("Factorial.calculate(0) did not return 1. TEST FAILED.");
		}
		if(calculate(5) == 120)
		{
			System.out.println("Factorial.calculate(5) returned 120. Test passed!");
		}
		else
		{
			System.out.println("Factorial.calculate(5) did not return 120. TEST FAILED.");
		}
	}
	public static long calculate(long n)
	{
		if (n < 0 || n > 20)
		{
			System.out.println("ERROR: Enter a number between 0 and 20");
			System.exit(0); 
		}
		if(n == 0)
		{
			return 1;
		}
		else
		{
			return (n * calculate(n-1));
		}
	}
}
// 0: 1, 1: 1, 2: 2, 3: 6, 4: 24, 5: 120