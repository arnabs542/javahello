package hw.review;

import java.util.ArrayList;
import java.util.List;

import utils.TreeNode;

public class DiagonalSumOfBinaryTree {

	public List<Integer> getDiagonalSum(TreeNode root) {
		if (root == null) {
			return null;
		}
		List<Integer> result = new ArrayList<>();
		dfs(root, 0, result);
		return result;
	}
	
	private void dfs(TreeNode root, int level, List<Integer> result) {
		if (root == null) {
			return;
		}
		if (level >= result.size()) {
			result.add(root.key);
		} else {
			int tmp = result.get(level);
			result.add(level, tmp + root.key);  // tocheck
		}
		dfs(root.left, level + 1, result);
		dfs(root.right, level, result);
	}
}
