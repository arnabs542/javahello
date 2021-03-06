package hw;

/**
 * 
 * 
 * 
Data Structure
Pre-order Traversal Of Binary Tree
Implement an iterative, pre-order traversal of a given binary tree, return the list of keys of each node in the tree as it is pre-order traversed.

Examples

        5

      /    \

    3        8

  /   \        \

1      4        11

Pre-order traversal is [5, 3, 1, 4, 8, 11]

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
import java.util.Deque;
import java.util.List;
import java.util.LinkedList;
import utils.TreeNode;

public class PreOrderTraversalOfBinaryTree {
	
	// ref
	public List<Integer> preOrder(TreeNode root) {
		ArrayList<Integer> res = new ArrayList<Integer>();
		if (root == null) { 
			return res;
		}	
		Deque<TreeNode> stack = new LinkedList<TreeNode>();
		stack.offerFirst(root);
		while (!stack.isEmpty()) {
			TreeNode cur = stack.pollFirst();
			res.add(cur.key);
			// the left subtree should be traversed before right subtree,
			// since stack is LIFO, we should push right into the stack first,
			// so for the next step the top element of the stack is the left
			// subtree.
			if (cur.right != null) {
				stack.offerFirst(cur.right);
			}
			if (cur.left != null) {
				stack.offerFirst(cur.left);
			}
		}
		return res;
		
	}	

	// self, good
	public List<Integer> preOrder1(TreeNode root) {
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
	

}
