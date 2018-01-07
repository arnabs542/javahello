package hw;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class RemoveAdjacentRepeatedCharactersIVTest {

	@Test
	void testDeDup_case1() {
		RemoveAdjacentRepeatedCharactersIV sol = new RemoveAdjacentRepeatedCharactersIV();
		String input = "abbbaaccz";
		String ans = "z";
		String res = sol.deDup(input);
		assertEquals(ans, res);
	}
	
	@Test
	void testDeDup_case2() {
		RemoveAdjacentRepeatedCharactersIV sol = new RemoveAdjacentRepeatedCharactersIV();
		String input = "aabccdc";
		String ans = "bdc";
		String res = sol.deDup(input);
		assertEquals(ans, res);
	}
	
	@Test
	void testDeDup_case3() {
		RemoveAdjacentRepeatedCharactersIV sol = new RemoveAdjacentRepeatedCharactersIV();
		String input = "";
		String ans = "";
		String res = sol.deDup(input);
		assertEquals(ans, res);
	}
	
	@Test
	void testDeDup_case4() {
		RemoveAdjacentRepeatedCharactersIV sol = new RemoveAdjacentRepeatedCharactersIV();
		String input = null;
		String ans = null;
		String res = sol.deDup(input);
		assertEquals(ans, res);
	}	
}
