package hw;

/**
 * Recursion a to the power of b Evaluate a to the power of b, assuming both a
 * and b are integers and b is non-negative.
 * 
 * Examples
 * 
 * power(2, 0) = 1 power(2, 3) = 8 power(0, 10) = 0 power(-2, 5) = -32 Corner
 * Cases
 * 
 * What if the result is overflowed? We can assume the result will not be
 * overflowed when we solve this problem on this online judge.
 * 
 * @author
 *
 */
public class APowerB {
	
	public long power(int a, int b) {
		// did not consider a is negative condition
		if (b == 0)
			return (long) 1; // important
		if (a == 0)
			return (long) 0; // to optimize
		if (a < 0) { // important
			int sign = 1;
			if (b % 2 == 1)
				sign = -1;
			return this.power_long((long) -a, b) * sign;
		}
		return this.power_long((long)a, b);
	}

	private long power_long(long a, int b) {
		// if (b == 0)
		// return 1; // move to main to save time
		if (b == 1)
			return a;
		long res = a * a;
		if (b % 2 == 1) {
			return a * this.power_long(res, b / 2);
		}else
			return this.power_long(res, b / 2);
		
	}
	
//////////////////////////////////////////////////////////////////////	
	
	public long power_wrong(int a, int b) {
		// did not consider a is negative condition
		if (b == 0)
			return (long) 1; // important
		if (a == 0)
			return (long) 0; // to optimize
		if (a < 0) { // important
			int sign = 1;
			if (b % 2 == 1)
				sign = -1;
			return this.power_long((long) -a, b) * sign;
		}
		return this.power_long((long)a, b);
	}

	private long power_long_wrong(long a, int b) {
		// if (b == 0)
		// return 1; // move to main to save time
		if (b == 1)
			return a;
		long res = a * a;
		if (b % 2 == 1) {
			res *= a;
		}
		return this.power_long(res, b / 2);
	}
}
