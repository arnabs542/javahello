package hw;

import utils.TreeNode;

/**
 * 
 * Recursion Lowest Common Ancestor I Given two nodes in a binary tree, find
 * their lowest common ancestor.
 * 
 * Assumptions
 * 
 * There is no parent pointer for the nodes in the binary tree
 * 
 * The given two nodes are guaranteed to be in the binary tree
 * 
 * Examples
 * 
 * 5
 * 
 * / \
 * 
 * 9 12
 * 
 * / \ \
 * 
 * 2 3 14
 * 
 * The lowest common ancestor of 2 and 14 is 5
 * 
 * The lowest common ancestor of 2 and 9 is 9
 * 
 * 
 * @author
 *
 */

public class LowestCommonAncestorI {
	// ref
	// return:
	// null - there is no one or two in the subtree.
	// non null -
	// 1) if there is only one node of one / two on the subtree, just return the one/two itself.
	// 2) if there both one / two are in the subtree, return the LCA.
	// 		a) one is two's descendant, return two.
	//		b) two is one's descendant, return one.
	//		c) otherwise, return the lowest node with one and two in the two different subtrees.
	public TreeNode lowestCommonAncestor(TreeNode root, TreeNode one, TreeNode two) {
		// Assumptions: root is not null, one and two guaranteed to be in the tree and not null.
		if (root == null) {
			return null;
		}
		// if root is one or two, we can ignore the later recursions.
		if (root == one || root == two) {
			return root;
		}
		TreeNode ll = lowestCommonAncestor(root.left, one, two);
		TreeNode lr = lowestCommonAncestor(root.right, one, two);
		if (ll != null && lr != null) {
			return root;
		}
		return ll == null ? lr : ll;
	}
	
	// Assumptions:
	// There is no parent pointer for the nodes in the binary tree
	// The given two nodes are guaranteed to be in the binary tree
	public TreeNode lowestCommonAncestor_selfAC(TreeNode root, TreeNode one, TreeNode two) {
		if (root == null) {
			return null;
		}
		if (one == null) {
			return two;
		}
		if (two == null) {
			return one;
		}
		return helper(root, one, two);
	}

	private TreeNode helper(TreeNode root, TreeNode one, TreeNode two) {
		if (root == null) {
			return null;
		}
		if (root == one || root == two) {
			return root;
		}
		TreeNode lfound = helper(root.left, one, two);
		TreeNode rfound = helper(root.right, one, two);
		if (lfound != null && rfound != null) {
			return root;
		} else if (lfound != null) {
			return lfound;
		} else {
			return rfound;
		}

	}
}
