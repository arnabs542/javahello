package hw;

import utils.TreeNode;

/**
 * 
 * Given the postorder traversal sequence of a binary search tree, reconstruct
 * the original tree.
 * 
 * Assumptions
 * 
 * The given sequence is not null There are no duplicate keys in the binary
 * search tree Examples
 * 
 * postorder traversal = {1, 4, 3, 11, 8, 5}
 * 
 * the corresponding binary search tree is
 * 
 * 5
 * 
 * / \
 * 
 * 3 8
 * 
 * / \ \
 * 
 * 1 4 11
 * 
 * How is the binary tree represented?
 * 
 * We use the pre order traversal sequence with a special symbol "#" denoting
 * the null node.
 * 
 * For Example:
 * 
 * The sequence [1, 2, #, #, 3, 4, #, #, #] represents the following binary
 * tree:
 * 
 * 1
 * 
 * / \
 * 
 * 2 3
 * 
 * /
 * 
 * 4
 * 
 * Medium Binary Search Tree
 * 
 * @author
 *
 */
public class ReconstructBinarySearchTreeWithPostorderTraversal {
	// self, ac
	public TreeNode reconstruct(int[] post) {
		// Assumptions: post is not null, no duplicated keys for the tree nodes.
		return helper(post, 0, post.length - 1);
	}

	private TreeNode helper(int[] post, int left, int right) {
		if (left > right) {
			return null;
		}
		TreeNode root = new TreeNode(post[right]);
		int mid = left; // [left, mid) will be left subtree, [mid, right) will right subtree
		while (mid < right && post[mid] < post[right]) {
			mid++;
		}
		root.left = helper(post, left, mid - 1);
		root.right = helper(post, mid, right - 1);
		return root;
	}
	
	
	///////////////////////////////////////////////////////////////////////////////
	
	// REF ?? did not ac
	public TreeNode reconstruct_ref_not_ac(int[] post) {
		// Assumptions: post is not null, no duplicated keys for the tree nodes.
		// Traversing position of the post order,
		// we traverse and construct the binary search tree
		// from the postOrder right to left.
		int[] index = new int[] {post.length - 1};
		return helper_ref(post, index, Integer.MIN_VALUE);
	}

	private TreeNode helper_ref(int[] post, int[] index, int min) {
		// Since it is a binary search tree,
		// the "min" is actually the root,
		// and we are using the root value to determine the boundary
		// of left/right subtree.
		if (index[0] < 0 || post[index[0]] <= min) {
			return null;
		}
		TreeNode root = new TreeNode(post[index[0]]--);
		root.left = helper_ref(post, index, root.key);
		root.right = helper_ref(post, index, min);
		return root;
	}
}
