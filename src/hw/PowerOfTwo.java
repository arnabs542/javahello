package hw;
/**
 * Bit Representation and Operation
 * 
Power Of Two
Determine if a given integer is power of 2.

Examples

16 is power of 2 (2 ^ 4)
3 is not
0 is not
-1 is not

 * @author 
 *
 */
public class PowerOfTwo {
	public boolean isPowerOfTwo(int number) {
		if (number <= 0) {
			return false;
		}
		// ignore all the trailing 0's
		while ((number & 1) == 0) {
			number >>>= 1;
		}
		// check if the number is 1 at the end;
		return number == 1;
	}
	
	// method 2
	public boolean isPowerOfTwoII(int number) {
		if (number <= 0) {
			return false;
		}
		// count the number of 1's.
		int count = 0;
		while (number > 0) {
			count += number & 1;
			number >>>= 1;
		}
		return count == 1;
	}
	
	// method 3
	public boolean isPowerOfTwoIII(int number) {
		// use the trick of number & (number - 1) will remove the right most 1 of number.
		// note boolean op is higher priority than bit op. use () !!!
		return number > 0 && ((number & (number - 1)) == 0);
	}
	
}
