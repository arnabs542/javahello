package hw;

import utils.TreeNode;

/**
 * Recursion
Check If Binary Tree Is Balanced
Check if a given binary tree is balanced. A balanced binary tree is one in which the depths of every node¡¯s left and right subtree differ by at most 1.

Examples

        5

      /    \

    3        8

  /   \        \

1      4        11

is balanced binary tree,

        5

      /

    3

  /   \

1      4

is not balanced binary tree.

Corner Cases

What if the binary tree is null? Return true in this case.
How is the binary tree represented?

We use the level order traversal sequence with a special symbol "#" denoting the null node.

For Example:

The sequence [1, 2, 3, #, #, 4] represents the following binary tree:

    1

  /   \

 2     3

      /

    4


 * @author 
 *
 */

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
public class CheckIfBinaryTreeIsBalanced {
  public boolean isBalanced(TreeNode root) {
    return getHeight(root) != -1;
  }
  
  private int getHeight(TreeNode root) {
    if (root == null) {
      return 0;
    }
    int leftHeight = 0, rightHeight = 0;

    leftHeight = getHeight(root.left);
    if (leftHeight == -1) {
      return -1;
    }


    rightHeight = getHeight(root.right);
    if (rightHeight == -1) {
      return -1;
    }

    if (Math.abs(leftHeight - rightHeight) > 1) {
      return -1;
    } else {
      return Math.max(leftHeight, rightHeight) + 1;
    }    
  }
}
