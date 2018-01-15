package hw_practice;

// ref class name is BoundedQueue
public class BoundedQueue {
	private int head;
	private int tail;
	private int size;
	private Integer[] array;
	
	public BoundedQueue(int cap) {
		array = new Integer[cap];
		head = tail = 0;
		size = 0;
	}
	
	public boolean offer(Integer ele) {}
	public Integer peek() {}
	public Integer poll() {}
	public int size() {}
	public boolean isEmpty() {}
	public boolean isFull() {}
	
}
