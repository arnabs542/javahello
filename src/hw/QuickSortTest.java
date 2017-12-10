package hw;

import static org.junit.jupiter.api.Assertions.*;
import utils.ConsolePrinter;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class QuickSortTest {


	@Test
	@DisplayName("case1_dpname")
	void testQuickSort_case1() {		
		QuickSort sol = new QuickSort();
		int[] arr = new int[] {3,5,1,2,4,8};
		int[] ans = new int[] {1,2,3,4,5,8};
		int[] res = sol.quickSort(arr);
		ConsolePrinter.printIntArray(res);
		assertArrayEquals(ans, res);
	}
	
	@Test
	@DisplayName("case2_dpname")
	void testQuickSort_case2() {		
		QuickSort sol = new QuickSort();
		int[] arr = new int[] {1};
		int[] res = sol.quickSort(arr);
		assertArrayEquals(arr, res);
	}
	
	@Test
	void testQuickSort_case3() {		
		QuickSort sol = new QuickSort();
		int[] arr = new int[] {1,4,-2};
		int[] ans = new int[] {-2,1,4};
		int[] res = sol.quickSort(arr);
		assertArrayEquals(ans, res);
	}

}
