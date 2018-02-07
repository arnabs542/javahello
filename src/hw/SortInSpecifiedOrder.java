package hw;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;

/**
 * Sort In Specified Order
 * 
 * Description
 * 
 * Given two integer arrays A1 and A2, sort A1 in such a way that the relative
 * order among the elements will be same as those are in A2.
 * 
 * For the elements that are not in A2, append them in the right end of the A1
 * in an ascending order.
 * 
 * Assumptions:
 * 
 * A1 and A2 are both not null. There are no duplicate elements in A2. Examples:
 * 
 * A1 = {2, 1, 2, 5, 7, 1, 9, 3}, A2 = {2, 1, 3}, A1 is sorted to {2, 2, 1, 1,
 * 3, 5, 7, 9}
 * 
 * @author
 *
 */

public class SortInSpecifiedOrder {
	static class MyComparator implements Comparator<Integer> {
		private HashMap<Integer, Integer> valPos;
		
		// Assumption: arr is not null and not empty
		public MyComparator(int[] arr) {
			valPos = new HashMap<Integer, Integer>();
			for (int i = 0; i < arr.length; i++) {
				valPos.put(arr[i], i);
			}
		}
		
		@Override
		public int compare(Integer o1, Integer o2) {
			Integer pos1 = valPos.get(o1);
			Integer pos2 = valPos.get(o2);
			if (pos1 != null && pos2 != null) {
				return pos1.compareTo(pos2);
			} else if (pos1 == null && pos2 == null) {
				return o1.compareTo(o2);
			}
			// return o1 != null ? -1 : 1; // should be pos1 here
			return pos1 != null ? -1 : 1;
		}

	}

	// Assumptions: A1, A2 are not null and A2 can not be empty
	public int[] sortSpecial(int[] A1, int[] A2) {
		Integer[] arr1 = toIntegerArray(A1);
		Arrays.sort(arr1, new MyComparator(A2));
		toIntArray(arr1, A1);
		return A1;
	}
	
	private Integer[] toIntegerArray(int[] arr) {
		Integer[] result = new Integer[arr.length];
		for (int i = 0; i < arr.length; i++) {
			result[i] = arr[i];
		}
		return result;
	}
	
	private void toIntArray(Integer[] arr, int[] result) {
		for (int i = 0; i < arr.length; i++) {
			result[i] = arr[i];
		}
	}
}
