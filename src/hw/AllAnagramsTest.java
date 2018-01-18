package hw;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

class AllAnagramsTest {

	@Test
	void testAllAnagrams_case1() {
		AllAnagrams sol = new AllAnagrams();
		String l = "abcbac", s = "ab";
		List<Integer> answer =  new ArrayList<>(Arrays.asList(0, 3)); 
		List<Integer> result = sol.allAnagrams(s, l);
		assertEquals(answer, result);	
	}

	@Test
	void testAllAnagrams_case2() {
		AllAnagrams sol = new AllAnagrams();
		String s = "aa", l = "aaa";
		List<Integer> answer =  new ArrayList<>(Arrays.asList(0, 1)); 
		List<Integer> result = sol.allAnagrams(s, l);
		assertEquals(answer, result);	
	}
	
	@Test
	void testAllAnagrams_case3() {
		AllAnagrams sol = new AllAnagrams();
		String s = "abbc", l = "abbcabcab";
		List<Integer> answer =  new ArrayList<>(Arrays.asList(0, 1, 2, 5)); 
		List<Integer> result = sol.allAnagrams(s, l);
		assertEquals(answer, result);	
	}
	
	@Test
	void testAllAnagrams_case4() {
		AllAnagrams sol = new AllAnagrams();
		String s = "abc", l = "abcabccefgaegdaes";
		List<Integer> answer =  new ArrayList<>(Arrays.asList(0, 1, 2, 3)); 
		List<Integer> result = sol.allAnagrams(s, l);
		assertEquals(answer, result);	
	}
	@Test
	void testAllAnagrams_case5() {
		AllAnagrams sol = new AllAnagrams();
		String l = "c", s = "ab";
		List<Integer> answer =  new ArrayList<>(); 
		List<Integer> result = sol.allAnagrams(s, l);
		assertEquals(answer, result);	
	}
	
}
