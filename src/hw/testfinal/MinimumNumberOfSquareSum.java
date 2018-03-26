package hw.testfinal;

/**
 * 
 * How to cut/split an input number into a minimum number of items such that
 * each item is equal to an integer's square value.
 * 
 * For example
 * 
 * 4 can be split to 1+1+1+1 (4 items) or 2^2 (1 item, which is the solution),
 * Return 1
 * 
 * 10 can be split to two items 3^2 + 1^2 (solution) or four items 2^2 + 2^2 +
 * 1^2 +1^2 Return 2
 * 
 * 18 can be split to two items 3^2 + 3^2, or 3 items 4^2 + 1^2 + 1^2. return 2.
 * 
 * 
 * 
 * @author
 *
 */
public class MinimumNumberOfSquareSum {
	// REF
	public int getMinSquareSum(int n) {
		// Assumptions: n is not negative.
		int[] dp = new int[n + 1];
		dp[0] = 0;
		dp[1] = 1;
		for (int i = 2; i <= n; i++) {
			dp[i] = i;
			for (int j = 0; j * j <= i; j++) {
				dp[i] = Math.min(dp[i], dp[i - j * j] + 1);
			}
		}
		return dp[n];
	}
	
	
	/**
	 * dp[x]: min … of number x dp[x] = Math.min(dp[x-k] + dp[k]) for 0 <= k <= x/2
	 * dp[0] = 0, dp[1] = 1
	 **/
	public int getMinSquareSum_self_ok(int n) {
		// Assumptions: n is not negative.
		int[] dp = new int[n + 1];
		dp[0] = 0;
		dp[1] = 1;
		for (int i = 2; i <= n; i++) {
			// check itself
			int sqrt = (int)Math.sqrt(i); // get pos square root // !!! double need to conver to int
			if (sqrt * sqrt == i) {
				dp[i] = 1;
				continue;
			}
			// check from previous status
			dp[i] = Integer.MAX_VALUE; // !!! better to init as i
			for (int k = 0; k <= i / 2; k++) {
				dp[i] = Math.min(dp[i], dp[i - k] + dp[k]);
			}
		}
		return dp[n];
	}
	
	public static void main(String[] args) {
		MinimumNumberOfSquareSum sol = new MinimumNumberOfSquareSum();
		int rst = sol.getMinSquareSum(18);
		System.out.println(rst);
	}
}
