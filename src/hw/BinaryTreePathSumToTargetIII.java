package hw;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import utils.TreeNode;

/**
 * 
 * Given a binary tree in which each node contains an integer number. Determine
 * if there exists a path (the path can only be from one node to itself or to
 * any of its descendants), the sum of the numbers on the path is the given
 * target number.
 * 
 * Examples
 * 
 * 5
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
 * 3
 * 
 * If target = 17, There exists a path 11 + 6, the sum of the path is target.
 * 
 * If target = 20, There exists a path 11 + 6 + 3, the sum of the path is
 * target.
 * 
 * If target = 10, There does not exist any paths sum of which is target.
 * 
 * If target = 11, There exists a path only containing the node 11.
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
 * Medium Binary Tree Depth First Search Dynamic Programming
 * 
 * @author
 *
 */
public class BinaryTreePathSumToTargetIII {

	// REF
	// Method 2: an O(n) solution.
	// Think about the related problem, how do you find if there is 
	// a subarray sum to target value?
	// If there is an O(n) solution to the above problem, we can extend
	// it to each of the root-> leaf paths of the binary tree.
	public boolean exist(TreeNode root, int target) {
		if (root == null) {
			return false;
		}
		Set<Integer> preSums = new HashSet<>();
		preSums.add(0);
		return helper(root, preSums, 0, target);
	}
	
	private boolean helper(TreeNode root, Set<Integer> preSums, int preSum, int target) {
		preSum += root.key;
		if (preSums.contains(preSum - target)) {
			return true;
		}
		boolean needRemove = preSums.add(preSum);  // !!! if the current preSum already exist in the set
		if (root.left != null && helper(root.left, preSums, preSum, target)) {
			return true;
		}
		if (root.right != null && helper(root.right, preSums, preSum, target)) {
			return true;
		}
		if (needRemove) { // !!!
			preSums.remove(preSum);
		}
		return false;
	}
	
	
	
	
	public boolean exist_REF1(TreeNode root, int target) {
		if (root == null) {
			return false;
		}
		// pass down the prefix of the path.
		List<TreeNode> path = new ArrayList<>();
		return helper(root, path, target);
	}
	
	private boolean helper_REF1(TreeNode root, List<TreeNode> path, int target) {
		if (root == null) {
			return false;
		}
		path.add(root);
		// check if can find a subpath ended at root node,
		// the sum of the subpath is target.
		int tmp = 0;
		for (int i = path.size() - 1; i >= 0; i--) {
			tmp += path.get(i).key;
			if (tmp == target) {
				return true;
			}
		}
		boolean result = helper(root.left, path, target) || helper(root.right, path, target);
		// don't forget for the cleanup when return to the previous level.
		path.remove(path.size() - 1);
		return result;
	}
	
	
	
	public boolean exist_self_ac(TreeNode root, int target) {
		List<Integer> preSum = new ArrayList<>();
		return helper(root, preSum, target);
	}

	private boolean helper_self(TreeNode root, List<Integer> preSum, int target) {
		if (root == null) {
			return false;
		}
		if (root.key == target) {
			return true;
		}
		int curSum = 0;
		if (preSum.size() == 0) {
			preSum.add(root.key);
		} else {
			curSum = root.key + preSum.get(preSum.size() - 1);
			preSum.add(curSum);
		}
		if (curSum == target) { // !!! don't forget, full path
			return true;
		}

		for (int i = 0; i < preSum.size() - 1; i++) {
			if (curSum - preSum.get(i) == target) {
				return true;
			}
		}
		boolean result = helper(root.left, preSum, target) || helper(root.right, preSum, target);
		preSum.remove(preSum.size() - 1); // !!! important, otherwise wrong
		return result;
	}

	public static void main(String[] args) {
		BinaryTreePathSumToTargetIII sol = new BinaryTreePathSumToTargetIII();
		TreeNode root = TreeNode.createTreeFromStringArr(new String[]{"3","-8","9","4","10","2","-5","1","-2"});
		int target = 8;		
		boolean result = sol.exist(root, target);
		System.out.println(result);
	}
}
