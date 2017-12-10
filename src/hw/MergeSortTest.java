package hw;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MergeSortTest {


	@Test
	@DisplayName("case1_dpname")
	void testMergeSort_case1() {		
		MergeSort sol = new MergeSort();
		int[] arr = new int[] {3,4,8,1,2};
		int[] ans = new int[] {1,2,3,4,8};
		int[] res = sol.mergeSort(arr);
		assertArrayEquals(ans, res);
	}
	
	@Test
	@DisplayName("case2_dpname")
	void testMergeSort_case2() {		
		MergeSort sol = new MergeSort();
		int[] arr = new int[] {1};
		int[] res = sol.mergeSort(arr);
		assertArrayEquals(arr, res);
	}
	
	@Test
	void testMergeSort_case3() {		
		MergeSort sol = new MergeSort();
		int[] arr = new int[] {1,4,-2};
		int[] ans = new int[] {-2,1,4};
		int[] res = sol.mergeSort(arr);
		assertArrayEquals(ans, res);
	}

}
