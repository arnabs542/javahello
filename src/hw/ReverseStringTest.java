package hw;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ReverseStringTest {

	@Test
	void testReverse_case1() {
		ReverseString sol = new ReverseString();
		String input = "";
		String answer = "";
		String result = sol.reverse(input);
		assertEquals(answer, result);
	}

	@Test
	void testReverse_case2() {
		ReverseString sol = new ReverseString();
		String input = "abc";
		String answer = "cba";
		String result = sol.reverse(input);
		assertEquals(answer, result);
	}

}
