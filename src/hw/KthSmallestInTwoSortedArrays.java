package hw;

/**
 * 
 * Kth Smallest In Two Sorted Arrays
 * 
 * Description
 * 
 * 
 * Given two sorted arrays of integers, find the Kth smallest number.
 * 
 * Assumptions
 * 
 * The two given arrays are not null and at least one of them is not empty
 * 
 * K >= 1, K <= total lengths of the two sorted arrays
 * 
 * Examples
 * 
 * A = {1, 4, 6}, B = {2, 3}, the 3rd smallest number is 3.
 * 
 * A = {1, 2, 3, 4}, B = {}, the 2nd smallest number is 2.
 * 
 * Hard Array Binary Search
 * 
 * @author
 *
 */
public class KthSmallestInTwoSortedArrays {
	public int kth(int[] a, int[] b, int k) {
		// Assumptions: a, b is not null, at least one of them 
		// is not empty, k <= a.length + b.length, k >= 1.
		return kth(a, 0, b, 0, k);
	}
	
	// in the subarray of a starting from  index aleft, and 
	// subarray of b starting from index bleft, find the keth smallest
	// element among these two subarrays.
	private int kth(int[] a, int aleft, int[] b, int bleft, int k) {
		// three base cases:
		// 1. we already eliminate all the elements in a.
		// 2. we already eliminate all the elements in b.
		// 3. when k is reduced to 1, don't miss this base case.
		// The reason why we have this as base case is in the following 
		// logic we need k >= 2 to make it work.
		if (aleft >= a.length) {
			return b[bleft + k - 1];
		}
		if (bleft >= b.length) {
			return a[aleft + k - 1];
		}
		if (k == 1) {
			return Math.min(a[aleft], b[bleft]);
		}
		// we compare the k/2 th element in a's subarray.
		// and the k/2 th element in b's subarray.
		// to determin which k/2 partition can be surely included
		// in the samllest k elements.
		int amid = aleft + k / 2 - 1;
		int bmid = bleft + k / 2 - 1;
		//--> why when amid > a.length, it's ok to set aval to Max?
		//--> so for sure a[left:] will be included in next recursion
		//--> why is it right? cause we are looking for kth ele,
		// try k/2 in a and k/2 in b. if a has less than k/2 eles, for sure 
		// b[bleft, bleft + k/2 - 1] can be removed from next recursion --> only k/2 removed
		int aval = amid >= a.length ? Integer.MAX_VALUE : a[amid];
		int bval = bmid >= b.length ? Integer.MAX_VALUE : b[bmid];
		if (aval <= bval) {
			return kth(a, amid + 1, b, bleft, k - k / 2);
		} else {
			return kth(a, aleft, b, bmid + 1, k - k / 2);
		}
	}
	
	public static void main(String[] args) {
		int[] arr1 = new int[] {1, 1, 100};
		int[] arr2 = new int[] {1, 1, 3, 4};
		int k = 5;
		KthSmallestInTwoSortedArrays sol = new KthSmallestInTwoSortedArrays();
		int result = sol.kth(arr1, arr2, k);
		System.out.println(result);
	}

}
