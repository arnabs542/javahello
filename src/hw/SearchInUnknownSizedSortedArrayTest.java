package hw;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class SearchInUnknownSizedSortedArrayTest {

	@Test
	void testSearch_case1() {
		SearchInUnknownSizedSortedArray sol = new SearchInUnknownSizedSortedArray();
		Dictionary dict = new int[] {1,3};
		int target = 3;
		int ans = 1;
		int res = sol.search(dict, target);
		assertEquals(ans, res);
	}

}
