package hw;

/**
 * 
 * Longest Palindromic Substring
 * 
 * Description
 * 
 * Given a string S, find the longest palindromic substring in S.
 * 
 * Assumptions
 * 
 * There exists one unique longest palindromic substring. The input S is not
 * null.
 * 
 * Examples Input: "abbc" Output: "bb"
 * 
 * Input: "abcbcbd" Output: "bcbcb" Medium String Dynamic Programming
 * 
 * @author
 *
 */

public class LongestPalindromicSubstring {
	// p[i][i] = 1
	// p[l][r] = p[l+1][r-1] if s[l] == s[r], else 0.
	public String longestPalindrome(String s) {
		// Assumptions: s is not null
		if (s.length() == 0) {
			return new String();
		}
		int n = s.length();
		boolean[][] p = new boolean[n][n];
		// p[i][j]: if it's palindromic string that ends on s[i,j],
		// inclusive.
		String result = s.substring(0, 1);
		for (int right = 0; right < n; right++) {
			p[right][right] = true;
			for (int left = right - 1; left >= 0; left--) {
				if (s.charAt(left) == s.charAt(right)) {
					p[left][right] = ((left + 1 >= right - 1) || p[left + 1][right - 1]);
					if (p[left][right] && right - left + 1 > result.length()) {
						result = s.substring(left, right + 1);
					}
				}
			}
		}
		return result;
	}
}
