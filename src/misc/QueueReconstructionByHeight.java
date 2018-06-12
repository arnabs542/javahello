package misc;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;

/**
 * 
 * 406. Queue Reconstruction by Height
 * 
 * DescriptionHintsSubmissionsDiscussSolution
 * 
 * Suppose you have a random list of people standing in a queue. Each person is
 * described by a pair of integers (h, k), where h is the height of the person
 * and k is the number of people in front of this person who have a height
 * greater than or equal to h. Write an algorithm to reconstruct the queue.
 * 
 * Note: The number of people is less than 1,100.
 * 
 * 
 * Example
 * 
 * Input: [[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]]
 * 
 * Output: [[5,0], [7,0], [5,2], [6,1], [4,4], [7,1]]
 * 
 * 
 * Difficulty:Medium Total Accepted:47.9K Total Submissions:84.6K
 * Contributor:LeetCode Companies
 * 
 * Related Topics
 * 
 * Similar Questions Count of Smaller Numbers After Self
 * 
 * @author
 *
 */

public class QueueReconstructionByHeight {
	/**
	 * Time: O(nlogn) + O(n) ==> O(nlogn)
	 * Space: O(n)
	 * 
	 * @param people
	 * @return
	 */
	public int[][] reconstructQueue(int[][] people) {
		if (people == null || people.length == 0 || people[0].length == 0) {
			return new int[0][0];
		}
		// sort by (height, -1) then (k, 1)
		Comparator<int[]> myComparator = new Comparator<int[]>() {
			public int compare(int[] a, int[] b) {
				if (b[0] == a[0]) {
					return a[1] - b[1];
				} else {
					return b[0] - a[0];
				}
			};
		};
		Arrays.sort(people, myComparator);
		int n = people.length;
		LinkedList<int[]> tmp = new LinkedList<>();
		for (int i = 0; i < n; i++) {
			tmp.add(people[i][1], new int[] {people[i][0], people[i][1]});
		}
		int[][] res = new int[people.length][2];
		res = tmp.toArray(res);
		return res;
	}

}

/**
 * 
 * We first sort the people to make them stand from the highest to shortest. For
 * people with same height, sort them according to the count of people before
 * them from small to big.
 * 
 * Then, we use the way similar to insert sorting to reorder the people. For a
 * given person to insert, all the people already sorted are higher, so we just
 * insert him in the "right" place to make the people before him as his "count"
 * indicates. Since he is shorter than all the people in the sorted list, the
 * "count" of the "existing" people does not be broken by the insertion.
 * 
 * 
 */