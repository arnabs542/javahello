package hw;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * Find all pairs of elements in a given array that sum to the pair the given
 * target number. Return all the distinct pairs of values.
 * 
 * Assumptions
 * 
 * The given array is not null and has length of at least 2 The order of the
 * values in the pair does not matter Examples
 * 
 * A = {2, 1, 3, 2, 4, 3, 4, 2}, target = 6, return [[2, 4], [3, 3]]
 * 
 * 
 * 
 * Medium Array Hashtable Sort
 * 
 * @author
 *
 */
public class TwoSumAllPairII {

	// REF
	// Method 1: sort the array first and use two pointers.
	public List<List<Integer>> allPairs(int[] array, int target) {
		// Assumptions: array is not null, array.legnth >= 2.
		Arrays.sort(array);
		List<List<Integer>> result = new ArrayList<>();
		int left = 0;
		int right = array.length - 1;
		while (left < right) {
			// ignore all the consecutive duplicate values when we want
			// to determine the smaller element of the pair.
			if (left > 0 && array[left] == array[left - 1]) {
				left++;
				continue;
			}
			int cur = array[left] + array[right];
			if (cur == target) {
				result.add(Arrays.asList(array[left], array[right]));
				left++;
				right--;
			} else if (cur < target) {
				left++;
			} else {
				right--;
			}

		}
		return result;

	}

	// Method 2: use HashMap.
	public List<List<Integer>> allPairsII(int[] array, int target) {
		// Assumptions: array is not null, array.legnth >= 2.
		List<List<Integer>> result = new ArrayList<>();
		// Record the number of existence of the values.
		Map<Integer, Integer> map = new HashMap<>();
		for (int num : array) {
			// Two cases when we need to make the pair of a solution:
			// 1. if 2 * x == target, and we need to make sure there is no duplicates.
			// 2. if x + y == target, and this is the first time jboth x and y 
			// are present, so we can make sure there is not duplicates.
			Integer count = map.get(num);
			if (num * 2 == target && count != null && count == 1) {
				result.add(Arrays.asList(num, num));
			} else if (map.containsKey(target - num) && count == null) {
				result.add(Arrays.asList(target - num, num));
			}
			if (count == null) {
				map.put(num, 1);
			} else {
				map.put(num, count + 1);
			}
		}
		return result;
	}

	// 2,1,3,2,4,3,4,2
	// 1,2,2,2,3,3,4,4 -- 2,4
	public List<List<Integer>> allPairs_self_ac(int[] array, int target) {
		// Assume: array is not null and length >= 2
		List<List<Integer>> result = new ArrayList<>();
		Map<Integer, Integer> map = new HashMap<>(); // record value, count
		for (int i = 0; i < array.length; i++) {
			// check for target - array[i]
			if (map.containsKey(target - array[i])) {
				// check if it's duplicated val
				if (map.containsKey(array[i])) {
					// if repeated value,
					// only when it's half target and and it's the 2nd one, ok
					// !!! don't forget condition array[i] == target - array[i]!!, can't use /3
					if (array[i] == target - array[i] && map.get(array[i]) == 1) {
						result.add(Arrays.asList(array[i], array[i]));
					}
				} else {
					// not repeated value
					result.add(Arrays.asList(target - array[i], array[i]));
				}
			}
			// add to map
			Integer count = map.get(array[i]);
			if (count == null) {
				map.put(array[i], 1);
			} else {
				map.put(array[i], count + 1);
			}
		}
		return result;
	}

	public static void main(String[] args) {
		TwoSumAllPairII sol = new TwoSumAllPairII();
		int[] arr1 = new int[] { 3, 3, 3, 3, 3, 3 };
		int[] arr = new int[] { 2, 2, 2, 2, 4, 4, 4 };
		int target = 6;
		List<List<Integer>> result = sol.allPairs(arr, target);
		for (List<Integer> line : result) {
			for (Integer idx : line) {
				System.out.print(idx);
				System.out.print(" ");
			}
			System.out.println();
		}
	}
}
