package hw.practice;

import java.util.Arrays;

public class MyHashMapRef<K, V> {
	private static class Node<K, V> {
		private final K key;
		private V value;
		private Node<K, V> next = null;
		
		public Node(K k, V v) {
			key = k;
			value = v;
		}
		
		public K getKey() {
			return key;
		}
		
		public V getValue() {
			return value;
		}
		
		public void setValue(V v) {
			value = v;
		}
		
		Node<K, V> getNext() {
			return next;
		}
		
		void setNext(Node<K, V> next) {
			this.next = next;
		}
		
	}
	
	private static final int INIT_CAP = 16;
	private static final double LOAD_FACTOR = 0.7;
	
	private Node<K, V>[] array;
	private int size;
	
	public MyHashMapRef() {
		array = (Node<K, V>[]) new Node[INIT_CAP];
		size = 0;
	}
	
	public int size() {
		return size;
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	public void clear() {
		Arrays.fill(array, null);
		size = 0;
	}
	
	public V get(K key) {
		Node<K, V> node = array[index(key)];
		while (node != null) {
			if (equalsKey(node.getKey(), key)) {
				return node.getValue();
			}
			node = node.getNext();
		}
		return null;
	}
	
	public boolean containsKey(K key) {
		Node<K, V> node = array[index(key)];
		while (node != null) {
			if (equalsKey(node.getKey(), key)) {
				return true;
			}
			node = node.getNext();
		}
		return false;
	}
	
	public V put(K key, V value) {
		int i = index(key);
		Node<K, V> node = array[i];
		// case 1: if there is existing node of key, update the value
		while (node != null) {
			if (equalsKey(node.getKey(), key)) {
				V oldValue = node.getValue();
				node.setValue(value);
				return oldValue;
			}
			node = node.getNext();
		}
		// case 2: no existing node, need to add new entry
		// first insert, then check if need rehashing.
		Node<K, V> newEntry = new Node<>(key, value);
		newEntry.setNext(array[i]);
		array[i] = newEntry;
		size++; // !!! don't forget
		if (needRehashing()) {
			rehash();
		}
		return null; // no existing node, oldvalue is treated as null
	}
	
	public V remove(K key) {
		int i = index(key);
		Node<K, V> prev = null;
		Node<K, V> curr = array[i];
		while (curr != null) {
			if (equalsKey(curr.getKey(), key)) {
				if (prev == null) {
					array[i] = curr.getNext();
				} else {
					prev = curr.getNext();
				}
				size--;
				return curr.getValue();
			}
			prev = curr;
			curr = curr.getNext();
		}
		return null;
	}
	
	private int hash(K key) {
		if (key == null) return 0;
		int code = key.hashCode();
		return code & 0x7fffffff;
	}
	
	private int index(K key) {
		return hash(key) % array.length;
	}
	
	// covers the null condition!!
	private boolean equalsKey(K a, K b) {
		return a == b || a != null && a.equals(b);
	}
	
	private boolean needRehashing() {
		return size > LOAD_FACTOR * array.length;
	}
	
	private void rehash() {
		Node<K, V>[] old = array;
		array = (Node<K, V>[]) new Node[old.length * 2];
		for (Node<K, V> e : old) {
			while (e != null) {
				Node<K, V> next = e.getNext();
				int i = index(e.getKey());
				e.setNext(array[i]);
				array[i] = e;
				e = next;
			}
		}
		
	}
	

}
