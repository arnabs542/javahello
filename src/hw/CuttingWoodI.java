package hw;

/**
 * 
 * There is a wooden stick with length L >= 1, we need to cut it into pieces,
 * where the cutting positions are defined in an int array A. The positions are
 * guaranteed to be in ascending order in the range of [1, L - 1]. The cost of
 * each cut is the length of the stick segment being cut. Determine the minimum
 * total cost to cut the stick into the defined pieces.
 * 
 * Examples
 * 
 * L = 10, A = {2, 4, 7}, the minimum total cost is 10 + 4 + 6 = 20 (cut at 4
 * first then cut at 2 and cut at 7)
 * 
 * Hard Array Dynamic Programming
 * 
 * @author
 *
 */
public class CuttingWoodI {
	public int minCost(int[] cuts, int length) {
		// Assumptions: cuts is not null, length >= 0, all cuts are alid numbers.
		// --> helper means all possible places to cut, adding 0 and length positions.
		// First we need to pad the original array at leftmost and right most 
		// position.
		int[] helper = new int[cuts.length + 2];
		helper[0] = 0;
		for (int i = 0; i < cuts.length; i++) {
			helper[i + 1] = cuts[i];
		}
		helper[helper.length - 1] = length;
		// minCost[i][j]: the min cost of cutting the cuts (i, j); --> note! i, j are not wood length, but cuts (helper) index!!!
		// -- if between i, j there is no more cut allowed, mingCost[i][j] = 0
		// -- otherwise, minCost[i][j] = min(minCost[i][k] + minCost[k][j]) + j - i, 
		// ---------- (cont') for any i < k < j, where k is the possibel cut place
		// final result is the minCost[0][length];
		int n = helper.length;
		int[][] minCost = new int[n][n];
		
		for (int right = 1; right < helper.length; right++) {
			for(int left = right - 1; left >= 0; left--) {
				if (left + 1 >= right) {
					minCost[left][right] = 0;
				} else {
					minCost[left][right] = Integer.MAX_VALUE;
					for (int mid = left + 1; mid < right; mid++) {
						int tmp = minCost[left][mid] + minCost[mid][right]; //  + right - left; -->
						// two notes here:
						// 1. can be moved to out of for loop, save computation time
						// 2. not right - left, that's the possible cuts(-->helper) index, helper[right] - helper[left]
						minCost[left][right] = Math.min(minCost[left][right], tmp);
					}
					minCost[left][right] += helper[right] - helper[left];
				}
			}
		}
		return minCost[0][n-1];
	}
}
