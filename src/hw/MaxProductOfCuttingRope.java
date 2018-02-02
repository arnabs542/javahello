package hw;

/**
 * 
 * DP Max Product Of Cutting Rope
 * 
 * Given a rope with positive integer-length n, how to cut the rope into m
 * integer-length parts with length p[0], p[1], ...,p[m-1], in order to get the
 * maximal product of p[0]*p[1]* ... *p[m-1]? m is determined by you and must be
 * greater than 0 (at least one cut must be made). Return the max product you
 * can have.
 * 
 * Assumptions
 * 
 * n >= 2 Examples
 * 
 * n = 12, the max product is 3 * 3 * 3 * 3 = 81(cut the rope into 4 pieces with
 * length of each is 3).
 * 
 * @author
 *
 */

public class MaxProductOfCuttingRope {

	public int cutRod_class_leftBig_rigthSmall2(int n) {
		// left big, right small idea.
		int[] M = new int[n + 1];
		M[0] = 0;
		M[1] = 0; // base case
		int curMax = 0;
		// for different length
		for (int i = 2; i <= n; i++) {
			curMax = 0;
			// for different cutting
			for (int j = 1; j <= i / 2; j++) {
				// j means big part, i-j means small part
				// curMax = Math.max( Math.max((j), M[j]) * (i - j), curMax);
				// --> when use j <= i/2, above line is wrong
				curMax = Math.max(Math.max((i - j), M[i - j]) * j, curMax);
			}
			M[i] = curMax;
		}
		return M[n];
	}

	public int cutRod_class_leftBig_rigthSmall(int n) {
		// M[i] means the maxproduct of cutting rope of length i
		// left big, right small idea.
		int[] M = new int[n + 1];
		M[0] = 0;
		M[1] = 0; // base case
		int curMax = 0;
		// for different length
		for (int i = 2; i <= n; i++) {
			curMax = 0;
			// for different cutting
			for (int j = 1; j < i; j++) {
				// j means big part, i-j means small part
				curMax = Math.max(Math.max(j, M[j]) * (i - j), curMax);
			}
			M[i] = curMax;
		}
		return M[n];
	}

	public int cutRod_class_leftBig_rigthBig(int n) {
		// M[i] means the maxproduct of cutting rope of length i
		// left big, right big idea.
		int[] M = new int[n + 1];
		M[0] = 0;
		M[1] = 0; // base case
		int curMax = 0;
		// for different length
		for (int i = 2; i <= n; i++) {
			curMax = 0;
			// for different cutting
			for (int j = 1; j <= i / 2; j++) {
				curMax = Math.max(Math.max(j, M[j]) * Math.max(i - j, M[i - j]), curMax);
			}
			M[i] = curMax;
		}
		return M[n];
	}

	public int maxProduct(int length) {
		// Assumptions: length >= 2.
		// and length can only cut into integers ??? what if not only integers?
		if (length == 2) {
			return 1;
		}
		int[] array = new int[length + 1];
		// self -->
		// array[i] means that for length i, the max product of all possible cuts
		// because product can be accumulated. (a*b*c) = (a*b) * c, so
		// the basic idea is to assume that arr[i-1] is done, then arr[i] can be
		// get by get max of all possible arr[j] * arr[i-j], but still one case not
		// covered. arr[j]/[i-j] is at least one cut within itself.
		// for arr[i], the arr[j]/[i-j] can have zero cut.
		// therefore, arr[i]
		// <--

		// array[0] = 1, can't do this, because at least one cut, m > 1 --> can do
		// arr[0] = 0
		array[1] = 0;
		array[2] = 1;
		for (int i = 3; i < array.length; i++) {
			// At least one of the partitions after cutting is <= i / 2,
			// so we can just pick that partition, and find the maximum product.
			for (int j = 1; j <= i / 2; j++) {
				// For the other partition, we can choose not cutting it or
				// cutting it, so the max number we can get is either i = j or array[i - j].
				// array[i] = Math.max(array[i], j * Math.max(i - j, array[i - j])); // --> ref
				// idea, need to ask why???
				// array[i] = Math.max(array[i], array[j] * array[i - j]); // wrong, missing the
				// arr[j] zero cut case.
				array[i] = Math.max(array[i], Math.max(j, array[j]) * Math.max((i - j), array[i - j])); // --> self, AC
			}
		}
		return array[length];
	}

	public static void main(String[] args) {
		MaxProductOfCuttingRope sol = new MaxProductOfCuttingRope();
		int n = 12;
		int result = sol.maxProduct(n);
	}
}
