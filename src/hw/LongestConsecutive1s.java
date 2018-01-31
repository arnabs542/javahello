package hw;

/**
 * DP
 * 
 * Longest Consecutive 1s
 * 
 * Given an array containing only 0s and 1s, find the length of the longest
 * subarray of consecutive 1s.
 * 
 * Assumptions
 * 
 * The given array is not null Examples
 * 
 * {0, 1, 0, 1, 1, 1, 0}, the longest consecutive 1s is 3.
 * 
 * @author
 *
 */
public class LongestConsecutive1s {
	public int longest(int[] array) {
		// Assumptions: array is not null.
		int result = 0;
		// current consecutive 1 length
		int cur = 0; 
		for (int i = 0; i < array.length; i++) {
			if (array[i] == 1) {
				if ( i == 0 || array[i - 1] == 0) {
					cur = 1;
				} else {
					cur++;
				}
				result = Math.max(result,  cur);
			}
			// ignore the != 1 cases, do nothing.
			// because when == 1, already check if i = 0 or [i - 1] = 0
		}
		return result;
	}
	
	public int longest_self_AC(int[] array) {
		// Assumptions: array is not null.
		int result = 0;
		// current consecutive 1 length
		int cur = 0; 
		for (int i = 0; i < array.length; i++) {
			if (array[i] == 1) {
				cur++;
				result = Math.max(result,  cur);
			} else {
				cur = 0;
			}
			
		}
		return result;
	}
}
