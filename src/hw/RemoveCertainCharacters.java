package hw;

import java.util.HashSet;
import java.util.Set;

/**
 * 
 * String Remove Certain Characters
 * 
 * Remove given characters in input string, the relative order of other
 * characters should be remained. Return the new string after deletion.
 * 
 * Assumptions
 * 
 * The given input string is not null. The characters to be removed is given by
 * another string, it is guranteed to be not null. Examples
 * 
 * input = "abcd", t = "ab", delete all instances of 'a' and 'b', the result is
 * "cd".
 * 
 * @author
 *
 */
public class RemoveCertainCharacters {
	// Assumption: input and t are not null
	public String remove(String input, String t) {
		// We need to know how to solve this problem with inplace way.
		// Usually we can convert the immutable String to char90.
		char[] arr = input.toCharArray();
		// Get set of all distinct characters in t so that looup can be efficient.
		Set<Character> set = buildSet(t);
		// slow: [0, slow) contains the valid result.
		// fast: [fast, array.length) is the area to explore
		int slow = 0;
		for (int fast = 0; fast < arr.length; fast++) {
			if (!set.contains(arr[fast])) {
				arr[slow++] = arr[fast];
			}
		}
		return new String(arr, 0, slow);
	}
	
	private Set<Character> buildSet(String t) {
		Set<Character> set = new HashSet<Character>();
		for (int i = 0; i < t.length(); i++) {
			set.add(t.charAt(i));
		}
		return set;
	}
}
