package hw;

/**
 * String
 * 
 * Remove Adjacent Repeated Characters I
 * 
 * Remove adjacent, repeated characters in a given string, leaving only one
 * character for each group of such characters.
 * 
 * Assumptions
 * 
 * Try to do it in place. 
 * 
 * Examples
 * 
 * "aaabbbc" is transferred to "bc" 
 * 
 * Corner Cases
 * 
 * If the given string is null, we do not need to do anything.
 * 
 * @author
 *
 */
public class RemoveAdjacentRepeatedCharactersI {
	public String deDup(String input) {
		// try to convert the input to char[],
		// and do it in place.
		if (input == null || input.length() == 0) {
			return input;
		}
		char[] arr = input.toCharArray();
		int end = 0; // [0, end) are good area, end is current position to put new valid char
		for (int i = 0; i < arr.length; i++) {
			// repeated char will be ignored except the first char in the sequence
			// which is i > 0 && arr[i] == arr[end - 1]
			if ( i == 0 || arr[i] != arr[end - 1]) {
				arr[end++] = arr[i];
			}
		}
		return new String(arr, 0, end); // !! need to have start and end idx, not the whole arr is good.
	}
}
