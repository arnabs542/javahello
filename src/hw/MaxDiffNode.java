package hw;

import utils.TreeNode;

/***
 * Given a binary tree, find the node with the max difference in the 
 * total number descendents in its left subtree and right subtree.
 * 
 * @author 
 *
 */
public class MaxDiffNode {
	public TreeNode maxDiffNode(TreeNode root) {
		if (root == null) {
			return null;
		}
		TreeNode[] node = new TreeNode[1];
		int[] maxDiff = new int[1];
		maxDiff[0] = 0;
		numNodes(root, node, maxDiff);
		return node[0];
	}
	
	// return the # of nodes in the subtree.
	private int numNodes(TreeNode root, TreeNode[] node, int[] maxDiff) {
		if (root == null) {
			return 0;
		}
		int leftNum = numNodes(root.left, node, maxDiff);
		int rightNum = numNodes(root.right, node, maxDiff);
		int curDiff = Math.abs(leftNum - rightNum);
		if (maxDiff[0] < curDiff) {
			maxDiff[0] = curDiff;
			node[0] = root;
		}
		return leftNum + rightNum + 1;
	}
}
