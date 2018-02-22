package hw;

import java.util.HashMap;
import java.util.Map;

import utils.RandomListNode;

/**
 * 
 * Each of the nodes in the linked list has another pointer pointing to a random
 * node in the list or null. Make a deep copy of the original list.
 * 
 * Medium Hashtable Linked List
 * 
 * @author
 *
 */
public class DeepCopyLinkedListWithRandomPointer {
	// self, AC
	public RandomListNode copy(RandomListNode head) {
		if (head == null) {
			return null;
		}
		Map<RandomListNode, RandomListNode> map = new HashMap<>();
		RandomListNode curSrc = head; // current node in src to copy from
		RandomListNode dummy = new RandomListNode(-1);
		RandomListNode curTarget = dummy;  // current node in target as the tail of already copied list
		while (curSrc != null) {
			if (!map.containsKey(curSrc)) {
				RandomListNode tmp = new RandomListNode(curSrc.value);
				map.put(curSrc, tmp);
			}
			curTarget.next = map.get(curSrc);
			curTarget = curTarget.next;
			// process random link
			if (curSrc.random != null && !map.containsKey(curSrc.random)) {
				RandomListNode tmp = new RandomListNode(curSrc.random.value);
				map.put(curSrc.random, tmp);
			}
			curTarget.random = map.get(curSrc.random); // if null will return null
			// move src pointer
			curSrc = curSrc.next;
		}
		return dummy.next;
	}
	
	/////////////////////////////////////////////////////
	
	// Method 1: using HashMap to avoid copy multiple times for the same node.
	public RandomListNode copy_REF(RandomListNode head) {
		if (head == null) {
			return null;
		}
		// Sentinel node to help construct the deep copy.
		RandomListNode dummy = new RandomListNode(-1);
		RandomListNode cur = dummy;
		// Maintains the mapping between the node in the original list and
		// the corresponding node in the new list.
		Map<RandomListNode, RandomListNode> map = new HashMap<>();
		while (head != null) {
			// copy the current node if necessary.
			if (!map.containsKey(head)) {
				map.put(head, new RandomListNode(head.value));  // --> save the tmp variable
			}
			// Connect the copied node to the deep copy list.
			cur.next = map.get(head);
			
			// copy the random node if necessary
			if (head.random != null) {
				if (!map.containsKey(head.random)) {
					map.put(head.random, new RandomListNode(head.random.value));
				}
				cur.next.random = map.get(head.random); 
			}
			
			head = head.next;
			cur = cur.next;
		}
		return dummy.next;
	}
	
	
	// Method 2: another three pass solution, not using HashMap,
	// but changing the original list structure during the copy
	// (it will be changed back at the end).
	public RandomListNode copy_REF2(RandomListNode head) {
		if (head == null) {
			return null;
		}
		// First pass, for each node in the original list, insert a 
		// copied node between the node and node.next.
		RandomListNode cur = head;
		while (cur != null) {
			// Make a copy of cur node, insert it to the middle of cur and cur.next.
			RandomListNode copy = new RandomListNode(cur.value);
			copy.next = cur.next;
			cur.next = copy;
			cur = cur.next.next;
		}
		// Second pass, link the random pointer for the copied node.
		cur = head;
		while (cur != null) {
			if (cur.random != null) {
				cur.next.random = cur.random.next; // cur.random is the orignal node, orignal.next is the copied node
			}
			cur = cur.next.next; // should be outside the if !!! pay attention
		}
		// Third pass, extract the copied node.
		cur = head;
		RandomListNode dummy = new RandomListNode(-1);
		RandomListNode copyPrev = dummy;
		while (cur != null) {
			copyPrev.next = cur.next;
			cur.next = cur.next.next;
			copyPrev =copyPrev.next;
			cur = cur.next;
		}
		return dummy.next;
	}
}
