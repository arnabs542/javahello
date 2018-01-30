package hw;

/**
 * DP
 * 
 * Edit Distance
 * 
 * Given two strings of alphanumeric characters, determine the minimum number of
 * Replace, Delete, and Insert operations needed to transform one string into
 * the other.
 * 
 * Assumptions
 * 
 * Both strings are not null Examples
 * 
 * string one: ¡°sigh¡±, string two : ¡°asith¡±
 * 
 * the edit distance between one and two is 2 (one insert ¡°a¡± at front then
 * replace ¡°g¡± with ¡°t¡±).
 * 
 * @author
 *
 */
public class EditDistance {
	public int editDistance(String one, String two) {
		// Assumptions: one, two are not null.
		// Again, using distance[i][j] to represent
		// substring(0, i) in one and substring(0, j) in two
		// string of char range [0, i), of length i
		int m = one.length(), n = two.length();
		int[][] distance = new int[m + 1][n + 1];
		for (int i = 0; i <= m; i++) {
			for (int j = 0; j <= n; j++) {
				if (i == 0) {
					distance[i][j] = j;
				} else if (j == 0) {
					distance[i][j] = i;
				} else if (one.charAt(i - 1) == two.charAt(j - 1)) {
					distance[i][j] = distance[i - 1][j - 1];
				} else {
					distance[i][j] = Math.min(distance[i][j - 1] + 1, distance[i - 1][j] + 1);
					distance[i][j] = Math.min(distance[i - 1][j - 1] + 1, distance[i][j]);
				}
			}
		}
		return distance[m][n];
	}
}
