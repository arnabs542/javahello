package hw.sprint;

import java.util.ArrayDeque;
import java.util.Deque;

import utils.TreeNode;

public class TwoSumIVInputIsBST {
	public boolean findTarget(TreeNode root, int k) {
		// base case
		if (root == null || (root.left == null && root.right == null)) {
			return false;
		}
		// stack 
		Deque<TreeNode> pred = new ArrayDeque<>(); // inorder
		Deque<TreeNode> succ = new ArrayDeque<>(); // reverse inorder
		
		TreeNode cur = root;
		while (cur != null) {
			succ.offerLast(cur);
			cur = cur.left;
		}
		cur = root;
		while (cur != null) {
			pred.offerLast(cur);
			cur = cur.right;
		}
		
		int left = nextSuccessor(succ);
		int right = nextPredecessor(pred);
		
		while (left < right) {
			if (left + right == k) {
				return true;
			} else if (left + right < k) {
				left = nextSuccessor(succ);
			} else {
				right = nextPredecessor(pred);
			}
		}
		return false;
	} 
	
	// left++
	private int nextSuccessor(Deque<TreeNode> stack) {
		TreeNode res = stack.pollLast();
		TreeNode cur = res.right;
		while (cur != null) {
			stack.offerLast(cur);
			cur = cur.left;
		}
		return res.val;
	}
	
	// right--
	private int nextPredecessor(Deque<TreeNode> stack) {
		TreeNode res = stack.pollLast();
		TreeNode cur = res.left;
		while (cur != null) {
			stack.offerLast(cur);
			cur = cur.right;
		}
		return res.val;
	}
}
