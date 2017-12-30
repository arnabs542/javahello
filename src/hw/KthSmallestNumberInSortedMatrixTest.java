package hw;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class KthSmallestNumberInSortedMatrixTest {

	@Test
	void testKthSmallest_case1() {
		KthSmallestNumberInSortedMatrix sol = new KthSmallestNumberInSortedMatrix();
		int[][] matrix = new int[][] {{1,2}};
		int k = 2;
		int ans = 2;
		int res = sol.kthSmallest(matrix, k);
		assertEquals(ans, res);
	}

}
