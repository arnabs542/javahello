package hw;
/**
 * Recursion
 * 
String Abbreviation Matching

Word ¡°book¡± can be abbreviated to 4, b3, b2k, etc. Given a string and an abbreviation, 
return if the string matches the abbreviation.

Assumptions:

The original string only contains alphabetic characters.
Both input and pattern are not null.
Examples:

pattern ¡°s11d¡± matches input ¡°sophisticated¡± since ¡°11¡± matches eleven chars ¡°ophisticate¡±.

 * 
 * @author 
 *
 */
public class StringAbbreviationMatching {
	// Method 1: Recursive way.
	public boolean match(String input, String pattern) {
		// Assumptions: input, pattern are not null.
		return match(input, pattern, 0, 0);
	}
	
	private boolean match(String s, String t, int si, int ti) {
		// only when we run out of s and t at the same time, it is a match
		if (si == s.length() && ti == t.length()) {
			return true;
		}
		// if only one of the index is done, means a mis-match
		if (si >= s.length() || ti >= t.length()) {
			return false;
		}
		// for both index are in the middle -->
		// case 1. if tchar is a letter (not a digit), schar must be a same letter, otherwise false
		if (t.charAt(ti) < '0' || t.charAt(ti) > '9') {
			if (s.charAt(si) == t.charAt(ti)) {
				return match(s, t, si + 1, ti + 1);
			}
			return false;
		}
		// case 2. tchar is a digit, need to find the total number, then move si accordingly.
		int count = 0;
		while (ti < t.length() && t.charAt(ti) >= '0' && t.charAt(ti) <= '9') {
			count = count * 10 + (t.charAt(ti) - '0');
			ti++;
		}
		return match(s, t, si + count, ti);
	}
	
	// Method 2: Iterative way.
	// Notice that the above recursive solution is a TAIL recursion,
	// it is very easy to convert it to an iterative one.
	public boolean match2(String input, String pattern) {
		// Assumptions: input, pattern are not null.
		int si = 0, ti = 0;
		while (si < input.length() && ti < pattern.length()) {
			// for non-digit pattern char
			if (pattern.charAt(ti) < '0' || pattern.charAt(ti) > '9') {
				if (input.charAt(si) == pattern.charAt(ti)) {
					ti++;
					si++;
				} else {
					return false;
				}
			} else {
				// for digit pattern char
				int count = 0;
				while (ti < pattern.length() && pattern.charAt(ti) >= '0' && pattern.charAt(ti) <= '9') {
					count = count * 10 + (pattern.charAt(ti) - '0');
					ti++;
				}
				si += count;
			}
		}
		return si == input.length() && ti == pattern.length();
	}
}
