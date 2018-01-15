package hw_practice;

/**
 * Use array to implement a queue
 *
 * Here note that it is a circular array!!!
 *
 */
public class BoundedQueue {
	private int head;
	private int tail;
	private int size;
	private Integer[] arr;
	
	public BoundedQueue(int cap) {
		arr = new Integer[cap];
		head = tail = 0;
		// head is the place to get, since init as 0, when poll check size first.
		// tail is the good place to put new value, when insert, check size, insert, update tail.
		size = 0;
	}

	public boolean offer(Integer ele) {
		if (size == arr.length) {
			return false;
		}
		arr[tail] = ele;
		tail = (tail + 1) % arr.length;
		size++;
		return true;
	}

	public Integer peek() {
		return size == 0 ? null : arr[head];
	}

	public Integer poll() {
		if (size == 0) {
			return null;
		}
		Integer result = arr[head];
		head = (head + 1) % arr.length;
		size--;
		return result;
	}

	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public boolean isFull() {
		return size == arr.length;
	}

	// self, think about resize when offer

	public void offer(Integer ele) {
		if (size == arr.length) {
			resize();
		}
		arr[tail] = ele;
		tail = (tail + 1) % arr.length;
		size++;
	}

	private static final int SCALE_FACTOR = 2;
	private void resize() {
		Integer[] newArr = new Integer[arr.length * SCALE_FACTOR];
		for (int i = 0; i < arr.length; i++) {
			newArr[0] = arr[(head + i) % arr.length];
		}
		head = 0;
		tail = arr.length;
	}
	
}
