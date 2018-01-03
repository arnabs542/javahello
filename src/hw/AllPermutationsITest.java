package hw;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

class AllPermutationsITest {

	@Test
	void testPermutations_case1() {
		AllPermutationsI sol = new AllPermutationsI();
		String set = "abc";
		ArrayList<String> ans =  new ArrayList<String>(Arrays.asList("abc", "acb", "bac", "bca", "cab", "cba")); 
		List<String> res = sol.permutations(set);
		Collections.sort(ans);
		Collections.sort(res);
		assertEquals(ans, res);	
	}

	@Test
	void testPermutations_case2() {
		AllPermutationsI sol = new AllPermutationsI();
		String set = "";
		ArrayList<String> ans =  new ArrayList<String>(Arrays.asList("")); 
		List<String> res = sol.permutations(set);
		Collections.sort(ans);
		Collections.sort(res);
		assertEquals(ans, res);	
	}
	
	@Test
	void testPermutations_case3() {
		AllPermutationsI sol = new AllPermutationsI();
		String set = null;
		ArrayList<String> ans =  new ArrayList<String>(); 
		List<String> res = sol.permutations(set);
		Collections.sort(ans);
		Collections.sort(res);
		assertEquals(ans, res);	
	}	
}
