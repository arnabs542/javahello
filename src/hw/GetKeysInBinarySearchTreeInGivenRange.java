package hw;

import java.util.ArrayList;
import java.util.List;

import utils.TreeNode;

/**
 * 
 * Recursion
Get Keys In Binary Search Tree In Given Range
Get the list of keys in a given binary search tree in a given range[min, max] in ascending order, both min and max are inclusive.

Examples

        5

      /    \

    3        8

  /   \        \

 1     4        11

get the keys in [2, 5] in ascending order, result is  [3, 4, 5]

Corner Cases

What if there are no keys in the given range? Return an empty list in this case.
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
 * public class TreeNode { public int key; public TreeNode left; public TreeNode
 * right; public TreeNode(int key) { this.key = key; } }
 */
public class GetKeysInBinarySearchTreeInGivenRange {
	public List<Integer> getRange(TreeNode root, int min, int max) {
		ArrayList<Integer> res = new ArrayList<Integer>();
		helper(root, min, max, res);
		return res;
	}

	private void helper(TreeNode root, int min, int max, ArrayList<Integer> res) {
		if (root == null) {
			return;
		}
		if (root.key > min) {
			helper(root.left, min, max, res);
		}
		// the order of checking left, self key, and right matters!!!
		// problem requires that keys in ascending order
		if (root.key >= min && root.key <= max) {
			res.add(root.key);
		}
		if (root.key < max) {
			helper(root.right, min, max, res);
		}

	}
}
