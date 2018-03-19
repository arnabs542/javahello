package hw;

/**
 * Data Structure 
 * 
 * K Closest In Sorted Array 
 * 
 * Given a target integer T, a
 * non-negative integer K and an integer array A sorted in ascending order, find
 * the K closest numbers to T in A.
 * 
 * Assumptions
 * 
 * A is not null K is guranteed to be >= 0 and K is guranteed to be <= A.length
 * Return
 * 
 * A size K integer array containing the K closest numbers(not indices) in A,
 * sorted in ascending order by the difference between the number and T.
 * Examples
 * 
 * A = {1, 2, 3}, T = 2, K = 3, return {2, 1, 3} or {2, 3, 1} A = {1, 4, 6, 8},
 * T = 3, K = 3, return {4, 1, 6}
 * 
 * @author
 *
 */
public class KClosestInSortedArray {
	public int[] kClosest(int[] arr, int target, int k) {
		if (arr == null || arr.length == 0 || k == 0) {
			return new int[0];
		}
		// if ( k == arr.length ) {
		// return arr;
		// }
		// this part is wrong, still need to consider the order for k == len
		int[] res = new int[k];
		int closest = findClosest(arr, target);
		res[0] = arr[closest];
		int left = closest - 1, right = closest + 1;
		for (int i = 1; i < k; i++) {
			// !!! should use abs here, not sure the value is pos or neg, diff from
			// reference!!!
			if (left >= 0 && right < arr.length && Math.abs(target - arr[left]) <= Math.abs(arr[right] - target)
					|| right >= arr.length) {
				res[i] = arr[left--];
			} else {
				res[i] = arr[right++];
			}
		}
		return res;
	}

	private int findClosest(int[] arr, int target) {

		int left = 0, right = arr.length - 1;
		while (left + 1 < right) {
			int mid = left + (right - left) / 2;
			if (arr[mid] == target) {
				return mid;
			} else if (arr[mid] < target) {
				left = mid;
			} else {
				right = mid;
			}
		}
		if (Math.abs(arr[left] - target) < Math.abs(arr[right] - target)) {
			return left;
		} else {
			return right;
		}
	}

	public int[] kClosest1(int[] arr, int target, int k) {
		if (arr == null || arr.length == 0) {
			return arr;
		}
		if (k == 0) {
			return new int[0]; // important for checking k, otherwise index out of bounds
		}
		// get the idx of the value closest to target
		int closest = this.searchClose(arr, 0, arr.length - 1, target, 0);
		// search to left and right to find top-k closest
		int[] res = new int[k];
		res[0] = arr[closest];
		int left = closest - 1, right = closest + 1;
		for (int i = 1; i < k; i++) {
			if (left >= 0 && right < arr.length) {
				if (Math.abs(arr[left] - target) <= Math.abs(arr[right] - target)) {
					res[i] = arr[left];
					left -= 1;
				} else {
					res[i] = arr[right];
					right += 1;
				}
			} else if (left >= 0) {
				res[i] = arr[left];
				left -= 1;
			} else {
				res[i] = arr[right];
				right += 1;
			}
		}
		return res;

	}

	private int searchClose(int[] arr, int left, int right, int target, int idx) {
		if (left > right)
			return idx;
		int mid = left + (right - left) / 2;
		if (arr[mid] == target)
			return mid;
		else if (arr[mid] < target) {
			if (Math.abs(arr[mid] - target) < Math.abs(arr[idx] - target)) {
				idx = mid;
			}
			return this.searchClose(arr, mid + 1, right, target, idx);
		} else {
			if (Math.abs(arr[mid] - target) < Math.abs(arr[idx] - target)) {
				idx = mid;
			}
			return this.searchClose(arr, left, mid - 1, target, idx);
		}

	}
}
