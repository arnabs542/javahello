package hw;

/**
 * Data Structure All Unique Characters II 
 * 
 * Determine if the characters of a
 * given string are all unique.
 * 
 * Assumptions
 * 
 * We are using ASCII charset, the value of valid characters are from 0 to 255
 * The given string is not null Examples
 * 
 * all the characters in "abA+\8" are unique "abA+\a88" contains duplicate
 * characters
 * 
 * @author
 *
 */
public class AllUniqueCharactersII {
	public boolean allUnique(String word) {
		// each int value can represent 32 bit, so we need 8 ints
		// to represent 256 bits.
		int[] vec = new int[8];
		char[] array = word.toCharArray();
		for (char c : array) {
			// for a value c in the range of 0-255,
			// it is actually mapped to the int value at c/32 as index,
			// and the c%32'th bit of the int value.
			int idx = c / 32;
			int bit = c % 32;
			if (((vec[idx] >>> bit) & 1) != 0) {
				// this bit occupied --> repeated char
				return false;
			}
			//!!! need to set the bit for current char !!! don't forget
			vec[idx] |= 1 << bit;
		}
		return true;
	}
	
	public static void main(String[] args) {
		AllUniqueCharactersII sol = new AllUniqueCharactersII();
		String input = "aa";
		boolean res = sol.allUnique(input);
		System.out.println(res);
	
	}
}
