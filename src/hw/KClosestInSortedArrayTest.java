package hw;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.Assert;

import utils.ConsolePrinter;

class KClosestInSortedArrayTest {

	@Test
	@DisplayName("case1_dpname")
	void testKClosest_case1() {
		KClosestInSortedArray sol = new KClosestInSortedArray();
		int[] arr = new int[] {1,2,3};
		int target = 2, k = 3;
		int[] ans = new int[] {2,1,3}; // or {2,3,1}
		int[] ans2 = new int[] {2, 3, 1};
		int[] res = sol.kClosest(arr, target, k);
		ConsolePrinter.printIntArray(res);
		Assert.assertTrue(Arrays.equals(res, ans) || Arrays.equals(res, ans2));
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
	
	@Test
	void testKClosest_case4() {
		KClosestInSortedArray sol = new KClosestInSortedArray();
		int[] arr = new int[] {1, 5};
		int target = 10, k = 2;
		int[] ans = new int[] {5, 1}; 
		int[] res = sol.kClosest(arr, target, k);
		ConsolePrinter.printIntArray(res);
		assertArrayEquals(ans, res);
	}
	
	@Test
	void testKClosest_case5() {
		KClosestInSortedArray sol = new KClosestInSortedArray();
		int[] arr = new int[] {1, 5};
		int target = 10, k = 1;
		int[] ans = new int[] {5}; 
		int[] res = sol.kClosest(arr, target, k);
		ConsolePrinter.printIntArray(res);
		assertArrayEquals(ans, res);
	}	
}
