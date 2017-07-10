import java.util.*;
public class Ramanujan

// USAGE: java Ramunujan <int>
{
	public static void main(String[] args)
	{
		int k = Integer.parseInt(args[0]);
	    double interval = ((2 * Math.sqrt(2))) / 9801;
		double sum = 0;
		double numerator = 0; 
		double denominator =  0; 
		
		for (int i = 0; i < k; i++)
		{
			numerator = (((Factorial.calculate(i * 4)) * (1103 + (26390 * i))));
			denominator = (((Math.pow(Factorial.calculate(i), 4)) * (Math.pow(396, 4 * i))));
			sum+= (numerator / denominator);
		}
		
		double result = 1 / (sum * interval); 
		System.out.println("Pi according to Ramanujan with " + k + " terms: " + result); 
		double error = Math.abs(((Math.PI - result) / Math.PI) * 100);
		System.out.printf("This differs from Java's value by %.20f percent.", error);
	}
}