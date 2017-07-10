import java.util.*;
import java.math.*;
// Extra Credit
// USAGE: java RamanujanBig <int> <int>

public class RamanujanBig
{
	public static void main(String[] args)
	{
		int k = Integer.parseInt(args[0]);  // where k is number of terms to be calculated in Ramanujan. k = 13 will yield 100 correct digits. 
		int j = Integer.parseInt(args[1]);  // where j is number of digits of pi to be displayed. 
	    BigDecimal interval = ((BigDecimal.valueOf(2).multiply(evaluate(j))).divide(BigDecimal.valueOf(9801), 1000, BigDecimal.ROUND_UP));
		BigDecimal result = new BigDecimal("0");
		BigInteger threeNinetySix = BigInteger.valueOf(396);
		
		for (int i = 0; i < k; i++)
		{
			BigInteger numerator = FactorialBig.calculate(BigInteger.valueOf(i * 4));
			numerator = numerator.multiply(BigInteger.valueOf(1103).add(BigInteger.valueOf(26390).multiply(BigInteger.valueOf(i))));
			BigInteger denominator = FactorialBig.calculate(BigInteger.valueOf(i)).pow(4).multiply(threeNinetySix.pow(4 * i));
			BigDecimal num = new BigDecimal(numerator);
			BigDecimal den = new BigDecimal(denominator);
			result = result.add(num.divide(den, j, BigDecimal.ROUND_UP));		
		}
		System.out.println(BigDecimal.valueOf(1).divide(result.multiply(interval), j, BigDecimal.ROUND_UP));
	}
	
	public static BigDecimal evaluate(int j)
	{
		BigDecimal result = new BigDecimal("1");
		BigDecimal increment = new BigDecimal("1");
		BigDecimal two = new BigDecimal("2");
		for (int i = 0; i < j; i++)
		{
			result = (result.add(two.divide(result, j, BigDecimal.ROUND_UP)).divide(two, j, BigDecimal.ROUND_UP));
			// the divide method is necessary to set the scale to 1000 digits, otherwise the compiler will complain about an infinite result.
			// the third parameter states the behavior of the 1000th digit. In this case, it is rounded up. 
		}
	    return result; // a square root of two is returned accurate to at least 999 digits. 
	}
}
/*Java does not support operation overloading, thus Object BigInteger and BigDecimal must be calculated with objects of their type with various methods
defined in their classes. BigDecimal and BigInteger objects can hold much larger numbers than java's primitive int and double types by representing the 
numbers as an immutable String. Therefore calculating digits of pi with these larger numbers offers an advantage in precision of larger integers or decimals.
*/