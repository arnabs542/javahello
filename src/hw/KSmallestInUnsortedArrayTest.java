package hw;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class KSmallestInUnsortedArrayTest {

	@Test
	void testKSmallest_case1() {
		KSmallestInUnsortedArray sol = new KSmallestInUnsortedArray();
		int[] arr = {3, 4, 1, 2, 5}; 
		int k = 3;
		int[] ans = {1, 2, 3};
		int[] res = sol.kSmallest(arr, k);
		assertArrayEquals(res, ans);
	}

}
