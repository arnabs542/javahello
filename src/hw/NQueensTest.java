package hw;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

class NQueensTest {

	@Test
	void testNqueens_case1() {
		NQueens sol = new NQueens();
		int n = 2;
		List<List<Integer>> answer = new ArrayList<>();
		List<List<Integer>> result = sol.nqueens(n);
        assertEquals(answer.size(), result.size());
        List<List<String>> sans = sol.convert(answer);
        List<List<String>> sres = sol.convert(result);
        Comparator<List<String>> listComp = new Comparator<>() {
        	@Override
        	public int compare(List<String> l1, List<String> l2) {
        		String s1 = String.join(",", l1);
        		String s2 = String.join(",", l2);
        		return s1.compareTo(s2);
        	}
        };
        Collections.sort(sans, listComp);
        Collections.sort(sres, listComp);
        for (int i = 0; i < answer.size(); i++) {
        	assertEquals(answer.get(i), result.get(i));
        }
	}
	
	@Test
	void testNqueens_case2() {
		NQueens sol = new NQueens();
		int n = 4;
		List<List<Integer>> answer = new ArrayList<>();
		answer.add(new ArrayList<Integer>(Arrays.asList(1, 3, 0, 2)) );
		answer.add(new ArrayList<Integer>(Arrays.asList(2, 0, 3, 1)) );
		List<List<Integer>> result = sol.nqueens(n);
        assertEquals(answer.size(), result.size());
        List<List<String>> sans = sol.convert(answer);
        List<List<String>> sres = sol.convert(result);
        Comparator<List<String>> listComp = new Comparator<>() {
        	@Override
        	public int compare(List<String> l1, List<String> l2) {
        		String s1 = String.join(",", l1);
        		String s2 = String.join(",", l2);
        		return s1.compareTo(s2);
        	}
        };
        Collections.sort(sans, listComp);
        Collections.sort(sres, listComp);
        for (int i = 0; i < answer.size(); i++) {
        	assertEquals(answer.get(i), result.get(i));
        }
	}

}
