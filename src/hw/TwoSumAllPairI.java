package hw;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * 
 * Find all pairs of elements in a given array that sum to the given target
 * number. Return all the pairs of indices.
 * 
 * Assumptions
 * 
 * The given array is not null and has length of at least 2.
 * 
 * Examples
 * 
 * A = {1, 3, 2, 4}, target = 5, return [[0, 3], [1, 2]]
 * 
 * A = {1, 2, 2, 4}, target = 6, return [[1, 3], [2, 3]]
 * 
 * 
 * 
 * Medium Array Hashtable
 * 
 * @author
 *
 */
public class TwoSumAllPairI {

	public List<List<Integer>> allPairs_REF(int[] array, int target) {
		// Assumptions: array is not null and length >= 2.
		List<List<Integer>> result = new ArrayList<>();
		// key: number, value: list of all possible indices.
		HashMap<Integer, List<Integer>> map = new HashMap<>(); // duplicate, should be list
		for (int i = 0; i < array.length; i++) {
			List<Integer> indices = map.get(target - array[i]);
			// If target-array[i] is in the map,
			// we can get all the pairs (j,i) with i as the larger index.
			if (indices != null) {
				for (int idx : indices) {
					result.add(Arrays.asList(idx, i)); 
				}
			}
			// add current index i to all the possible indices for value of array[i]
			if (!map.containsKey(array[i])) {
				map.put(array[i], new ArrayList<Integer>());
			}
			map.get(array[i]).add(i);
		}
		return result;
	}

	// self
	public List<List<Integer>> allPairs(int[] array, int target) {
		// Assumptions: array is not null and length >= 2.
		// Arrays.sort(array); // !!! DO NOT sort, ask for indices, if for values not necessary if using hashmap.
		List<List<Integer>> result = new ArrayList<>();
		HashMap<Integer, List<Integer>> map = new HashMap<>(); // duplicate, should be list
		for (int i = 0; i < array.length; i++) {
			List<Integer> indices = map.get(target - array[i]);
			if (indices != null) {
				for (Integer idx : indices) {
					result.add(Arrays.asList(idx, i)); // asList return an Arrays$ArrayList instance, ok for result if
														// only read
				}
			}
			indices = map.get(array[i]);
			if (indices != null) {
				indices.add(i);
			} else {
				// map.put(array[i], Arrays.asList(i)); //asList return an Arrays$ArrayList
				// instance
				// --> can't do this, should new an ArrayList, otherwise later add() op will be
				// unsupported.
				map.put(array[i], new ArrayList<Integer>(Arrays.asList(i)));
			}
		}
		return result;
	}

	// --> with duplicates, this problem is better to be solved with hashmap
	// way, THINK THROUGH FIRST then write
	public List<List<Integer>> allPairs_wrong_not_finish(int[] array, int target) {
		// Assumptions: array is not null and length >= 2.
		Arrays.sort(array);
		List<List<Integer>> result = new ArrayList<>();
		int i = 0, j = array.length - 1;
		while (i < j) {
			int tmp = array[i] + array[j];
			if (tmp == target) {
				result.add(Arrays.asList(i, j));
				// check repeated i, j

			} else if (tmp < target) {
				i++;
			} else {
				j--;
			}
		}
		return result;
	}

	public static void main(String[] args) {
		TwoSumAllPairI sol = new TwoSumAllPairI();
		int[] input1 = new int[] { 1, 2, 2, 4 };
		int[] input = new int[] { 1, 2, 3, 4, 5 };

		int target = 6;
		List<List<Integer>> result = sol.allPairs(input, target);
		for (List<Integer> line : result) {
			for (Integer idx : line) {
				System.out.print(idx);
				System.out.print(" ");
			}
			System.out.println();
		}
	}
}
