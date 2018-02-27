package hw;

import utils.TreeNode;

/**
 * 
 * In a binary search tree, find the node containing the closest number to the
 * given target number.
 * 
 * Assumptions:
 * 
 * The given root is not null. There are no duplicate keys in the binary search
 * tree. Examples:
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
 * closest number to 4 is 5
 * 
 * closest number to 10 is 11
 * 
 * closest number to 6 is 6
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
public class ClosestNumberInBinarySearchTree {
	public int closest(TreeNode root, int target) {
		// Assumptions: root is not null, no duplicated keys in the tree nodes.
		int result = root.key;
		// result (closest number) has to be on the path of finding the target value in the tree.
		while (root != null) {
			if (root.key == target) {
				return target;
			} else {
				if (Math.abs(target - root.key) < Math.abs(target - result)) {
					result = root.key;
				}
				if (root.key < target) {
					root = root.right;
				} else {
					root = root.left;
				}
			}
		}
		return result;
	}
}
