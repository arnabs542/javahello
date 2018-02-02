package hw;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class MaxProductOfCuttingRopeTest {

	@Test
	void testMaxProduct_case1() {
		MaxProductOfCuttingRope sol = new MaxProductOfCuttingRope();
		int length = 12;
		int answer = 81; // 4 * 4 * 4 * 4
		int result = sol.maxProduct(length);
		assertEquals(answer, result);
	}

	@Test
	void testMaxProduct_case2() {
		MaxProductOfCuttingRope sol = new MaxProductOfCuttingRope();
		int length = 5;
		int answer = 6; 
		int result = sol.maxProduct(length);
		assertEquals(answer, result);
	}
	
	@Test
	void testMaxProduct_case3() {
		MaxProductOfCuttingRope sol = new MaxProductOfCuttingRope();
		int length = 25;
		int answer = 8748; 
		int result = sol.maxProduct(length);
		assertEquals(answer, result);
	}
	
}
