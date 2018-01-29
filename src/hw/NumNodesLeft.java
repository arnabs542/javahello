/**
 * StoreNumberOfNodesInLeftSubtree
 * 
 * 
 */
package hw;

public class NumNodesLeft {
	static class TreeNode {
		int key;
		TreeNode left;
		TreeNode right;
		// store the # of nodes in left subtree.
		int numNodesLeft;
		
		public TreeNode(int key) {
			this.key = key;
		}
	}
	
	public void numNodesLeft(TreeNode root) {
		numNodes(root);
	}
	
	// return the # of nodes in the subtree.
	private int numNodes(TreeNode root) {
		if (root == null) {
			return 0;
		}
		int leftNum = numNodes(root.left);
		int rightNum = numNodes(root.right);
		root.numNodesLeft = leftNum;
		return leftNum + rightNum + 1;
	}
}
