package hw;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class LargestSubMatrixSumTest {

	@Test
	void testLargest_case1() {
		LargestSubMatrixSum sol = new LargestSubMatrixSum();
		int[][] matrix = new int[][] { 
			{1, -2, -1, 4},
			{1, -1, 1, 1},			  
			{0, -1, -1, 1},
			{0, 0, 1, 1} };
		int answer = 7;
		int result = sol.largest(matrix);
		assertEquals(answer, result);
	}
	
	@Test
	void testLargest_case2() {
		LargestSubMatrixSum sol = new LargestSubMatrixSum();
		int[][] matrix = new int[][] { 
			{-1,-2,-3},
			{-4,-3,-2},
			{-3,0,-1} 
		};
		int answer = 0;
		int result = sol.largest(matrix);
		assertEquals(answer, result);
	}
	
	@Test
	void testLargest_case3() {
		LargestSubMatrixSum sol = new LargestSubMatrixSum();
		int[][] matrix = new int[][] { 
			{2,-1, 2, 1,-3},
			{0,-2,-1, 2, 1},
			{3, 2, 1,-3,-2}
		};
		int answer = 6;
		int result = sol.largest(matrix);
		assertEquals(answer, result);
	}

}
