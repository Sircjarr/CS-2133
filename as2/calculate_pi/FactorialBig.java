import java.util.*;
import java.math.*;

// Extra credit with BigInteger
public class FactorialBig
{
	public static BigInteger calculate(BigInteger n)
	{
		BigInteger x = new BigInteger("0"); 
		BigInteger one = new BigInteger("1");
	
		if(n.compareTo(new BigInteger("0")) == 0)
		{
			return one;
		}
		else
		{
			return x.add((calculate(n.subtract(one)).multiply(n)));
		}
	}
}
// 0: 1, 1: 1, 2: 2, 3: 6, 4: 24, 5: 120