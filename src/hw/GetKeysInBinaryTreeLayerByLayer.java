package hw;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

import utils.TreeNode;

/**
 * Data Structure
Get Keys In Binary Tree Layer By Layer
Get the list of list of keys in a given binary tree layer by layer. Each layer is represented by a list of keys and the keys are traversed from left to right.

Examples

        5

      /    \

    3        8

  /   \        \

 1     4        11

the result is [ [5], [3, 8], [1, 4, 11] ]

Corner Cases

What if the binary tree is null? Return an empty list of list in this case.
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

/**
// https://docs.oracle.com/javase/tutorial/java/generics/inheritance.html
// This is a common misunderstanding when it comes to programming with generics, 
// but it is an important concept to learn.
 * 
 * 	Box<Number> box = new Box<Number>();
	box.add(new Integer(10));   // OK
	box.add(new Double(10.1));  // OK
	
 * Now consider the following method:

	public void boxTest(Box<Number> n) {  }
	
	What type of argument does it accept? By looking at its signature, 
	you can see that it accepts a single argument whose type is Box<Number>. 
	But what does that mean? Are you allowed to pass in Box<Integer> or Box<Double>, 
	as you might expect? The answer is "no", 
	because Box<Integer> and Box<Double> are not subtypes of Box<Number>.
	For information on how to create a subtype-like relationship between two generic 
	classes when the type parameters are related, see Wildcards and Subtyping.
 * 
 */

public class GetKeysInBinaryTreeLayerByLayer {
	public List<List<Integer>> layerByLayer(TreeNode root) {
		ArrayList<List<Integer>> res = new ArrayList<List<Integer>>();
		//ArrayList<List<Integer>> res = new ArrayList<ArrayList<Integer>>(); 
		// -- the above is also wrong 'type mismatch, can not convert from al<al<int>> to al<l<int>>'
		// ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>(); 
		// -- the above self ok, return res shows type mismatch, check comments above
		
		if (root == null) {
			return res;
		}
		Deque<TreeNode> seeds = new LinkedList<TreeNode>();
		seeds.offerLast(root);
		while (!seeds.isEmpty()) {
			ArrayList<Integer> curLayer = new ArrayList<Integer>(); //!!! integer not treenode here.
			int layerSize = seeds.size();
			for (int i = 0; i < layerSize; i++) {
				TreeNode tmp = seeds.pollFirst();
				curLayer.add(tmp.key);
				if (tmp.left != null) {
					seeds.offerLast(tmp.left);
				}
				if (tmp.right != null) {
					seeds.offerLast(tmp.right);
				}
			}
			res.add(curLayer);

		}
		return res;
	}
}
