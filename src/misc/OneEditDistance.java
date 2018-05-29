package misc;

/**
 * 
 * One Edit Distance Description Determine if two given Strings are one edit
 * distance.
 * 
 * One edit distance means you can only insert one character/delete one
 * character/replace one character to another character in one of the two given
 * Strings and they will become identical.
 * 
 * Assumptions:
 * 
 * The two given Strings are not null Examples:
 * 
 * s = "abc", t = "ab" are one edit distance since you can remove the trailing
 * 'c' from s so that s and t are identical
 * 
 * s = "abc", t = "bcd" are not one edit distance
 * 
 * Medium String
 * 
 * 
 * 
 * @author
 *
 */

public class OneEditDistance {
	public boolean oneEditDistance(String s, String t) {
		// basic idea:
		// 1. make s the shorter one
		// 2. use two pointers to trace through chars in s and t
		// 2.1. if same char, continue
		// 2.2. if different char,
		// if s and t same length, keep count as 1, and continue
		// else, skip one char in t (cause s is shorter) and keep count, continue
		// next time when diff, if count already one, return false,
		// at the end of loop, check if count is 1 or not
		// !!!! input "", "g" --> true
		// !!!! input "typea", "typeak" --> true

		if (s == null && t == null) {
			return true;
		}
		int slen = s.length(), tlen = t.length();
		if (Math.abs(slen - tlen) >= 2) {
			return false;
		}
		if (slen == 0 && tlen == 1 || tlen == 0 && slen == 1) {
			return true;
		}
		if (slen > tlen) {
			String tmp = t;
			t = s;
			s = tmp;
		}
		int i = 0, j = 0, count = 0;
		while (i < s.length() && j < t.length()) {
			char a = s.charAt(i);
			char b = t.charAt(j);
			// when same characters
			if (a == b) {
				i += 1;
				j += 1;
				continue;
			}
			// when different characters
			// case1, already different before
			if (count == 1) {
				return false;
			}
			// keep record of current count
			count += 1;
			// case 2, same length -- replace
			if (s.length() == t.length()) {
				i += 1;
				j += 1;
				continue;
			} else {
				// case 3, t longer -- delete from t
				j += 1;
				continue;
			}
		}
		// !!!! add j position check
		return (count == 1 || j == t.length() - 1); // if 0 still return false
	}
}
