/**
 * 516. Longest Palindromic Subsequence
 * Difficulty: Medium | Tags: String, Dynamic Programming
 * https://leetcode.com/problems/longest-palindromic-subsequence/
 *
 * Pattern: LCS DP (LPS reduced to Longest Common Subsequence with the Reversed
 * String)
 *
 * Key insight: the longest palindromic subsequence of s is exactly the LCS of s
 * and its reverse, because a subsequence that reads the same forwards and
 * backwards in s is precisely a sequence common to s and reverse(s); the
 * standard LCS table then applies unchanged.
 *
 * Time Complexity: O(N^2) - fills an (N+1) x (N+1) DP table with constant work
 * per cell (N = s.length())
 *
 * Space Complexity: O(N^2) - the full 2-D LCS table plus the reversed-string
 * copy
 *
 * Edge Cases Handled: empty string (1 x 1 table yields 0), single character
 * (yields 1), all-same string (matches on every diagonal cell), strings that
 * are already palindromes, repeated characters via the max() branch; null input
 * is not guarded (NPE)
 */
class LongestPalindromicSubsequence {
    public int longestPalindromeSubseq(String s) {
        StringBuilder t = new StringBuilder(s);

        return lcs(s, t.reverse().toString());
    }

    private int lcs(String s, String t) {
        int[][] dp = new int[s.length() + 1][t.length() + 1];

        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[i].length; j++) {
                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        return dp[s.length()][t.length()];
    }
}
