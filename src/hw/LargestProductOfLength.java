package hw;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;

/***
 * 
 * Given a dictionary containing many words, find the largest product of two
 * words¡¯ lengths, such that the two words do not share any common characters.
 * 
 * Assumptions
 * 
 * The words only contains characters of 'a' to 'z' 
 * The dictionary is not null
 * and does not contains null string, and has at least two strings 
 * 
 * If there is
 * no such pair of words, just return 0 Examples
 * 
 * dictionary = [¡°abcde¡±, ¡°abcd¡±, ¡°ade¡±, ¡°xy¡±], the largest product is 5 * 2 =
 * 10 (by choosing ¡°abcde¡± and ¡°xy¡±)
 * 
 * 
 * 
 * Hard String
 * 
 * @author
 *
 */
public class LargestProductOfLength {
	public int largestProduct(String[] dict) {
		// Assumptions: dict is not null and has length >= 2,
		// there is no null String in the dict.
		// The words in the dict only use characters a - z.
		// Get the bit mask for each of the word in the dict,
		// the bit mask is represented by the lowest 26 bits of an
		// integer.
		// each of the bit represents one of the characters in a - z.
		HashMap<String, Integer> bitMasks = getBitMasks(dict);
		// sort the dict by length of the words in descending order.
		Arrays.sort(dict, new Comparator<String>() {
			@Override
			public int compare(String s1, String s2) {
				if (s1.length() == s2.length()) {
					return 0;
				}
				return s1.length() < s2.length() ? 1 : -1;
			}
		});
		int largest = 0;
		// !!! because we are trying to find biggest product, searching from longest ones.
		// i -- the right boundary (starting from 1, because at least need to compare 2 different 
		// strings ) ; j -- the left one, which is < i, and starting from 0, any one that is shorter
		// or equal length compared to i
		for (int i = 1; i < dict.length; i++) {
			for (int j = 0; j < i; j++) {
				int prod = dict[i].length() * dict[j].length();
				if (prod < largest) {
					break;
				}
				int iMask = bitMasks.get(dict[i]);
				int jMask = bitMasks.get(dict[j]);
				// if two words do not share any common chars,
				// the bit masks "and" result should be 0.
				// since there is not any position that two bit masks 
				// all have 1 and 1 (1 & 1 = 1). (0,0); (0,1); (1,0) are all OK, & = 0
				if ((iMask & jMask) == 0) { // !!! bit opration better to have ()
					largest = prod;
					break; // this line added by self
				}
			}
		}
		return largest;
	}

	private HashMap<String, Integer> getBitMasks(String[] dict) {
		// the 26 characters a - z are mapped to 0 - 25th bit.
		// to determine which bit it is for a char x,
		// use (x - 'a') since their values are in a consecutive range.
		// if character x exists in the word, 
		// we set that bit at corresponding index to 1 -- by using 1 left 
		// shift index times and |= to current mask.
		HashMap<String, Integer> result = new HashMap<>();
		for (String str : dict) {
			int bitMask = 0;
			for (int i = 0; i < str.length(); i++) {
				bitMask |= 1 << (str.charAt(i) - 'a');
			}
			result.put(str, bitMask);
		}
		return result;
	}
}
