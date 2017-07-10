import java.util.*;
public class Poly
{
	public int[] coefficients; 
	public String polynomial;
	
	public Poly(int[] coefficients)
	{
		this.coefficients = new int[coefficients.length];
		for(int i = 0; i < coefficients.length; i++)
		{
			this.coefficients[i] = coefficients[i];
		}
	}
	
	public int degree()
	{
		return coefficients.length-1;
	}
	
	@Override
	public String toString()
	{
		polynomial = ""; 
		if (coefficients[coefficients.length - 1] == 1)  //This if statement handles the assignment of the first term. 
		{
			polynomial += ("x^" + (coefficients.length - 1));
		}
		else if (coefficients[coefficients.length - 1] == -1)
		{
			polynomial += ("-x" + (coefficients.length - 1));
		}
		else
		{
			polynomial += (coefficients[coefficients.length - 1] + "x^" + (coefficients.length-1));
		}
		
		for (int i = coefficients.length-2; i >= 0; i--)  // Subsequent terms are added appropriately
		{
			if (coefficients[i] == 1)
			{
				polynomial += ( "+x^" + i);
			}
			else if (coefficients[i] == -1)
			{
				polynomial += ("-x^" + i);
			}
			else if(coefficients[i] > 0)
			{
		        polynomial += ("+" + coefficients[i] + "x^" + i);
			}
			else if(coefficients[i] < 0)
			{
				polynomial += (coefficients[i] + "x^" + i); 
			}
			else
			{
				continue;
			}
		
			if (i == 0) // Ensures that powers of 0 and 1 are displayed properly in the polynomial
			{
				polynomial = polynomial.substring(0, polynomial.length() - 3);
			}
			else if (i == 1)
			{
				polynomial = polynomial.substring(0, polynomial.length() - 2);
			}
		}
		return polynomial;
	}
	
	public Poly add(Poly a)
	{
		int [] newPolyCoefficients;
		if (this.degree() > a.degree()) //this if statement decides which coefficient array is larger by looking at the largest power. 
		{
			newPolyCoefficients = new int[this.degree()+1];
			newPolyCoefficients = this.coefficients.clone(); //this .clone() method "clones" an array with the exact elements. 
			for (int i = 0; i < a.coefficients.length; i++)
			{
				newPolyCoefficients[i] += a.coefficients[i]; //the polynomials are added to form the new int[]
			}
		}
		else
		{
			newPolyCoefficients = new int[a.degree()+1];
			newPolyCoefficients = a.coefficients.clone(); 
			for (int i = 0; i < this.coefficients.length; i++)
			{
				newPolyCoefficients[i] += this.coefficients[i];
			}
		}
		Poly z = new Poly(newPolyCoefficients);
		return z;
	}
	
	public double evaluate(double x)
	{
		double evaluation = 0; 
		for(int i = 0; i < coefficients.length; i++)
		{
			evaluation += (Math.pow(x, i) * coefficients[i]);
		}
		return evaluation;
	}
	
	public static void main(String[] args)
	{
		int[] coefficientArray = {4, 0, -8, 0, 3, 2};
		Poly pol = new Poly(coefficientArray);
		
		int[] coefficientArray2 = {0, -2, 4, 1};
		Poly pol2 = new Poly(coefficientArray2);
		
		Poly z = pol.add(pol2);
		
		// TESTS //
		for(int i = 0; i < coefficientArray.length; i++) 
		{
			if (coefficientArray[i] == pol.coefficients[i])
			{
				if (i == coefficientArray.length -1)
				{
					System.out.println("Test passed! Constructor for Poly works properly for first polynomial.");
					break;
				}
				continue;
			}
			else 
			{
				System.out.println("TEST FAILED! Constructor for Poly does not work properly for first polynomial.");
				break;
			}
		}
		for(int i = 0; i < coefficientArray2.length; i++)
		{
			if (coefficientArray2[i] == pol2.coefficients[i])
			{
				if (i == coefficientArray2.length -1)
				{
					System.out.println("Test passed! Constructor for Poly works properly for second polynomial.");
					break;
				}
				continue;
			}
			else 
			{
				System.out.println("TEST FAILED! Constructor for Poly does not work properly for second polynomial.");
				break;
			}
		}
		if (pol.toString().equals("2x^5+3x^4-8x^2+4")) {
			System.out.println("Test passed! pol.toString() returns 2x^5+3x^4-8x^2+4.");
		}
		else {
			System.out.println("TEST FAILED! pol.toString() returns " + pol.toString());
		}
		if (pol2.toString().equals("x^3+4x^2-2x")) {
			System.out.println("Test passed! pol2.toString() returns x^3+4x^2-2x.");
		}
		else {
			System.out.println("TEST FAILED! pol2.toString() returns " + pol.toString());
		}
		if (pol.degree() == 5) {
			System.out.println("Test passed! pol.degree() returns 5.");
		}
		else {
			System.out.println("TEST FAILED! pol.degree() returns " + pol.degree());
		}
		if (pol2.degree() == 3) {
			System.out.println("Test passed! pol.degree() returns 3.");
		}
		else {
			System.out.println("TEST FAILED! pol.degree() returns " + pol.degree());
		}
		if (pol.evaluate(2.0) == 84) {
			System.out.println("Test passed! pol.evaluate(2.0) returns 84.");
		}
		else {
			System.out.println("TEST FAILED! pol.evaluate(2.0) returns " + pol.evaluate(2.0));
		}
		if (pol2.evaluate(2.0) == 20) {
			System.out.println("Test passed! pol2.evaluate(2.0) returns 20.");
		}
		else {
			System.out.println("TEST FAILED! pol2.evaluate(2.0) returns " + pol.evaluate(2.0));
		}
		if (z.toString().equals("2x^5+3x^4+x^3-4x^2-2x+4")) {
			System.out.print("Test passed! pol.add(pol2) adds polynomials.");
		}
		else {
			System.out.println("TEST FAILED. pol.add(pol2) returns the new polynomial " + z.toString());
		}	
	}
}