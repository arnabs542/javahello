package hw;

import utils.TreeNode;

/**
 * Lowest Common Ancestor II
 * 
 * Given two nodes in a binary tree (with parent pointer available), find their
 * lowest common ancestor.
 * 
 * Assumptions
 * 
 * There is parent pointer for the nodes in the binary tree
 * 
 * The given two nodes are not guaranteed to be in the binary tree
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
 * The lowest common ancestor of 2 and 8 is null (8 is not in the tree)
 * 
 * Medium Binary Tree Linked List
 * 
 * @author
 *
 */

/**
 * public class TreeNode { public int key; public TreeNode left; public TreeNode
 * right; public TreeNode(int key) { this.key = key; } }
 */

public class LowestCommonAncestorII {

	// basic idea, get depths of tow nodes, then adjust one of the ->parent step-diff times.
	// then ->parent same steps and will merge at the same node which is lca.
	public TreeNodeP lowestCommonAncestor(TreeNodeP one, TreeNodeP two) {
		int d1 = depth(one);
		int d2 = depth(two);
		// This is a small trick that can guarantee when calling mergeNode(),
		// the first list is the shorter list, the second list is the longer one.
		if (d1 < d2) {
			return mergeNode(one, two, d2 - d1);
		} else {
			return mergeNode(two, one, d1 - d2);
		}
	}
	
	// get the depth of the list from the node to the root of the tree
	// along the path using parent pointers.
	private int depth(TreeNodeP node) {
		int depth = 0;
		while (node != null) {
			node = node.parent;
			depth++;
		}
		return depth;
	}
	
	// assume that shorter is closer to root in the tree (shallower)
	private TreeNodeP mergeNode(TreeNodeP shorter, TreeNodeP longer, int diff) {
		while (diff > 0) {
			longer = longer.parent;
			diff--;
		}
		while (longer != shorter) {
			longer = longer.parent;
			shorter = shorter.parent;
		}
		return longer;
	}

	public static class TreeNodeP {
		public int key;
		public TreeNodeP left;
		public TreeNodeP right;
		public TreeNodeP parent;

		public TreeNodeP(int key) {
			this.key = key;
		}
	}

}
