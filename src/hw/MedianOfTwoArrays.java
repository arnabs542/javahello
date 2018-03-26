package hw;

/**
 * 
 * Median Of Two Arrays
 * 
 * Description
 * 
 * Given two arrays of integers, find the median value.
 * 
 * Assumptions
 * 
 * The two given array are not null and at least one of them is not empty The
 * two given array are not guaranteed to be sorted
 * 
 * 
 * Examples
 * 
 * A = {4, 1, 6}, B = {2, 3}, median is 3 
 * A = {1, 4}, B = {3, 2}, median is 2.5
 * 
 * Hard Array Binary Search
 * 
 * @author
 *
 */

public class MedianOfTwoArrays {
	public double median(int[] a, int[] b) {
		// Assumptions: a, b are not null
		int m = a.length, n = b.length;
		if ((m + n) % 2 == 1) {
			return findKth(a, 0, a.length - 1, b, 0, b.length - 1, (m + n) / 2 + 1) * 1.0;
		} else {
			int r1 = findKth(a, 0, a.length - 1, b, 0, b.length - 1, (m + n) / 2);
			int r2 = findKth(a, 0, a.length - 1, b, 0, b.length - 1, (m + n) / 2 + 1);
			return (r1 + r2) * 0.5;
		}
	}

	private int findKth(int[] a, int al, int ar, int[] b, int bl, int br, int k) {
		
		if (al > ar) {
			return b[findKthIdx(b, bl, br, k)];
		}
		if (bl > br) {
			return a[findKthIdx(a, al, ar, k)];
		}
		
		if (k == 1) {
			return Math.min(a[findKthIdx(a, al, ar, 1)], b[findKthIdx(b, bl, br, 1)]);
		}
		
		int idx1 = findKthIdx(a, al, ar, k / 2);
		int idx2 = findKthIdx(b, bl, br, k / 2);
		if (a[idx1] == b[idx2]) {
			if (k/2 + k/2 == k) {
				return a[idx1];
			} else {
				return findKth(a, idx1 + 1, ar, b, idx2 + 1, br, 1);
			}
		}
		else if (a[idx1] < b[idx2]) {
			//return findKth(a, idx1 + 1, ar, b, bl, idx2, k - k / 2);  // case 2, can't cut b to idx2
			return findKth(a, idx1 + 1, ar, b, bl, br, k - k / 2);
		} else {
			//return findKth(a, al, idx2, b, idx2 + 1, br, k - k / 2);
			return findKth(a, al, ar, b, idx2 + 1, br, k - k / 2);

		}

	}
	
	private int findKthIdx(int[] a, int l, int r, int k) {
		if (k == 1 && l == r) {
			return l;
		}
		int idx = quickSelect(a, l, r);
		if (idx - l + 1 == k) {
			return idx;
		} else if (idx - l + 1 < k) {
			return findKthIdx(a, idx + 1, r, k - (idx - l + 1));
		} else {
			return findKthIdx(a, l, idx - 1, k);
		}
	}

	// use a[end] as pivot, partition a, return the right index of pivot
	private int quickSelect(int[] a, int start, int end) {
		if (start >= end) {
			return start;
		}
		int pivot = a[end];
		// l: current index to check, at end, [start: l-1] are smaller than pivot, l ends on a >= pivot position
		// r: current right index to check (can be good), at end, [end:] are >= pivot
		// out of loop, swap l and end, and return l
		int l = start, r = end; // !!! not r = end - 1
		while (l <= r) { //!!! no == --> !!! should have ==
			if (a[l] < pivot) {
				l++;
				continue;
			}
			if (a[r] >= pivot) {
				r--;
				continue;
			}
			swap(a, l, r);
			l++;
			r--;
		}
		swap(a, l, end);
		return l;
	}

	private void swap(int[] x, int a, int b) {
		int tmp = x[a];
		x[a] = x[b];
		x[b] = tmp;
	}
	
	public static void main(String[] args) {
		int[][] a = new int[][] {{4, 1, 6}, {2, 3}}; // expect 3.0
		int[][] a2 = new int[][] {{2, 3}, {1, 4}}; // expect 2.5
		int[][] a3 = new int[][] {{3, 4, 2}, {9, 11, 8}}; // expect 6.0
		int[][] a4 = new int[][] {{2,4,9,1,3,7}, {}}; // expect 3.5


		MedianOfTwoArrays sol = new MedianOfTwoArrays();
		double result = sol.median(a[0], a[1]);
		System.out.println(result);
	}
}


class MedianOfTwoArrays_WRONG {
	public double median(int[] a, int[] b) {
		// Assumptions: a, b are not null
		int m = a.length, n = b.length;
		if ((m + n) % 2 == 1) {
			return findKth(a, 0, a.length - 1, b, 0, b.length - 1, (m + n) / 2 + 1) * 1.0;
		} else {
			int r1 = findKth(a, 0, a.length - 1, b, 0, b.length - 1, (m + n) / 2);
			int r2 = findKth(a, 0, a.length - 1, b, 0, b.length - 1, (m + n) / 2 + 1);
			return (r1 + r2) * 0.5;
		}
	}

	private int findKth(int[] a, int al, int ar, int[] b, int bl, int br, int k) {
		if (al > ar) {
			return findKth(b, bl, br, k);
		}
		if (bl > br) {
			return findKth(a, al, br, k);
		}
		
		int idx1 = al-1, idx2 = bl-1;
		if (al <= ar) {
			idx1 = quickSelect(a, al, ar);
		}
		if (bl <= br) {
			idx2 = quickSelect(b, bl, br);
		}
		if (idx1 - al + 1 + idx2 - bl + 1 == k) {
			return Math.max(a[idx1], b[idx2]);
		} else if (idx1 - al + 1 + idx2 - bl + 1 < k) {
			return findKth(a, idx1 + 1, ar, b, idx2 + 1, br, k - (idx1 - al + 1 + idx2 - bl + 1));
		} else {
			// return findKth(a, al, idx1, b, bl, idx2, k);  // can't do idx - 1 on both a, b, will miss out answer
			// should at least adjust one idx, otherwise infinite loop!!! when no change!!!
			if (a[idx1] > b[idx2]) {
				return findKth(a, al, idx1 - 1, b, bl, idx2, k);
			} else {
				return findKth(a, al, idx1, b, bl, idx2 - 1, k);
			}

		}
	}
	
	private int findKth(int[] a, int l, int r, int k) {
		if (k == 1 && l == r) {
			return l;
		}
		int idx = quickSelect(a, l, r);
		if (idx - l + 1 == k) {
			return a[idx];
		} else if (idx - l + 1 < k) {
			return findKth(a, idx + 1, r, k - (idx - l + 1));
		} else {
			return findKth(a, l, idx - 1, k);
		}
	}

	private int quickSelect(int[] a, int start, int end) {
		if (start >= end) {
			return start;
		}
		int pivot = a[end];
		int l = start, r = end; // !!! not r = end - 1
		while (l < r) { //!!! no ==
			if (a[l] < pivot) {
				l++;
				continue;
			}
			if (a[r] >= pivot) {
				r--;
				continue;
			}
			swap(a, l, r);
			l++;
			r--;
		}
		swap(a, l, end);
		return l;
	}

	private void swap(int[] x, int a, int b) {
		int tmp = x[a];
		x[a] = x[b];
		x[b] = tmp;
	}

}
