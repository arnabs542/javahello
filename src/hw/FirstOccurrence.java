package hw;

/**
 * Data Structure
First Occurrence
Given a target integer T and an integer array A sorted in ascending order, find the index of the first occurrence of T in A or return -1 if there is no such index.

Assumptions

There can be duplicate elements in the array.
Examples

A = {1, 2, 3}, T = 2, return 1
A = {1, 2, 3}, T = 4, return -1
A = {1, 2, 2, 2, 3}, T = 2, return 1
Corner Cases

What if A is null or A of zero length? We should return -1 in this case.
 * @author 
 *
 */
public class FirstOccurrence {
	
	  public int firstOccur(int[] arr, int target) {
		    if (arr == null || arr.length == 0) {
		      return -1;
		    }
		    int left = 0, right = arr.length - 1;
		    while ( left + 1 < right) {
		      int mid = left + (right - left) / 2;
		      if (arr[mid] == target) {
		        right = mid;
		      } else if (arr[mid] < target) {
		        left = mid + 1;
		      } else {
		        right = mid - 1;
		      }
		    }
		    if (arr[left] == target) {
		      return left;
		    } else if (arr[right] == target) {
		      return right;
		    }
		    return -1;
		  }
	  public int firstOccur1(int[] arr, int target) {
		    if (arr == null || arr.length == 0)
		      return -1;
		    return this.searchFirst(arr, 0, arr.length - 1, target);  
		  }
		  
		  private int searchFirst(int[] arr, int left, int right, int target){
		    if (left > right)
		      return -1;
		    int mid = left + (right - left) / 2;
		    if (arr[mid] == target){
		      if (mid == left)
		        return mid;
		      else
		        return this.searchFirst(arr, left, mid, target);
		    }
		    else if (arr[mid] > target)
		      return this.searchFirst(arr, left, mid - 1, target);
		    else
		      return this.searchFirst(arr, mid + 1, right, target);
		  }
}
