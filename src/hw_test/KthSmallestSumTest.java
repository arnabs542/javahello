package hw_test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class KthSmallestSumTest {

	@Test
	void testKthSmallest_case1() {
		KthSmallestSum sol = new KthSmallestSum();
		int[] a = new int[]{1, 4, 6, 8, 10};
		int[] b = new int[]{1, 4, 5, 7, 8};
		int k = 3;
		int answer = 5;
		int result = sol.kthSmallest(a,  b, k);
		assertEquals(answer, result);
	}
	
	@Test
	void testKthSmallest_case2() {
		KthSmallestSum sol = new KthSmallestSum();
		int[] a = new int[]{1, 4, 6, 8, 10};
		int[] b = new int[]{1, 4, 5, 7, 8};
		int k = 4;
		int answer = 6;
		int result = sol.kthSmallest(a,  b, k);
		assertEquals(answer, result);
	}

	@Test
	void testKthSmallest_case3() {
		KthSmallestSum sol = new KthSmallestSum();
		int[] a = new int[]{1, 7, 11};
		int[] b = new int[]{2, 4, 6};
		int k = 3;
		int answer = 7;
		int result = sol.kthSmallest(a,  b, k);
		assertEquals(answer, result);
	}
	
	@Test
	void testKthSmallest_case4() {
		KthSmallestSum sol = new KthSmallestSum();
		int[] a = new int[]{1, 7, 11};
		int[] b = new int[]{2, 4, 6};
		int k = 4;
		int answer = 9;
		int result = sol.kthSmallest(a,  b, k);
		assertEquals(answer, result);
	}
	
	@Test
	void testKthSmallest_case5() {
		KthSmallestSum sol = new KthSmallestSum();
		int[] a = new int[]{1, 7, 11};
		int[] b = new int[]{2, 4, 6};
		int k = 8;
		int answer = 15;
		int result = sol.kthSmallest(a,  b, k);
		assertEquals(answer, result);
	}
	
}
