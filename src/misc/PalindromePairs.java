package misc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * 
 * 336. Palindrome Pairs
 * 
DescriptionHintsSubmissionsDiscussSolution
Given a list of unique words, find all pairs of distinct indices (i, j) in the given list, so that the concatenation of the two words, i.e. words[i] + words[j] is a palindrome.

Example 1:
Given words = ["bat", "tab", "cat"]
Return [[0, 1], [1, 0]]
The palindromes are ["battab", "tabbat"]
Example 2:
Given words = ["abcd", "dcba", "lls", "s", "sssll"]
Return [[0, 1], [1, 0], [3, 2], [2, 4]]
The palindromes are ["dcbaabcd", "abcddcba", "slls", "llssssll"]
Credits:
Special thanks to @dietpepsi for adding this problem and creating all test cases.

Difficulty:Hard
Total Accepted:40.3K
Total Submissions:147.4K
Contributor:LeetCode
Companies 

Related Topics 

Similar Questions 
Longest Palindromic SubstringShortest Palindrome


python
https://leetcode.com/problems/palindrome-pairs/discuss/79209/Accepted-Python-Solution-With-Explanation



 * 
 * @author 
 *
 */
public class PalindromePairs {
	// Trie method, 97%+, https://leetcode.com/problems/palindrome-pairs/discuss/79195/O(n*k2)-java-solution-with-Trie-structure
	private static class TrieNode {
		TrieNode[] next;
		int index;
		List<Integer> list;
		
		TrieNode() {
			next = new TrieNode[26];
			index = -1;
			list = new ArrayList<>();
		}
	}
	
    public List<List<Integer>> palindromePairs(String[] words) {
    	List<List<Integer>> result = new ArrayList<>();
    	TrieNode root = new TrieNode();
    	for (int i = 0; i < words.length; i++) {
    		addWord(root, words[i], i);
    	}
    	for (int i = 0; i < words.length; i++) {
    		search(words, i, root, result);
    	}
    	return result;
    }
	
    // add reverse of word into trie
    private void addWord(TrieNode root, String word, int idx) {
    	for (int i = word.length() - 1; i >= 0; i--) {
    		int j = word.charAt(i) - 'a';
    		if (root.next[j] == null) {
    			root.next[j] = new TrieNode();
    		}
    		if (isPalindrome(word, 0, i)) {
				root.list.add(idx);
			}
    		root = root.next[j];
    	}
    	root.list.add(idx); // list: for palindrome of remaining substring of word(reversed)
    	root.index = idx;  // label this node as a word's ending (reversed)
    }
	
    // search each word in the trie
    private void search(String[] words, int idx, TrieNode root, List<List<Integer>> result) {
    	String word = words[idx];
    	for (int j = 0; j < word.length(); j++) {
    		// for cases when (aaa|palindrom)|(aaa)
    		if (root.index >= 0 && root.index != idx && isPalindrome(word, j, word.length() - 1)) {
    			result.add(Arrays.asList(idx, root.index));
    		}
    		root = root.next[word.charAt(j) - 'a'];
    		if (root == null) {
    			return;
    		}
    	}
    	// for cases when (idx, j) words are like (aaa)|(palindrome|aaa), 
    	for (int j : root.list) {
    		if (idx == j) continue;
    		result.add(Arrays.asList(idx, j));
    	}
    }
    
    private boolean isPalindrome(String word, int i, int j) {
        while (i < j) {
        	if (word.charAt(i++) != word.charAt(j--)) return false;
        }	
        return true;
    }
	
	////////////////////////////////////////////////////////////////////////////////////////
	
	// HashMap way
    public List<List<Integer>> palindromePairs_HashMapWay(String[] words) {  // 80%
    	List<List<Integer>> result = new ArrayList<>();
    	if (words == null || words.length == 0) {
    		return result;
    	}
    	// build the map, with each word and it's prefix
    	HashMap<String, Integer> map = new HashMap<>();
    	for (int i = 0; i < words.length; i++) {
    		map.put(words[i], i);
    	}

    	// special cases: "" can be combin with any palindrom string
    	if (map.containsKey("")) {
    		int blankIdx = map.get("");
    		for (int i = 0; i < words.length; i++) {
    			if (i == blankIdx) continue;
    			if (isPalindrome(words[i])) {
    				result.add(Arrays.asList(blankIdx, i));
    				result.add(Arrays.asList(i, blankIdx));
    			}
    		}
    	}
    	
    	// find all string and reverse string pairs
    	for (int i = 0; i < words.length; i++) {
    		String cur_r = reverseStr(words[i]);
    		if (map.containsKey(cur_r)) {
    			int found = map.get(cur_r);
    			if (found == i) continue;
    			result.add(Arrays.asList(i, found));
    		}
    	}
    	
    	// find the pair s1, s2 that 
    	// case 1: s1[0: cut] is palindrom and s1[cut + 1] = reverse(s2) ==> (s2, s1)
    	// case 2: s1[cut+1: ] is palindrom and s1[0: cut] = reverse(s2) => (s1, s2)
    	for (int i = 0; i < words.length; i++) {
    		String word = words[i];
    		for (int cut = 1; cut < word.length(); cut++) {
    			if (isPalindrome(word.substring(0, cut))) {
    				String cut_r = reverseStr(word.substring(cut));
    				if (map.containsKey(cut_r)) {
    					int found = map.get(cut_r);
    					if (found == i) continue;
    					result.add(Arrays.asList(found, i));
    				}
    			}
    			if (isPalindrome(word.substring(cut))) {
    				String cut_r = reverseStr(word.substring(0, cut));
    				if (map.containsKey(cut_r)) {
    					int found = map.get(cut_r);
    					if (found == i) continue;
    					result.add(Arrays.asList(i, found));
    				}
    			}
    		}
    	}
    	return result;
    }
    
    private String reverseStr(String str) {
    	StringBuilder sb = new StringBuilder(str);
    	return sb.reverse().toString();
    }
    
    private boolean isPalindrome(String s) {
    	int l = 0; 
    	int r = s.length() - 1;
    	while (l < r) {
    		if (s.charAt(l) != s.charAt(r)) {
    			return false;
    		}
    		l++;
    		r--;
    	}
    	return true;
    }
    
    
}
