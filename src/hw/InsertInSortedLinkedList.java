package hw;

import utils.ListNode;

/**
 * Insert a value in a sorted linked list.

Examples

L = null, insert 1, return 1 -> null
L = 1 -> 3 -> 5 -> null, insert 2, return 1 -> 2 -> 3 -> 5 -> null
L = 1 -> 3 -> 5 -> null, insert 3, return 1 -> 3 -> 3 -> 5 -> null
L = 2 -> 3 -> null, insert 1, return 1 -> 2 -> 3 -> null
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
public class InsertInSortedLinkedList {
  public ListNode insert(ListNode head, int value) {
    // write your solution here
    ListNode target = new ListNode(value);
    ListNode dummy = new ListNode(-1);
    dummy.next = head;
    ListNode left = dummy, right = head; // typo of dummy !!!
    while (right != null && right.value < value){ // && not and !!!
      left = right;
      right = right.next;
    }
    left.next = target;
    target.next = right;
      
    return dummy.next;
  }
}
