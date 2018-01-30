package hw;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ArrayHopperIITest {

	@Test
	void testMinJump_case1() {
		ArrayHopperII sol = new ArrayHopperII();
		int[] input = new int[]{3, 3, 1, 0, 4};
		int answer = 2;
		int result = sol.minJump(input);
		assertEquals(answer, result);
	}
	
	@Test
	void testMinJump_case2() {
		ArrayHopperII sol = new ArrayHopperII();
		int[] input = new int[]{2, 1, 1, 0, 2};
		int answer = -1;
		int result = sol.minJump(input);
		assertEquals(answer, result);
	}
	
	@Test
	void testMinJump_case3() {
		ArrayHopperII sol = new ArrayHopperII();
		int[] input = new int[]{0};
		int answer = 0;
		int result = sol.minJump(input);
		assertEquals(answer, result);
	}

}
