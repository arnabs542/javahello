package misc;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * 
 * 448. Find All Numbers Disappeared in an Array
 * 
 * Given an array of integers where 1 ¡Ü a[i] ¡Ü n (n = size of array), some
 * elements appear twice and others appear once.
 * 
 * Find all the elements of [1, n] inclusive that do not appear in this array.
 * 
 * Could you do it without extra space and in O(n) runtime? You may assume the
 * returned list does not count as extra space.
 * 
 * Example:
 * 
 * Input: [4,3,2,7,8,2,3,1]
 * 
 * Output: [5,6]
 * 
 * Difficulty:Easy Total Accepted:94K Total Submissions:183.8K
 * Contributor:yuhaowang001
 * 
 * Companies
 * 
 * Related Topics
 * 
 * Similar Questions First Missing PositiveFind All Duplicates in an Array
 * 
 * @author
 *
 */
public class FindAllNumbersDisappearedinanArray {
	 public List<Integer> findDisappearedNumbers(int[] nums) {
		 List<Integer> res = new ArrayList<Integer>();
		 if (nums == null || nums.length == 0) {
			 return res;
		 }
		 // turn vals to negatives
		 for (int num : nums) {
			 int idx = Math.abs(num) - 1;
			 nums[idx] = -Math.abs(nums[idx]);
		 }
		 for (int i = 0; i < nums.length; i++) {
			 if (nums[i] > 0) {
				 res.add(i + 1);
			 }
		 }
		 return res;
	 }
}
