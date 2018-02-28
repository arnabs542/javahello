package hw;

import static org.junit.jupiter.api.Assumptions.assumingThat;

import java.util.HashMap;
import java.util.Map;

import utils.TreeNode;

/**
 * 
 * Given the preorder and inorder traversal sequence of a binary tree,
 * reconstruct the original tree.
 * 
 * Assumptions
 * 
 * The given sequences are not null and they have the same length There are no
 * duplicate keys in the binary tree Examples
 * 
 * preorder traversal = {5, 3, 1, 4, 8, 11}
 * 
 * inorder traversal = {1, 3, 4, 5, 8, 11}
 * 
 * the corresponding binary tree is
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
 * The sequence [1, 2, #, 3, 4, #, #, #] represents the following binary tree:
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
 * Medium Binary Tree
 * 
 * 
 * @author
 *
 */
public class ReconstructBinaryTreeWithPreorderAndInorder {
	
	// self, ac
	public TreeNode reconstruct(int[] in, int[] pre) {
		// Assumptions:
		// The given sequences are not null and they have the same length.
		// There are no duplicate keys in the binary tree.
		return helper(in, pre, 0, 0, in.length);
	}

	private TreeNode helper(int[] in, int[] pre, int inLeft, int preLeft, int length) {
		if (length == 0) {
			return null;
		}
		TreeNode root = new TreeNode(pre[preLeft]);
		int mid = inLeft;
		while (in[mid] != pre[preLeft]) { // --> this part should be optimized using val-idx map, like refI
			mid++; // !! mid not in, typo
		}
		int leftLen = mid - inLeft;
		int rightLen = length - 1 - leftLen;
		root.left = helper(in, pre, inLeft, preLeft + 1, leftLen); // !!! preorder, not post order
		root.right = helper(in, pre, inLeft + leftLen + 1, preLeft + 1 + leftLen, rightLen); // !!! preorder, not post
																								// order
		return root;
	}
	
	
	////////////////////////////////
	
	// REF
	// Method 1: Utilizing the inOrder sequence to determine the size of left/right subtrees.
	public TreeNode reconstructI(int[] in, int[] pre) {
		// Assumptions:
		// The given sequences are not null and they have the same length.
		// There are no duplicate keys in the binary tree.
		Map<Integer, Integer> inIdx = indexMap(in);
		return helperI(pre, inIdx, 0, in.length - 1, 0, pre.length - 1);
	}
	
	private Map<Integer, Integer> indexMap(int[] in) {
		Map<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < in.length; i++) {
			map.put(in[i], i);
		}
		return map;
	}

	private TreeNode helperI(int[] pre, Map<Integer, Integer> inIdx, int inLeft, int inRight, int preLeft, int preRight) {
		if (inLeft > inRight) {
			return null;
		}
		TreeNode root = new TreeNode(pre[preLeft]);
		int inMid = inIdx.get(pre[preLeft]);
		root.left = helperI(pre, inIdx, inLeft, inMid - 1, preLeft + 1, preLeft + inMid - inLeft);
		root.right = helperI(pre, inIdx, inMid + 1, inRight, preLeft + inMid - inLeft + 1, preRight);
		return root;
	}
	
	// Method 2: Another Linear Solution with traversing and constructing the binary tree
	// using preOrder and inOrder at the same time.
	public TreeNode reconstructII(int[] in, int[] pre) {
		// Assumptions:
		// The given sequences are not null and they have the same length.
		// There are no duplicate keys in the binary tree.
		int[] preIndex = new int[] {0}; // the index of root for pre
		int[] inIndex = new int[] {0};  // ?? the index of left for in -- in 
		return helperII(pre, in, preIndex, inIndex, Integer.MAX_VALUE);
	}
	
	private TreeNode helperII(int[] pre, int[] in, int[] preIndex, int[] inIndex, int target) {
		// Traversing and construct the binary tree using preOrder and inOrder,
		// the preOrder: [root] [left sub] [right sub]
		// the  inOrder: [left sub] [root] [right sub]
		// from preOrder, we know the root of the binary tree,
		// then we know the left/right subtree.
		// "target" is the actually the root value, and we are using inOrder to identify the boundary of left subtree.
		if (inIndex[0] >= in.length || in[inIndex[0]] == target) {
			return null;
		}
		TreeNode root = new TreeNode(pre[preIndex[0]]);
		// preOrder, advance the index by 1 since we already finish the root;
		preIndex[0]++; // --> left subtree's root idx
		root.left = helperII(pre, in, preIndex, inIndex, root.key);
		// inOrder, after finish the left subtree, we can advance the index by 1.
		inIndex[0]++;
		root.right = helperII(pre, in, preIndex, inIndex, target);
		return root;
	}
	
	public static void main(String[] args) {
		ReconstructBinaryTreeWithPreorderAndInorder sol = new ReconstructBinaryTreeWithPreorderAndInorder();
		int[] pre = new int[] {2, 1, 3};
		int[] in = new int[] {1, 2, 3};
		TreeNode root = sol.reconstructII(in, pre);
		TreeNode.printTree(root);
	}
	
	
	
}
