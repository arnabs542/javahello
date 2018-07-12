package misc;

import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * 
 * 854. K-Similar Strings 
 * 
 * DescriptionHintsSubmissionsDiscussSolution 
 * 
 * Strings A
 * and B are K-similar (for some non-negative integer K) if we can swap the
 * positions of two letters in A exactly K times so that the resulting string
 * equals B.
 * 
 * Given two anagrams A and B, return the smallest K for which A and B are
 * K-similar.
 * 
 * Example 1:
 * 
 * Input: A = "ab", B = "ba" Output: 1 Example 2:
 * 
 * Input: A = "abc", B = "bca" Output: 2 Example 3:
 * 
 * Input: A = "abac", B = "baca" Output: 2 Example 4:
 * 
 * Input: A = "aabc", B = "abca" Output: 2 Note:
 * 
 * 1 <= A.length == B.length <= 20 A and B contain only lowercase letters from
 * the set {'a', 'b', 'c', 'd', 'e', 'f'}
 * 
 * 
 * @author
 *
 */
public class KSimilarStrings {
    /**
    Assume: A, B are valid inputs
    Basic Idea: use bfs to swap all possible A's char pairs to make it same as B.
    use a queue, to keep candidates of transformed A, for every candidate, only
    swap the first pair (possible pair), then the new form is put to queue again.
    the times of private inner.
    */
    public int kSimilarity(String A, String B) {
        if (A.equals(B)) return 0;
        Deque<Tuple> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        queue.offerLast(new Tuple(A, 0));
        visited.add(A);
        int count = 0; // how many swaps
        while (!queue.isEmpty()) {
            count++;
            int levelSize = queue.size();
            // process each candidate in current level
            for (int i = 0; i < levelSize; i++) {
                Tuple seed = queue.pollFirst();
                // left: get to the first unequal char of A and B
                int left = seed.idx;
                String seedStr = seed.str;
                while(left < seedStr.length() && seedStr.charAt(left) == B.charAt(left)) left++;
                // swap with all possible other chars behind left idx, but will TLE, try to prune some swap using if conditions
                for (int right = left + 1; right < seedStr.length(); right++) {
                    if (seedStr.charAt(right) == B.charAt(right) || seedStr.charAt(right) != B.charAt(left)) continue;  // this line go with left + 1 @ line71
                    // if (seedStr.charAt(right) == B.charAt(right) || seedStr.charAt(left) != B.charAt(right)) continue; // this line go with left @ line 71
                    String newSeed = swap(seedStr, left, right);  // !!! not seedStr = , should use a new String,
                    if (newSeed.equals(B)) return count;  // matched, return result
                    if (visited.add(newSeed)) {
                        queue.offerLast(new Tuple(newSeed, left + 1));
                    }
                }
            }
        }
        return -1;
    }
    

    private String swap(String str, int i, int j) {
        char[] chars = str.toCharArray();
        char tmp = chars[i];
        chars[i] = chars[j];
        chars[j] = tmp;
        return new String(chars);
    }
    
    private static class Tuple {
        String str;
        int idx;
        
        public Tuple(String str, int idx) {
            this.str = str;
            this.idx = idx;
        }
    }
    
    public static void main(String[] args) {
    	//String A = "abac", B = "baca"; // ans 2
    	//String A = "abccaacceecdeea", B = "bcaacceeccdeaae"; // ans 9
    	String A = "abbcac", B = "abcbca"; // ans 2

    	KSimilarStrings sol = new KSimilarStrings();
    	System.out.println(sol.kSimilarity(A, B));
    }
}
