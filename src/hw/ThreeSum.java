package hw;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Determine if there exists three elements in a given array that sum to the
 * given target number. Return all the triple of values that sums to target.
 * 
 * Assumptions
 * 
 * The given array is not null and has length of at least 3 No duplicate triples
 * should be returned, order of the values in the tuple does not matter Examples
 * 
 * A = {1, 2, 2, 3, 2, 4}, target = 8, return [[1, 3, 4], [2, 2, 4]]
 * 
 * 
 * 
 * Medium Array Sort
 * 
 * @author
 *
 */
public class ThreeSum {
	public List<List<Integer>> allTriples(int[] array, int target) {
		// Assumptions: array is not null, array.length >= 3.
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		Arrays.sort(array);
		for (int i = 0; i < array.length - 2; i++) {  // --> !!!length - 1
			// Our goal is to find i < j < k, such that
			// array[i] + [j] + [k] == target,
			// To make sure there is no duplicate tuple,
			// ignore all the duplicate possible i.
			// e.g, if have 2, 2, 2, only the first 2 will be selected as i.
			if (i > 0 && array[i] == array[i - 1]) {
				continue;
			}
			int left = i + 1;
			int right = array.length - 1;
			while (left < right) {
				int tmp = array[left] + array[right];
				if (tmp + array[i] == target) {
					result.add(Arrays.asList(array[i], array[left], array[right]));
					left++;
					// ignore all possible duplicate j as well.
					while (left < right && array[left] == array[left - 1]) {
						left++;
					}
				} else if (tmp + array[i] < target) {
					left++;
				} else {
					right--;
				}
			}
		}
		return result;
	}
}
