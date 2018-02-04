package hw.practice;
/**
 * Use array to implement a queue
 *
 * in java,
 * --> The linked list implementation: LinkedList
 * --> The array implementation: ArrayDeque
 */
public class BoundedStack {
	private int[] arr;
	private int head;

	public BoundedStack(int cap) {
	    if (cap <= 0) {
	        throw new IllegalArgumentException("capacity should not be <= 0.");
        }
        arr = new int[cap];
	    head = -1; // head means a good position to get value
    }
	public boolean push(int ele) {
	    if (head == arr.length - 1) {
	        return false; // think about resize!!!
        }
        arr[++head] = ele;
	    return true;
    }

	public Integer pop() {
	    return head == -1 ? null : arr[head--];
    }

	public Integer top() {
	    return head == -1 ? null : arr[head];
    }

}
