package hw;

/**
 * 
 * Interleave Strings
 * 
 * Description
 * 
 * Given three strings A, B and C. Determine if C can be created by merging A
 * and B in a way that maintains the relative order of the characters in A and
 * B.
 * 
 * Assumptions
 * 
 * none of A, B, C is null
 * 
 * Examples
 * 
 * C = "abcde", A = "acd", B = "be", return true
 * 
 * C = "abcde", A = "adc", B = "be", return false
 * 
 * 
 * Medium 2 D Array
 * 
 * 
 * @author
 *
 */
public class InterleaveStrings {
	public boolean canMerge_ref(String a, String b, String c) {
		// Assumptions: a, b, c are not null.
		int alen = a.length();
		int blen = b.length();
		int clen = c.length();
		if (alen + blen != clen) {
			return false;
		}
		
		// a common trick is to use i to represent ith character in a,
		// the 1st character is actually a.charAt(0).
		// the benefit is we can eliminate some base cases.
		boolean[][] canMerge = new boolean[alen + 1][blen + 1];
		for (int i = 0; i <= alen; i++) {
			for (int j = 0; j <= blen; j++) {
				// this is the only base case we need to take care.
				if (i == 0 && j == 0) {
					canMerge[i][j] = true;
				}
				// two possible ways of matching the character in c. // i = 2, j = 2, k = 3
				if (i > 0 && a.charAt(i - 1) == c.charAt(i + j - 1)) {
					canMerge[i][j] = canMerge[i - 1][j];
				}
				if (j > 0 && b.charAt(j - 1) == c.charAt(i + j - 1)) {
					canMerge[i][j] |= canMerge[i][j - 1];
				}
 			}
		}
		return canMerge[alen][blen];
	}
	
	// self, ac, recursive way
	public boolean canMerge(String a, String b, String c) {
		// Assumptions: a, b, c are not null.
		int alen = a.length();
		int blen = b.length();
		int clen = c.length();
		if (alen + blen != clen) {
			return false;
		}
		
		return helper(a, b, c, 0, 0, 0);
	}
	
	private boolean helper(String a, String b, String c, int i, int j, int k) {
		if (i == a.length() && j == b.length() && k == c.length()) {
			return true;
		}
		if ( i == a.length() ) {
			if (b.charAt(j) != c.charAt(k)) {
				return false;
			}
			return helper(a, b, c, i, j + 1, k + 1);
		}
		if (j == b.length()) {
			if (a.charAt(i) != c.charAt(k)) {
				return false;
			}
			return helper(a, b, c, i + 1, j, k + 1);
		}
		char target = c.charAt(k);
		char ca = a.charAt(i), cb = b.charAt(j);
		if (ca != target && cb != target) {
			return false;
		}
		return helper(a, b, c, i + 1, j, k + 1) || helper(a, b, c, i, j + 1, k + 1);
	} 
	
	public static void main(String[] args) {
		//String C = "abcde", A = "acd", B = "be";  // expect true
		String C = "abcde", A = "adc", B = "be";  // return false
		InterleaveStrings sol = new InterleaveStrings();
		boolean result = sol.canMerge(A, B, C);
		System.out.println(result);
	}
}
