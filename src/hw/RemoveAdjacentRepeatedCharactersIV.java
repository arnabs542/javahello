package hw;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

/**
 * 
 * String Remove Adjacent Repeated Characters IV
 * 
 * Repeatedly remove all adjacent, repeated characters in a given string from
 * left to right.
 * 
 * No adjacent characters should be identified in the final string.
 * 
 * Examples
 * 
 * "abbbaaccz" --> "aaaccz" --> "ccz" --> "z" "aabccdc"--> "bccdc" --> "bdc"
 * 
 * @author
 *
 */
public class RemoveAdjacentRepeatedCharactersIV {
	public String deDup(String input) {
		if (input == null || input.length() <= 1) { // !!! not ==0, can be <= 1 here
			return input;
		}
		char[] arr = input.toCharArray();
		int end = 0; // where good end is
		for (int i = 1; i < arr.length; i++) {
			if (end == -1 || arr[end] != arr[i]) {
				arr[++end] = arr[i];  // ++end, not end++, note the meaning of end
			} else {
				end--;
				while (i + 1 < arr.length && arr[i] == arr[i + 1]) {
					i++;
				}
			}
		}
		return new String(arr, 0, end + 1);
	}

	// self, accepted
	public String deDup_self(String input) {
		if (input == null || input.length() <= 1) { // !!! not ==0, can be <= 1 here
			return input;
		}
		char[] arr = input.toCharArray();
		LinkedList<Character> stack = new LinkedList<Character>(); // !!! can't use Stack on left
		stack.offerLast(arr[0]);
		char pre = arr[0]; // can't do = '', this is not used actually
		int i = 1;
		while (i < arr.length) {
			if (stack.isEmpty() || stack.peekLast() != arr[i]) {
				stack.offerLast(arr[i]);
				pre = arr[i];
				i++;
			} else {
				char tmp = arr[i];
				stack.removeLast();
				while (i < arr.length && arr[i] == tmp) {
					i++;
				}
			}
		}
		return getString(stack);
	}

	private String getString(LinkedList<Character> stack) {
		char[] arr = new char[stack.size()];
		for (int i = arr.length - 1; i >= 0; i--) {
			arr[i] = stack.pollLast();
		}
		return new String(arr);
	}

	// reference
	public String deDup_ref(String input) {
		if (input == null || input.length() <= 1) {
			return input;
		}
		// try to convert the string to char[], and do it in-place.
		char[] arr = input.toCharArray();
		// instead of using a extra stack explicitly, we can actually
		// reuse the left side of the original char[] as the "stack"
		// end: is where the top of the stack is .
		int end = 0;
		for (int i = 1; i < arr.length; i++) {
			// if the stack is empty(where end == -1) or there is no duplicate chars,
			// we are able to push the char into the stack.
			if (end == -1 || arr[i] != arr[end]) {
				arr[++end] = arr[i];
			} else {
				// otherwise, we need to pop the top element by end--,
				// and ignore all the consecutive duplicate chars.
				end--;
				while (i + 1 < arr.length && arr[i] == arr[i + 1]) {
					i++;
				}
			}
		}
		return new String(arr, 0, end + 1);
	}
}
