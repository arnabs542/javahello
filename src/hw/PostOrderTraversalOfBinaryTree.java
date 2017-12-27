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
import java.util.Collections;
import java.util.Deque;
import java.util.List;

import com.sun.net.httpserver.Authenticator.Result;

import java.util.LinkedList;
import utils.TreeNode;

public class PostOrderTraversalOfBinaryTree {
	
	public List<Integer> postOrder(TreeNode root) {
		// self -- ref
		ArrayList<Integer> res = new ArrayList<Integer>();
		if (root == null) { 
			return res;
		}
		Deque<TreeNode> stack = new LinkedList<TreeNode>();
		stack.offerLast(root);
		TreeNode prev = null;
		while (!stack.isEmpty()) {
			TreeNode cur = stack.peekLast();
			if (prev == null || cur == prev.left || cur == prev.right) {
				if (cur.left != null) {
					stack.offerLast(cur.left);
				} else if (cur.right != null) {
					stack.offerLast(cur.right);
				} else {
					stack.pollLast();
					res.add(cur.key);
				}
			} else if (cur.left == prev) {
				if (cur.right != null) {
					stack.offerLast(cur.right);
				}
			} else {
				// cur.right = prev || cur == prev
				stack.pollLast();
				res.add(cur.key);
			}
			prev = cur;
			
		}
		return res;
	}	
	
	public List<Integer> postOrder2(TreeNode root) {
		// ref
		// Method 2: check the relation between the current node and the previous node
		// to determin which direction should go next.
		ArrayList<Integer> res = new ArrayList<Integer>();
		if (root == null) { 
			return res;
		}
		Deque<TreeNode> stack = new LinkedList<TreeNode>();		
		stack.offerFirst(root);
		// to record the previous node on the way of DFS so that we can determine the 
		// direction
		TreeNode prev = null;
		while (!stack.isEmpty()) {
			TreeNode cur = stack.peekFirst();
			// if we are going down, either left/right direction.
			if (prev == null || cur == prev.left || cur == prev.right) {
				// if we can still go down, try go left first.
				if (cur.left != null) {
					stack.offerFirst(cur.left);
				} else if (cur.right != null) {
					stack.offerFirst(cur.right);
				} else {
					// if we can not go either way, meaning cur is a leaf node.
					stack.pollFirst();
					res.add(cur.key);
				}
			} else if (prev == cur.right || prev == cur.left && cur.right == null) {
				// if we are going up from the right side or going up from the left side
				// but we can not go right afterwards.
				stack.pollFirst();
				res.add(cur.key);
			} else {
				// otherwise, we are going up from the left side and we can go down right side.
				stack.offerFirst(cur.right);
			}
			prev = cur;
		}
		return res;
	}

	public List<Integer> postOrder1(TreeNode root) {
		// ref
		// Method 1: post-order is the reverse order of pre-order with traversing
		// right subtree before traversing left subtree.
		ArrayList<Integer> res = new ArrayList<Integer>();
		if (root == null) { 
			return res;
		}
		Deque<TreeNode> preOrder = new LinkedList<TreeNode>();
		preOrder.offerFirst(root);
		while (!preOrder.isEmpty()) {
			TreeNode current = preOrder.pollFirst();
			// conduct the result for the special pre-order traversal.
			res.add(current.key);
			// in pre-order the right subtree will be traversed before the 
			// left subtree so pushing left child first.
			if (current.left != null) {
				preOrder.offerFirst(current.left);
			}
			if (current.right != null) {
				preOrder.offerFirst(current.right);
			}
		}
		// reverse the pre-order traversal sequence to get the post-order result.
		Collections.reverse(res);
		return res;
	}
	

}
