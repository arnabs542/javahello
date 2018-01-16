package hw;

/**
 * String Reverse String Reverse a given string.
 * 
 * Assumptions
 * 
 * The given string is not null.
 * 
 * @author
 *
 */
public class ReverseString {
	// Method 1: interative reverse.
	public String reverse(String input) {
		if (input == null || input.length() <= 1) {
			return input;
		}
		char[] chars = input.toCharArray();
		for (int i = 0, j = chars.length - 1; i < j; i++, j--) {
			swap(chars, i, j);
		}
		return new String(chars);
	}
	
	private void swap(char[] chars, int a, int b) {
		char tmp = chars[a];
		chars[a] = chars[b];
		chars[b] = tmp;
	} 
	
	// Method 2: recursive way
	public String reverse_recursive (String input) {
		if (input == null || input.length() <= 1) {
			return input;
		}
		char[] chars = input.toCharArray();
		reverseHelper(chars, 0, chars.length - 1);
		return new String(chars);
	}
	
	private void reverseHelper(char[] chars, int left, int right) {
		if (left >= right) {
			return;
		}
		swap(chars, left, right);
		reverseHelper(chars, left + 1, right - 1);
	}
}
