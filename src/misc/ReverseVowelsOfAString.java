package misc;

import java.util.HashSet;
import java.util.Set;

public class ReverseVowelsOfAString {
	public String reverseVowels(String s) {
		if (s == null || s.length() == 0) {
			return s;
		}
		//String vowels = "aeiouAEIOU";
		Set<Character> vowels = new HashSet<>();
		String vs = "aeiouAEIOU";
		for (char c : vs.toCharArray()) {
			vowels.add(c);
		}
		char[] chars = s.toCharArray();
		int start = 0;
		int end = s.length() - 1;
		while (start < end) {
			if (! vowels.contains(chars[start])) {  // + "" if using old string vowels
				start++;
				continue;
			}
			if (! vowels.contains(chars[end])) { // + ""
				end--;
				continue;
			}
			swap(chars, start, end);
			start++;
			end--;
		}
		return new String(chars);
		
	}
	
	private void swap(char[] chars, int i, int j) {
		char tmp = chars[j];
		chars[j] = chars[i];
		chars[i] = tmp;
	}
}
