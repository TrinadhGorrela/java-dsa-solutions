/**
 * 583. Delete Operation for Two Strings
 * Difficulty: Medium | Tags: String, Dynamic Programming, Longest Common
 * Subsequence
 * https://leetcode.com/problems/delete-operation-for-two-strings/
 *
 * Pattern: Longest Common Subsequence (LCS)
 *
 * Key insight: The minimum deletions to make two strings equal equals
 * len(word1) + len(word2) - 2 × LCS. This holds because we keep the longest
 * shared subsequence and delete everything else from both strings. Standard 2D
 * LCS DP computes dp[i][j] = length of LCS of word1[0..i) and word2[0..j).
 *
 * Time Complexity: O(M * N) — DP table of size (M+1) × (N+1) with constant work
 * per cell
 *
 * Space Complexity: O(M * N) — 2D DP array stores LCS lengths for all prefix
 * pairs
 *
 * Edge Cases Handled: one or both strings empty (LCS = 0, returns sum of
 * lengths), strings already equal (LCS = min length, returns difference of
 * lengths), completely disjoint character sets (LCS = 0)
 */
class DeleteOperationForTwoStrings {
    public int minDistance(String word1, String word2) {
        int[][] dp = new int[word1.length() + 1][word2.length() + 1];

        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[i].length; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        int min = word1.length() + word2.length() - (2 * dp[word1.length()][word2.length()]);
        return min;
    }
}
