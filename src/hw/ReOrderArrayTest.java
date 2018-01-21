package hw;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ReOrderArrayTest {

	@Test
	void testReorder_case1() {
		ReOrderArray sol = new ReOrderArray();
		int[] input = new int[] {1,2,3,4,5,6};
		int[] answer = new int[] {1,4,2,5,3,6};
		int[] result = sol.reorder(input);
		assertArrayEquals(answer, result);
	}

	@Test
	void testReorder_case2() {
		ReOrderArray sol = new ReOrderArray();
		int[] input = new int[] {1,2,3,4,5,6,7};
		int[] answer = new int[] {1,4,2,5,3,6,7};
		int[] result = sol.reorder(input);
		assertArrayEquals(answer, result);
	}
	
	
}
