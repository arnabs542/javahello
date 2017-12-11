package hw;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import utils.ConsolePrinter;

class KClosestInSortedArrayTest {

	@Test
	@DisplayName("case1_dpname")
	void testKClosest_case1() {
		KClosestInSortedArray sol = new KClosestInSortedArray();
		int[] arr = new int[] {1,2,3};
		int target = 2, k = 3;
		int[] ans = new int[] {2,1,3}; // or {2,3,1}
		int[] res = sol.kClosest(arr, target, k);
		ConsolePrinter.printIntArray(res);
		assertArrayEquals(ans, res);
	}

	@Test
	void testKClosest_case2() {
		KClosestInSortedArray sol = new KClosestInSortedArray();
		int[] arr = new int[] {1, 4, 6, 8};
		int target = 3, k = 3;
		int[] ans = new int[] {4,1,6}; 
		int[] res = sol.kClosest(arr, target, k);
		ConsolePrinter.printIntArray(res);
		assertArrayEquals(ans, res);
	}
	
	@Test
	void testKClosest_case3() {
		KClosestInSortedArray sol = new KClosestInSortedArray();
		int[] arr = new int[] {1};
		int target = 0, k = 0;
		int[] ans = new int[] {}; 
		int[] res = sol.kClosest(arr, target, k);
		ConsolePrinter.printIntArray(res);
		assertArrayEquals(ans, res);
	}
	
}
