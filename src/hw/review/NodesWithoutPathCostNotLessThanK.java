package hw.review;

import java.util.ArrayList;
import java.util.List;

import utils.TreeNode;

/**
 * 
 * Tree + Recursion 3 steps:
 * 
 * 1. what to expect: the depth of the deepest leaf of the left subtree the
 * depth of the ...right
 * 
 * 2. what to do: if the max of the leaft result and right result < k print this
 * node
 * 
 * 3. what to return: the max computed in step 2.
 * 
 * @author
 *
 */

public class NodesWithoutPathCostNotLessThanK {
	
	// ref
	// return the depth of the deepest leaf under root
	// not necessarily accurate: < k >=k k
	public int find(TreeNode root, int level, int k) {  // what is k ???  here level will start from 0
		if (root == null || level >= k) {         // base case, // optimiaze to use level >= k, for sure  
																// subtrees will be deeper, and will not make the answer
			return level;                              
		}
		int left = find(root.left, level + 1, k);
		int right = find(root.right, level + 1, k);
		if (left < k && right < k) {
			System.out.println(root.key);
		}
		return left > right ? left : right; 
	}
	
	
	//////////////////////////////////////////////////////////
	void printNodes(TreeNode root, int k) {
		if (root == null) {
			return;
		}
		List<TreeNode> result = new ArrayList<>();
		helper(root, 1, result, k);
		// or other fancy print functions
		for (TreeNode node : result) {
			System.out.println(node.key + " ");
		}
	}

	// get the path cost of the root node it self
	int helper(TreeNode root, int level, List<TreeNode> result, int k) {
		int left = 0, right = 0;
		if (root.left == null && root.right == null) {
			// return level;  // !!! can't return here!!! check leaf itself!!!
			left = level;
		} else {
			if (root.left != null) {
				left = helper(root.left, level + 1, result, k);
			}
			if (root.right != null) {
				right = helper(root.right, level + 1, result, k);
			}
		}
		if (left < k && right < k) {
			result.add(root);
		}
		return Math.max(left, right);
	}
}
