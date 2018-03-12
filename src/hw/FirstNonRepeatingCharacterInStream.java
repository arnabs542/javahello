package hw;

import java.util.HashMap;
import java.util.HashSet;

/**
 * 
 * Given a stream of unlimited characters, keep track of the FIRST non-repeating character from stream.
 * 
 * You need to tell the first non-repeating character in O(1) time at any monent.
 * 
 * @author 
 *
 */
public class FirstNonRepeatingCharacterInStream {
	// each node is a double linked list node
	// and it contains one distinct character.
	static class Node {
		Node prev;
		Node next;
		Character ch;
		
		Node(Character ch) {
			this.ch = ch;
		}
	}
	
	// record the head and tail of the list all the time.
	// only the characters appearing just once will be 
	// in the double linked list.
	private Node head;
	private Node tail;
	// for any character, it could be three state:
	// 1. not existed yet, it will not be in singled Map or repeated Set.
	// 2. only existed once, it will be in singled Map and there will be a corresponding node in the list.
	// 3. exists more than twice, it will be in the repeated Set.
	// and it will be removed from the list.
	private HashMap<Character, Node> singled;
	private HashSet<Character> repeated;
	
	public FirstNonRepeatingCharacterInStream() {
		// an example of using sentinel node to eliminate some corner cases.
		tail = new Node(null);
		tail.next = tail.prev = tail;
		head = tail;
		singled = new HashMap<Character, Node>();
		repeated = new HashSet<Character>();
	}
	
	public void read(char ch) {
		// if the character already exists more than once,
		// we just ignore.
		if (repeated.contains(ch)) {
			return;
		}
		Node node = singled.get(ch);
		if (node == null) {
			// if the character appears for the first time,
			// it should be added to the list and added to the nonRepeated.
			node = new Node(ch);
			append(node);
		} else {
			// if the character is already in the nonRepeated Map,
			// we should remove it from the Map and list,
			// and put it into the repeated Set.
			remove(node);
		}
	}
	
	// --> operate both map and doubly linked list
	// --> not necessary to make the list as a loop ?? --> loop is good for saving the time to check null head/tail,
	private void append(Node node) {
		singled.put(node.ch, node);
		tail.next = node;
		node.prev = tail;
		node.next = head;
		tail = tail.next;
	}
	
	private void remove(Node node) {
		repeated.add(node.ch);
		singled.remove(node.ch);
		// use sentinel node so that some of the corner cases will be eliminated.
		node.prev.next = node.next;
		node.next.prev = node.prev;
		if (node == tail) {
			tail = node.prev;
		}
//		if (node == head) { // ??? in REF this if is not there --> because there is dummy / sentinel head
//			head = head.next;
//		}
		node.prev = node.next = null;
	}
	
	public Character firstNonRepeating() {
		// when head == tail, it means there is only
		// the sentinel node in the list.
		if (head == tail) {
			return null;
		}
		return head.next.ch;
	}
	
	
}
