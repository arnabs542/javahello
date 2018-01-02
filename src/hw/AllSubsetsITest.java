package hw;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

class AllSubsetsITest {

	@Test
	void testSubSets_case1() {
		AllSubsetsI	sol = new AllSubsetsI();
		String set = "abc";
		ArrayList<String> ans =  new ArrayList<String>(Arrays.asList("", "a", "ab", "abc", "ac", "b", "bc", "c")); 
		List<String> res = sol.subSets(set);
		Collections.sort(ans);
		Collections.sort(res);
		assertEquals(ans, res);
	}

	@Test
	void testSubSets_case2() {
		AllSubsetsI	sol = new AllSubsetsI();
		String set = "";
		ArrayList<String> ans =  new ArrayList<String>(Arrays.asList("")); 
		List<String> res = sol.subSets(set);
		Collections.sort(ans);
		Collections.sort(res);
		assertEquals(ans, res);
	}
	
	@Test
	void testSubSets_case3() {
		AllSubsetsI	sol = new AllSubsetsI();
		String set = null;
		ArrayList<String> ans =  new ArrayList<String>(); 
		List<String> res = sol.subSets(set);
		Collections.sort(ans);
		Collections.sort(res);
		assertEquals(ans, res);
	}
	
}
