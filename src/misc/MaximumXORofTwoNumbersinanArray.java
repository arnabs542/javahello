package misc;

import java.util.HashSet;
import java.util.Set;

/**
 * 
 * 421. Maximum XOR of Two Numbers in an Array
 * 
 * Given a non-empty array of numbers, a0, a1, a2, … , an-1, where 0 ≤ ai < 231.
 * 
 * Find the maximum result of ai XOR aj, where 0 ≤ i, j < n.
 * 
 * Could you do this in O(n) runtime?
 * 
 * Example:
 * 
 * Input: [3, 10, 5, 25, 2, 8]
 * 
 * Output: 28
 * 
 * Explanation: The maximum result is 5 ^ 25 = 28.
 * 
 * 
 * Difficulty:Medium 
 * Total Accepted:24.6K 
 * Total Submissions:51K
 * Contributor:shen5630 Companies
 * 
 * Related Topics
 * 
 * 
 * @author
 *
 */

class Solution {  // ac with 97%
    private static class Trie {
        Trie[] children = new Trie[2];
    }
    
    public int findMaximumXOR(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        // init the trie according to nums, 
        // if a trie does NOT have child 0/1, then children[0/1] would be null
        Trie root = initTrieTree(nums); 
        int result = 0;
        // for each num in nums, find the possible max xor result
        for (int num : nums) {
            // check from highest digit to lowest digit, 32 in total
            // target: 
            // node has the child of (current digit of num ^ 1) -> goto that child, tmpResult != 1 << i
            // otherwise, goto the only child, tmpResult remains unchanged
            int tmpResult = 0;
            Trie node = root;
            for (int i = 31; i >= 0; i--) { // !!! --, not ++
                int digit = (num >> i) & 1; // !!! should be 1 / 0, not 100000000, so num >>, not 1 <<
                int targetChildIdx = digit ^ 1;
                if (node.children[targetChildIdx] != null) {
                    node = node.children[targetChildIdx];
                    tmpResult |= (1 << i);
                } else {
                    node = node.children[digit];
                    // current digit of tmpResult is 0, check to do early pruning
                    if (((result >> i) & 1) > 0) {
                        break;
                    }
                }
                
            }
            result = Math.max(result, tmpResult);
        }
        return result;
    }
    
    private Trie initTrieTree(int[] nums) {
        Trie root = new Trie();
        for (int num : nums) {
            Trie node = root;
            for (int i = 31; i >= 0; i--) {
                // int digit = num & ( 1 << i);  //!!! WRONG way, AIndexOutOfBounds
                int digit = (num >> i) & 1;
                if (node.children[digit] == null) {
                    node.children[digit] = new Trie();
                }
                node = node.children[digit];
            }
        }
        return root;
    }
	
}


public class MaximumXORofTwoNumbersinanArray {
	/**
	 * Ref implementation
	 * Another idea of using Trie.
	 * 1. build trie with children 0, 1 nodes, and with 32 levels (1 bit each level)  -> O(n)
	 * 2. for each num, search in the Trie to find max possible XOR result. -> O(n)
	 * 
	 * Time: O(n)
	 * Space: O(1, 2^32) ??
	 * 
	 * @param nums
	 * @return
	 */
    private static final class Trie {
        Trie[] children;
        public Trie() {
            children = new Trie[2];
        }
    }
	
	public int findMaximumXOR(int[] nums) {
        if(nums == null || nums.length == 0) {
            return 0;
        }
        // Init Trie.
        Trie root = new Trie();
        for(int num: nums) {
            Trie curNode = root;
            for(int i = 31; i >= 0; i --) {
                int curBit = (num >>> i)  & 1;
                if(curNode.children[curBit] == null) {
                    curNode.children[curBit] = new Trie();
                }
                curNode = curNode.children[curBit];
            }
        }
        int max = Integer.MIN_VALUE;
        for(int num: nums) {
            Trie curNode = root;
            int curSum = 0;
            for(int i = 31; i >= 0; i --) {
                int curBit = (num >>> i) & 1;
                if(curNode.children[curBit ^ 1] != null) {
                    curSum += (1 << i);
                    curNode = curNode.children[curBit ^ 1];
                }else {
                    curNode = curNode.children[curBit];
                }

                // for this case: even if all left bits results are 1s, curSum still cannot catch up max value
                if (curSum < max && max - curSum >= (1 << i) - 1) {
                    break;
                }
            }
            max = Math.max(curSum, max);
        }
        return max;
    }
	
	
}



class MaximumXORofTwoNumbersinanArray_HashMap {
	/**
	 * basic idea:
	 * if broute-force way, use for-for loop two xor each two eles in nums, and keep track of max
	 * => Time: O(n ^ 2 ), Space : O(1)
	 * 
	 * a ^ b = c ==> a ^ c = b
	 * so if there is a result, a ^ result = b, use this to GUESS the result, starting from max result 
	 * possibility. suppose a max result on digit k is 1, test it with (if any (number in input ^ result)
	 * is also in the input). if true, then this digit can be 1, otherwise should be 0.
	 * 
	 * Since we are testing digit by digit, remember to maintain the prefix mask as part of the result.
	 * Why? how are you going to guarantee that current digit's 1 (if possible) is generate with the 
	 * same number that forms the previous digits' 1s? ==> by keeping and extending the prefix
	 * 
	 * Time: O(32 * n) => O(n)
	 * Space: O(n)
	 * 
	 * @param nums
	 * @return
	 */
    public int findMaximumXOR(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int result = 0, mask = 0;
        // start to process from left to right (highest to lowest);
        // cause the highest digit's 1 will beat all the sum of it's right digits' 1s.
        // so starting from left will guarantee to find the max XOR result.
        for (int i = 31; i >= 0; i--) {
            mask = result | (1 << i);
            Set<Integer> prefixSet = new HashSet<>();
            for (int num : nums) {
                prefixSet.add(num & mask);  // !!! & , not ^
            }
            for (int prefix : prefixSet) {
                if (prefixSet.contains(mask ^ prefix)) {
                    result = mask;
                    break;
                }
            }
        }
        return result;
    }
	
    public int findMaximumXOR_ref(int[] nums) {
        if (nums == null || nums.length == 0) {
        	return 0;
        }  
        int max = 0, mask = 0;
        // guess the result digit by digit, starting from highest digit
        for (int i = 31; i >= 0; i--) {
        	mask = mask | ( 1 << i);
        	Set<Integer> prefixSet = new HashSet<>();
        	for (int num : nums) {
        		prefixSet.add(num & mask);
        	}
        	// tmp: the guess of result with previous max + current digit set as 1
        	int tmp = max | ( 1 << i);
        	for (int prefix : prefixSet) {
        		// if current digit '1' will have result, update max as tmp
        		if (prefixSet.contains(tmp ^ prefix)) {
        			max = tmp;
        			break;
        		}
        	}
        }
        return max;
    }
    
    
    public static void main(String[] args) {
    	int x = 25 ^ 5;
    	System.out.println(x + "");
    }
}

/**
 * https://kingsfish.github.io/2017/12/15/Leetcode-421-Maximum-XOR-of-Two-Numbers-in-an-Array/
 * 
 * 我以为，上面的解法已经是最佳解法了，结果却发现前面还有一个提交时间小山峰，查看了一下发现有更好的解法，
 * 此解法用到了Tries的数据结构。此数据结构我从来之前都没听说过，研究了一下算法后发现，其实Tries类似于二叉树，
 * 主要的思路是这样：构建一棵深度为33的二叉树。root节点左孩子为1，右孩子为0代表着所有数字的最高位，
 * 其次根据次高位继续往下。如果某一个节点左右子树都不为空，那么得到最终答案的两个数字肯定分别出自于左右子树
 * 且此位为1；如果任意一个为空，那么最终答案该位为0，依次迭代得到最终结果。对此解法我并没有详细研究，感兴趣
 * 的可以自己去看看  
 * https://leetcode.com/problems/maximum-xor-of-two-numbers-in-an-array/discuss/91059/java-on-solution-using-trie
 * 
 * 
 * 
 * ******************************************************************************
 * 
 * Java O(n) solution using bit manipulation and HashMap 53.5K VIEWS 181 Last
 * Edit: June 21, 2018 12:14 PM
 * 
 * tangx668 tangx668 181 public class Solution { public int findMaximumXOR(int[]
 * nums) { int max = 0, mask = 0; for(int i = 31; i >= 0; i--){ mask = mask | (1
 * << i); Set<Integer> set = new HashSet<>(); for(int num : nums){ set.add(num &
 * mask); } int tmp = max | (1 << i); for(int prefix : set){ if(set.contains(tmp
 * ^ prefix)) { max = tmp; break; } } } return max; } }
 * 
 * 
 * I think most people who find it hard to understand the code is stuck on this
 * line if(set.contains(tmp ^ prefix)) The tricky part here is that we need to
 * be aware of a key property of XOR applying on the above line: if A ^ B = C,
 * then A ^ B ^ B = C ^ B, then A = C ^ B Before executing that line, max stands
 * for the maximum we can get if we consider only the most significant i - 1
 * bits, tmp stands for the potential max value we can get when considering the
 * most significant i bits. How can we get this tmp? The only way we can get
 * this value is that we have two values A and B in the set (a set of most
 * significant i bits of each member), such that A ^ B equals to tmp. As
 * mentioned earlier, A ^ B = tmp is equivalent to A = tmp ^ B. Here is where
 * that line comes in: set.contains(tmp ^ B).
 * 
 * BTW, though this is a great solution, it is actually faulty if the input
 * contains negative numbers (though not required by the problem itself) as i
 * starts from 31 instead of 30. It would be a perfect solution if the input is
 * unsigned int instead.
 * 
 * 
 */