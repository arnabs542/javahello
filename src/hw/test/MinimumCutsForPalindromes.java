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
		if (input == null || input.length() <= 1) {
			return 0;
		}
		char[] chars = input.toCharArray();
		int n = chars.length;
		boolean[][] isPal = new boolean[n+1][n+1];
		// substring[i-1, j-1] is palindrome or not.
		int[] minCut = new int[n + 1];
		minCut[0] = -1; // important!! since when for the whole string is palindrome, use 0 cut, --> con't
		// but method use 1+minCut[end-1], so need to set [0] as -1 to get the number out.
		// minCut[i]: string of length i, substring[0, i-1]
		for (int i = 2; i <= n; i++) {
			minCut[i] = i - 1;
			for (int j = i; j >= 1; j--) { // not >= 0, should be >= 1
				// because the index meaning of i, j should be the same, and --> isPal's index --> j's stop range
				if (chars[j-1] == chars[i-1]) {
					if (j + 1 >= i - 1 || isPal[j + 1][i - 1] == true) {
						isPal[j][i] = true;
						minCut[i] = Math.min(minCut[i], 1 + minCut[j-1]); // since [j-1, i-1] is palindrome, here should be j-1, not minCut[j]
						// since j - 1 can be 0, which means whole string is palindrome, either put extra check here, or put minCut[0] = -1 before
					} else {
						isPal[j][i] = false;
					}
				}
			}
		}
		return minCut[n];
	}

	public int minCuts_Ref(String input) {
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
					isP[start][end] = end - start < 2 || isP[start + 1][end - 1];
				}
				// use isP[start][end] to calculate minCuts[end].
				if (isP[start][end]) {
					minCuts[end] = Math.min(minCuts[end], 1 + minCuts[start - 1]);
				}
			}
		}
		return minCuts[len] - 1;
	}
	
	public static void main(String[] args) {
		MinimumCutsForPalindromes sol = new MinimumCutsForPalindromes();
		String input = "ababbbabbababa"; // expect 3
		String input1 = "aaaaaabbabb"; // expect 1
		int result = sol.minCuts(input);
		System.out.println(result);
	}
}
