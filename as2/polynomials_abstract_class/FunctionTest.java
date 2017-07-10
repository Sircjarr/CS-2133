import java.util.*;
public class FunctionTest
{
	public static void main(String[] args)
	{
		System.out.print(findRoot(3, 4, .00000001));
	}
	
	public static double findRoot(double a, double b, double epsilon)
	{
		double x = (a+b)/2d;
		if (Math.abs(a-x) < epsilon)
		{
			return x; 
		}
		else if ((Math.sin(x) > 0 && Math.sin(a) > 0) || (Math.sin(a) < 0 && Math.sin(x) < 0))
		{
			return findRoot(x, b, epsilon);
		}
		else
		{
			return findRoot(a, x, epsilon);
		}
	}
}