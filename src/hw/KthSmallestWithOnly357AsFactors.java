package hw;

import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * 
 * Kth Smallest With Only 3, 5, 7 As Factors
 * 
 * Find the Kth smallest number s such that s = 3 ^ x 5 ^ y 7 ^ z, x > 0 and y >
 * 0 and z > 0, x, y, z are all integers.
 * 
 * Assumptions
 * 
 * K >= 1 Examples
 * 
 * the smallest is 3 5 7 = 105 the 2nd smallest is 3 ^ 2 5 7 = 315 the 3rd
 * smallest is 3 5 ^ 2 7 = 525 the 5th smallest is 3 ^ 3 5 7 = 945
 * 
 * 
 * 
 * 
 * Medium Breadth First Search Deque Heap
 * 
 * @author
 *
 */
public class KthSmallestWithOnly357AsFactors {
	// Method 1: BFS
	public long kth(int k) {
		// Assumptions: k >= 1.
		PriorityQueue<Long> minHeap = new PriorityQueue<>(k); // init of size k
		Set<Long> visited = new HashSet();
		minHeap.offer(3 * 5 * 7L); // !!! use Long, and long is like 123L
		visited.add(3 * 5 * 7L);
		// !!! no need to maintain x, y, z for expand and generating,
		// due to the target value is PRODUCTION of 3^x * 5^y * 7^z,
		// when expand and generate for x + 1, y + 1 and z + 1,
		// can directly do val * 3, val * 5 and val * 7
		while (k > 1) {
			long current = minHeap.poll();
			if (visited.add(3 * current)) { // !!! smart, use set.add() to both check exist and add
				minHeap.offer(3 * current);
			}
			if (visited.add(5 * current)) { 
				minHeap.offer(5 * current);
			}
			if (visited.add(7 * current)) { 
				minHeap.offer(7 * current);
			}
			k--;
		}
		
		return minHeap.peek(); // for kth big, only need to poll first k-1 ones, and check heap top.
	}
	
	
	// Method 2: Linear solution using 3 deques.
	public long kthII(int k) {
		// Assumptions: k >= 1.
		long seed = 3 * 5 * 7L;
		// we use three deques to maintain all the possible values.
		// the rule is :
		// deque3 only keeps the value of seed * 3^x;
		// deque5 only keeps the value of seed * 3^x * 5^y;
		// deque7 only keeps the value of seed * 3^x * 5^y * 7^z;
		Deque<Long> three = new LinkedList<>();
		Deque<Long> five = new LinkedList<>();
		Deque<Long> seven = new LinkedList<>();
		three.add(seed * 3);
		five.add(seed * 5);
		seven.add(seed * 7);
		long result = seed;
		while (k > 1) {
			if (three.peekFirst() < five.peekFirst() && three.peekFirst() < seven.peekFirst()) {
				result = three.pollFirst();
				three.offerLast(result * 3);
				five.offerLast(result * 5);
				seven.offerLast(result * 7);
			} else if (five.peekFirst() < three.peekFirst() && five.peekFirst() < seven.peekFirst()) {
				result = five.pollFirst();
				five.offerLast(result * 5);
				seven.offerLast(result * 7);
			} else {
				result = seven.pollFirst();
				seven.offerLast(result * 7);
			}
			k--;  // already put seed--1st one to result, for k-th, only need to do k-1 times new val to result
 		}
		return result;

	}
}
