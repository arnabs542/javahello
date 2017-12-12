package hw;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import utils.ConsolePrinter;
import utils.ListNode;
import utils.ListNode;

class ReOrderLinkedListTest {

	@Test
	void testReorder_case1() {
		ReOrderLinkedList sol = new ReOrderLinkedList();
		int[] arr = new int[] {1,2,3,4};
		ListNode head = ListNode.array2List(arr);
		int[] ans = new int[] {1,4,2,3}; 
		head = sol.reorder(head);
		int[] res = ListNode.list2Array(head);
		ConsolePrinter.printIntArray(res);
		assertArrayEquals(ans, res);	}

}
