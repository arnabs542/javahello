package hw;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TopKFrequentWordsTest {

	@Test
	void testTopKFrequent_case1() {
		TopKFrequentWords sol = new TopKFrequentWords();
		String[] combo = new String[] {"a", "a", "b", "b", "b", "b", "c", "c", "c", "d"};
		int k = 2;
		String[] answer = new String[] {"b", "c"};
		String[] result = sol.topKFrequent(combo, k);
		assertArrayEquals(answer, result);
	}

	@Test
	void testTopKFrequent_case2() {
		TopKFrequentWords sol = new TopKFrequentWords();
		String[] combo = new String[] {"a", "a", "b", "b", "b", "b", "c", "c", "c", "d"};
		int k = 4;
		String[] answer = new String[] {"b", "c", "a", "d"};
		String[] result = sol.topKFrequent(combo, k);
		assertArrayEquals(answer, result);
	}
	
	@Test
	void testTopKFrequent_case3() {
		TopKFrequentWords sol = new TopKFrequentWords();
		String[] combo = new String[] {"a", "a", "b", "b", "b", "b", "c", "c", "c", "d"};
		int k = 5;
		String[] answer = new String[] {"b", "c", "a", "d"};
		String[] result = sol.topKFrequent(combo, k);
		assertArrayEquals(answer, result);
	}	
}
