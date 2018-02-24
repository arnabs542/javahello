package hw;

import utils.TreeNode;

/**
 * Given a binary tree where all the right nodes are leaf nodes, flip it upside
 * down and turn it into a tree with left leaf nodes as the root.
 * 
 * Examples
 * 
 * 1
 * 
 * / \
 * 
 * 2 5
 * 
 * / \
 * 
 * 3 4
 * 
 * is reversed to
 * 
 * 3
 * 
 * / \
 * 
 * 2 4
 * 
 * / \
 * 
 * 1 5
 * 
 * How is the binary tree represented?
 * 
 * We use the pre order traversal sequence with a special symbol "#" denoting
 * the null node.
 * 
 * For Example:
 * 
 * The sequence [1, 2, #, 3, 4, #, #, #] represents the following binary tree:
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
 * Medium 
 * Binary Tree 
 * Iterative, Linked List, Recursion
 * 
 * @author
 *
 */
public class ReverseBinaryTreeUpsideDown {
	public TreeNode reverse(TreeNode root) {
		// Method 1: Recursion
		if (root == null || root.left == null) { // note root.left!!!
			return root; // return root, not null !!!
		}
		TreeNode newRoot = reverse(root.left);
		root.left.left = root;
		root.left.right = root.right;
		
		root.left = null;  // don't forget this part
		root.right = null;
		
		return newRoot;
	}
	
	
	public TreeNode reverseI(TreeNode root) {
		// Method 2: Iterative
		if (root == null) {
			return null;
		}
		TreeNode node = root;
		while (node.left != null) {
			TreeNode left = node.left;
			left.left = node;
			left.right = node.right;
			node = node.left;
		}
		root.left = null;
		root.right = null;
		return node;
	}
	
	public TreeNode reverseI_REF(TreeNode root) {
		// Method 2: Iterative
		TreeNode prev = null;
		TreeNode prevRight = null;
		while (root != null) {
			TreeNode next = root.left;
			TreeNode right = root.right;
			root.right = prevRight;
			root.left = prev;
			prevRight = right;
			prev = root;
			root = next;
		}
		return prev;
	}
}
