package hw;

/**
 * Data Structure
Partition Linked List
Given a linked list and a target value T, partition it such that all nodes less than T are listed before the nodes larger than or equal to target value T. The original relative order of the nodes in each of the two partitions should be preserved.

Examples

L = 2 -> 4 -> 3 -> 5 -> 1 -> null, T = 3, is partitioned to 2 -> 1 -> 4 -> 3 -> 5 -> null

 * @author 
 *
 */
import utils.ListNode;
public class PartitionLinkedList {
	public ListNode partition(ListNode head, int target) {
	    // write your solution here
	    if (head == null)
	      return null;   // do NOT forget ;
	    ListNode small = new ListNode(-1), big = new ListNode(-1);
	    ListNode sdummy = small, bdummy = big;
	    while (head != null){
	      if (head.value < target){
	       small.next = head;
	       small = small.next;
	      }
	      else{
	        big.next = head;
	        big = big.next;
	      }
	      head = head.next;
	    }
	    ListNode dummy = new ListNode(-1);
	    ListNode tail = dummy;
	    if (sdummy.next != null){
	      tail.next = sdummy.next;
	      tail = small;
	    }
	    if (bdummy.next != null){
	      tail.next = bdummy.next;
	      tail = big;
	    }
	    tail.next = null;   // !!! important, otherwise overtime.
	    return dummy.next;
	  }
	
	
	/**
	 * wrong because wrong understanding of problem.
	 * read carefully.
	 * No need for middle part.  
	 * @param head
	 * @param target
	 * @return
	 */
	public ListNode partition_wrong(ListNode head, int target) {
		    // write your solution here
		    if (head == null)
		      return null;   // do NOT forget ;
		    ListNode small = new ListNode(-1), big = new ListNode(-1), mid = new ListNode(-1);
		    ListNode sdummy = small, bdummy = big, mdummy = mid;
		    while (head != null){
		      if (head.value < target){
		       small.next = head;
		       small = small.next;
		      }
		      else if (head.value == target){
		        mid.next = head;
		        mid = mid.next;
		      }else{
		        big.next = head;
		        big = big.next;
		      }
		      head = head.next;
		    }
		    ListNode dummy = new ListNode(-1);
		    ListNode tail = dummy;
		    if (sdummy.next != null){
		      tail.next = sdummy.next;
		      tail = small;
		    }
		    if (mdummy.next != null){
		      tail.next = mdummy.next;
		      tail = mid;
		    }
		    if (bdummy.next != null){
		      tail.next = bdummy.next;
		      tail = big;
		    }
		    tail.next = null;   // !!! important, otherwise overtime.

		    return dummy.next;
		  }
}
