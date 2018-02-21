package hw.practice;

import java.util.Deque;
import java.util.LinkedList;

import utils.TreeNode;

public class BinaryTreeIterator {
//	private TreeNode root;
	private Deque<TreeNode> stack = new LinkedList<>();
	
	public BinaryTreeIterator(TreeNode root) {
		pushLeft(root);
	}
	
	public boolean hasNext() {
		return !stack.isEmpty(); // !
	}
	
	public int next() { // return int, and tmp.key
		TreeNode tmp = stack.pollLast();
		pushLeft(tmp.right);
		return tmp.key;
	}
	
	private void pushLeft(TreeNode root) {
		// for (;root != null; stack.offerLast(root.left), root = root.left) {} 
		// should push root itself
		for (;root != null; stack.offerLast(root), root = root.left) {} 
	}
}
