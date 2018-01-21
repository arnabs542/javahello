package hw;

/**
 * 
 * String
 * 
 * Decompress String II
 * 
 * Given a string in compressed form, decompress it to the original string. The
 * adjacent repeated characters in the original string are compressed to have
 * the character followed by the number of repeated occurrences.
 * 
 * Assumptions
 * 
 * 1. The string is not null
 * 
 * 2. The characters used in the original string are guaranteed to be ‘a’ - ‘z’
 * 
 * 3. There are no adjacent repeated characters with length > 9
 * 
 * Examples
 * 
 * "a1c0b2c4" → "abbcccc"
 * 
 * "a1c0c2" -> "acc"
 * 
 * @author
 *
 */
public class DecompressStringII {
	
	// Method 1: "in place".
	// when we say in place, it usually means the input is a long enough
	// char array, and the original string only occupies part of the array
	// starting from index 0, and usually the length to represent the 
	// original string is given.
	public String decompress_inplace(String input) {
		if (input.isEmpty()) {
			return input;
		}
		// We need to handle the 
		// "a0", "a1", "a2" cases (the decoded string is no longer than original input) and
		// "a3", "a4"... cases (the decoded string i longer)
		// in two passes to valid conflict, since the encoding of the two cases
		// require different directions.
		char[] array = input.toCharArray();
		return decodeLong(array, decodeShort(array));
	}
	
	// return the length of the decoded string,
	// only cares about 'a0/1/2' cases, A.K.A
	// the decoded string is shorter or equal-length
	private int decodeShort(char[] input) {
		// since the decoded string is shorter, we should
		// do the decoding work from left to right direction.
		int end = 0;
		for (int i = 0; i < input.length; i += 2) {
			int digit = getDigit(input[i + 1]);
			if (digit >= 0 && digit <= 2) {
				for (int j = 0; j < digit; j++) {
					input[end++] = input[i];
				}
			} else {
				// we don't handle the longer decoded string here.
				input[end++] = input[i];
				input[end++] = input[i + 1];
			}
		}
		return end;
	}
	
	// take care of "a3/4/5..." cases
	// the decoded string is longer.
	// length: the length of the valid part starting from index 0. 
	// --> in input, [0, length) is the areas that we need to work on, [length:] can be ignored.
	private String decodeLong(char[] input, int length) {
		// we need to calculate the new required length.
		int newLength = length;
		// --> ??? --> here do not need to check if it is a letter or a number
		// for letter, it will return a value outside of (2, 9], e.g. 'a'-'0' = 49
		// then the length will not be added.
		// same for the following when copying from end to 0.
		for (int i = 0; i < length; i++) {
			int digit = getDigit(input[i]);
			if (digit > 2 && digit <= 9) {
				newLength += digit - 2;
			}
		}
		// Notice: if it is required to do this in place, usually the input
		// array is a sufficient large one, you will not need to 
		// allocate a new array. This solution is only for demonstration.
		char[] result = new char[newLength];
		int end = newLength - 1;
		for (int i = length - 1; i >= 0; i--) {
			int digit = getDigit(input[i]);
			// is a valid digit place ( not letter)
			if (digit > 2 && digit <= 9) {
				// decrease i to get the letter before the digit.
				i--; 
				for (int j = 0; j < digit; j++) {
					result[end--] = input[i];
				}
			} else {
				// we already take care of the shorter cases, 'a1' in previous pass.
				// we can leave as it is . e.g. the input could be "abc2";
				result[end--] = input[i];
			}
		}
		return new String(result);
	}
	
	private int getDigit(char digit) {
		return digit - '0';
	}
	
	// Method 2: using StringBuilder to help
	public String decompress(String input) {
		// Assumptions: input is not null.
		char[] array = input.toCharArray();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < array.length; i++) {
			char ch = array[i++]; // ++ for the number following the char
			int count = array[i] - '0';
			for (int c = 0; c < count; c++) {
				sb.append(ch);
			}
		}
		return sb.toString();
	}

	public String decompress_self(String input) {
		if (input == null || input.length() <= 1) {
			return input;
		}
		StringBuilder sb = new StringBuilder();
		char[] chars = input.toCharArray();
		for (int i = 0; i < chars.length; i++) {
			char tmp = chars[i++];
			int cnt = chars[i] - '0';
			for (int j = 0; j < cnt; j++) {
				sb.append(tmp);
			}
		}
		return sb.toString();
	}
}
