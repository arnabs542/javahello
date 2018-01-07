package hw;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * Data Structure Common Numbers Of Two Sorted Arrays Find all numbers that
 * appear in both of two sorted arrays (the two arrays are all sorted in
 * ascending order).
 * 
 * Assumptions
 * 
 * In each of the two sorted arrays, there could be duplicate numbers. Both two
 * arrays are not null. Examples
 * 
 * A = {1, 1, 2, 2, 3}, B = {1, 1, 2, 5, 6}, common numbers are [1, 1, 2]
 * 
 * @author
 *
 */
public class CommonNumbersOfTwoSortedArray {
	// Assumptions: there could be duplicated elements in the given arrays.
	// Method 1: two pointers.
	public List<Integer> common(List<Integer> a, List<Integer> b) {
		List<Integer> res = new ArrayList<Integer>();
		int i = 0, j = 0;
		while (i < a.size() && j < b.size()) {
			if (a.get(i) == b.get(j)) {
				res.add(a.get(i));
				i++;
				j++;
			} else if (a.get(i) < b.get(j)) {
				i++;
			} else {
				j++;
			}
		}
		return res;
	}
	
	// Method 2: use HashMap.
	public List<Integer> commonII(List<Integer> a, List<Integer> b) { 
		List<Integer> common = new ArrayList<Integer>();
		HashMap<Integer, Integer> countA = new HashMap<>();
		for (int num : a) {
			Integer c = countA.get(num);
			if (c == null) {
				countA.put(num, 1);
			} else {
				countA.put(num, c + 1);
			}
		}
		HashMap<Integer, Integer> countB = new HashMap<>();
		for (int num : b) {
			Integer c = countB.get(num);
			if (c == null) {
				countB.put(num, 1);
			} else {
				countB.put(num, c + 1);
			}
		}		
		
		for (Map.Entry<Integer, Integer> entry : countA.entrySet()) {
			Integer ctB = countB.get(entry.getKey());
			if (ctB != null) {
				int appear = Math.min(entry.getValue(), ctB);
				for (int i = 0; i < appear; i++) {
					common.add(entry.getKey());
				}
			}
		}
		return common;
		
	}
}
