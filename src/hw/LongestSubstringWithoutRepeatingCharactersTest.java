package hw;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class LongestSubstringWithoutRepeatingCharactersTest {

	@Test
	void testLongest_case1() {
		LongestSubstringWithoutRepeatingCharacters sol = new LongestSubstringWithoutRepeatingCharacters();
		String input = "";
		int answer = 0;
		int result = sol.longest(input);
		assertEquals(answer, result);
	}

	@Test
	void testLongest_case2() {
		LongestSubstringWithoutRepeatingCharacters sol = new LongestSubstringWithoutRepeatingCharacters();
		String input = "bcdfbd";
		int answer = 4;
		int result = sol.longest(input);
		assertEquals(answer, result);
	}
	
	@Test
	void testLongest_case3() {
		LongestSubstringWithoutRepeatingCharacters sol = new LongestSubstringWithoutRepeatingCharacters();
		String input = "efhrgsayekasdanfev";
		int answer = 9;
		int result = sol.longest(input);
		assertEquals(answer, result);
	}
	
}
