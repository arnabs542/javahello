package hw;

import java.util.Deque;
import java.util.LinkedList;

import utils.TreeNode;

/**
 * Data Structure
Check If Binary Tree Is Completed
Check if a given binary tree is completed. A complete binary tree is one in which every level of the binary tree is completely filled except possibly the last level. Furthermore, all nodes are as far left as possible.

Examples

        5

      /    \

    3        8

  /   \

1      4

is completed.

        5

      /    \

    3        8

  /   \        \

1      4        11

is not completed.

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
 * public class TreeNode { public int key; public TreeNode left; public TreeNode
 * right; public TreeNode(int key) { this.key = key; } }
 */
public class CheckIfBinaryTreeIsCompleted {
	public boolean isCompleted(TreeNode root) {
		if (root == null) {
			return true;
		}
		Deque<TreeNode> queue = new LinkedList<TreeNode>();
		queue.offerLast(root);
		boolean startLeaf = false;
		while (!queue.isEmpty()) { // !! isEmpty(), don't forget ()
			TreeNode cur = queue.pollFirst();
			if (cur.left != null) {
				if (startLeaf) {
					return false;
				} else {
					queue.offerLast(cur.left);
				}
			} else {
				startLeaf = true;
			}
			if (cur.right != null) {
				if (startLeaf) {
					return false;
				} else {
					queue.offerLast(cur.right);
				}
			} else {
				startLeaf = true;
			}
		}
		return true;
	}
}
