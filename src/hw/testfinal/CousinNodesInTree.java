package hw.testfinal;

import utils.TreeNode;

/**
 * 
 * Check if two nodes are cousins in a Binary Tree Given the binary Tree and the
 * two nodes say ‘a’ and ‘b’, determine whether the two nodes are cousins of
 * each other or not. Two nodes are cousins of each other if they are at same
 * level and have different parents. Assume the two given nodes a and b are in
 * the given binary tree.
 * 
 * Example
 * 
 *    6 
 *   / \ 
 *  3   5 
 * / \ / \ 
 * 7 8 1 2
 * 
 * 7 and 1, result is TRUE. 
 * 3 and 5, result is FALSE. 
 * 7 and 5, result is FALSE.
 * 
 * class TreeNode { int val; TreeNode left; TreeNode right; }
 * 
 * 
 * @author
 *
 */
public class CousinNodesInTree {
	
	// REF, a better way is to do it in one recursion (LCA similar way),
	// what from parent: parent's depth
	// what to return to parent: node's height if found, or -1 if not found
	// what to do at current level: 
	// 	if found two non-neg height from children, same height and diff with current height > 1
	//		=> children height, update  global result as true, otherwise -1.
	// 	if all -1 from children, return -1
	// 	if one -1 and one valid value, return valid value
	// !! one important thing to decide two nodes are cousin.
	
	public boolean isCousin(TreeNode root, TreeNode a, TreeNode b) {
		boolean[] result = new boolean[1];
		helper(root, a, b, result, 0);
		return result[0];
	}
	
	private int helper(TreeNode root, TreeNode a, TreeNode b, boolean[] result, int level) {
		if (root == null) {
			return -1;
		}
		if (root == a || root == b) {
			return level;
		}
		int left = helper(root.left, a, b, result, level + 1);
		int right = helper(root.right, a, b, result, level + 1);
		if (left == right && left - level > 1) {  // !!! already cover left != -1
			result[0] = true;
			// return left;
		} 
//		else {
//			// other cases can be combined.
//			return Math.max(left, right);
//		}
		return Math.max(left, right);
	}
	
	
	
	
	// basic idea: find depth of both nods, and get lca, then check if
	// cousin or not.
	public boolean isCousin_self_OK(TreeNode root, TreeNode a, TreeNode b) {
		int la = getLevel(root, a, 0);
		int lb = getLevel(root, b, 0);  
		if (la != lb) {
			return false;
		}
		TreeNode lca = getLCA(root, a, b);
		return !(lca.left == a && lca.right == b || lca.left == b && lca.right == a);

	}

	private int getLevel(TreeNode root, TreeNode target, int level) {
		if (root == null) {
			return -1;
		}
		if (root == target) {
			return level;
		}

		int left = getLevel(root.left, target, level + 1);
		if (left == -1) {
			return getLevel(root.right, target, level + 1);
		} else {
			return left;
		}
	}

	private TreeNode getLCA(TreeNode root, TreeNode a, TreeNode b) {
		if (root == a || root == b) {
			return root;
		}
		if (root == null) {
			return null;
		}
		TreeNode left = getLCA(root.left, a, b);
		TreeNode right = getLCA(root.right, a, b);
		if (left != null && right != null) {
			return root;
		} else {
			return left != null ? left : right;
		}
	}
	
	public static void main(String[] args) {
		TreeNode root = TreeNode.createTreeFromString("6,3,5,7,8,1,2");
		TreeNode a = TreeNode.getNode(root, 7);
		TreeNode b = TreeNode.getNode(root, 1);
		CousinNodesInTree sol = new CousinNodesInTree();
		boolean result = sol.isCousin(root, a, b);
		System.out.println(result);
	}

}
