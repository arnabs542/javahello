package hw.testfinal;

/**
 * 
 * Given an array of strings, find if all the strings can be chained to form a
 * circle. Two string s1 and s2 can be chained, iff the last letter of s1 is
 * identical to the first letter of s2.
 * 
 * Assume all the strings given are not null or empty. For example, “abc” and
 * “cd” can be chained, “abc” and “dz” can not be chained.
 * 
 * Example
 * 
 * Input: arr[] = {"aaa", "bbb", "baa", "aab"}; Output: True, The given input
 * strings can be chained to form a circle. The strings can be chained as "aaa",
 * "aab", "bbb" and "baa"
 * 
 * 
 * 
 * @author
 *
 */

public class ChainStrings {

	// basic idea 1, form a directed graph, take a string as a node, check // if it
	// can find a circle path.

	// basic idea 2, (sort the string according to first char, → no need ) //
	// permutation,
	// prune dfs branch if next string’s first char is not the same as
	// current string’s last char.

	// first string and

	public boolean canChain(String[] arr) {
		// Assumption: arr is not null and not empty.
		// no empty string in it.

		// base case
		if (arr.length == 1 && arr[0].charAt(0) == arr[0].charAt(arr[0].length() - 1)) {
			return true;
		}
		boolean[] used = new boolean[arr.length];
		int[] path = new int[arr.length];
		path[0] = 0;
		used[0] = true;
		// dfs and check
		return check(arr, 1, path, used);

	}

	private boolean check(String[] arr, int idx, int[] path, boolean[] used) {
		if (idx == arr.length - 1) {
			// not enough time, use [-1] for last char ….
			if (arr[path[idx]].charAt(-1) == arr[path[0]].charAt(0)) {
				return true;
			} else {
				return false;
			}
		}
		boolean flag = false;
		for (int i = idx; i < arr.length; i++) {
			char tail = arr[path[idx - 1]].charAt(-1);

			if (!used[i] && tail == arr[i].charAt(0)) {
				flag = true;
				used[i] = true;
				path[idx] = i;
				boolean result = check(arr, idx + 1, path, used);
				if (result == true) {
					return true;
				}
				used[i] = false;
			}
			if (!flag) {
				return false;
			}
		}
		return false;
	}

}
