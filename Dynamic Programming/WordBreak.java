/**
 * 139. Word Break
 * Difficulty: Medium | Tags: Array, Hash Table, String, Dynamic Programming,
 * Trie, Memoization, Brute-Force Search
 * https://leetcode.com/problems/word-break/
 *
 * Pattern: Bottom-Up Dynamic Programming + Hash Set Lookup (Bounded Split
 * Window)
 *
 * Key insight: dp[i] is true iff the prefix s[0..i) can be segmented into
 * dictionary words; since no dictionary word exceeds maxLen, only split points
 * in [i - maxLen, i) can ever succeed, so the inner loop is bounded by maxLen
 * instead of scanning the whole prefix.
 *
 * Time Complexity: O(N * L^2) - N positions, at most L split points per
 * position, each requiring an O(L) substring build + hash lookup (L = longest
 * dict word)
 *
 * Space Complexity: O(N + D) - dp array of size N + 1 plus a hash set holding
 * the dictionary strings
 *
 * Edge Cases Handled: empty string (dp[0] = true base case), empty wordDict
 * (maxLen = 0 skips the inner loop, correctly returns false), split index
 * clamped via Math.max(0, i - maxLen) to avoid negative substring bounds, early
 * break on the first successful split, duplicate words in the dictionary
 * (deduplicated by the HashSet); null inputs are not guarded (NPE)
 */
class WordBreak {
    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> set = new HashSet<>(wordDict);
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;
        int maxLen = 0;

        for (String t : wordDict) {
            maxLen = Math.max(maxLen, t.length());
        }

        for (int i = 1; i < dp.length; i++) {
            for (int j = i - 1; j >= Math.max(0, i - maxLen); j--) {
                if (dp[j] && set.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }

        return dp[s.length()];
    }
}
