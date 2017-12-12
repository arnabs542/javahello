package hw;

/**
 * 
 * 
 * Data Structure
ReOrder Linked List
Reorder the given singly-linked list N1 -> N2 -> N3 -> N4 -> бн -> Nn -> null to be N1- > Nn -> N2 -> Nn-1 -> N3 -> Nn-2 -> бн -> null

Examples

L = null, is reordered to null
L = 1 -> null, is reordered to 1 -> null
L = 1 -> 2 -> 3 -> 4 -> null, is reordered to 1 -> 4 -> 2 -> 3 -> null
L = 1 -> 2 -> 3 -> null, is reordred to 1 -> 3 -> 2 -> null


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

import utils.ListNode;
public class ReOrderLinkedList {
  public ListNode reorder(ListNode head) {
    if (head == null)
      return null;
    // find the two halves of thel list;
    ListNode mid = this.middleNode(head);
    ListNode one = head, two = mid.next;
    mid.next = null;
    
    // reverse the last half;
    two = this.reverse(two);
    
    // merge the two list;
    return this.merge(one, two);
  }
  
  
  private ListNode middleNode(ListNode head) {
	    // write your solution here
	    if(head == null)
	      return null;
	    ListNode dummy = new ListNode(-1);
	    dummy.next = head;
	    ListNode fast = dummy, slow = dummy;
	    while(fast != null && fast.next != null){  //!!! first check fast
	      slow = slow.next;
	      fast = fast.next.next;
	    }
	    return slow;
	  }
	  
	private ListNode reverse(ListNode head) {
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
  
  private ListNode merge(ListNode one, ListNode two){
    ListNode dummy = new ListNode(-1);
    ListNode tail = dummy;
    while (one != null && two != null){
      tail.next = one;
      tail = tail.next;
      tail.next = two;
      tail = tail.next;
      one = one.next;
      two = two.next;
    }
    if (one != null){
      tail.next = one;
    }
    return dummy.next;
  }
  
}
