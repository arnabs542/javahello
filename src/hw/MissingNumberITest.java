package hw;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class MissingNumberITest {

	@Test
	void testMissing_case1() {
		MissingNumberI sol = new MissingNumberI();
		int[] arr = new int[] {2,1,4};
		int ans = 3;
		int res = sol.missing(arr);
		assertEquals(ans, res);
	}

	@Test
	void testMissing_case2() {
		MissingNumberI sol = new MissingNumberI();
		int[] arr = new int[] {2,1,3};
		int ans = 4;
		int res = sol.missing(arr);
		assertEquals(ans, res);
	}
	
	@Test
	void testMissing_case3() {
		MissingNumberI sol = new MissingNumberI();
		int[] arr = new int[] {};
		int ans = 1;
		int res = sol.missing(arr);
		assertEquals(ans, res);
	}
	
}
