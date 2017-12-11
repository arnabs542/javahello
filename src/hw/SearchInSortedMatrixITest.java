package hw;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class SearchInSortedMatrixITest {

	@Test
	void testSearch_case1() {
		SearchInSortedMatrixI sol = new SearchInSortedMatrixI();
		int[][] mat = new int[][] {{1,2,3,4}};
		int target = 4;
		int[] ans = new int[] {0, 3};
		int[] res = sol.search(mat, target);
		assertArrayEquals(ans, res);
	
	}

}
