package hw;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.Test;

class ReverseWordsInASentenceITest {

	@Test
	void testReverseWords_case1() {
		ReverseWordsInASentenceI sol = new ReverseWordsInASentenceI();
		String input = "";
		String answer = "";
		String result = sol.reverseWords(input);
		assertEquals(answer, result);
	}

	@Test
	void testReverseWords_case2() {
		ReverseWordsInASentenceI sol = new ReverseWordsInASentenceI();
		String input = "I love Google";
		String answer = "Google love I";
		String result = sol.reverseWords(input);
		assertEquals(answer, result);
	}
	
}
