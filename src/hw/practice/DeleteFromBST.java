package hw.practice;
/**
 * leetcode 450
 * 
 */
import utils.TreeNode;

public class DeleteFromBST {

	public TreeNode delete(TreeNode root, int target) {
		if (root == null) {
			return null;
		}
		
		if (target < root.key) {
			root.left = delete(root.left, target);
		}
		if (target > root.key) {
			root.right = delete(root.right, target);
		}
		
		// need to delete current root
		if (root.left == null && root.right == null) {
			return null;
		}
		if (root.left == null) {
			return root.right;
		}
		if (root.right == null) {
			return root.left;
		}
		// both children exists, case 4
		// case 4.1
		if (root.right.left == null) {
			root.right.left = root.left;
			return root.right;
		}
		// case 4.2
		// root.right.left != null
		TreeNode smallest = deleteSmallest(root.right);
		smallest.left = root.left;
		smallest.right = root.right;
		return smallest;
	}
	
	private TreeNode deleteSmallest(TreeNode root) {
		TreeNode prev = null, cur = root;
		while (cur.left != null) {
			prev = cur;
			cur = cur.left;
		}
		prev.left = cur.right;
		return cur;
	}
}
