package hw;

import java.util.HashSet;
import java.util.Set;

/**
 * DP
 * 
 * Dictionary Word I
 * 
 * Given a word and a dictionary, determine if it can be composed by
 * concatenating words from the given dictionary.
 * 
 * Assumptions
 * 
 * The given word is not null and is not empty The given dictionary is not null
 * and is not empty and all the words in the dictionary are not null or empty
 * Examples
 * 
 * Dictionary: {¡°bob¡±, ¡°cat¡±, ¡°rob¡±}
 * 
 * Word: ¡°robob¡± return false
 * 
 * Word: ¡°robcatbob¡± return true since it can be composed by "rob", "cat", "bob"
 * 
 * 
 * @author
 *
 */
public class DictionaryWordI {
	public boolean canBreak(String input, String[] dict) {
		// Assumptions:
		// input is not null or empty.
		// dict is not null and dict is not empty,
		// all the strings in dict are not null or empty.
		Set<String> dictSet = toSet(dict);

		// Notice: sometimes it will be handy to have such index matching,
		// canBreak[i] mens index (i-1) in input, also
		// can represent the substring(0,i).
		boolean[] canBreak = new boolean[input.length() + 1];
		canBreak[0] = true;
		for (int i = 1; i < canBreak.length; i++) {
			for (int j = 0; j < i; j++) {
				if (dictSet.contains(input.substring(j, i)) && canBreak[j]) {
					canBreak[i] = true;
					break;
				}
			}
		}
		return canBreak[canBreak.length - 1];
	}

	private Set<String> toSet(String[] dict) {
		Set<String> set = new HashSet<>();
		for (String s : dict) {
			set.add(s);
		}
		return set;
	}
}
