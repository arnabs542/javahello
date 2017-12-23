package hw;
/**
 * Data Structure
Reverse Linked List
Reverse a singly-linked list.

Examples

L = null, return null
L = 1 -> null, return 1 -> null
L = 1 -> 2 -> 3 -> null, return 3 -> 2 -> 1 -> null
 * @author 
 *
 */
/**
 * class ListNode {
 *   public int value;
 *   public ListNode next;
 *   public ListNode(int value) {
 *     this.value = value;
 *     next = null;
 *   }
 * }
 */

public class Solution {
	  public ListNode reverse(ListNode head) {
		  //iterative way
		    if (head == null || head.next == null) {
		      return head;
		    }
		    ListNode left = null, right = head;
		    while (right != null) {
		      ListNode tmp = right.next;
		      right.next = left;
		      left = right;
		      right = tmp;
		    }
		    return left;
		  }
	
	  public ListNode reverse1(ListNode head) {
	    // recursive way
	    if (head == null || head.next == null) {
	      return head;
	    }
	    ListNode newHead = reverse(head.next);
	    ListNode nextNode = head.next;
	    nextNode.next = head;
	    head.next = null;
	    return newHead;
	  }
	}
class Solution1 {
  public ListNode reverse(ListNode head) {
    // write your solution here
    if (head == null)
      return null;
    ListNode dummy = new ListNode(-1);
    dummy.next = head;
    ListNode curr = head, tail = head;
    ListNode cnext = curr.next;
    while(cnext != null){
      ListNode nnext = cnext.next;
      cnext.next = dummy.next;
      dummy.next = cnext;
      cnext = nnext;
    }
    tail.next = null;
    return dummy.next;
  }
}
