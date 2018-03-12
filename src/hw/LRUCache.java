package hw;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * Implement LRU Cache
 * 
 * Description
 * 
 * Implement a least recently used cache. It should provide set(), get()
 * operations. If not exists, return null (Java), false (C++).
 * 
 * Medium Hashmap Heap Linked List
 * 
 * @author
 *
 */

// first need a hash map
// second need to keep a doubly linked list for recording recent use
// since map need to be synced with list, how? use Node (in list) as value of the hashmap
// Node need to have both key and value, so list-->map is also possible when need to delete oldest node
public class LRUCache<K, V> {

	private final int limit; // final!!!
	private Node<K, V> head;
	private Node<K, V> tail;
	private Map<K, Node<K, V>> map; // !!! Node<K, V> !!! Map

	// limit is the max capacity of the cache
	public LRUCache(int limit) {
		this.limit = limit;
		this.map = new HashMap<K, Node<K, V>>();
	}

	public void set(K key, V value) {
		// 1) exist, update map value, update to head
		// 2) new, put to map, update to head, if cache full, delete tail in map and list
		Node<K, V> node = null;
		// 1. if the key already in the cache, we need to update its value
		// and move it to head (most ercent position).
		if (map.containsKey(key)) {
			node = map.get(key);
			node.value = value;
			remove(node);  // --> use remove and append two ops to adjust node in list
			// append(node);
		} else if (map.size() < limit){
			// 2. if the key is not in the cache and we still have space,
			// we can add append a new node to head.
			node = new Node<>(key, value);
			// map.put(key, node);  // ---> no need, REUSE the node, the map updated at the same time. 
			// append(node);
		} else {
			// 3. if the key is not in the cache and we don't have space,
			// we need to evict the tail and REUSE the node let it maintain 
			// the new key, value and put it to head.
			node = tail;
			remove(node);
			node.update(key, value);
			// map.put(key, node);
			// append(node);
		}
		append(node); // !!this is a common step for all 3 cases, can be moved here to make code cleaner
	}

	public V get(K key) {
		// get the value (check if exist)
		// update the linkedlist
		Node<K, V> node = map.get(key);
		if (node == null) {
			return null;
		}
		// even it is a read operation, it is still a write operation to
		// the double linked list, and we need to move the node to head.
		remove(node);
		append(node);
		return node.value;
	}

	private Node<K, V> remove(Node<K, V> node) { // !!! should return the removed node, not void
		map.remove(node.key);  // ??? why operate map here?
		if (node.prev != null) { // !!! always check for null case
			node.prev.next = node.next;
		}
		if (node.next != null) { // !!! always check for null case
			node.next.prev = node.prev;
		}
		if (node == head) { // !!! check when node is head or tail
			head = head.next;
		}
		if (node == tail) {
			tail = tail.prev;
		}
		node.next = node.prev = null; // !!! remove unnecessary link of node
		return node;
	}

	private Node<K, V> append(Node<K, V> node) {
		map.put(node.key, node);  // ??? why operate map here?
		if (head == null) {
			head = tail = node;
		} else {
			node.next = head;
			head.prev = node;
			head = node;
		}
		return node;
	}

	static class Node<K, V> {
		K key;
		V value;
		Node<K, V> next;
		Node<K, V> prev;

		Node(K key, V value) {
			this.key = key;
			this.value = value;
		}

		void update(K key, V value) {
			this.key = key;
			this.value = value;
		}

	}

}
