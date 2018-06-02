package misc;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

class CoinPathTest {

	@Test
	void testcheapestJump_case1() {
		CoinPath sol = new CoinPath();
		int[] A = new int[] {1,2,4,-1,2};
		int B = 2; // expect [1,3,5]
		List<Integer> answer = new ArrayList<>(Arrays.asList(1, 3, 5));
		List<Integer> result = sol.cheapestJump(A, B);
		assertEquals(answer, result);	
	}

	@Test
	void testcheapestJump_case2() {
		CoinPath sol = new CoinPath();
		int[] A = new int[] {0, 0, 0, 0, 0, 0};
		int B = 3; 
		List<Integer> answer = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6));
		List<Integer> result = sol.cheapestJump(A, B);
		assertEquals(answer, result);	
	}
	
}
