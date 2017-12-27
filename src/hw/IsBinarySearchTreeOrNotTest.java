package hw;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import utils.TreeNode;

class IsBinarySearchTreeOrNotTest {

	@Test
	void testIsBST_case1() {
		IsBinarySearchTreeOrNot sol = new IsBinarySearchTreeOrNot();
		String[] vals = {"9","7","10","#","#","8","12"};
		TreeNode root = TreeNode.createTreeFromStringArr(vals);
		boolean ans = false;
		boolean res = sol.isBST(root);
		assertEquals(res, ans);
	}

}
