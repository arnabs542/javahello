package misc;

import java.util.List;

/**
 * 139. Word Break DescriptionHintsSubmissionsDiscussSolution Given a non-empty
 * string s and a dictionary wordDict containing a list of non-empty words,
 * determine if s can be segmented into a space-separated sequence of one or
 * more dictionary words.
 * 
 * Note:
 * 
 * The same word in the dictionary may be reused multiple times in the
 * segmentation. You may assume the dictionary does not contain duplicate words.
 * Example 1:
 * 
 * Input: s = "leetcode", wordDict = ["leet", "code"] Output: true Explanation:
 * Return true because "leetcode" can be segmented as "leet code". Example 2:
 * 
 * Input: s = "applepenapple", wordDict = ["apple", "pen"] Output: true
 * Explanation: Return true because "applepenapple" can be segmented as "apple
 * pen apple". Note that you are allowed to reuse a dictionary word. Example 3:
 * 
 * Input: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
 * Output: false
 * 
 * Difficulty:Medium Total Accepted:218.3K Total Submissions:686.5K
 * Contributor:LeetCode Companies
 * 
 * Related Topics
 * 
 * Similar Questions Word Break II
 * 
 * @author
 *
 */
public class WordBreak {
	public boolean wordBreak(String s, List<String> wordDict) {
		if (s == null || s.length() == 0 || wordDict == null || wordDict.size() == 0) {
			return false;
		}
		// dp[i] init
		int n = s.length();
		boolean[] dp = new boolean[n + 1]; // !!! mapping: dp[i] -- s[i - 1]
		dp[0] = true;
		// loop through and keep status
		for (int i = 1; i < n + 1; i++) {
			for (String word : wordDict) {
				int wordLen = word.length();
				if (wordLen <= i && dp[i - wordLen] && s.substring(i - wordLen, i).equals(word)) { // !!! substring
																									// index should -1
					// !!! string equals, not ==
					dp[i] = true;
					break;
				}
			}
		}
		return dp[n];
	}
}
