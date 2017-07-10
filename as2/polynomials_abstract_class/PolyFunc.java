public class PolyFunc extends Function
{
	public int[] coefficients; 
	public String polynomial;
	
	public PolyFunc(int[] coefficients)
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
	
	public PolyFunc add(PolyFunc a)
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
		PolyFunc z = new PolyFunc(newPolyCoefficients);
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
}
/*PolyFunc is an object that contains an int array of coefficients and produces their respective polynomial with the toString() method. The add() method is 
used to add two separate polynomials. This method makes use of the degree() method to find out which coefficient array is larger. Lastly, the evaluate() 
method is used in the Function class to return a double that has been evaluated from a given polynomial. */