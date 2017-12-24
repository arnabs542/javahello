package hw;

/**
 * Data Structure
Check If Linked List Has A Cycle
Check if a given linked list has a cycle. Return true if it does, otherwise return false. 

 


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
  public boolean hasCycle(ListNode head) {
    if (head == null)
      return false;
    ListNode slow = head, fast = head;
    while (fast != null && fast.next != null){
      slow = slow.next;
      fast = fast.next.next;
      if (slow == fast)
        return true;
    }
    return false;
  }
  
  
}
