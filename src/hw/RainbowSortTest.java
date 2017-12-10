package hw;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import utils.ConsolePrinter;

class RainbowSortTest {

	@Test
	@DisplayName("case1_dpname")
	void testRainbowSort_case1() {		
		RainbowSort sol = new RainbowSort();
		int[] arr = new int[] {0, -1, -1,1,1};
		int[] ans = new int[] {-1, -1, 0, 1, 1};
		int[] res = sol.rainbowSort(arr);
		//ConsolePrinter.printIntArray(res);
		assertArrayEquals(ans, res);
	}
	
	@Test
	@DisplayName("case2_dpname")
	void testQuickSort_case2() {		
		RainbowSort sol = new RainbowSort();
		int[] arr = new int[] {1};
		int[] res = sol.rainbowSort(arr);
		assertArrayEquals(arr, res);
	}
	
	@Test
	void testQuickSort_case3() {		
		RainbowSort sol = new RainbowSort();
		int[] arr = new int[] {0, 1};
		int[] ans = new int[] {0, 1};
		int[] res = sol.rainbowSort(arr);
		assertArrayEquals(ans, res);
	}

	@Test
	@DisplayName("case4_dpname")
	void testRainbowSort_case4() {		
		RainbowSort sol = new RainbowSort();
		int[] arr = new int[] { -1, -1,-1,-1};
		int[] ans = new int[] { -1, -1,-1,-1};
		int[] res = sol.rainbowSort(arr);
		ConsolePrinter.printIntArray(res);
		assertArrayEquals(ans, res);
	}
	
}
