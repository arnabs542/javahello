package hw;

/**
 * 
 * String Hexadecimal Representation
 * 
 * Generate the hexadecimal representation for a given non-negative integer
 * number as a string. The hex representation should start with "0x".
 * 
 * There should not be extra zeros on the left side.
 * 
 * Examples
 * 
 * 0's hex representation is "0x0" 255's hex representation is "0xFF"
 * 
 * @author
 *
 */

public class HexadecimalRepresentation {
	public String hex(int number) {
		String prefix = "0x";
		// handle the special case of 0 first.
		// because 0 should not need to be 0x00, just 0x0 is good.
		if (number == 0) {
			return prefix + "0";
		}
		// using StringBuilder so append operation is more efficient.
		StringBuilder sb = new StringBuilder();
		while (number > 0) {
			int rem = number % 16;
			if (rem < 10 ) {
				// for 0 - 9, here is why need to special handle corner case 0
				sb.append((char)('0' + rem));
			} else {
				// for A - F
				sb.append((char) (rem - 10 + 'A'));
			}
			number >>>= 4;
		}
		// reverse it at last so it all complexity is O(n).
		return prefix + sb.reverse().toString();
	}
}
