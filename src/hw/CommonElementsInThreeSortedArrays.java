package hw;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * Find all common elements in 3 sorted arrays.
 * 
 * Assumptions
 * 
 * The 3 given sorted arrays are not null
 * 
 * There could be duplicate elements in each of the arrays Examples
 * 
 * A = {1, 2, 2, 3}, B = {2, 2, 3, 5}, C = {2, 2, 4}, the common elements are
 * [2, 2]
 * 
 * 
 * 
 * Medium Array
 * 
 * @author
 *
 */
public class CommonElementsInThreeSortedArrays {

	public List<Integer> common_REF(int[] a, int[] b, int[] c) {
		// Assumptions: a, b, c are not null; all arrays are sorted.
		List<Integer> result = new ArrayList<>();
		int i = 0, j = 0, k = 0;
		while (i < a.length && j < b.length && k < c.length) {
			if (a[i] == b[j] && b[j] == c[k]) {
				result.add(a[i]);
				i++;
				j++;
				k++;
			} else if (a[i] <= b[j] && a[i] <= c[k]) { // a[i] is min
				i++;
			} else if (b[j] <= a[i] && b[j] <= c[k]) { // b[j] is min
				j++;
			} else { // c[k] is min
				k++;
			}
		}
		return result;
	}

	public List<Integer> common_self_ac(int[] a, int[] b, int[] c) {
		// Assumptions: a, b, c are not null; all arrays are sorted.
		List<Integer> result = new ArrayList<>();
		// int minLen = Math.min(a.length, b.length); // minLen not necessary with i,j,k
		// minLen = Math.min(minLen, c.length);
		// if (minLen == 0) {
		// return result;
		// }
		int i = 0, j = 0, k = 0;
		while (i < a.length && j < b.length && k < c.length) {
			if (a[i] == b[j] && b[j] == c[k]) {
				result.add(a[i]);
				i++;
				j++;
				k++;
			} else {
				int curMax = Math.max(a[i], b[j]);
				curMax = Math.max(c[k], curMax);
				if (a[i] < curMax) {
					i++;
				}
				if (b[j] < curMax) {
					j++;
				}
				if (c[k] < curMax) {
					k++;
				}
			}
		}
		return result;
	}
}
