package hw;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Determine if there exists a set of four elements in a given array that sum to
 * the given target number.
 * 
 * Assumptions
 * 
 * The given array is not null and has length of at least 4 Examples
 * 
 * A = {1, 2, 2, 3, 4}, target = 9, return true(1 + 2 + 2 + 4 = 8)
 * 
 * A = {1, 2, 2, 3, 4}, target = 12, return false
 * 
 * 
 * 
 * Medium Array Hashtable Sort
 * 
 * @author
 *
 */
public class FourSum {
	// Method 1: sort the array first , O(n^3)
	public boolean exist(int[] array, int target) {
		// Assumptions: array is not null, array.length >= 4.
		Arrays.sort(array);
		for (int i = 0; i < array.length - 3; i++) {
			for (int j = i + 1; j < array.length - 2; j++) {
				int left = j + 1;
				int right = array.length - 1;
				int curTarget = target - array[i] - array[j];
				while (left < right) {
					int sum = array[left] + array[right];
					if (sum == curTarget) {
						return true;
					} else if (sum < curTarget) {
						left++;
					} else {
						right--;
					}
				}
			}
		}
		return false;
	}

	// Method 2: O(n^2 * logn), get all pairs of number s and apply 2 sum.
	public boolean existII(int[] array, int target) {
		// Assumptions: array is not null, array.length >= 4.
		// We need to sort the array first , and find i,j,k,l such that
		// i<j<k<l and the sum is target.
		// we split the tuples into two pair elements: (i,j) and (k,l).
		// so that array[i] + arr[j] <= arr[k] + arr[l].
		Arrays.sort(array);
		Element[] pairSum = getPairSum(array);
		Arrays.sort(pairSum);
		int left = 0;
		int right = pairSum.length - 1;
		// pairSum are sorted by sum, the right idx, then the left idx.
		while (left < right) {
			// only return true if two pair sums' sum is target and the larger pair
			// sum's left idx > smaller pair sum's right idx.
			if (pairSum[left].sum + pairSum[right].sum == target && pairSum[left].right < pairSum[right].left) {
				return true;
			} else if (pairSum[left].sum + pairSum[right].sum < target) {
				left++;
			} else {
				right--;
				// when two pair sums' sum > target, right--
				// when two pair sums' sum == target but larger pair sum's left idx
				// <= smaller pair sum's right idx, we need to do right--;
				// because the only thing we can guarantee is that
				// right now the smaller pair sum's right idx is the smallest one 
				// among all pairSums with the same sum, and it is possible we can find 
				// another pair of (target - pair[left]) with smaller right idx.
			}
		}
		return false;
	}

	static class Element implements Comparable<Element> {
		int left;
		int right;
		int sum;

		Element(int left, int right, int sum) {
			this.left = left;
			this.right = right;
			this.sum = sum;
		}

		@Override
		public int compareTo(Element another) {
			if (this.sum != another.sum) {
				return this.sum < another.sum ? -1 : 1;
			} else if (this.right != another.right) {
				return this.right < another.right ? -1 : 1;
			} else if (this.left != another.left) {
				return this.left < another.left ? -1 : 1;
			}
			return 0;
		}
	}

	private Element[] getPairSum(int[] arr) {
		int n = arr.length;
		Element[] pairSum = new Element[n * (n - 1) / 2];
		int curIdx = 0;
		for (int i = 1; i < n; i++) { // i is the right idx
			for (int j = 0; j < i; j++) { // j is the left idx
				pairSum[curIdx++] = new Element(j, i, arr[i] + arr[j]); // !!! j, i, not i, j
			}
		}
		return pairSum;
	}

	// <-- end of Method 2.
	/////////////////////////////////////////////////////////////////////////////

	// Method 3: HashMap O(n^2)
	public boolean existIII(int[] array, int target) {
		// Assumptions: array is not null, array.length >= 4.
		Map<Integer, Pair> map = new HashMap<>();
		// the order of traversing i, j is not arbitrary, we should guarantee
		// we can always look at the pair with the smallest right index.
		for (int r = 1; r < array.length; r++) {
			for (int l = 0; l < r; l++) {
				int pairSum = array[l] + array[r];
				// we need to guarantee there exists another pair with right idx smaller
				// than the current pair's left idx.
				if (map.containsKey(target - pairSum) && map.get(target - pairSum).right < l) {
					return true;
				}
				// we only need to store the pair with smallest right idx.
				if (!map.containsKey(pairSum)) {
					map.put(pairSum, new Pair(l, r));
				}
			}
		}
		return false;
	}
	
	// each pair is representing a pair of numbers in the array by their idx
	static class Pair {
		int left;
		int right;
		
		Pair(int left, int right) {
			this.left = left;
			this.right = right;
		}
	}

	public static void main(String[] args) {

	}

}
