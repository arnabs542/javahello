package hw;

/**
 * Data Structure
Last Occurrence
Given a target integer T and an integer array A sorted in ascending order, find the index of the last occurrence of T in A or return -1 if there is no such index.

Assumptions

There can be duplicate elements in the array.

Examples

A = {1, 2, 3}, T = 2, return 1
A = {1, 2, 3}, T = 4, return -1
A = {1, 2, 2, 2, 3}, T = 2, return 3
Corner Cases

What if A is null or A is array of zero length? We should return -1 in this case.

 * @author 
 *
 */
public class LastOccurrence {
  public int lastOccur(int[] arr, int target) {
	    if (arr == null || arr.length == 0)
	      return -1;
	    return this.searchLast(arr, 0, arr.length - 1, target);  
	  }
	  
  private int searchLast(int[] arr, int left, int right, int target){
    if (left > right)
      return -1;
    int mid = left + (right - left) / 2;
    if (arr[mid] == target){
      if (mid == left)
        if (arr[right] > target)
          return mid;
        else
          return right;
      else
        return this.searchLast(arr, mid, right, target);
    }
    else if (arr[mid] > target)
      return this.searchLast(arr, left, mid - 1, target);
    else
      return this.searchLast(arr, mid + 1, right, target);
  }
}
