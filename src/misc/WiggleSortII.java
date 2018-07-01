package misc;

import java.util.Arrays;
import java.util.Collections;

/**
 * 
 * 324. Wiggle Sort II
DescriptionHintsSubmissionsDiscussSolution
Given an unsorted array nums, reorder it such that nums[0] < nums[1] > nums[2] < nums[3]....

Example 1:

Input: nums = [1, 5, 1, 1, 6, 4]
Output: One possible answer is [1, 4, 1, 5, 1, 6].
Example 2:

Input: nums = [1, 3, 2, 2, 3, 1]
Output: One possible answer is [2, 3, 1, 3, 1, 2].
Note:
You may assume all input has valid answer.

Follow Up:
Can you do it in O(n) time and/or in-place with O(1) extra space?
 * 
 * @author 
 *
 */
public class WiggleSortII {
	/**
	 * 我们可以先给数组排序，然后在做调整。调整的方法是找到数组的中间的数，
	 * 相当于把有序数组从中间分成两部分，然后从前半段的末尾取一个，
	 * 在从后半的末尾去一个，这样保证了第一个数小于第二个数，然后从前半段取倒数第二个，
	 * 从后半段取倒数第二个，这保证了第二个数大于第三个数，且第三个数小于第四个数，
	 * 以此类推直至都取完
	 * 
	 * time: O(nlogn)
	 * space: O(n)
	 * 
	 * @param nums
	 */
	public void wiggleSort(int[] nums) {
		Arrays.sort(nums);
		int[] tmp = new int[nums.length];
		int s = (nums.length + 1) >> 1, t = nums.length;
		for (int i = 0; i < nums.length; i++) {
			tmp[i] = (i & 1) == 0 ? nums[--s] : nums[--t];
		}
		for (int i = 0; i < nums.length; i++) {
			nums[i] = tmp[i];
		}
	}

}





/***

https://leetcode.com/problems/wiggle-sort-ii/discuss/77678/3-lines-Python-with-Explanation-Proof

Roughly speaking I put the smaller half of the numbers on the even indexes and the larger half on the odd indexes.

def wiggleSort(self, nums):
    nums.sort()
    half = len(nums[::2])
    nums[::2], nums[1::2] = nums[:half][::-1], nums[half:][::-1]
Alternative, maybe nicer, maybe not:

def wiggleSort(self, nums):
    nums.sort()
    half = len(nums[::2]) - 1
    nums[::2], nums[1::2] = nums[half::-1], nums[:half:-1]
    
    
Explanation / Proof
I put the smaller half of the numbers on the even indexes and the larger half on the odd indexes, both from right to left:

Example nums = [1,2,...,7]      Example nums = [1,2,...,8] 

Small half:  4 . 3 . 2 . 1      Small half:  4 . 3 . 2 . 1 .
Large half:  . 7 . 6 . 5 .      Large half:  . 8 . 7 . 6 . 5
--------------------------      --------------------------
Together:    4 7 3 6 2 5 1      Together:    4 8 3 7 2 6 1 5
I want:

Odd-index numbers are larger than their neighbors.
Since I put the larger numbers on the odd indexes, clearly I already have:

Odd-index numbers are larger than or equal to their neighbors.
Could they be "equal to"? That would require some number M to appear both in the smaller and the larger half. It would be the largest in the smaller half and the smallest in the larger half. Examples again, where S means some number smaller than M and L means some number larger than M.

Small half:  M . S . S . S      Small half:  M . S . S . S .
Large half:  . L . L . M .      Large half:  . L . L . L . M
--------------------------      --------------------------
Together:    M L S L S M S      Together:    M L S L S L S M
You can see the two M are quite far apart. Of course M could appear more than just twice, for example:

Small half:  M . M . S . S      Small half:  M . S . S . S .
Large half:  . L . L . M .      Large half:  . L . M . M . M
--------------------------      --------------------------
Together:    M L M L S M S      Together:    M L S M S M S M
You can see that with seven numbers, three M are no problem. And with eight numbers, four M are no problem. Should be easy to see that in general, with n numbers, floor(n/2) times M is no problem. Now, if there were more M than that, then my method would fail. But... it would also be impossible:

If n is even, then having more than n/2 times the same number clearly is unsolvable, because you'd have to put two of them next to each other, no matter how you arrange them.
If n is odd, then the only way to successfully arrange a number appearing more than floor(n/2) times is if it appears exactly floor(n/2)+1 times and you put them on all the even indexes. And to have the wiggle-property, all the other numbers would have to be larger. But then we wouldn't have an M in both the smaller and the larger half.
So if the input has a valid answer at all, then my code will find one.


*/