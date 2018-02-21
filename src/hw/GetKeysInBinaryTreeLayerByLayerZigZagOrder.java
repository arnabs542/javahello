package hw;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

import utils.TreeNode;

/**
 * 
 * 
 * Get the list of keys in a given binary tree layer by layer in zig-zag order.
 * 
 * Examples
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
 * the result is [5, 3, 8, 11, 4, 1]
 * 
 * Corner Cases
 * 
 * What if the binary tree is null? Return an empty list in this case. How is
 * the binary tree represented?
 * 
 * We use the level order traversal sequence with a special symbol "#" denoting
 * the null node.
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
 * Medium Binary Tree Breadth First Search
 * 
 * @author
 *
 */
public class GetKeysInBinaryTreeLayerByLayerZigZagOrder {
	public List<Integer> zigZag(TreeNode root) {
		Deque<TreeNode> deque = new LinkedList<>();
		if (root == null) {
			// return deque; // type not match compile time error
			return new LinkedList<>();
		}
		List<Integer> list = new LinkedList();
		deque.offerFirst(root);
		int layer = 0;
		while (!deque.isEmpty()) {
			int size = deque.size();
			for (int i = 0; i < size; i++) {
				if (layer == 0) {
					// at even layer, from right to left.
					// so adding next layer nodes to the other side
					// first right, then left
					TreeNode tmp = deque.pollLast();
					list.add(tmp.key);
					if (tmp.right != null) {
						deque.offerFirst(tmp.right);
					}
					if (tmp.left != null) {
						deque.offerFirst(tmp.left);
					}
				} else {
					// odd layer, from left to right.
					TreeNode tmp = deque.pollFirst();
					list.add(tmp.key);
					if (tmp.left != null) {
						deque.offerLast(tmp.left);
					}
					if (tmp.right != null) {
						deque.offerLast(tmp.right);
					}
				}
				
			}
			layer = 1 - layer; // !!! update layer

		}
		return list;
	}
}
