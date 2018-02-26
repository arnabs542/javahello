package hw;

import utils.TreeNode;

/**
 * 
 * Delete In Binary Search Tree
 * 
 * Delete the target key K in the given binary search tree if the binary search
 * tree contains K. Return the root of the binary search tree.
 * 
 * Find your own way to delete the node from the binary search tree, after
 * deletion the binary search tree's property should be maintained.
 * 
 * Assumptions
 * 
 * There are no duplicate keys in the binary search tree
 * 
 * Medium Binary Tree Iteration Recursion
 * 
 * 
 * @author
 *
 */

public class BinarySearchTreeDelete {
	// REF, check "How to remove a target node from BST"
	public TreeNode delete(TreeNode root, int key) {
		if (root == null) {
			return null;
		}
		if (key == root.key) {
			if (root.left == null) {
				return root.right;
			} else if (root.right == null) {
				return root.left;
			} else if (root.right.left == null) {
				root.right.left = root.left;
				return root.right;
			} else {
				TreeNode newRoot = deleteSmallest(root.right);
				newRoot.left = root.left;
				newRoot.right = root.right;
				return newRoot;
			}
		} else if (key > root.key) {
			root.right = delete(root.right, key);
		} else {
			root.left = delete(root.left, key);
		}
		return root;
	}
	
	private TreeNode deleteSmallest(TreeNode root) {
		while (root.left.left != null) {
			root = root.left;
		}
		TreeNode smallest = root.left;
		root.left = root.left.right;
		return smallest;
	}
	
	
	
	// find the target node, and delete
	// replace it with the right-most leaf of it's left subtree
	// do it in a recursive way
	// if key does not exist in the tree, do nothing and return root?
	public TreeNode delete_self_ok(TreeNode root, int key) {
		if (root == null) {
			return null;
		}
		if (root.key == key) {
			// case 1, root has no left subtree
			if (root.left == null) {
				return root.right;
			}
			// case 2, root has left sub tree, find the max node (may not be leaf (00 childrend)) from left subtree
			TreeNode leaf = deleteMaxLeftNode(root); // !! should not pass root.left
			leaf.left = root.left;
			leaf.right = root.right;
			root.left = null;
			root.right = null;
			return leaf;
		} else if (root.key < key) {
			root.right = delete(root.right, key);
		} else {
			root.left = delete(root.left, key);
		}
		return root;
	}

	// root is not null !!!--> if need to delete root itself, then parent of root will not get updated!!
	// return the leaf node.
	// ---> not actually the LEAF, can be a node that has left but not right !!!, handle the child (if) of node!!!
	// ---> in a tree, when handle delete, always check parent, and children !!!, for children, always consider 4 cases. 00, 10, 01, 11 
	// TreeNode deleteMaxLeftNode(TreeNode root) {
	TreeNode deleteMaxLeftNode(TreeNode root) {
		TreeNode prev = root;
		TreeNode cur = root.left; // left is not null
		while (cur.right != null) {
			prev = cur;
			cur = cur.right;
		}
		if (prev != root) {
			// prev.right = null;
			prev.right = cur.left;
		} else {
			// prev.left = null;
			prev.left = cur.left;
		}
		return cur;
	}
	
	public static void main(String[] args) {
		BinarySearchTreeDelete sol = new BinarySearchTreeDelete();
		TreeNode root = TreeNode.createTreeFromString("2, 1, 3");
		// TreeNode root1 = TreeNode.createTreeFromString("7, 3, 2, 1, #, #, #, 5, 4, #, #, 6, #, #, 16, 11, #, 12, #, #, 18, #, 20, #, #");
		int key = 3;
		TreeNode result = sol.delete(root, key);
		TreeNode.printTree(result);
	}
}
