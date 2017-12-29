package hw;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import utils.TreeNode;

class GetKeysInBinaryTreeLayerByLayerTest {

	@Test
	void testLayerByLayer_case1() {
		GetKeysInBinaryTreeLayerByLayer sol = new GetKeysInBinaryTreeLayerByLayer();
		TreeNode root = TreeNode.createTreeFromString("5,3,8,1,4,#,11");
		List<List<Integer>> res = sol.layerByLayer(root);
		ArrayList<List<Integer>> ans = new ArrayList<List<Integer>>();
		//ans = [ [5], [3, 8], [1, 4, 11] ];
		ArrayList<Integer> r1 = new ArrayList<Integer>();
		r1.add(5);
		ArrayList<Integer> r2 = new ArrayList<Integer>();
		r2.addAll(List.of(3, 8));
		ArrayList<Integer> r3 = new ArrayList<Integer>();
		r3.addAll(List.of(1, 4, 11));
		ans.add(r1);
		ans.add(r2);
		ans.add(r3);
		assertTrue(ans.equals(res));
	}

}
