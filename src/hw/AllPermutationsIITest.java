package hw;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

class AllPermutationsIITest {

	@Test
	void testPermutations_case1() {
		AllPermutationsII sol = new AllPermutationsII();
		String set = "abc";
		ArrayList<String> ans =  new ArrayList<String>(Arrays.asList("abc", "acb", "bac", "bca", "cab", "cba")); 
		List<String> res = sol.permutations(set);
		Collections.sort(ans);
		Collections.sort(res);
		assertEquals(ans, res);	
	}

	@Test
	void testPermutations_case2() {
		AllPermutationsII sol = new AllPermutationsII();
		String set = "";
		ArrayList<String> ans =  new ArrayList<String>(Arrays.asList("")); 
		List<String> res = sol.permutations(set);
		Collections.sort(ans);
		Collections.sort(res);
		assertEquals(ans, res);	
	}
	
	@Test
	void testPermutations_case3() {
		AllPermutationsII sol = new AllPermutationsII();
		String set = null;
		ArrayList<String> ans =  new ArrayList<String>(); 
		List<String> res = sol.permutations(set);
		Collections.sort(ans);
		Collections.sort(res);
		assertEquals(ans, res);	
	}
	
	@Test
	void testPermutations_case4() {
		AllPermutationsII sol = new AllPermutationsII();
		String set = "aba";
		ArrayList<String> ans =  new ArrayList<String>(Arrays.asList("aab", "aba", "baa")); 
		List<String> res = sol.permutations(set);
		Collections.sort(ans);
		Collections.sort(res);
		assertEquals(ans, res);	
	}	
	
	@Test
	void testPermutations_case5() {
		AllPermutationsII sol = new AllPermutationsII();
		String set = "acba";
		ArrayList<String> ans =  new ArrayList<String>(Arrays.asList("aabc", "aacb", "abac", "abca", "acab", "acba", "baac", "baca", "bcaa", "caab", "caba", "cbaa")); 
		List<String> res = sol.permutations(set);
		Collections.sort(ans);
		Collections.sort(res);
		assertEquals(ans, res);	
	}
	

}
