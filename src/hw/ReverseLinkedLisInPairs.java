package hw;

import utils.ListNode;

/**
 * Data Structure Reverse Linked List In Pairs Reverse pairs of elements in a
 * singly-linked list.
 * 
 * Examples
 * 
 * L = null, after reverse is null L = 1 -> null, after reverse is 1 -> null L =
 * 1 -> 2 -> null, after reverse is 2 -> 1 -> null L = 1 -> 2 -> 3 -> null,
 * after reverse is 2 -> 1 -> 3 -> null
 * 
 * @author
 *
 */
public class ReverseLinkedLisInPairs {
	// Method 1: recursion
	public ListNode reverseInPairs(ListNode head) {
		if (head == null || head.next == null) {
			return head;
		}
		ListNode newHead = head.next;
		head.next = reverseInPairs(newHead.next); // ref is written as reverseInPairs(head.next.next);
		newHead.next = head; // should change after recursion, otherwise may be wrong
		return newHead;
	}
	
	// Method 2: iterative, the original ref did not online AC, got NPE for input {1,2,3,4,5}
	public ListNode reverseInPairs_ref_WRONG(ListNode head) {
		// this is a copy from ref, wrong!!
		ListNode dummy = new ListNode(0);
		dummy.next = head;
		ListNode cur = dummy;
		while (cur.next != null && cur.next.next != null) {
			ListNode next = cur.next.next;
			cur.next.next = cur.next.next.next;
			next.next = cur.next;
			cur = cur.next.next;
		}
		return dummy.next;
	}
	public ListNode reverseInPairs2_ref_self_AC(ListNode head) {
		ListNode dummy = new ListNode(0);
		dummy.next = head;
		ListNode pre = dummy, one = head;
		while (one != null && one.next != null) {
			ListNode two = one.next, next = two.next;
			one.next = next;
			two.next = one;
			pre.next = two;
			
			pre = pre.next.next;
			one = pre.next;
		}
		return dummy.next;
	}
	
	
	public ListNode reverseInPairs_self(ListNode head) {
		// check corner cases.
		if (head == null || head.next == null) {
			return head;
		}
		ListNode newHead = head.next;
		ListNode pre = new ListNode(-1), one = head, two = head.next, post = two.next;
		while (one != null && one.next != null) {
			two = one.next;
			post = two.next;
			two.next = one;
			one.next = post;
			pre.next = two;

			pre = pre.next.next;
			one = post;
		}
		return newHead;
	}
}
