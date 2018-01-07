package hw;

/**
 * String Remove Spaces
 * 
 * Given a string, remove all leading/trailing/duplicated empty spaces.
 * 
 * Assumptions:
 * 
 * The given string is not null. Examples:
 * 
 * " a" --> "a"
 * "   I love MTV " --> "I love MTV"
 * 
 * @author
 *
 */
public class RemoveSpaces {
	// Assumption: input is not null.
	public String removeSpaces(String input) {
		if (input.isEmpty()) { // input.length() == 0
			return input;
		}
		char[] arr = input.toCharArray();
		int end = 0; // [0, end) are good areas
		
		// when to ignore the current space char? 
		// 1) all space chars after a space char, only the first space char is kept
		// 2) all spaces at the beginning ( before the first non-space char )
		// 3) trailing space: according to above 1) and 2) , there may be one trailing space char, need to process later
		for (int i = 0; i < arr.length; i++) {
			if ( arr[i] == ' ' && (i == 0 || arr[i - 1] == ' ')) { // can't use " "
				continue;
			}
			arr[end++] = arr[i];
		}
		// post process, according to case 3 above
		if ( end > 0 && arr[end - 1] == ' ') {
			end--;
		}
		return new String(arr, 0, end);
	}
}
