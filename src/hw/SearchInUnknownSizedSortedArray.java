package hw;
/**
 * 
 * Data Structure
Search In Unknown Sized Sorted Array
Given a integer dictionary A of unknown size, where the numbers in the dictionary are sorted in ascending order, determine if a given target integer T is in the dictionary. Return the index of T in A, return -1 if T is not in A.

Assumptions

dictionary A is not null
dictionary.get(i) will return null(Java)/INT_MIN(C++) if index i is out of bounds
Examples

A = {1, 2, 5, 9, ......}, T = 5, return 2
A = {1, 2, 5, 9, 12, ......}, T = 7, return -1
 * @author 
 *
 */
/*
*  interface Dictionary {
*    public Integer get(int index);
*  }
*/
import utils.Dictionary;

public class SearchInUnknownSizedSortedArray {


	// You do not need to implement the Dictionary interface.
	// You can use it directly, the implementation is provided when testing your solution.
	  public int search(Dictionary dict, int target) {
	    // Write your solution here
	    if (dict == null)
	      return -1;
	    return this.helper(dict, target, 0, 1);  
	  }
	  
	  private int helper(Dictionary dict, int target, int left, int right){
	    if (left > right)
	      return -1;
	    // int rval = dict.get(right);
	    if ( dict.get(right) != null && dict.get(right) < target){
	      return this.helper(dict, target, right, right*2);
	    }
	    int mid = left + (right - left) / 2;
	    // int tmp = dict.get(mid); // java does not accept this when return null
	    if (dict.get(mid) == null){
	      return this.helper(dict, target, left, mid - 1);
	    }else{
	      int tmp = dict.get(mid);
	      if (tmp == target){
	        return mid;
	      }else if (tmp > target){  // !!! not <
	        return this.helper(dict, target, left, mid - 1);
	      }else{
	        return this.helper(dict, target, mid + 1, right);
	      }
	    } 
	  }
	}


