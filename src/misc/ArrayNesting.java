package misc;

/**
 * 
 * 565. Array Nesting DescriptionHintsSubmissionsDiscussSolution A zero-indexed
 * array A of length N contains all integers from 0 to N-1. Find and return the
 * longest length of set S, where S[i] = {A[i], A[A[i]], A[A[A[i]]], ... }
 * subjected to the rule below.
 * 
 * Suppose the first element in S starts with the selection of element A[i] of
 * index = i, the next element in S should be A[A[i]], and then A[A[A[i]]]… By
 * that analogy, we stop adding right before a duplicate element occurs in S.
 * 
 * Example 1: Input: A = [5,4,0,3,1,6,2] Output: 4 Explanation: A[0] = 5, A[1] =
 * 4, A[2] = 0, A[3] = 3, A[4] = 1, A[5] = 6, A[6] = 2.
 * 
 * One of the longest S[K]: S[0] = {A[0], A[5], A[6], A[2]} = {5, 6, 2, 0} Note:
 * N is an integer within the range [1, 20,000]. The elements of A are all
 * distinct. Each element of A is an integer within the range [0, N-1].
 * 
 * 
 * 
 * Difficulty:Medium Total Accepted:19.6K Total Submissions:39.6K
 * Contributor:fallcreek Companies
 * 
 * Related Topics
 * 
 * Similar Questions Nested List Weight SumFlatten Nested List IteratorNested
 * List Weight Sum II
 * 
 * @author
 *
 */
public class ArrayNesting {
    public int arrayNesting(int[] nums) {
        int result = 0;
        for (int i = 0; i < nums.length; i++) {
        		int size = 0, k = i;
        		while (nums[k] >= 0) {
        			size += 1;
        			int next = nums[k];
        			nums[k] = -1;
        			k = next;
        		}
        		result = Math.max(result, size);
        }
        return result;
    }    		
	
    public int arrayNesting1(int[] nums) {
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < 0) continue;
            int length = 1, val = nums[i];
            while (Math.abs(val) != i) {
                length++;
                val = nums[Math.abs(val)];
                nums[Math.abs(val)] *= -1;
            }
            res = Math.max(res, length);
        }
        return res;   
    }
	
	
	public int arrayNesting_wrong(int[] nums) {
    	/**
    	 * when nums[0] = 0, * (-1) will cause infinite loop!!!
    	 */
        if (nums == null || nums.length == 0) {
        		return 0;
        }
        int result = 0;
        for (int i = 0; i < nums.length; i++) {
        		if (nums[i] < 0) {
        			continue;
        		}
        		int count = 0;
        		int a = i;
        		while (nums[a] >= 0) {
        			count += 1;
        			int tmp = nums[a];
        			nums[a] = nums[a] * (-1);
        			a = tmp;
        		}
        		result = Math.max(result, count);
        }
        // revertBack(nums);
        return result;
    }
    
    public static void main(String[] args) {
    		//int[] nums = new int[] {0, 1, 2};
    		int[] nums = new int[] {5, 4, 0, 3, 1, 6, 2};
    		ArrayNesting an = new ArrayNesting();
    		System.out.println(an.arrayNesting(nums));
    }
}
