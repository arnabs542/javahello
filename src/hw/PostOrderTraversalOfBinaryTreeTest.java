package hw;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import utils.ConsolePrinter;
import utils.TreeNode;

class PostOrderTraversalOfBinaryTreeTest {

	@Test
	void testInOrder_case1() {
		PostOrderTraversalOfBinaryTree sol = new PostOrderTraversalOfBinaryTree();
		TreeNode root = TreeNode.createTreeFromString("1,2,3,4,5,#,6,#,#,7");
		int[] ans = {4, 7, 5, 2, 6, 3, 1};
		List<Integer> resList = sol.postOrder(root);
		int[] res = resList.stream().mapToInt(i -> i).toArray();
		ConsolePrinter.printIntArray(res);
		Assert.assertTrue(Arrays.equals(res, ans));
	}

	
	@Test
	void testInOrder_case2() {
		PostOrderTraversalOfBinaryTree sol = new PostOrderTraversalOfBinaryTree();
		TreeNode root = TreeNode.createTreeFromString("5, 3, 8, 1, 4, #, 11");
		int[] ans = {1, 4, 3, 11, 8, 5};
		List<Integer> resList = sol.postOrder(root);
		int[] res = resList.stream().mapToInt(i -> i).toArray();
		ConsolePrinter.printIntArray(res);
		Assert.assertTrue(Arrays.equals(res, ans));
	}
}
