package hw;

import java.util.HashSet;
import java.util.Set;

/**
 * String Longest Substring Without Repeating Characters Given a string, find
 * the longest substring without any repeating characters and return the length
 * of it. The input string is guaranteed to be not null.
 * 
 * For example, the longest substring without repeating letters for "bcdfbd" is
 * "bcdf", we should return 4 in this case.
 * 
 * @author
 *
 */
public class LongestSubstringWithoutRepeatingCharacters {

	// ref, same idea, but code is much cleaner
	public int longest(String input) {
		// Assumptions: the input string is not null.
		// the distinct set contains all distinct characters
		// in the sliding window of (slow, fast).
		Set<Character> distinct = new HashSet<>();
		int slow = 0;
		int fast = 0;
		int longest = 0;
		while (fast < input.length()) {
			if (distinct.contains(input.charAt(fast))) {
				// if there is duplicated character, we need to move the slow pointer.
				distinct.remove(input.charAt(slow++));
			} else {
				// if there is no duplicate character, we can slide 
				// fast pointer and we have a new sliding window of 
				// (slow, fast) containing all distinct characters.
				distinct.add(input.charAt(fast++));
				longest = Math.max(longest, fast - slow);
			}
		}
		return longest;
	}	
		
	
	public int longest_self_accept(String input) {
		if (input == null || input.length() == 0) {
			return 0;
		}
		int result = 1;
		HashSet<Character> charSet = new HashSet<>();
		char[] chars = input.toCharArray();
		int left = 0; // [left, right) is the unique-char area
		int right = 0; // current char to explore
		while (right < chars.length) {
			if (charSet.add(chars[right])) {
				result = Math.max(result, right - left + 1);
			} else {
				while (chars[left] != chars[right]) {
					charSet.remove(chars[left]);
					left += 1;
				}
				//charSet.remove(chars[left]);
				left += 1;
			}
			right += 1;
		}
		return result;
	}
}
