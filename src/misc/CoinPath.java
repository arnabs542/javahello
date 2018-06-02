package misc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/***
 * 
 * 656. Coin Path DescriptionHintsSubmissionsDiscussSolution
 * 
 * Given an array A (index starts at 1) consisting of N integers: A1, A2, ...,
 * AN and an integer B. The integer B denotes that from any place (suppose the
 * index is i) in the array A, you can jump to any one of the place in the array
 * A indexed i+1, i+2, …, i+B if this place can be jumped to. Also, if you step
 * on the index i, you have to pay Ai coins. If Ai is -1, it means you can’t
 * jump to the place indexed i in the array.
 * 
 * Now, you start from the place indexed 1 in the array A, and your aim is to
 * reach the place indexed N using the minimum coins. You need to return the
 * path of indexes (starting from 1 to N) in the array you should take to get to
 * the place indexed N using minimum coins.
 * 
 * If there are multiple paths with the same cost, return the lexicographically
 * smallest such path.
 * 
 * If it's not possible to reach the place indexed N then you need to return an
 * empty array.
 * 
 * Example 1: Input: [1,2,4,-1,2], 2 Output: [1,3,5] 
 * 
 * Example 2: Input:
 * [1,2,4,-1,2], 1 Output: [] 
 * 
 * Note: Path Pa1, Pa2, ..., Pan is lexicographically
 * smaller than Pb1, Pb2, ..., Pbm, if and only if at the first i where Pai and
 * Pbi differ, Pai < Pbi; when no such i exists, then n < m. A1 >= 0. A2, ...,
 * AN (if exist) will in the range of [-1, 100]. Length of A is in the range of
 * [1, 1000]. B is in the range of [1, 100].
 * 
 * Difficulty:Hard Total Accepted:4.5K Total Submissions:17.5K
 * Contributor:todayhardtomorrowsunshine Companies
 * 
 * Related Topics
 * 
 * Similar Questions House RobberHouse Robber II
 * 
 * @author
 *
 */
public class CoinPath {
	/****
	 * Basic idea: dp[i]: min cost to jump from [0] to [i] dp[i] = min (dp[k] +
	 * A[i]) for all i > k >= i - B
	 * 
	 ****/

	public List<Integer> cheapestJump(int[] A, int B) {
		if (A == null || A.length == 0 || A[A.length - 1] == -1 || (A.length > 1 && B == 0 || B < 0)) {
			return new ArrayList<Integer>();
		}
		int n = A.length;
		List<Integer> res = new ArrayList<>();

		int[] dp = new int[n];
		Arrays.fill(dp, Integer.MAX_VALUE);
		dp[0] = 0;

		int[] pos = new int[n]; // previous index to get to current i
		Arrays.fill(pos, -1);
		int[] len = new int[n]; // number of jumps
		Arrays.fill(len, Integer.MAX_VALUE);
		for (int i = 0; i < n; i++) {
			if (A[i] == -1)
				continue;
			int start = i - B >= 0 ? i - B : 0;
			for (int j = start; j < i; j++) {
				if (dp[j] == Integer.MAX_VALUE)
					continue;
				int tmp = dp[j] + A[i];
				if (tmp < dp[i] || (tmp == dp[i] && len[i] < len[j] + 1)) {
                //if (tmp < dp[i] || (tmp == dp[i] && pos[i] > j)) {
                		dp[i] = tmp;
					pos[i] = j;
					len[i] = len[j] + 1;
				}
			}
		}
		if (dp[n - 1] == Integer.MAX_VALUE)
			return res;
		for (int cur = n - 1; cur != -1; cur = pos[cur]) {
			res.add(0, cur + 1);  //!!! not cur, problem require index starting from 1
		}
		return res;
	}
	
	public static void main(String[] args) {
		int[] A = new int[] {1,2,4,-1,2};
		int B = 2; // expect [1,3,5]
		CoinPath sol = new CoinPath();
		System.out.println(sol.cheapestJump(A, B));
	}
}
