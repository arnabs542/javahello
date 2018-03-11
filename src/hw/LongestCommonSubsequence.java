package hw;

/**
 * Longest Common Subsequence
 * 
 * Find the length of longest common subsequence of two given strings.
 * 
 * Assumptions
 * 
 * The two given strings are not null Examples
 * 
 * S = ¡°abcde¡±, T = ¡°cbabdfe¡±, the longest common subsequence of s and t is
 * {¡®a¡¯, ¡®b¡¯, ¡®d¡¯, ¡®e¡¯}, the length is 4.
 * 
 * 
 * 
 * Medium 2 D Array String
 * 
 * @author
 *
 */
public class LongestCommonSubsequence {
	public int longest_ref(String s, String t) {
		// Assumptions: s, t are not null
		// using the trick of "add 1" index will make the base case
		// easier to handle, e.g. the 0th row and the 0th column will
		// be all 0s.

		int m = s.length();
		int n = t.length();
		// dp[i][j]: the lcs of substring s[0,i-1] and t[0,j-1]
		int[][] longest = new int[m + 1][n + 1];
		for (int i = 1; i < m + 1; i++) {
			for (int j = 1; j < n + 1; j++) {
				if (s.charAt(i - 1) == t.charAt(j - 1)) {
					longest[i][j] = longest[i - 1][j - 1] + 1;
				} else {
					longest[i][j] = Math.max(longest[i - 1][j], longest[i][j - 1]);
				}
			}
		}
		return longest[m][n];

	}
	
	public int longest(String s, String t) {
		// Assumptions: s, t are not null
		if (s.length() == 0 || t.length() == 0) {
			return 0;  // !!! 0, not new String()
		}
		int m = s.length();
		int n = t.length();
		// dp[i][j]: the lcs of substring s[0,i-1] and t[0,j-1]
		int[][] dp = new int[m + 1][n + 1];
		for (int i = 1; i < m + 1; i++) {
			for (int j = 1; j < n + 1; j++) {
				if (s.charAt(i - 1) == t.charAt(j - 1)) {
					dp[i][j] = dp[i - 1][j - 1] + 1;
				} else {
					dp[i][j] = Math.max(dp[i - 1][j - 1], dp[i - 1][j]);
					dp[i][j] = Math.max(dp[i][j], dp[i][j - 1]);
					// ref use only [i-1][j], [i][j-1]
					// --> right, actually in this problem [i-1][j-1] are already 
					// covered by [i-1][j] / [i][j-1]
				}
			}
		}
		return dp[m][n];

	}
}
