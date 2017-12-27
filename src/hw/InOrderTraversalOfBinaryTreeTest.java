package hw;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import utils.ConsolePrinter;
import utils.TreeNode;

class InOrderTraversalOfBinaryTreeTest {

	@Test
	void testInOrder_case1() {
		InOrderTraversalOfBinaryTree sol = new InOrderTraversalOfBinaryTree();
		TreeNode root = TreeNode.createTreeFromString("1,2,3,4,5,#,6,#,#,7");
		int[] ans = {4,2,7,5,1,3,6};
		List<Integer> resList = sol.inOrder(root);
		int[] res = resList.stream().mapToInt(i -> i).toArray();
		ConsolePrinter.printIntArray(res);
		Assert.assertTrue(Arrays.equals(res, ans));
	}

	@Test
	void testInOrder_case2() {
		InOrderTraversalOfBinaryTree sol = new InOrderTraversalOfBinaryTree();
		TreeNode root = TreeNode.createTreeFromString("1,2,3,4,5,#,6,#,#,7");
		int[] ans = {4,2,7,5,1,3,6};
		List<Integer> resList = sol.inOrder(root);
		int[] res = resList.stream().mapToInt(i -> i).toArray();
		ConsolePrinter.printIntArray(res);
		Assert.assertTrue(Arrays.equals(res, ans));
	}
	
}
