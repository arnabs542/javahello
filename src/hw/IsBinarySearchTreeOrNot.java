package hw;

import utils.TreeNode;

/**
 * Recursion
Is Binary Search Tree Or Not
Determine if a given binary tree is binary search tree.

Assumptions

There are no duplicate keys in binary search tree.
You can assume the keys stored in the binary search tree can not be Integer.MIN_VALUE or Integer.MAX_VALUE.
Corner Cases

What if the binary tree is null? Return true in this case.

 * @author 
 *
 */

/**
 * public class TreeNode { public int key; public TreeNode left; public TreeNode
 * right; public TreeNode(int key) { this.key = key; } }
 */
public class IsBinarySearchTreeOrNot {
	public boolean isBST(TreeNode root) {
		return isBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
	}

	private boolean isBST(TreeNode root, int minValue, int maxValue) {
		if (root == null) {
			return true;
		}
		if (root.key < minValue || root.key > maxValue) {
			return false;
		}
		return isBST(root.left, minValue, Math.min(maxValue, root.key - 1))
				&& isBST(root.right, Math.max(minValue, root.key + 1), maxValue); // !!typo, maxValue not minValue
	}

	// only check left, right with direct parent is not enough!
	// the value range of current is affected by all ancestors.
	// check case 1.
	public boolean isBST_wrong(TreeNode root) {
		if (root == null) {
			return true;
		}
		if (root.left != null && root.left.key >= root.key) {
			return false;
		}
		if (root.right != null && root.right.key <= root.key) {
			return false;
		}
		return isBST(root.left) && isBST(root.right);
	}
}
