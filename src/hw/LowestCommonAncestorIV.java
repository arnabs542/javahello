package hw;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import utils.TreeNode;

/**
 * 
 * Lowest Common Ancestor IV
 * 
 * Given K nodes in a binary tree, find their lowest common ancestor.
 * 
 * Assumptions
 * 
 * K >= 2
 * 
 * There is no parent pointer for the nodes in the binary tree
 * 
 * The given K nodes are guaranteed to be in the binary tree
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
 * The lowest common ancestor of 2, 3, 14 is 5
 * 
 * The lowest common ancestor of 2, 3, 9 is 9
 * 
 * Hard Binary Tree
 * 
 * 
 * @author
 *
 */
public class LowestCommonAncestorIV {
	
	public TreeNode lowestCommonAncestor_ref(TreeNode root, List<TreeNode> nodes) {
		// Assumptions: the list of nodes is not null or not empty,
		// all the nodes in the list are auaranteed to be in the tree.
		Set<TreeNode> set = new HashSet<>(nodes);
		return helper_ref(root, set);
	}
	
	private TreeNode helper_ref(TreeNode root, Set<TreeNode> set) {
		if (root == null) {
			return null;
		}
		if (set.contains(root)) {
			return root;
		}
		TreeNode left = helper_ref(root.left, set);
		TreeNode right = helper_ref(root.right, set);
		if (left != null && right != null) {
			return root;
		} else {
			return left != null ? left : right;
		}
	}
	
///////////////////////////////////////////////////////////////////////////////////////////	
	
	public TreeNode lowestCommonAncestor(TreeNode root, List<TreeNode> nodes) {
		if (root == null || nodes == null || nodes.size() == 0) {
			return null;
		}
		TreeNode result = helper(root, nodes);
		return result;
	}
	
	private TreeNode helper(TreeNode root, List<TreeNode> nodes) {
		if (root == null) {
			return null;
		}
		for (TreeNode tmp : nodes) {
			if (tmp == root) {
				return root;
			}
		}
		TreeNode left = helper(root.left, nodes);
		TreeNode right = helper(root.right, nodes);
		if (left != null && right != null) {
			return root;
		} else {
			return left != null ? left : right;
		}
	}
}
