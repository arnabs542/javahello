package hw;

import static org.junit.jupiter.api.Assumptions.assumingThat;

/**
 * 
 * 
 * We have a list of piles of stones, each pile of stones has a certain weight,
 * represented by an array of integers. In each move, we can merge two adjacent
 * piles into one larger pile, the cost is the sum of the weights of the two
 * piles. We merge the piles of stones until we have only one pile left.
 * Determine the minimum total cost.
 * 
 * Assumptions
 * 
 * stones is not null and is length of at least 1 Examples
 * 
 * {4, 3, 3, 4}, the minimum cost is 28
 * 
 * merge first 4 and first 3, cost 7
 * 
 * merge second 3 and second 4, cost 7
 * 
 * merge two 7s, cost 14
 * 
 * total cost = 7 + 7 + 14 = 28
 * 
 * 
 * Hard Array Dynamic Programming
 * 
 * @author
 *
 */
public class MergeStones {
	
	 
	public int minCost_REF(int[] stones) {
		// Assumptions: stones is not null, stones.length >= 1.
		// This problem is actually the same one with CUtting Wood I.
		// Only difference is for each partition(i,j), we need to
		// compute the length.
		int n = stones.length;
		int[][] cost = new int[n][n]; 
		// subSum[i][j]: the length of partition(i, j).
		int[][] subSum = new int[n][n];
		for (int r = 0; r < n; r++) {
			for (int l = r; l >= 0; l--) {
				if (l == r) {
					cost[l][r] = 0;
					subSum[l][r] = stones[l];
				} else {
					subSum[l][r] = subSum[l][r-1] + stones[r];
					cost[l][r] = Integer.MAX_VALUE;
					for (int m = l; m < r; m++) { 
						cost[l][r] = Math.min(cost[l][r], cost[l][m] + cost[m+1][r] + subSum[l][r]);
					}	
				}
			}
		}
		return cost[0][n-1];
	}	
	
	// self, 
	public int minCost(int[] stones) {
		if (stones == null || stones.length == 0) {
			return 0;
		}
		int n = stones.length;
		// return length n + 1, preSum[i]: first i stones, ending on stones[i-1] 
		int[] preSum = findPreSum(stones);
		// can't do this, understand the cost of [lm],[mr] and adding cost of current merge.
//		if (n <= 3) {
//			return preSum[n] - preSum[0];
//		}
		int[][] minCost = new int[n+1][n+1]; // minCost[i][j]: cost for merge stones[i-1][j-1];
		for (int r = 1; r <= n; r++) {
			for (int l = r; l > 0; l--) {
				if (l == r) {
					minCost[l][r] = 0;
				} else {
					int tmp = Integer.MAX_VALUE;
					for (int m = l + 1; m <= r; m++) { // m: sub-left part ends on m - 1, sub-right part start on m
						tmp = Math.min(tmp, minCost[l][m-1] + minCost[m][r]);
					}
					tmp += preSum[r] - preSum[l - 1];
					minCost[l][r] = tmp;
				}
			}
		}
		return minCost[1][n];
	}
	
	
	
	public int minCost_wrong(int[] stones) {
		if (stones == null || stones.length == 0) {
			return 0;
		}
		int n = stones.length;
		// return length n + 1, preSum[i]: first i stones, ending on stones[i-1] 
		int[] preSum = findPreSum(stones);
		if (n <= 3) {
			return preSum[n] - preSum[0];
		}
		int[][] minCost = new int[n+1][n+1]; // minCost[i][j]: merge stones[i-1][j-1];
		for (int r = 1; r <= n; r++) {
			for (int l = r; l > 0; l--) {
				if (l == r) {
					// minCost[l][r] = stones[l-1];
					minCost[l][r] = 0;
				} else if (l + 1 == r ) {
					minCost[l][r] = stones[l-1] + stones[r-1];
				} else {
					int tmp1 = Integer.MAX_VALUE;
					// remember to distinguish check single pile on l and single pile on r cases;
					// single l
					tmp1 = Math.min(tmp1, minCost[l][l] + minCost[l+1][r] + preSum[r] - preSum[l]);
					// single r
					tmp1 = Math.min(tmp1, minCost[l][r-1] + minCost[r][r] + preSum[r-1] - preSum[l-1]);
					// so m should be [m+2, r-1]
					int tmp2 = Integer.MAX_VALUE;
					for (int m = l + 2; m < r; m++) { // m: sub-left part ends on m - 1, sub-right part start on m
						// minCost[l][r] = Math.min(minCost[l][r], minCost[l][m] + minCost[m][r]);
						// minCost[l][r] = Math.min(minCost[l][r], minCost[l][m-1] + minCost[m][r]);
						tmp2 = Math.min(tmp2, minCost[l][m-1] + minCost[m][r]);
					}
					tmp2 += preSum[r] - preSum[l - 1];
					// minCost[l][r] += preSum[r] - preSum[l - 1]; //!! can not do this generally
					// --> if in any of lm, mr partition, there is a single pile (xiao duan), then one of the weight will
					// be added repeatedly, see case of 3 piles.
					minCost[l][r] = Math.min(tmp1, tmp2);

				}
			}
		}
		return minCost[1][n];
	}
	
	private int[] findPreSum(int[] stones) {
		int[] sum = new int[stones.length + 1];
		for (int i = 0; i < stones.length; i++) {
			sum[i+1] = sum[i] + stones[i]; 
		}
		return sum;
	}
	
	public static void main(String[] args) {
		//int[] stones = new int[] {4, 3, 3, 4}; // expect 28
		int[] stones = new int[] {1, 2, 4}; // expect 10
		MergeStones sol = new MergeStones();
		int result = sol.minCost(stones);
		System.out.println(result);
	}
}
