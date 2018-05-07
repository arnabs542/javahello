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
  /**
  * basic idea: top down, passing the current length uptill current node, 
  * if current nodes's child == cur'val + 1, do path + 1
  * else, starting from 1
  * updating a global max path length during the DFS
  **/
  public int longestConsecutive(TreeNode root) {
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

