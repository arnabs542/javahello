package hw.test;

/**
 * 
 * Minimum Cuts For Palindromes
 * 
 * Description
 * 
 * Given a string, a partitioning of the string is a palindrome partitioning if
 * every substring of the partition is a palindrome. Determine the fewest cuts
 * needed for a palindrome partitioning of a given string.
 * 
 * Assumptions
 * 
 * The given string is not null 
 * 
 * Examples
 * 
 * ¡°a | babbbab | bab | aba¡± is a palindrome partitioning of ¡°ababbbabbababa¡±.
 * 
 * The minimum number of cuts needed is 3.
 * 
 * Medium Array String Exam 1
 * 
 * @author
 *
 */
public class MinimumCutsForPalindromes {
	public int minCuts(String input) {
		// Assumptions: input is not null.
		char[] array = input.toCharArray();
		int len = array.length;
		if (len == 0) {
			return 0;
		}
		// isP[i][j] indicates if the substring (i-1, j-1) is palindrome.
		boolean[][] isP = new boolean[len + 1][len + 1];
		// minCuts[i] indicates the min cuts for substring (0, i-1).
		int[] minCuts = new int[len + 1];
		for (int end = 1; end <= len; end++) {
			// initialization of minCuts[end], we at most need end cuts
			// (separate all the characters).
			minCuts[end] = end;
			for (int start = end; start >= 1; start--) {
				// calculate isP[start][end] first.
				if (array[start - 1] == array[end - 1]) {
					isP[start][end] = end - start < 2 || isP[start+1][end - 1];
				}
				// use isP[start][end] to calculate minCuts[end].
				if (isP[start][end]) {
					minCuts[end] = Math.min(minCuts[end], 1 + minCuts[start - 1]);
				}
			}
		}
		return minCuts[len] - 1;

	}
}
