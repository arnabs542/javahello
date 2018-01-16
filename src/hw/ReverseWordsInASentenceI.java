package hw;

/**
 * String Reverse Words In A Sentence I Reverse the words in a sentence.
 * 
 * Assumptions
 * 
 * Words are separated by single space
 * 
 * There are no heading or tailing white spaces
 * 
 * Examples
 * 
 * "I love Google" -->  "Google love I"
 * 
 * Corner Cases
 * 
 * If the given string is null, we do not need to do anything.
 * 
 * @author
 *
 */
public class ReverseWordsInASentenceI {
	public String reverseWords(String input) {
		if (input == null || input.length() <= 1) {
			return input;
		}
		char[] chars = input.toCharArray();
		reverse(chars, 0, chars.length - 1);
		reverseWord(chars);
		return new String(chars);
	}
	
	private void reverse(char[] chars, int left, int right) {
		for (int i = left, j = right; i < j; i++, j--) {
			char tmp = chars[i];
			chars[i] = chars[j];
			chars[j] = tmp;
		}
	}
	
	private void reverseWord(char[] chars) {
		int start = -1, end = -1;
		for (int i = 0; i < chars.length; i++) {
			if (chars[i] != ' ' && (i == 0 || chars[i - 1] == ' ')) {
				start = i;
				continue;
			}
			if (chars[i] != ' ' && (i == chars.length - 1 || chars[i + 1] == ' ')) {
				end = i;
				reverse(chars, start, end);
			}
			
		}
		
	}
}
