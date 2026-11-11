/**
 * 97. Interleaving String
 * Difficulty: Medium | Tags: String, Dynamic Programming
 * https://leetcode.com/problems/interleaving-string/
 *
 * Pattern: Dynamic Programming (Top-Down Memoization, 2D State)
 *
 * Key insight: Because k = i + j always holds, the progress through s3 is fully
 * determined by (i, j) — how many characters were consumed from s1 and s2 — so
 * the exponential interleaving branching collapses to only (m+1)(n+1) distinct
 * subproblems, each solved once via the memo table.
 *
 * Time Complexity: O(m * n) - each (i, j) memo state is computed exactly once,
 * and each computation performs only O(1) character comparisons.
 *
 * Space Complexity: O(m * n) - the Boolean[m+1][n+1] memo table, plus an O(m +
 * n) recursion stack in the worst case.
 *
 * Edge Cases Handled: total length mismatch (s1.length() + s2.length() !=
 * s3.length() returns false up front), one or both source strings empty (bounds
 * checks and the +1 dp sizing), fully consuming s3 (k == s3.length() base case,
 * safe because lengths were pre-validated), and dead-end states (memoized as
 * false so failed branches are never re-explored). Assumes non-null inputs per
 * LeetCode constraints.
 */
class InterleavingString {
    public boolean isInterleave(String s1, String s2, String s3) {
        if (s1.length() + s2.length() != s3.length()) {
            return false;
        }

        Boolean[][] dp = new Boolean[s1.length() + 1][s2.length() + 1];

        return helper(dp, s1, s2, s3, 0, 0, 0);
    }

    private boolean helper(Boolean[][] dp, String s1, String s2, String s3, int i, int j, int k) {
        if (s3.length() == k) {
            return true;
        }

        if (dp[i][j] != null) {
            return dp[i][j];
        }

        if (i < s1.length()) {
            if (s1.charAt(i) == s3.charAt(k)) {
                dp[i][j] = helper(dp, s1, s2, s3, i + 1, j, k + 1);
                if (dp[i][j]) {
                    return true;
                }
            }
        }

        if (j < s2.length()) {
            if (s2.charAt(j) == s3.charAt(k)) {
                dp[i][j] = helper(dp, s1, s2, s3, i, j + 1, k + 1);
                if (dp[i][j]) {
                    return true;
                }
            }
        }

        dp[i][j] = false;

        return false;
    }
}
