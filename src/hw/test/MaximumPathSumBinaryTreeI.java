package hw.test;

import utils.TreeNode;

/**
 * 
 * Maximum Path Sum Binary Tree I
 * 
 * Description
 * 
 * Given a binary tree in which each node contains an integer number. Find the
 * maximum possible sum from one leaf node to another leaf node. If there is no
 * such path available, return Integer.MIN_VALUE(Java)/INT_MIN (C++).
 * 
 * Examples
 * 
 * -15
 * 
 * / \
 * 
 * 2 11
 * 
 * / \
 * 
 * 6 14
 * 
 * The maximum path sum is 6 + 11 + 14 = 31.
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
 * Medium Binary Tree Exam 1 Java TIME USED :
 * 
 * case 2: [[-1,-2,-6,-3,-4,null,null,-7,null,-5]] Expected: [-18]
 * 
 * case 3: {"-6","-8","2","11","#","-1","8","2"}) Expected: 9
 * 
 * @author
 *
 */
public class MaximumPathSumBinaryTreeI {
	public int maxPathSum_REF(TreeNode root) {
		// we can wrap a global variable into any array so that 
		// we can change the value during recursion.
		int[] max = new int[] { Integer.MIN_VALUE };
		maxSumHelper(root, max);
		return max[0];
	}

	// the returned value is the max path sum from
	// root to any leaf node in its subtree.
	private int maxSumHelper(TreeNode root, int[] max) {
		if (root == null) {
			return 0;
		}
		int leftSum = maxSumHelper(root.left, max);
		int rightSum = maxSumHelper(root.right, max);

		// only when root node has both left and right child,
		// we need to possibly update the max path sum.
		int singleMax = Math.max(leftSum, rightSum);
		if (root.left != null && root.right != null) {
			max[0] = Math.max(max[0], leftSum + rightSum + root.key);
			return Math.max(leftSum, rightSum) + root.key;
		}
		return root.left != null ? leftSum + root.key : rightSum + root.key;
		
		
	}
	
	//////////////////////////////////////////////////////////
	
	
	
	
	public int maxPathSum(TreeNode root) {
		int[] result = new int[] { Integer.MIN_VALUE };
		if (root == null) {
			return result[0];
		}
		dfs(root, result);
		return result[0];
	}

	// return the bigger single path that include root
	// but during dfs, also record possbile max sum
	// when the leave-leave path does not include
	// the current root.
	private int dfs(TreeNode root, int[] result) {
		if (root == null) {
			return 0;
		}
		int leftSum = dfs(root.left, result);
		int rightSum = dfs(root.right, result);
		// !!! only leaf-to-leaf, so not the following 5 cases.
		// only possible is 5 for current root, and return max(3, 4)
		// 1. only left, 2. only right, 3, left + root
		// 4. right + root, 5, left + root + right
		// !!! only both children null node are considered leaf!!

		int singleMax = Math.max(leftSum, rightSum);
		// int curMax = Math.max(singleMax, singleMax + root.key);
		// curMax = Math.max(curMax, leftSum + rightSum + root.key);
		
		if (root.left != null && root.right != null) {
			result[0] = Math.max(result[0], leftSum + rightSum + root.key);
			System.out.println("updating global max...");
		}
		System.out.println("node: " + root.key + " " + result[0] + " " + (singleMax + root.key));

		// need to distinguish especially when one of the children is null!!! ,need to return the leaf branch, not the null branch
		// see case 2.
		if (root.left != null && root.right != null) {
			return singleMax + root.key;	
		} else {
			return root.left != null ? leftSum + root.key : rightSum + root.key;
		}
		
	}

	public static void main(String[] args) {
		//TreeNode root = TreeNode.createTreeFromStringArr(new String[] { "-6", "-8", "2", "11", "#", "-1", "8", "2" }); // 9
		TreeNode root = TreeNode.createTreeFromStringArr(new String[] {"-1","-2","-6","-3","-4","#","#","-7","#","-5"}); // 18
		MaximumPathSumBinaryTreeI sol = new MaximumPathSumBinaryTreeI();
		int result = sol.maxPathSum(root);
		System.out.println(result);
	}

}
