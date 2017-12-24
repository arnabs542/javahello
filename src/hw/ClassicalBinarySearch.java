package hw;
/**
 * 
 * @author 
 * 
 * Data Structure
Classical Binary Search
Given a target integer T and an integer array A sorted in ascending order, find the index i such that A[i] == T or return -1 if there is no such index.

Assumptions

There can be duplicate elements in the array, and you can return any of the indices i such that A[i] == T.
Examples

A = {1, 2, 3, 4, 5}, T = 3, return 2
A = {1, 2, 3, 4, 5}, T = 6, return -1
A = {1, 2, 2, 2, 3, 4}, T = 2, return 1 or 2 or 3
Corner Cases

What if A is null or A is of zero length? We should return -1 in this case.
 *
 */
public class ClassicalBinarySearch {
	
	  public int binarySearch(int[] arr, int target) {
		    if (arr == null || arr.length == 0){
		      return -1;
		    }
		    int left = 0, right = arr.length - 1;
		    while (left <= right) {
		      int mid = left + (right - left) / 2;
		      if (arr[mid] == target) {
		        return mid;
		      } else if (arr[mid] < target) {
		        left = mid + 1;
		      } else {
		        right = mid - 1;
		      }
		    }
		    return -1;
		  }

	  public int binarySearch1(int[] arr, int target) {
		    // Write your solution here.
		    if(arr == null || arr.length == 0){
		      return -1;
		    }
		    return this.search(arr, 0, arr.length-1, target);
		  }
		  
		  private int search(int[] arr, int left, int right, int target){
		    if(left > right)
		      return -1;
		    int mid = left + (right-left)/2;
		    if (arr[mid] == target)
		      return mid;
		    else if (arr[mid] > target)
		      return this.search(arr, left, mid - 1, target);
		    else
		      return this.search(arr, mid + 1, right, target);
		  }	
	
}
