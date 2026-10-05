/**
 * 115. Distinct Subsequences
 * Difficulty: Hard | Tags: String, Dynamic Programming
 * https://leetcode.com/problems/distinct-subsequences/
 *
 * Pattern: 2D Memoized DP (Top-Down Recursion + Cache)
 *
 * Key insight: At each pair (sIndex, tIndex), the question is "how many ways
 * can t[tIndex..] be formed from s[sIndex..]?" When characters match, branching
 * occurs: either consume the match (advance both pointers) or skip it (advance
 * s only); when they differ, only skipping is valid. Memoizing each unique
 * (sIndex, tIndex) pair avoids redundant recomputation of overlapping
 * subproblems.
 *
 * Time Complexity: O(s.length() * t.length()) - Each of the s.length() *
 * t.length() unique subproblem states is solved exactly once due to
 * memoization.
 *
 * Space Complexity: O(s.length() * t.length()) - The memoization table stores a
 * result for every (sIndex, tIndex) pair, plus O(s.length()) recursion stack
 * depth in the worst case.
 *
 * Edge Cases Handled: t longer than s (recursion exhausts s first, returning
 * 0); empty t (immediately returns 1, representing one empty subsequence);
 * empty s with non-empty t (returns 0); no matching characters between s and t
 * (all paths skip, returning 0); s equals t (exactly one valid subsequence, the
 * full string); duplicate characters in s creating multiple valid alignments.
 */
class DistinctSubsequences {
    public int numDistinct(String s, String t) {
        int[][] dp = new int[s.length()][t.length()];

        for (int[] i : dp) {
            Arrays.fill(i, -1);
        }

        return helper(dp, s, t, 0, 0);
    }

    private static int helper(int[][] dp, String s, String t, int sIndex, int tIndex) {
        if (tIndex == t.length()) {
            return 1;
        }

        if (sIndex == s.length()) {
            return 0;
        }

        if (dp[sIndex][tIndex] != -1) {
            return dp[sIndex][tIndex];
        }

        if (s.charAt(sIndex) == t.charAt(tIndex)) {
            int pick = helper(dp, s, t, sIndex + 1, tIndex + 1);
            int skip = helper(dp, s, t, sIndex + 1, tIndex);

            dp[sIndex][tIndex] = pick + skip;

            return dp[sIndex][tIndex];
        }

        int skip = helper(dp, s, t, sIndex + 1, tIndex);

        dp[sIndex][tIndex] = skip;

        return dp[sIndex][tIndex];
    }
}
