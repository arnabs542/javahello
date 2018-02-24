package hw;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 
 * Merge K sorted array into one big sorted array in ascending order.
 * 
 * Assumptions
 * 
 * The input arrayOfArrays is not null, none of the arrays is null either.
 * Medium Heap
 * 
 * @author
 *
 */
public class MergeKSortedArray {
	public int[] merge(int[][] arrayOfArrays) {
		// Assumptions: arrayOfArrays is not null, none of the array is null either.
		PriorityQueue<Entry> minHeap = new PriorityQueue<>(11, new MyComp());
		int length = 0;
		for (int i = 0; i < arrayOfArrays.length; i++) {
			if (arrayOfArrays[i].length != 0) {
				// we use two index to record the position of each element;
				// the index of the array in the arrayOfArrays,
				// the index of the element in the array.
				length += arrayOfArrays[i].length;
				minHeap.offer(new Entry(i, 0, arrayOfArrays[i][0]));
			}
		}
		
		int[] result = new int[length];
		int cur = 0;
		while (!minHeap.isEmpty()) {
			Entry tmp = minHeap.poll();
			result[cur++] = tmp.value;  // cur++, neat way to write
			if (tmp.y + 1 < arrayOfArrays[tmp.x].length) { // !! remember to check index and if the array is at end or not
				// reuse the same Entry object but advance the index by 1. --> neat way to use the obj
				tmp.y++;
				tmp.value = arrayOfArrays[tmp.x][tmp.y];
				minHeap.offer(tmp);
			}
		}
		return result;
		
	}
	
	static class MyComp implements Comparator<Entry> { // generic type is with Comparator, compare need to be public
		@Override
		public int compare(Entry i1, Entry i2) {
			return Integer.compare(i1.value, i2.value);
		}

	}
	
	static class Entry {
		public int x;
		public int y;
		public int value;
		
		public Entry(int x, int y, int value) {
			this.x = x;
			this.y = y;
			this.value = value;
		}
	}
}
