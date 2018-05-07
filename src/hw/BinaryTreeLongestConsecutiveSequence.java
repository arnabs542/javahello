package hw;

import utils.TreeNode;

/**
 * public class TreeNode {
 *   public int key;
 *   public TreeNode left;
 *   public TreeNode right;
 *   public TreeNode(int key) {
 *     this.key = key;
 *   }
 * }
 */



public class BinaryTreeLongestConsecutiveSequence {
	public int longestConsecutive_REF1(TreeNode root) {
		// REF, bottom up recursion
		if (root == null) {
			return 0;
		}
		int[] max = new int[1];
		dfs(root, max);
		return max[0];
	}
	
	/**
	 *  the return value represent the longest consecutive sequence from the current
	 *  root (with current root included)
	 */
	private int dfs(TreeNode root, int[] max) {
		// base case
		if (root == null) {
			return 0;
		}
		
		int cur = 1;
		int left = dfs(root.left, max);
		int right = dfs(root.right, max);
		
		if (root.left != null && root.key + 1 == root.left.key) {
			cur = Math.max(cur, 1 + left);
		}
		if (root.right != null && root.key + 1 == root.right.key) {
			cur = Math.max(cur, 1 + right);
		}
		max[0] = Math.max(max[0], cur);
		return cur;
	}
	
	/**
	 * Top down way
	 * @param root
	 * @return
	 */
	public int longestConsecutive_REF2(TreeNode root) {
		// REF, top down recursion
		if (root == null) {
			return 0;
		}
		int[] max = new int[1];
		dfs(root, root.key, 0, max);
		return max[0];
	}
	
	private void dfs(TreeNode root, int prev, int longest, int[] max) {
		// base case
		if (root == null) {
			return;
		}
		// longest = 0.
		if (prev == root.key - 1) {
			longest++;
		} else {
			longest = 1;
		}
		max[0] = Math.max(max[0], longest);
		dfs(root.left, root.key, longest, max);
		dfs(root.right, root.key, longest, max);
	}

	
	
	
  /**
  * basic idea: top down, passing the current length uptill current node, 
  * if current nodes's child == cur'val + 1, do path + 1
  * else, starting from 1
  * updating a global max path length during the DFS
  * 
  * Time: O(n) -- traversal
  * Space: O(height)
  * 
  **/
  public int longestConsecutive_Self(TreeNode root) {
    // Write your solution here
    if (root == null) {
    	return 0;
    }
    int[] result = new int[]{1};
    helper(root, 1, result);
    return result[0];
  }
  
  private void helper(TreeNode root, int pathLen, int[] result) {
    result[0] = Math.max(result[0], pathLen);
    if (root.left != null) {
    	if (root.left.key == root.key + 1) {
      	helper(root.left, pathLen + 1, result);
      } else {
      	helper(root.left, 1, result);
      }
    }
    
    if (root.right != null) {
    	if (root.right.key == root.key + 1) {
      	helper(root.right, pathLen + 1, result);
      } else {
      	helper(root.right, 1, result);
      }
    }
  }
}

