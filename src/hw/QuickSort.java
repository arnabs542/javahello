package hw;

/**
 * 
Recursion
Quick Sort
Given an array of integers, sort the elements in the array in ascending order. The quick sort algorithm should be used to solve this problem.

Examples

{1} is sorted to {1}
{1, 2, 3} is sorted to {1, 2, 3}
{3, 2, 1} is sorted to {1, 2, 3}
{4, 2, -3, 6, 1} is sorted to {-3, 1, 2, 4, 6}
Corner Cases

What if the given array is null? In this case, we do not need to do anything.
What if the given array is of length zero? In this case, we do not need to do anything.
 *
 */

import java.util.Random;

public class QuickSort {
	  public int[] quickSort(int[] arr) {
	    if (arr == null) {
	      return arr;
	    }
	    quickSort(arr, 0, arr.length - 1);
	    return arr;
	  }
	  
	  private void quickSort(int[] arr, int left, int right) {
	    if (left >= right) {
	      return;
	    }
	    int pivotIdx = partition(arr, left, right);
	    quickSort(arr, left, pivotIdx - 1);
	    quickSort(arr, pivotIdx + 1, right);
	  }
	  
	  private int partition(int[] arr, int left, int right) {
	    int pivotIdx = getPivotIdx(left, right);
	    swap(arr, pivotIdx, right);
	    int i = left, j = right - 1;
	    int target = arr[right];
	    while (i <= j) {
	      if (arr[i] < target) {
	        i++;
	      } else if (arr[j] >= target) {
	        j--;
	      } else {
	        swap(arr, i, j);
	        i++;
	        j--;
	      }
	    }
	    swap(arr, i, right);
	    return i;
	  }

	  private int getPivotIdx(int left, int right) {
		    int res = left + (int)(Math.random() * (right - left + 1));
		    return res;
		  }
	  
	  private void swap(int[] arr, int i, int j) {
	    int tmp = arr[i];
	    arr[i] = arr[j];
	    arr[j] = tmp;
	  }
	}

class QuickSort1 {

	public int[] quickSort(int[] arr) {
		// write your solution here
		if (arr == null || arr.length == 0) {
			return arr;
		}
		return this.sort(arr, 0, arr.length - 1);
	}

	private int[] sort(int[] arr, int left, int right) {
		if (left >= right)
			return arr;
		if (right - left >= 3)
			this.init_pivot(arr, left, right);
		int idx = this.partition(arr, left, right);
		this.sort(arr, left, idx - 1);
		this.sort(arr, idx + 1, right);
		return arr;
	}

	private void init_pivot(int[] arr, int left, int right) {
		int idx = new Random().nextInt(right - left + 1) + left;
		this.swap(arr, idx, right);
	}

	private int partition(int[] arr, int left, int right) {
		int pivot = arr[right];
		int i = left, j = right - 1;
		while (i <= j) {
			while (i <= j && arr[i] < pivot)
				i += 1;
			while (i <= j && arr[j] > pivot)
				j -= 1;
			if (i >= j) {
				this.swap(arr, i, right);
				break;
			} else {
				this.swap(arr, i, j);
				i += 1;
				j -= 1;
			}
		}
		return i;
	}

	private void swap(int[] arr, int a, int b) {
		int tmp = arr[a];
		arr[a] = arr[b];
		arr[b] = tmp;
	}

}
