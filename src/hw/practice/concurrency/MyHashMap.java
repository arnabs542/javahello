package hw.practice.concurrency;

import java.util.Arrays;

public class MyHashMap<K, V> {
	static class Node<K, V> {
		final K key;
		V value;
		Node<K, V> next;
		
		Node(K key, V value) {
			this.key = key;
			this.value = value;
		}
		
		public K getKey() {
			return key;
		}
		
		public V getValue() {
			return value;
		}
		
		public void setValue(V value) {
			this.value = value;
		}
	}
	
	private Node<K, V>[] array;
	private int size;
	private float loadFactor;
	
	private static final int DEFAULT_CAPACITY = 100;
	private static final float DEFAULT_LOAD_FACTOR = 0.8f;
	
	public MyHashMap(int cap, float loadFactor) {
		if (cap <= 0) {
			throw new IllegalArgumentException("cap can not be <= 0.");
		}
		array =  (Node<K, V>[])(new Node[cap]);  // why needs type conversion? generic
		this.loadFactor = loadFactor;
		size = 0;
	}
	
	public MyHashMap() {
		this(DEFAULT_CAPACITY, DEFAULT_LOAD_FACTOR);
	}
	
	public synchronized int size() {
		return size;
	}
	
	public synchronized boolean isEmpty() {
		return size == 0;
	}
	
	public synchronized void clear() {
		Arrays.fill(array, null);
		size = 0;
	}
	
	private int hash(K key) {
		if (key == null) {
			return 0;
		}
		int code = key.hashCode();
		return code & 0x7FFFFFFF;
	}
	
	private int getIndex(int hash) {
		return hash % array.length;
	}
	
	private boolean equalsKey(K k1, K k2) {
		// primitive value ==, obj address ==, obj value equals
		return k1 == k2 || k1 != null && k1.equals(k2);
	}
	
	public synchronized V put(K key, V value) {
		int index = getIndex(hash(key));
		Node<K, V> cur = array[index];
		while (cur != null) {
			// check if there are existing node
			if (equalsKey(cur.key, key)) {
				// found, get old value, update value, and return old value
				V result = cur.value;
				cur.value = value;
				return result;
			}
			cur = cur.next;
		}
		Node<K, V> newHead = new Node<>(key, value);
		newHead.next = array[index];
		array[index] = newHead;
		size++;
		if (needRehashing()) {
			rehashing();
		}
		return null;
	}
	
	private boolean needRehashing() {
		float ratio = (size + 0.0f) / array.length;
		return ratio >= loadFactor;
	}
	
	private boolean rehashing() {
		// new double size array;
		// for each node in the old array
		// do the put() operation to the new larger array
		return true;
	}
	
	public synchronized V get(K key) {
		int index = getIndex(hash(key));
		Node<K, V> cur = array[index];
		while (cur != null) {
			if (equalsKey(cur.key, key)) {
				return cur.value;
			}
			cur = cur.next;
		}
		// when not found
		return null;
	}
	
	public synchronized boolean containsKey(K key) {
		int index = getIndex(hash(key));
		for (Node<K, V> node = array[index]; node != null; node = node.next) {
			if (equalsKey(node.key, key)) {
				return true;
			}
		}
		return false;
	}
	
	private boolean equalsValue(V v1, V v2) {
		return v1 == v2 || v1 != null && v1.equals(v2);
	}
	
	public synchronized boolean containsValue(V value) {
		if (isEmpty()) {
			return false;
		}
		for (Node<K, V> node : array) {
			while (node != null) {
				if (equalsValue(node.value, value)) {
					return true;
				}
				node = node.next;
			}
		}
		return false;
	}
	
	public synchronized V remove(K key) {
		int index = getIndex(hash(key));
		Node<K, V> prev = null;
		Node<K, V> cur = array[index];
		while(cur != null) {
			if (equalsKey(cur.key, key)) {
				// check if it's the head node
				if (prev == null) {
					array[index] = cur.next;
					size--;
					return cur.value;
				} else {
					prev.next = cur.next;
					size--;
					return cur.value;
				}
			}
			prev = cur;
			cur = cur.next;
			
		}
		return null;
	}
	
	/***
	 * 
	 * synchronized on non-static method == synchronized(this)
	 * static synchronized == synchronized(Counter.class) != synchronized(all instances)
	 * 
	 * 
	 * (Frequently asked in real-life interviews):
	 * How to make a hashmap thread safe, where shall we put synchronized keyword to? Wj? what is the implication?
	 * (Hint: which public APIs will read values, which will write? How shall we address the data races?
	 * 
	 * One step forward, what is the problem of your design? Can you make it even better?
	 * 
	 * ==> synchronized hash map (course-grained lock) 
	 * vs. concurrent hash map(fine-grained lock) --> not the whole array locked?
	 * 
	 * javadoc: https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/ConcurrentHashMap.html
	 * Tick: spearating HashMap into segments and perform sync operations on each one of them
	 * separately(sharding).
	 * 
	 */
	
	
}
