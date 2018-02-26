package hw;

import utils.TreeNode;

/**
 * Find the target key K in the given binary search tree, return the node that
 * contains the key if K is found, otherwise return null.
 * 
 * Assumptions
 * 
 * There are no duplicate keys in the binary search tree
 * 
 * Easy Binary Tree Iteration RecursionF
 * 
 * @author
 *
 */
public class SearchInBinarySearchTree {
	// Recursive way
	public TreeNode search(TreeNode root, int key) {
		if (root == null) {
			return null;
		}
		if (root.key == key) {
			return root;
		} else if (root.key < key) {
			return search(root.right, key); // !!! don't twist the direction!!!
		} else {
			return search(root.left, key);
		}

	}

	// Iterative way
	public TreeNode search2(TreeNode root, int key) {
		TreeNode cur = root;
		while (cur != null) {
			if (cur.key == key) {
				return cur;
			} else if (cur.key < key) {
				cur = cur.right;
			} else {
				cur = cur.left;
			}
		}
		return null;

	}
}
