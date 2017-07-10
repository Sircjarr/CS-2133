import java.util.*;
public class Gregory
{
	public static void main(String[] args)
	{
		int nthTerm = Integer.parseInt(args[0]); 
		double[] gregorySeries = new double[2000];
		int denom = 1; 
		for (int i = 0; i < gregorySeries.length; i++)
		{
			gregorySeries[i] = 1d/denom; 
			denom+=2; 
		}
		double gregoryPi = 0;
		for (int i = 0; i < nthTerm; i++)
		{
			if (i%2==0)
			{
				gregoryPi+= gregorySeries[i]; 
			}
			else
			{
				gregoryPi-= gregorySeries[i]; 
			}
		}
		double adjustedPi = gregoryPi * 4d;
		System.out.println("Pi according to Gregory series: " + adjustedPi);
		double error = ((Math.PI - adjustedPi) / Math.PI) * 100; 
		System.out.println("This differs from Java's value by: " + error + " percent.");
	}
}