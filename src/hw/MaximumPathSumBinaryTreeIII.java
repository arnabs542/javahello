package hw;

import utils.TreeNode;

/**
 * 
 * Given a binary tree in which each node contains an integer number. Find the
 * maximum possible subpath sum(both the starting and ending node of the subpath
 * should be on the same path from root to one of the leaf nodes, and the
 * subpath is allowed to contain only one node).
 * 
 * Assumptions
 * 
 * The root of given binary tree is not null Examples
 * 
 * -5
 * 
 * / \
 * 
 * 2 11
 * 
 * / \
 * 
 * 6 14
 * 
 * /
 * 
 * -3
 * 
 * The maximum path sum is 11 + 14 = 25
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
 * Medium Binary Tree
 * 
 * @author
 *
 */
public class MaximumPathSumBinaryTreeIII {
	// self, ac, bottom-up way
	public int maxPathSum(TreeNode root) {
		// Assumptions: The root of given binary tree is not null.
		int[] result = new int[] { Integer.MIN_VALUE };
		// need to do traversal of all root-to-leaf paths
		// maxpathsum will be a subpath of one of the root-to-leaf paths
		// at least one node in the root
		helper(root, result);
		return result[0];
	}

	private int helper(TreeNode root, int[] max) {
		if (root == null) {
			return 0;
		}
		int leftSum = helper(root.left, max);
		int rightSum = helper(root.right, max);
		// only root, root + left, root + right, left, right
		// return must contain root;
		int subMax = Math.max(0, Math.max(leftSum, rightSum));
		max[0] = Math.max(max[0], root.key + subMax);
		return root.key + subMax;

	}

	// Method 2: self, ac, top down, similar to dp way of finding max path sum.
	// check "Maximum Path Sum Binary Tree III"
	public int maxPathSumII(TreeNode root) {
		// Assumptions: The root of given binary tree is not null.
		int[] result = new int[] { Integer.MIN_VALUE };
		// need to do traversal of all root-to-leaf paths
		// maxpathsum will be a subpath of one of the root-to-leaf paths
		// at least one node in the root
		dpHelper(root, 0, result);
		return result[0];
	}

	private void dpHelper(TreeNode root, int preSum, int[] max) {
		if (root == null) {
			// update possible max when reach leaf.
			// max[0] = Math.max(max[0], preSum);  // --> no need, no value of root update here
			return;
		}
		if (preSum < 0) {
			preSum = 0;
		}
		preSum += root.key;
		max[0] = Math.max(max[0], preSum); 
		// class doc ref: this is actually a pre-order traversal. that's it!
		dpHelper(root.left, preSum, max);
		dpHelper(root.right, preSum, max);
	}
}
