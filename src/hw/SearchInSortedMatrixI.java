package hw;

/**
 * Data Structure
Search In Sorted Matrix I
Given a 2D matrix that contains integers only, which each row is sorted in an ascending order. The first element of next row is larger than (or equal to) the last element of previous row.

Given a target number, returning the position that the target locates within the matrix. If the target number does not exist in the matrix, return {-1, -1}.

Assumptions:

The given matrix is not null, and has size of N * M, where N >= 0 and M >= 0.
Examples:

matrix = { {1, 2, 3}, {4, 5, 7}, {8, 9, 10} }

target = 7, return {1, 2}

target = 6, return {-1, -1} to represent the target number does not exist in the matrix.

 * @author 
 *
 */

public class SearchInSortedMatrixI {
	  public int[] search(int[][] matrix, int target) {
		    // Write your solution here.
		    if (matrix.length == 0 || matrix[0].length == 0){
		      return new int[]{-1, -1};
		    }
		    int rows = matrix.length, cols = matrix[0].length;
		    int msize = rows * cols;  // duplicated -1 here !!!!
		    return this.helper(matrix, target, 0, msize - 1);
		    
		  }
		  
		  private int[] helper(int[][] mat, int target, int left, int right){
		    if (left > right){
		      return new int[]{-1, -1};
		    }
		    int mid = left + (right - left) / 2;
		    int[] xy = this.toCoordinate(mat, mid);
		    int x = xy[0], y = xy[1];
		    if (mat[x][y] == target)
		      return xy;
		    else if(mat[x][y] < target)  
		      return this.helper(mat, target, mid + 1, right);
		    else
		      return this.helper(mat, target, left, mid - 1);
		  }
		  
		  private int[] toCoordinate(int[][] mat, int mid){
		    // 5(0 -- 4)(5 -- 9)(10 ...)
		    // 7 -- > (1, 2)
		    int x = mid / mat[0].length;
		    int y = mid % mat[0].length;
		    return new int[]{x, y};
		  }
}
