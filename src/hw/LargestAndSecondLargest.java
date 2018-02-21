package hw;

import java.util.ArrayList;
import java.util.List;

/**
 * Largest And Second Largest
 * 
 * Use the least number of comparisons to get the largest and 2nd largest number
 * in the given integer array. Return the largest number and 2nd largest number.
 * 
 * Assumptions
 * 
 * The given array is not null and has length of at least 2 Examples
 * 
 * {2, 1, 5, 4, 3}, the largest number is 5 and 2nd largest number is 4.
 * 
 * Medium Array
 * 
 * @author
 *
 */
public class LargestAndSecondLargest {
	public int[] largestAndSecond(int[] array) {
		// Assumptions: array is not null, array.length >= 2.
		
		// Convert the original array to Element array.
		// So that the comparison process will also store the compared values
		// for each element.
		Element[] helper = convert(array);
		
		// largerLength is the left partition's length containing the 
		// larger vlaues after each round of comparison.
		// For each round, the comparison is still doing for each of 
		// the indices pairs (i, largerLength - 1 - i).
		// So that the larger lements are always on the left side,
		/// and the largerLength will be cut in half each round.
		// larger Length is obviously initiated by the array's length.
		int largerLength = array.length;
		// We will terminate when there is only one element left on the
		// larger partition, and it has to be the largers value.
		// the second largest value is the largest value in the 1st large
		// element's compared values.
		while (largerLength > 1) {
			compareAndSwap(helper, largerLength);
			largerLength = (largerLength + 1) / 2; // make sure the odd length will cover mid element
		}
		return new int[] {helper[0].value, largest(helper[0].comparedValues)};
	}
	
	private Element[] convert(int[] arr) {
		Element[] helper = new Element[arr.length];
		for (int i = 0; i < arr.length; i++) {
			helper[i] = new Element(arr[i]);
		}
		return helper;
	}
	
	// Compare each of the indices pairs (i, largerLength - 1 - i),
	// swap the larger values to the left side if necessary,
	// and put the smaller value into the larger value's compare values list.
	private void compareAndSwap(Element[] helper, int largerLength) {
		for (int i = 0; i < largerLength / 2; i++) {
			if (helper[i].value < helper[largerLength - 1 - i].value) {
				swap(helper, i, largerLength - 1 - i);
			}
			// don't forget to update the compared value!!!
			// now it's after swap, [i] is the bigger one and [ll-1-i] is the smaller one
			helper[i].comparedValues.add(helper[largerLength - 1 - i].value);
		}
	}
	
	private int largest(List<Integer> list) {
		// This is guaranteed to be O(1).
		int max = list.get(0);
		// Using Iterator is guaranteed to be O(1) traversing each of the 
		// elements in the list.
		for (int num : list) {
			max = Math.max(max, num);
		}
		return max;
	}
	
	private void swap(Element[] arr, int a, int b) {
		Element tmp = arr[a];
		arr[a] = arr[b];
		arr[b] = tmp;
	}
	
	// The Element class will be used to store the original value
	// in the array and all the values compared to it.
	static class Element {
		int value;
		List<Integer> comparedValues;
		
		Element(int value) {
			this.value = value;
			this.comparedValues = new ArrayList<>();
		}
	}
}
