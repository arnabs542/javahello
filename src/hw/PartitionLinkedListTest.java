package hw;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import utils.ConsolePrinter;
import utils.ListNode;

class PartitionLinkedListTest {

	@Test
	void testPartition_case1() {
		PartitionLinkedList sol = new PartitionLinkedList();
		int[] arr = new int[] {2,4,3,5,1};
		int target = 3;
		int[] ans = new int[] {2,1,4,3,5}; 
		ListNode head = ListNode.array2List(arr);
		head = sol.partition(head, target);
		int[] res = ListNode.list2Array(head);
		ConsolePrinter.printIntArray(res);
		assertArrayEquals(ans, res);
	}

}
