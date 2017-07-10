import java.util.*;
public abstract class Function //Class and method purposes are written on the bottom of the programs: Function, SinFunc, CosFunc, and PolyFunc.
{
	public static void main(String[] args)
	{
		SinFunc a = new SinFunc();
		CosFunc b = new CosFunc();
		System.out.println("The root of sin(x) between 3 and 4 is " + a.findRoot(3, 4, .00000001));
		System.out.println("The root of cos(x) between 1 and 3 is " + b.findRoot(1, 3, .00000001));
	
		int[] coefficientsOne = {-3, 0, 1};
		PolyFunc c = new PolyFunc(coefficientsOne);
		int[] coefficientsTwo = {-2, -1, 1};
		PolyFunc d = new PolyFunc(coefficientsTwo);
		System.out.println("The root of x^2-3 is " + c.findRoot(0, 5, .00000001));
		System.out.println("The root of x^2-x-2 is " + d.findRoot(0, 5, .00000001));
		System.out.println();
		
		//TESTS//
		if (a.findRoot(3, 4, .00000001) == 3.1415926590561867) {
			System.out.println("Test passed! a.findRoot(3, 4, .00000001) returns pi with same digits as FunctionTest.");
		}
		else {
			System.out.println("TEST FAILED! a.findRoot(3, 4, .00000001 returns " + a.findRoot(3, 4, .00000001) + " which is different from FunctionTest."); 
		}
		if (b.findRoot(1, 3, .00000001) == 1.5707963332533836) {
			System.out.println("Test passed! b.findRoot(1, 3, .00000001) returns pi/2.");
		}
		else {
			System.out.println("TEST FAILED! b.findRoot(1, 3, .00000001) returns " + b.findRoot(1, 3, .00000001));
		}
		if (c.toString().equals("x^2-3")) {
			System.out.println("Test passed! c.toString() returns x^2-3");
		}
		else {
			System.out.println("TEST FAILED! c.toString() returns " + c.toString());
		}
		if (d.toString().equals("x^2-x-2")) {
			System.out.println("Test passed! d.toString() returns x^2-x-2");
		}
		else {
			System.out.println("TEST FAILED! d.toString() returns " + d.toString());
		}
	}
	
	public abstract double evaluate(double x);
	
	public double findRoot(double a, double b, double epsilon)
	{
		double x = (a+b)/2d;
		if (Math.abs(a-x) < epsilon)
		{
			return x; 
		}
		else if ((evaluate(x) > 0 && evaluate(a) > 0) || (evaluate(a) < 0 && evaluate(x) < 0))
		{
			return findRoot(x, b, epsilon);
		}
		else
		{
			return findRoot(a, x, epsilon);
		}
	}
}
/* The Function class is abstract because of the abstract method evaluate(). Therefore the Function class allows manipulation of this method by other classes
that extend it. The function class also contains the findRoot() method that provides the calculations and makes use
of the evaluate method, whose instructions are written in another class. The main method is also located here to tie the program together. */