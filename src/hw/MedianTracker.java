package hw;

import java.util.Collections;
import java.util.PriorityQueue;

// MedianTracker will call read(0 method to read the values one by one.
public class MedianTracker {
	private PriorityQueue<Integer> smallerHalf;
	private PriorityQueue<Integer> largerHalf;

	public MedianTracker() {
		// We care about the smallest number of the larger half and
		// largest number of the smaller half.
		// So for the smaller half we use a max heap and for the
		// larger half we use a min heap.
		largerHalf = new PriorityQueue<Integer>();
		smallerHalf = new PriorityQueue<Integer>(11, Collections.reverseOrder());
	}

	public void read(int value) {
		// We maintain the property:
		// size(smallerHalf) == size(largeHalf) when there are even number of values
		// read,
		// or size(smallerHalf) == size(largerHalf) + 1 when there are odd number of
		// values read.
		if (smallerHalf.isEmpty() || value <= smallerHalf.peek()) {
			smallerHalf.offer(value);
		} else {
			largerHalf.offer(value);
		}
		// After we insert the value,
		// only when size(smallerHalf) == size(largeHalf) + 2 or
		// size(smallerHalf) == size(largerHalf) - 1 will break the balance,
		// and we need to do the adjustment accordingly.
		if (smallerHalf.size() - largerHalf.size() >= 2) {
			largerHalf.offer(smallerHalf.poll());
		} else if (largerHalf.size() > smallerHalf.size()) {
			smallerHalf.offer(largerHalf.poll());
		}
	}

	public Double median() {
		int size = size();
		// By the property we maintained, it is easy to know that
		// if the number of values read is odd, the largest one in
		// the smaller half is the median.
		if (size == 0) {
			return null;
		} else if (size % 2 != 0) {
			return (double) (smallerHalf.peek());
		} else {
			return (smallerHalf.peek() + largerHalf.peek()) / 2.0;
		}
	}

	private int size() {
		return smallerHalf.size() + largerHalf.size();
	}

}

// self, AC
class Solution {
	// keep smaller half of all read vals, size n / 2 (+ 1)
	private PriorityQueue<Integer> smallerMaxHeap;
	// keep larger half of all read vals, size n / 2;
	private PriorityQueue<Integer> largerMinHeap;
	// size differs at most 1, ood 1, even 0,
	// therefore median will be smallerMaxHeap[0] when odd,
	// or (smallerMaxHeap[0] + largerMinHeap[0]) / 2

	public Solution() {
		smallerMaxHeap = new PriorityQueue<>(11, Collections.reverseOrder());
		largerMinHeap = new PriorityQueue<>(11); // can and better to go without init size.
	}

	public void read(int value) {
		// since smallerHeap will always be bigger or equal in size than the other,
		// to make code simpler, can always insert into smallerHeap first.
		// Then adjust the size if necessary
		smallerMaxHeap.offer(value);
		if (smallerMaxHeap.size() - largerMinHeap.size() >= 2) {
			int tmp = smallerMaxHeap.poll();
			largerMinHeap.offer(tmp);
		}
		// if (smallerMaxHeap.peek() > largerMinHeap.peek()) { // !! NPE, need to check
		// largerMinHeap size here!!!, Null will NPE when try to compare with int value ??
		if (largerMinHeap.size() > 0 && smallerMaxHeap.peek() > largerMinHeap.peek()) {
			// int tmp = smallerMaxHeap.poll();
			// largerMinHeap.offer(tmp);
			// tmp = largerMinHeap.poll();
			// smallerMaxHeap.offer(tmp);
			// --> simpler way to write
			largerMinHeap.offer(smallerMaxHeap.poll());
			smallerMaxHeap.offer(largerMinHeap.poll());

		}
	}

	public Double median() {
		// write your implementation here.
		int size = smallerMaxHeap.size() + largerMinHeap.size();
		if (size == 0) {
			return null;
		} else if (size % 2 == 1) {
			return (double) (smallerMaxHeap.peek());
		} else {
			return (Double) ((smallerMaxHeap.peek() + largerMinHeap.peek()) / 2.0);
		}
	}
}
