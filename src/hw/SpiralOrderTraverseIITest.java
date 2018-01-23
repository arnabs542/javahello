package hw;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

class SpiralOrderTraverseIITest {

	@Test
	void testSpiral_case1() {
		SpiralOrderTraverseII sol = new SpiralOrderTraverseII();
		int[][] matrix = new int[][]{{1,2,3,4,5},{6,7,8,9,10},{11,12,13,14,15}};
		List<Integer> answer = new ArrayList<>(Arrays.asList(1,2,3,4,5,10,15,14,13,12,11,6,7,8,9));
		List<Integer> result = sol.spiral(matrix);
		assertEquals(answer, result);
	}
	
	@Test
	void testSpiral_case2() {
		SpiralOrderTraverseII sol = new SpiralOrderTraverseII();
		int[][] matrix = new int[][]{{1,2,3},{4,5,6},{7,8,9}};
		List<Integer> answer = new ArrayList<>(Arrays.asList(1,2,3,6,9,8,7,4,5));
		List<Integer> result = sol.spiral(matrix);
		assertEquals(answer, result);
	}

}
