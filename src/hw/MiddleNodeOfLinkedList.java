package hw;


/**
 * 
 * Data Structure
Middle Node Of Linked List
Find the middle node of a given linked list.

Examples

L = null, return null
L = 1 -> null, return 1
L = 1 -> 2 -> null, return 1
L = 1 -> 2 -> 3 -> null, return 2
L = 1 -> 2 -> 3 -> 4 -> null, return 2
 * 
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

public class Solution {
	  public ListNode middleNode(ListNode head) {
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
	}

