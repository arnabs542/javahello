package misc;

/**
 * 
 * 280. Wiggle Sort
 * 
 * DescriptionHintsSubmissionsDiscussSolution
 * 
 * Given an unsorted array nums, reorder it in-place such that nums[0] <=
 * nums[1] >= nums[2] <= nums[3]....
 * 
 * Example:
 * 
 * Input: nums = [3,5,2,1,6,4] 
 * Output: One possible answer is [3,5,1,6,2,4]
 * 
 * 
 * Difficulty:Medium Total Accepted:44.8K Total Submissions:76K
 * Contributor:LeetCode Companies
 * 
 * Related Topics
 * 
 * Similar Questions Sort ColorsWiggle Sort II
 * 
 * @author
 *
 */

public class WiggleSort {
	/**
	 * basic idea: 
	 * 
	 * for every odd index, make it bigger, for even index, make it smaller
	 * so go through the input, starting from 1
	 * if for odd, if not bigger, swap with i - 1
	 * for even, if not smaller, swap with i - 1
	 * 
	 * time: O(nlogn)
	 * space: O(n)
	 * 
	 * @param nums
	 */
    public void wiggleSort(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
        	if (i % 2 == 1) {
        		// odd position
        		if (nums[i] < nums[i - 1]) {
        			swap(nums, i, i - 1);
        		}
        	} else {
        		if (nums[i] > nums[i - 1]) {
        			swap(nums, i, i - 1);
        		}
        	}
        }
    }
    
    private void swap(int[] arr, int i, int j) {
    	int tmp = arr[i];
    	arr[i] = arr[j];
    	arr[j] = tmp;
    }
}

