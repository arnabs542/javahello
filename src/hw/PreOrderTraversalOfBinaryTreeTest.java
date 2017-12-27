package hw;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import utils.ConsolePrinter;
import utils.TreeNode;

class PreOrderTraversalOfBinaryTreeTest {

	@Test
	void testInOrder_case1() {
		PreOrderTraversalOfBinaryTree sol = new PreOrderTraversalOfBinaryTree();
		TreeNode root = TreeNode.createTreeFromString("1,2,3,4,5,#,6,#,#,7");
		int[] ans = {1,2,4,5,7,3,6};
		List<Integer> resList = sol.preOrder(root);
		int[] res = resList.stream().mapToInt(i -> i).toArray();
		ConsolePrinter.printIntArray(res);
		Assert.assertTrue(Arrays.equals(res, ans));
	}

	
	@Test
	void testInOrder_case2() {
		PreOrderTraversalOfBinaryTree sol = new PreOrderTraversalOfBinaryTree();
		TreeNode root = TreeNode.createTreeFromString("5, 3, 8, 1, 4, #, 11");
		int[] ans = {5, 3, 1, 4, 8, 11};
		List<Integer> resList = sol.preOrder(root);
		int[] res = resList.stream().mapToInt(i -> i).toArray();
		ConsolePrinter.printIntArray(res);
		Assert.assertTrue(Arrays.equals(res, ans));
	}
}
