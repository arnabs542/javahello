package hw;

import utils.TreeNode;

/**
 * Recursion
Symmetric Binary Tree
Check if a given binary tree is symmetric.

Examples

        5

      /    \

    3        3

  /   \    /   \

1      4  4      1

is symmetric.

        5

      /    \

    3        3

  /   \    /   \

1      4  1      4

is not symmetric.

Corner Cases

What if the binary tree is null? Return true in this case.
How is the binary tree represented?

We use the level order traversal sequence with a special symbol "#" denoting the null node.

For Example:

The sequence [1, 2, 3, #, #, 4] represents the following binary tree:

    1

  /   \

 2     3

      /

    4


 * @author 
 *
 */

/**
 * public class TreeNode { public int key; public TreeNode left; public TreeNode
 * right; public TreeNode(int key) { this.key = key; } }
 */
public class SymmetricBinaryTree {
	public boolean isSymmetric(TreeNode root) {
		if (root == null) {
			return true;
		}
		return isSymmetric(root.left, root.right);
	}

	private boolean isSymmetric(TreeNode one, TreeNode two) {
		if (one == null && two == null) {
			return true;
		} else if (one == null || two == null) {
			return false;
		} else if (one.key != two.key) {
			return false;
		} else {
			return isSymmetric(one.left, two.right) && isSymmetric(one.right, two.left);
		}
	}

	public boolean isSymmetric_wrong(TreeNode root) {
		if (root == null) {
			return true;
		}
		if (root.left == null && root.right == null) {
			return true;
		} else if (root.left == null || root.right == null) {
			return false;
		} else if (root.left.key != root.right.key) {
			return false;
		} else {
			return isSymmetric(root.left) && isSymmetric(root.right);
		}
	}
}
