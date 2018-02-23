package hw;

import utils.TreeNode;

/**
 * 
 * Given a binary tree in which each node contains an integer number. Find the
 * maximum possible sum from any node to any node (the start node and the end
 * node can be the same).
 * 
 * Assumptions
 * 
 * ​The root of the given binary tree is not null Examples
 * 
 * -1
 * 
 * / \
 * 
 * 2 11
 * 
 * / \
 * 
 * 6 -14
 * 
 * one example of paths could be -14 -> 11 -> -1 -> 2
 * 
 * another example could be the node 11 itself
 * 
 * The maximum path sum in the above binary tree is 6 + 11 + (-1) + 2 = 18
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
 * Hard Binary Tree
 * 
 * @author
 *
 */
public class MaximumPathSumBinaryTreeII {
	public int maxPathSum(TreeNode root) {
		int[] max = new int[] { Integer.MIN_VALUE }; // should be MIN_VALUE, not max
		helper(root, max);
		return max[0];
	}

	private int helper(TreeNode root, int[] max) {
		if (root == null) {
			return 0;
		}
		int left = helper(root.left, max);
		int right = helper(root.right, max);
		// root, left, right,
		// root + left, root + right, root + left + right
		// !!! --> need to consider case when all nodes have neg vals. 
		// null children will return 0 ( > than neg vals!!!)
//		int singleMax = Math.max(root.key, left);
//		singleMax = Math.max(singleMax, right);
//		int returnMax = Math.max(root.key + left, root.key + right);
//		returnMax = Math.max(root.key, returnMax);
//		int curMax = Math.max(root.key + left + right, singleMax);
//		curMax = Math.max(curMax, returnMax);
//		max[0] = Math.max(max[0], curMax);
		int returnMax = root.key;
		int curMax = root.key; // at least have to take a node
		if (root.left != null) {
			returnMax = Math.max(returnMax, root.key + left);
			curMax += (left > 0 ? left : 0);
		}
		if (root.right != null) {
			returnMax = Math.max(returnMax, root.key + right);
			curMax += (right > 0 ? right : 0);
		}
		max[0] = Math.max(max[0], curMax);
		return returnMax;
	}
	
	
	////////////////////////////////////////////////////////////////
	
	public int maxPathSum_REF(TreeNode root) {
		// Assumptions: root is not null.
		// max stores the global maximum path sum and will be
		// updated during the recursion.
		int[] max = new int[] { Integer.MIN_VALUE }; 
		helper_ref(root, max);
		return max[0];
	}

	// return the maximum path sum of the "single" path.
	private int helper_ref(TreeNode root, int[] max) {
		if (root == null) {
			return 0;
		}
		int left = helper(root.left, max);
		int right = helper(root.right, max);
		left = left < 0 ? 0 : left;
		right = right < 0 ? 0 : right;
		max[0] = Math.max(root.key + left + right, max[0]); 
		//--> can do this and the above left, right value change, one important condition
		// is that you have to take at least one node, and it should be the root node for current level!!!
		return root.key + Math.max(left, right);
	}	
	
	
	
	public static void main(String[] args) {
		TreeNode root = TreeNode.createTreeFromStringArr(new String[]{"-1","-2","-4","-3","#","-5"}); // expect -1
		MaximumPathSumBinaryTreeII sol = new MaximumPathSumBinaryTreeII();
		int result = sol.maxPathSum(root);
		System.out.println(result);
	}
}
