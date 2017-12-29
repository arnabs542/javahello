package hw;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import utils.TreeNode;

class CheckIfBinaryTreeIsCompletedTest {

	@Test
	void testIsCompleted_case1() {
		CheckIfBinaryTreeIsCompleted sol = new CheckIfBinaryTreeIsCompleted();
		TreeNode root = TreeNode.createTreeFromString("5,3,8,1,4");
		boolean ans = true;
		boolean res = sol.isCompleted(root);
		assertEquals(ans, res);
	}

	@Test
	void testIsCompleted_case2() {
		CheckIfBinaryTreeIsCompleted sol = new CheckIfBinaryTreeIsCompleted();
		TreeNode root = TreeNode.createTreeFromString("5,3,8,1,4, #, 11");
		boolean ans = false;
		boolean res = sol.isCompleted(root);
		assertEquals(ans, res);
	}
	
	@Test
	void testIsCompleted_case3() {
		CheckIfBinaryTreeIsCompleted sol = new CheckIfBinaryTreeIsCompleted();
		TreeNode root = null;
		boolean ans = true;
		boolean res = sol.isCompleted(root);
		assertEquals(ans, res);
	}
}
