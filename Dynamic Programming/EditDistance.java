/**
 * 72. Edit Distance
 * Difficulty: Medium | Tags: String, Dynamic Programming
 * https://leetcode.com/problems/edit-distance/
 *
 * Pattern: Dynamic Programming (2D Table / Bottom-Up)
 *
 * Key insight: The edit distance between the prefixes of word1 and word2 only
 * depends on the three adjacent table cells (insert, delete, replace), so each
 * state is computed once from previously solved subproblems. Because the
 * recurrence uses only the previous row/current column, a full 2D table fills
 * correctly in a single bottom-up sweep.
 *
 * Time Complexity: O(M*N) - One pass over every (i, j) cell of the M x N DP
 * table.
 *
 * Space Complexity: O(M*N) - Stores the full 2D DP table.
 *
 * Edge Cases Handled: empty word1 or word2 (returns the length of the longer
 * string via the base-case init of dp[i][0] and dp[0][i]); equal characters
 * (takes the diagonal without a cost); first rows/columns seeded with 0..M and
 * 0..N as deletion/insertion bases.
 */
class EditDistance {
    public int minDistance(String word1, String word2) {
        if (word1.length() == 0 || word2.length() == 0) {
            return Math.max(word1.length(), word2.length());
        }

        int[][] dp = new int[word1.length() + 1][word2.length() + 1];

        for (int i = 0; i <= word1.length(); i++) {
            dp[i][0] = i;
        }

        for (int i = 0; i <= word2.length(); i++) {
            dp[0][i] = i;
        }

        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[i].length; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    int ins = dp[i - 1][j];
                    int del = dp[i][j - 1];
                    int rem = dp[i - 1][j - 1];

                    dp[i][j] = 1 + Math.min(ins, Math.min(del, rem));
                }
            }
        }

        return dp[word1.length()][word2.length()];
    }
}
