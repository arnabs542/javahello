package hw;

/**
 * Majority Number I
 * 
 * Description
 * 
 * Given an integer array of length L, find the number that occurs more than 0.5
 * * L times.
 * 
 * Assumptions
 * 
 * The given array is not null or empty It is guaranteed there exists such a
 * majority number Examples
 * 
 * A = {1, 2, 1, 2, 1}, return 1
 * 
 * Easy Array
 * 
 * 
 * @author
 *
 */

public class MajorityNumberI {
	
	// ref
	public int majority(int[] array) {
		// Assumptions: array is not null and is not empty.
		// majority number guarantees to exist.
		int candidate = array[0];
		int cnt = 1;
		for (int i = 1; i < array.length; i++) {
			if (cnt == 0) {
				cnt++;
				candidate = array[i];
			} else if (candidate == array[i]){
				cnt++;
			} else {
				cnt--;
			}
		}
		return candidate;
	}
	
	public int majority_self_ac(int[] array) {
		int candidate = array[0];
		int cnt = 1;
		for (int i = 1; i < array.length; i++) {
			if (array[i] == candidate) {
				cnt++;
			} else {
				cnt--;
				if (cnt < 1) {
					candidate = array[i];
					cnt = 1;
				}
			}
		}
		return candidate;
	}
}
