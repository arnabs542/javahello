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
				}
			}
		}
		return dp[m][n];

	}
}
