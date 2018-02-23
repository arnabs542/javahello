package hw;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import utils.TreeNode;

/**
 * Given the levelorder and inorder traversal sequence of a binary tree,
 * reconstruct the original tree.
 * 
 * Assumptions
 * 
 * The given sequences are not null and they have the same length There are no
 * duplicate keys in the binary tree Examples
 * 
 * levelorder traversal = {5, 3, 8, 1, 4, 11}
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
 * We use level order traversal sequence with a special symbol "#" denoting the
 * null node.
 * 
 * For Example:
 * 
 * The sequence [1, 2, 3, #, #, 4] represents the following binary tree:
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
 * Hard Binary Tree
 * 
 * @author
 *
 */
public class ReconstructBinaryTreeWithLevelorderAndInorder {
	// basic idea: use the in-order to find out which part is left / right for the level order,
	// then divide and conquer recursively
	// use list container to dynamically keep the left node ints and right node ints as input param for the helper func
	public TreeNode reconstruct(int[] in, int[] level) {
		// Assumptions: level, in are not null,
		// there is no duplicate in the binary tree.
		Map<Integer, Integer> inMap = new HashMap<>();
		for (int i = 0; i < in.length; i++) {
			inMap.put(in[i], i);
		}
		List<Integer> lList = new ArrayList<>();
		for (int num : level) {
			lList.add(num);
		}
		return helper(lList, inMap);
	}
	
	private TreeNode helper(List<Integer> level, Map<Integer, Integer> inMap) {
		if (level.isEmpty()) {
			return null;
		}
		// TreeNode root = new TreeNode(level.get(0));
		TreeNode root = new TreeNode(level.remove(0)); // remove instead of get, will return [0] and remove at the same time
		List<Integer> left = new ArrayList<>();
		List<Integer> right = new ArrayList<>();
		int rootIdx = inMap.get(root.key);
		for (int num : level) {
			if (inMap.get(num) < rootIdx) {
				left.add(num);
			} else {
				right.add(num);
			}
		}
		root.left = helper(left, inMap);
		root.right = helper(right, inMap);
		return root;
	}
}
