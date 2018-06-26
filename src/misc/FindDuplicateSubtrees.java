package misc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import utils.TreeNode;

/**
 * 
 * 652. Find Duplicate Subtrees
DescriptionHintsSubmissionsDiscussSolution
Given a binary tree, return all duplicate subtrees. For each kind of duplicate subtrees, you only need to return the root node of any one of them.

Two trees are duplicate if they have the same structure with same node values.

Example 1:

        1
       / \
      2   3
     /   / \
    4   2   4
       /
      4
The following are two duplicate subtrees:

      2
     /
    4
and

    4
Therefore, you need to return above trees' root in the form of a list.

 * 
 * 
 * @author 
 *
 */



/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */


/**
 * Basic Idea: post order serialization, AC as 86%
 * 
 * Time: O(n^2)
 * Space: O(n^2)
 * 
 * @author 
 *
 */
public class FindDuplicateSubtrees {
    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
       List<TreeNode> result = new ArrayList<>();
       if (root == null) {
    	   return result;
       }
       Map<String, Integer> map = new HashMap<>();
       StringBuilder sb = new StringBuilder();
       helperPostOrder(root, map, sb, result);
       return result;
    }
    
    private void helperPostOrder(TreeNode root, Map<String, Integer> map, StringBuilder sb, List<TreeNode> result) {
    	if (root == null) {
    		return;
    	}
    	int left = sb.length();
    	sb.append("(");
    	helperPostOrder(root.left, map, sb, result);
    	sb.append(",");
    	helperPostOrder(root.right, map, sb, result);
    	sb.append(",");
    	sb.append(root.val + "");
    	sb.append(")");
    	int right = sb.length();
    	String hash = sb.substring(left);
    	int count = map.getOrDefault(hash, 0);
    	if (count == 1) {
    		// repeat second time, douplicated found, add to result
    		result.add(root);
    		// !!! don't forget to + 1 for count  ==> can simplify code of count + 1 to all cases
    	} 
    	map.put(hash, count + 1);
    	
//    	else if (count == 0) {
//    		// new subtree, add to map
//    		map.put(hash, 1);
//    	} else {
//    		// found on 3rd time onward
//    		map.put(hash, count + 1);
//    	}
    }
}

/**
 * 
 * Global value numbering, AC ~ 90%
 * 
 * View each root as an operator, view each left/right child as an operand, then a big expression
 * 
 * Time: O(n)
 * Space: O(n)
 * 
 * 
 * @author 
 *
 */

class FindDuplicateSubtrees2 {
    private int nextNumber = 1;
    
	public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        List<TreeNode> result = new ArrayList<>();
        if (root == null) {
     	   return result;
        }
        Map<Expression, Integer> expressMap = new HashMap<>();
        Map<TreeNode, Integer> nodeMap = new HashMap<>();
        // label each treenode with a global number, store in nodeMap, same subtree root has same number
        numberNodes(root, nodeMap, expressMap);
        // process the result according to nodeMap
        Map<Integer, Integer> countMap = new HashMap<>();
        for (Map.Entry<TreeNode, Integer> entry: nodeMap.entrySet()) {
        	int count = countMap.getOrDefault(entry.getValue(), 0);
        	if (count == 1) {
        		result.add(entry.getKey());
        	}
        	countMap.put(entry.getValue(), count + 1);
        }
        return result;
    }
    
    private int numberNodes(TreeNode root, Map<TreeNode, Integer> nodeNumberMap, 
    		Map<Expression, Integer> expressNumberMap) {
    	if (root == null) {
    		return 0;
    	}
    	int leftNumber = numberNodes(root.left, nodeNumberMap, expressNumberMap);
    	int rightNumber = numberNodes(root.right, nodeNumberMap, expressNumberMap);
    	Expression expression = new Expression(root.val, leftNumber, rightNumber);
    	Integer rootNumber = expressNumberMap.get(expression);
    	if (rootNumber == null) {
    		rootNumber = nextNumber;
    		nextNumber++;
    		expressNumberMap.put(expression, rootNumber);
    	}
    	nodeNumberMap.put(root, rootNumber);
    	return rootNumber;
    }
    
    private static class Expression{
    	int root;
    	int left;
    	int right;
    	public final String key;
    	
    	Expression(int root, int left, int right) {
    		this.root = root;
    		this.left = left;
    		this.right = right;
    		key = Integer.toString(root) + "," + Integer.toString(left) + "," + Integer.toString(right);
    	}
    	
    	@Override
    	public int hashCode() {
    		return key.hashCode();
    	}
    	
    	@Override
    	public boolean equals(Object obj) {
    		// null or not same class 
    		if (obj == null || obj.getClass() != this.getClass()) {
    			return false;
    		}
    		// same obj as this
    		if (this == obj) {
    			return true;
    		}
    		return key.equals(((Expression)obj).key);
    	}
    }
}
