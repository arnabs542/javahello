package hw;
/**
 * Recursion
N Queens
Get all valid ways of putting N Queens on an N * N chessboard so that no two Queens threaten each other.

Assumptions

N > 0

Return

	A list of ways of putting the N Queens
	Each way is represented by a list of the Queen's y index for x indices of 0 to (N - 1)
	
Example

N = 4, there are two ways of putting 4 queens:

[1, 3, 0, 2] --> 
the Queen on the first row is at y index 1, 
the Queen on the second row is at y index 3, 
the Queen on the third row is at y index 0 and 
the Queen on the fourth row is at y index 2.

[2, 0, 3, 1] --> 
the Queen on the first row is at y index 2, 
the Queen on the second row is at y index 0, 
the Queen on the third row is at y index 3 and 
the Queen on the fourth row is at y index 1.

 * @author 
 *
 */
public class NQueens {
	// Method 1: validate the queen position in O(n) each time.
	public List<List<Integer>> nqueens(int n) {
		// Assumptions: n > 0.
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		// cur will be a list of size n, and cur[i] is the column number
		// where the queen on row i positioned
	}
}
