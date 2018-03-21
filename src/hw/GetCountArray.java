package hw;

import java.lang.reflect.Array;

import utils.ConsolePrinter;

/**
 * 
 * Get Count Array
 * 
 * 
 * 
 * 
 * @author 
 *
 */
public class GetCountArray {
	public int[] countArray(int[] array) {
		// The indexArray contains the indices in the original array,
		// and it will be sorted by the corresponding number in the 
		// original array.
		// The countArray is the actual return array.
		// The helper array is to help the merge sort.
		int[] indexArr = initialIndexArray(array);
		int[] countArr = new int[array.length];
		int[] helper = new int[array.length];
		mergeSort(array, indexArr, countArr, helper, 0, array.length - 1);
		return countArr;
	}
	
	// The indices are just 0 - (array.length - 1)
	private int[] initialIndexArray(int[] arr) {
		int[] indices = new int[arr.length];
		for (int i = 0 ; i < arr.length; i++ ) {
			indices[i] = i;
		}
		return indices;
	}
	
	private void mergeSort(int[] arr, int[] indexArr, int[] countArr, int[] helper, int left, int right) {
		if (left >= right) {
			if (left >= right) {
				return;
			}
		}
		int mid = left + (right - left) / 2;
		mergeSort(arr, indexArr, countArr, helper, left, mid);
		mergeSort(arr, indexArr, countArr, helper, mid + 1, right);
		merge(arr, indexArr, countArr, helper, left, mid, right);
	}
	
	private void merge(int[] arr, int[] indexArr, int[] countArr, int[] helper, int left, int mid, int right) {
		System.out.println("mergeing: " + left + ", " + mid + ", " + right);
		copyArray(indexArr, helper, left, right);
		int l = left;
		int r = mid + 1;
		int cur = left;
		while (l <= mid) {
			// when sorting the indexArr, we use the corresponding value in the original array.
			if (r > right || arr[helper[l]] <= arr[helper[r]]) {
				countArr[helper[l]] += (r - mid - 1);
				indexArr[cur++] = helper[l++];
			} else {
				indexArr[cur++] = helper[r++];
			}
		}
	}
	
	private void copyArray(int[] indexArr, int[] helper, int left, int right) {
		for (int i = left; i <= right; i++) {
			helper[i] = indexArr[i];
		}
	}
	
	public static void main(String[] args) {
		int[] arr = new int[] {3, 2, 1};      // expect [2, 1, 0]
		GetCountArray sol = new GetCountArray();
		int[] result = sol.countArray(arr); 
		ConsolePrinter.printIntArray(result);
	}
}
