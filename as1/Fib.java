public class Fib
{
	public static void main(String[] args)
	{
		int nthTerm = Integer.parseInt(args[0]); 
		int[] fibSequence = new int [nthTerm+1]; 
		fibSequence[1] = 1;
		fibSequence[2] = 1; 
		for (int i = 3; i<fibSequence.length; i++)
		{
			fibSequence[i] = fibSequence[i-2] + fibSequence[i-1];
		}
		System.out.print(fibSequence[nthTerm]);
	}
}