package hw;
/**
 * 
 * Data Structure
Closest In Sorted Array
Given a target integer T and an integer array A sorted in ascending order, find the index i in A such that A[i] is closest to T.

Assumptions

There can be duplicate elements in the array, and we can return any of the indices with same value.
Examples

A = {1, 2, 3}, T = 2, return 1
A = {1, 4, 6}, T = 3, return 1
A = {1, 4, 6}, T = 5, return 1 or 2
A = {1, 3, 3, 4}, T = 2, return 0 or 1 or 2
Corner Cases

What if A is null or A is of zero length? We should return -1 in this case.
 * 
 * @author 
 *
 */

import java.lang.Math;
public class ClosestInSortedArray {
	
	 public int closest(int[] arr, int target) {
		    if (arr == null || arr.length == 0) {
		      return -1;
		    }
		    int left = 0, right = arr.length - 1;
		    while (left + 1 < right) {
		      int mid = left + (right - left) / 2;
		      if (arr[mid] == target) {
		        return mid;
		      } else if (arr[mid] < target) {
		        left = mid;
		      } else {
		        right = mid;
		      }
		    }
		    if (Math.abs(arr[left] - target) < Math.abs(arr[right] - target)) {
		      return left;
		    } else {
		      return right;
		    }
		  }	
	
  public int closest1(int[] arr, int target) {
	    if (arr == null || arr.length == 0)
	      return -1;
	    return this.searchClose(arr, 0, arr.length - 1, target, 0);  
	  }
	  
  private int searchClose(int[] arr, int left, int right, int target, int idx){
    if (left > right)
      return idx;
    int mid = left + (right - left) / 2;
    if (arr[mid] == target)
      return mid;
    else if (arr[mid] < target){
      if (Math.abs(arr[mid] - target) < Math.abs(arr[idx] - target)){
        idx = mid;
      }
      return this.searchClose(arr, mid + 1, right, target, idx);
    }else{
      if (Math.abs(arr[mid] - target) < Math.abs(arr[idx] - target)){
        idx = mid;
      }
      return this.searchClose(arr, left, mid - 1, target, idx);      
    }  
      
  }
}
