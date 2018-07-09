package misc;

import java.util.Arrays;

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
 * 
 * Difficulty:Medium
Total Accepted:41.1K
Total Submissions:155K
Contributor:dietpepsi
Companies 

Related Topics 

Similar Questions 
Sort ColorsKth Largest Element in an ArrayWiggle Sort

 * @author 
 *
 */
public class WiggleSortII {
	/**
	 * 
	 * Step by step explanation of index mapping in Java
37.8K
VIEWS
198
Last Edit: June 21, 2018 9:34 PM

shuoshankou
shuoshankou
 347
The virtual index idea in the post https://leetcode.com/discuss/77133/o-n-o-1-after-median-virtual-indexing
is very brilliant! However, it takes me a while to understand why and how it works. There is no 'nth_element' in Java, but you can use 'findKthLargest' function from "https://leetcode.com/problems/kth-largest-element-in-an-array/" to get the median element in average O(n) time and O(1) space.

Assume your original array is {6,13,5,4,5,2}. After you get median element, the 'nums' is partially sorted such that the first half is larger or equal to the median, the second half is smaller or equal to the median, i.e

13   6   5   5   4   2

         M
In the post https://leetcode.com/discuss/76965/3-lines-python-with-explanation-proof, we have learned that , to get wiggle sort, you want to put the number in the following way such that

(1) elements smaller than the 'median' are put into the last even slots

(2) elements larger than the 'median' are put into the first odd slots

(3) the medians are put into the remaining slots.

Index :       0   1   2   3   4   5
Small half:   M       S       S    
Large half:       L       L       M
M - Median, S-Small, L-Large. In this example, we want to put {13, 6, 5} in index 1,3,5 and {5,4,2} in index {0,2,4}

The index mapping, (1 + 2*index) % (n | 1) combined with 'Color sort', will do the job.

After selecting the median element, which is 5 in this example, we continue as the following

Mapped_idx[Left] denotes the position where the next smaller-than median element  will be inserted.
Mapped_idx[Right] denotes the position where the next larger-than median element  will be inserted.


Step 1: 
Original idx: 0    1    2    3    4    5  
Mapped idx:   1    3    5    0    2    4 
Array:        13   6    5    5    4    2 
             Left
              i
                                      Right
 nums[Mapped_idx[i]] = nums[1] = 6 > 5, so it is ok to put 6 in the first odd index 1. We increment i and left.


Step 2: 
Original idx: 0    1    2    3    4    5  
Mapped idx:   1    3    5    0    2    4 
Array:        13   6    5    5    4    2 
                  Left
                   i
                                      Right
 nums[3] = 5 = 5, so it is ok to put 6 in the index 3. We increment i.


Step 3: 
Original idx: 0    1    2    3    4    5  
Mapped idx:   1    3    5    0    2    4 
Array:        13   6    5    5    4    2 
                  Left
                        i
                                     Right
 nums[5] = 2 < 5, so we want to put it to the last even index 4 (pointed by Right). So, we swap nums[Mapped_idx[i]] with nums[Mapped_idx[Right]], i.e. nums[5] with nums[4], and decrement Right. 




Step 4: 
Original idx: 0    1    2    3    4    5  
Mapped idx:   1    3    5    0    2    4 
Array:        13   6    5    5    2    4 
                  Left
                        i
                               Right
 nums[5] = 4 < 5, so we want to put it to the second last even index 2. So, we swap nums[5] with nums[2], and decrement Right. 




Step 5: 
Original idx: 0    1    2    3    4    5  
Mapped idx:   1    3    5    0    2    4 
Array:        13   6    4    5    2    5 
                  Left
                        i
                            Right
 nums[5] = 5 < 5, it is ok to put it there, we increment i.


Step 6: 
Original idx: 0    1    2    3    4    5  
Mapped idx:   1    3    5    0    2    4 
Array:        13   6    4    5    2    5 
                  Left
                             i
                            Right
 nums[0] = 13 > 5, so, we want to put it to the next odd index which is 3 (pointed by 'Left'). So, we swap nums[0] with nums[3], and increment 'Left' and 'i'.


Step Final: 
Original idx: 0    1    2    3    4    5  
Mapped idx:   1    3    5    0    2    4 
Array:        5    6    4    13   2    5 
                      Left
                                  i
                            Right
i > Right, we get the final wiggle array 5 6 4 13 2 5 !
	 * 
	 * 
	 * 
	 * 
	 * @param nums
	 */
	public void wiggleSort(int[] nums) {
		// did NOT AC yet
		int median = findKthLargest(nums, (nums.length + 1) / 2);
		int n = nums.length;
		int left = 0, i = 0, right = n - 1;
		while (i <= right) {
			if (nums[newIndex(i, n)] > median) {
				swap(nums, newIndex(left++, n), newIndex(i++, n));
			} else if (nums[newIndex(i, n)] < median) {
				swap(nums, newIndex(right--, n), newIndex(i, n));
			} else {
				i++;
			}
		}
	}
	
	private int findKthLargest(int[] nums, int k) {
		return findKthHelper(nums, 0, nums.length - 1, k);
	}
	
	private int findKthHelper(int[] nums, int left, int right, int k) {
		int pidx = partition(nums, left, right);
		if (pidx == left + k - 1) {
			return nums[pidx];
		} else if (pidx < k - 1) {
			return findKthHelper(nums, pidx + 1, right, k - (pidx - left) - 1);
		} else {
			return findKthHelper(nums, left, pidx - 1, k );
		}
	}
	
	private int partition(int[] nums, int l, int r) {
		int pivot = nums[r];
		int i = l, j = r - 1;
		while (i <= j) {
			if (nums[i] >= pivot) {
				i++;
				continue;
			}
			if (nums[j] < pivot) {
				j--;
				continue;
			}
			swap(nums, i, j);
			i++;
			j--;
		}
		swap(nums, i, r);
		return i;
	}
	
	
	
	private void swap(int[] nums, int i, int j) {
		int tmp = nums[j];
		nums[j] = nums[i];
		nums[i] = tmp;
	}
	
	private int newIndex(int index, int n) {
		return ( 1 + 2 * index) % (n | 1);
	}
	
	
	/**
	 * Basic idea: sort the nums,
	 * then split into two parts, s(maller) and t(bigger) parts
	 * for even index positions, put bigger half
	 * for odd index positions, put smaller half
	 * @param nums
	 */
	public void wiggleSort_sort(int[] nums) {
		Arrays.sort(nums);
		int[] tmp = new int[nums.length];
		int s = (nums.length + 1) >> 1;
		int t = nums.length;
		for (int i = 0; i < nums.length; i++) {
			tmp[i] = (i & 1) == 0 ? nums[--s] : nums[--t];
		}
		for (int i = 0; i < nums.length; i++) {
			nums[i] = tmp[i];
		}
	}

}

/**
 * 
https://leetcode.com/problems/wiggle-sort-ii/discuss/77681/O(n)-time-O(1)-space-solution-with-detail-explanations


Methodology:

Idea 1.

As @whnzinc pointed out in this thread, all elements in nums can be classified into three categories:

(1) Larger than the median;

(2) Equal to the median;

(3) Smaller than the median.

Note that it's possible to find the median within O(n)-time and O(1)-space.

Note: We can use nth_element to find the median, but it's not O(n)-time and O(1)-space. For the sake of simplicity, I might use nth_element as well.

Idea 2.

As @StefanPochmann pointed out in this thread, we can arrange the elements in the three categories in a deterministic way.

(1) Elements that are larger than the median: we can put them in the first few odd slots;

(2) Elements that are smaller than the median: we can put them in the last few even slots;

(3) Elements that equal the median: we can put them in the remaining slots.

Update: According to @StefanPochmann's thread, we can use a one-pass three-way partition to rearrange all elements. His idea is to re-map the indices into its destined indices, odd indices first and even indices follow.

Example:

Original Indices:    0  1  2  3  4  5  6  7  8  9 10 11
Mapped Indices:      1  3  5  7  9 11  0  2  4  6  8 10
(its reverse mapping is)

Mapped Indices:      0  1  2  3  4  5  6  7  8  9 10 11
Original Indices:    6  0  7  1  8  2  9  3 10  4 11  5   (wiggled)
In order to achieve this, we can use a function alike

int map_index(int idx, int n) {
    return (2 * idx + 1) % (n | 1);
}
where (n | 1) calculates the nearest odd that is not less than n.

Complexities: (On the condition that finding median is O(n)-time and O(1)-space)

Time: O(n)

Space: O(1)


 * 
 */



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