package hw;

import utils.ConsolePrinter;

/**
 * 
 * Recursion Merge Sort Given an array of integers, sort the elements in the
 * array in ascending order. The merge sort algorithm should be used to solve
 * this problem.
 * 
 * Examples
 * 
 * {1} is sorted to {1} {1, 2, 3} is sorted to {1, 2, 3} {3, 2, 1} is sorted to
 * {1, 2, 3} {4, 2, -3, 6, 1} is sorted to {-3, 1, 2, 4, 6} Corner Cases
 * 
 * What if the given array is null? In this case, we do not need to do anything.
 * What if the given array is of length zero? In this case, we do not need to do
 * anything.
 *
 */


public class MergeSort {
	  public int[] mergeSort(int[] arr) {
	    if (arr == null || arr.length == 0) {
	      return arr;
	    }
	    int[] store = new int[arr.length];
	    helper(arr, 0, arr.length - 1, store);
	    return arr;
	  }
	  
	  private void helper(int[] arr, int left, int right, int[] store) {
	    if (left >= right) {
	      return;
	    }
	    int mid = left + (right - left) / 2;
	    helper(arr, left, mid, store);
	    helper(arr, mid + 1, right, store);
	    merge(arr, left, mid, right, store);
	  }
	  
	  private void merge(int[] arr, int left, int mid, int right, int[] store) {
	    for (int i = left; i <= right; i++) {
	      store[i] = arr[i];
	    }
	    int a = left, b = mid + 1;
	    for (int i = left; i <= right; i++) {
	      if (a <= mid && b <= right) {
	        if (store[a] <= store[b]) {
	          arr[i] = store[a++];  
	        } else {
	          arr[i] = store[b++];
	        }
	      } else if (a <= mid) {
	        arr[i] = store[a++];
	      }
	    }
	  }
	}


class Solution1 {
	  public int[] mergeSort(int[] arr) {
	    if (arr == null || arr.length == 0){
	      return arr;  // can't return [], wrong syntax
	    }
	    int[] helper = new int[arr.length];
	    mSort(arr, helper, 0, arr.length - 1);
	    return arr;
	  }
	  
	  private void mSort(int[] arr, int[] helper, int left, int right){
	    if (left >= right){
	      return;
	    }
	    int mid = left + (right - left) / 2;
	    mSort(arr, helper, left, mid);
	    mSort(arr, helper, mid + 1, right);
	    merge(arr, helper, left, mid, right);
	  }
	  
	  private void merge(int[] arr, int[] helper, int left, int mid, int right){
	    int i = left, j = mid + 1;
	    // copy to helper for merge purpose
	    for (int k = left; k <= right; k++){
	      helper[k] = arr[k];
	    }
	    while (i <= mid && j <= right){
	      if (helper[i] <= helper[j]){ // typo, should be j, not right here
	        arr[left++] = helper[i++];
	      } else {
	        arr[left++] = helper[j++];
	      }
	    }
	    while (i <= mid){
	      arr[left++] = helper[i++];
	    }
	  }
	}


class MergeSort1 {
	public int[] mergeSort(int[] arr) {

		if (arr == null || arr.length == 0) {
			return arr;
		}

		return this.msort_helper(arr, 0, arr.length - 1);
	}

	private int[] msort_helper(int[] arr, int left, int right) {
		// recursion returning condition
		if (left >= right) {
			return new int[] { arr[left] };
		}

		int mid = left + (right - left) / 2;
		// sort left half and right half
		int[] left_half = this.msort_helper(arr, left, mid);
		int[] right_half = this.msort_helper(arr, mid + 1, right);
		// return merged result of the two halves
		return this.merge_helper(left_half, right_half);
	}

	private int[] merge_helper(int[] left_arr, int[] right_arr) {
		int[] res = new int[left_arr.length + right_arr.length];
		int i = 0, j = 0;
		while (i < left_arr.length && j < right_arr.length) {
			if (left_arr[i] <= right_arr[j]) {
				res[i + j] = left_arr[i];
				i += 1;
			} else {
				res[i + j] = right_arr[j];
				j += 1;
			}
		}
		while (i < left_arr.length) {
			res[i + j] = left_arr[i];
			i += 1;
		}
		while (j < right_arr.length) {
			res[i + j] = right_arr[j];
			j += 1;
		}
		return res;

	}
}
