package hw;

import utils.TreeNode;

/**
 * 
 * In a binary search tree, find the node containing the largest number smaller
 * than the given target number.
 * 
 * If there is no such number, return INT_MIN.
 * 
 * Assumptions:
 * 
 * The given root is not null. There are no duplicate keys in the binary search
 * tree. Examples
 * 
 * 5
 * 
 * / \
 * 
 * 2 11
 * 
 * / \
 * 
 * 6 14
 * 
 * largest number smaller than 1 is Integer.MIN_VALUE(Java) or INT_MIN(c++)
 * 
 * largest number smaller than 10 is 6
 * 
 * largest number smaller than 6 is 5
 * 
 * How is the binary tree represented?
 * 
 * We use the level order traversal sequence with a special symbol "#" denoting
 * the null node.
 * 
 * For Example:
 * 
 * The sequence [1, 2, 3, #, #, 4] represents the following binary tree:
 * 
 * 1
 * 
 * / \
 * 
 * 2 3
 * 
 * /
 * 
 * 4
 * 
 * Medium Binary Search Tree
 * 
 * @author
 *
 */

public class LargestNumberSmallerInBinarySearchTree {
	public int largestSmaller(TreeNode root, int target) {
		// Assumptions: The given root is not null. There are no duplicate keys in the
		// binary search tree.
		int result = Integer.MIN_VALUE;
		while (root != null) {
			if (root.key >= target) {
				root = root.left;
			} else {
				if (result == Integer.MIN_VALUE || target - root.key < target - result) {
					result = root.key;
				}
				root = root.right;
			}
		}
		return result;
	}
	
	public int largestSmaller_REF(TreeNode root, int target) {
		// Assumptions: The given root is not null. There are no duplicate keys in the
		// binary search tree.
		int result = Integer.MIN_VALUE;
		while (root != null) {
			if (root.key >= target) {
				root = root.left;
			} else {
				// the candidates are all the nodes on the path of 
				// searching for target, which is smaller than target.
				// and note that, the later searched nodes has larger 
				// value than the earlier searched ones.
//				if (result == Integer.MIN_VALUE || target - root.key < target - result) {
//					result = root.key;
//				}
				// --> above if can be removed because of the ref idea "note that, the later searched nodes has larger 
				// value than the earlier searched ones." If once the left path goes to the node with bigger than target value,
				// the above root.key >= condition is true and result will not be updated.
				// inside this branch, root.key is guaranteed to be smaller, and will grow bigger (on right path)
				result = root.key;
				root = root.right;
			}
		}
		return result;
	}
}
