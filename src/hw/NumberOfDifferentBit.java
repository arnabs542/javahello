package hw;

/**
 * None Number Of Different Bits Determine the number of bits that are different
 * for two given integers.
 * 
 * Examples
 * 
 * 5(??0101??) and 8(??1000??) has 3 different bits
 * 
 *  2147483647,-2147483648 --> 32
 * 
 * 8, -8 --> 
 * 
 * @author
 *
 */
public class NumberOfDifferentBit {
	public int diffBits(int a, int b) {
		// after exclusive or, only the bits where a and b are diff will be 1
		a ^= b;
		int count = 0;
		// in Java, notice that we are using logical right shift >>>.
		// and a != 0 as the terminate condition.
		while (a != 0) { // a > 0 is wrong, should consider neg vals
			count += a & 1;
			a >>>= 1;
		}
		return count;
	}
	
	public static void main(String[] args) {
		NumberOfDifferentBit sol = new NumberOfDifferentBit();
		int a = 8, b = -8;
		int res = sol.diffBits(a, b);
		System.out.println(res);
	}
}
