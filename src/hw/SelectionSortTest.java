package hw;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

import org.junit.jupiter.api.Test;

class SelectionSortTest {
	

	@Test
	@DisplayName("case1_dpname")
	void testSolve_case1() {		
		SelectionSort ss = new SelectionSort();
		int[] arr1 = new int[] {3,4,8,1,2};
		int[] ans1 = new int[] {1,2,3,4,8};
		int[] res1 = ss.solve(arr1);
		assertArrayEquals(ans1, res1);
	}
	
	@Test
	@DisplayName("case2_dpname")
	void testSolve_case2() {		
		SelectionSort ss = new SelectionSort();
		int[] arr1 = new int[] {1};
		int[] res1 = ss.solve(arr1);
		assertArrayEquals(arr1, res1);
	}
	
	@Test
	void testSolve_case3() {		
		SelectionSort ss = new SelectionSort();
		int[] arr = new int[] {1,4,-2};
		int[] ans = new int[] {-2,1,4};
		int[] res = ss.solve(arr);
		assertArrayEquals(ans, res);
	}

}
