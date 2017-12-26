package hw;

/**
 * Data Structure
In-order Traversal Of Binary Tree
Implement an iterative, in-order traversal of a given binary tree, return the list of keys of each node in the tree as it is in-order traversed.

Examples

        5

      /    \

    3        8

  /   \        \

1      4        11

In-order traversal is [1, 3, 4, 5, 8, 11]

Corner Cases

What if the given binary tree is null? Return an empty list in this case.
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

import java.util.ArrayList;
import java.util.List;
import java.util.LinkedList;
import utils.TreeNode;

public class InOrderTraversalOfBinaryTree {
	/**
	 * Recurisve way
	 * 
	 * @param root
	 * @return
	 */
	public List<Integer> inOrder(TreeNode root) {
		ArrayList<Integer> res = new ArrayList<Integer>();
		if (root == null) {
			return res;
		}
		TreeNode cur = root;
		LinkedList<TreeNode> stack = new LinkedList<TreeNode>();
		while (cur != null || !stack.isEmpty()) {
			if (cur != null) {
				res.add(cur.key); // !!!should be .key, not node
				stack.offerLast(cur);
				cur = cur.left;
			} else {
				cur = stack.pollLast();
				cur = cur.right;
			}
		}
		return res;
	}

	public List<Integer> inOrder_recursive(TreeNode root) {
		ArrayList<Integer> res = new ArrayList<Integer>();
		if (root == null) {
			return res;
		}
		helper(root, res);
		return res;
	}

	private void helper(TreeNode root, ArrayList<Integer> res) {
		if (root == null) {
			return;
		}
		helper(root.left, res);
		res.add(root.key); // java arraylist use add, not append; 2) root.key not root
		helper(root.right, res);
	}
}
