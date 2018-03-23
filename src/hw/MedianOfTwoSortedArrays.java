package hw;

/**
 * 
 * Median Of Two Sorted Arrays 
 * 
 * Description 
 * 
 * Given two sorted arrays of integers
 * in ascending order, find the median value.
 * 
 * Assumptions
 * 
 * The two given array are not null and at least one of them is not empty 
 * The
 * two given array are guaranteed to be sorted 
 * 
 * Examples
 * 
 * A = {1, 4, 6}, B = {2, 3}, median is 3 
 * A = {1, 4}, B = {2, 3}, median is 2.5
 * 
 * Hard Array Binary Search
 * 
 * @author
 *
 */

public class MedianOfTwoSortedArrays {
	public double median(int[] a, int[] b) {
		// Assumptions: a, b are not null, at least one is not empty
		int len = a.length + b.length;
		if (len % 2 == 1) {
			return findKth(a, b, len / 2 + 1);
		} else {
			return (findKth(a, b, len / 2) + findKth(a, b, len / 2 + 1)) * 0.5;
		}
	}
	
	private int findKth(int[] a, int[] b, int k) {
		return helper(a, 0, a.length - 1, b, 0, b.length - 1, k);
	}
	
	// --> actually not necessary to have the right index. see kthsmallestintwosortedarrays for ref.
	private int helper(int[] a, int al, int ar, int[] b, int bl, int br, int k) {
		
		if (al > ar) {
			return b[bl + k - 1];
		}
		if (bl > br) {
			return a[al + k - 1];
		}
		// !!! important to have this as return case, otherwise wrong (index out bound or inifinite)
		if (k == 1) {
			return Math.min(a[al], b[bl]);
		}
		int m = k / 2;
		int amid = al + m - 1 > ar ? Integer.MAX_VALUE : a[al + m - 1];
		int bmid = bl + m - 1 > br ? Integer.MAX_VALUE : b[bl + m - 1];
		if (amid <= bmid) {
			return helper(a, al + m, ar, b, bl, br, k - m);
		} else {
			return helper(a, al, ar, b, bl + m, br, k - m);
		}
	}
	
	public static void main(String[] args) {
		int[] arr1 = new int[] {1, 1, 100};
		int[] arr2 = new int[] {1, 1, 3, 4}; // expect 1
		MedianOfTwoSortedArrays sol = new MedianOfTwoSortedArrays();
		double result = sol.median(arr1, arr2);
		System.out.println(result);
	}
}
