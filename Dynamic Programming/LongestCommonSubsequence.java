/**
 * 1143. Longest Common Subsequence
 * Difficulty: Medium | Tags: String, Dynamic Programming, Longest Common
 * Subsequence
 * https://leetcode.com/problems/longest-common-subsequence/
 *
 * Pattern: 2D bottom-up DP table for subsequence matching
 *
 * Key insight: When text1[i-1]==text2[j-1], extend the diagonal (matched prefix
 * grows); otherwise carry forward the best of dropping one character from
 * either string. The DP invariant is memo[i][j] = LCS length of prefixes of
 * length i and j.
 *
 * Time Complexity: O(m*n) - Fill every cell of the (m+1)x(n+1) table
 *
 * Space Complexity: O(m*n) - Full 2D table
 *
 * Edge Cases Handled: empty strings return 0; no common chars yield 0;
 * identical strings yield min(m,n)
 */
class LongestCommonSubsequence {
    public int longestCommonSubsequence(String text1, String text2) {
        int[][] dp = new int[text1.length() + 1][text2.length() + 1];

        for (int i = 1; i <= text1.length(); i++) {
            for (int j = 1; j <= text2.length(); j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[text1.length()][text2.length()];
    }
}
