/**
 * 1621. Number of Sets of K Non-Overlapping Line Segments
 * Difficulty: Medium | Tags: Math, Dynamic Programming, Combinatorics, Prefix
 * Sum
 * https://leetcode.com/problems/number-of-sets-of-k-non-overlapping-line-segmen
 * ts/
 *
 * Pattern: Top-Down Dynamic Programming (Memoized DFS) with a (position,
 * segments remaining, currently drawing) state.
 *
 * Key insight: At each point the three choices - skip, start/extend, or finish
 * a segment - are encoded by the isDrawing flag, which enforces that once a
 * segment is closed the next one must start later, guaranteeing non-overlap.
 * Each state transition is O(1), so memoizing the n*k*2 states eliminates
 * repeated subproblem work.
 *
 * Time Complexity: O(N*K) - at most N*(K+1)*2 distinct states, each computed in
 * O(1).
 *
 * Space Complexity: O(N*K) - the Integer[n][k+1][2] memo table, plus recursion
 * depth up to N on the call stack.
 *
 * Edge Cases Handled: k == 0 returns 1 immediately with no segments drawn;
 * running out of points (i >= n) with segments still pending returns 0 (e.g. n
 * == 1 with k >= 1) before any dp index access, avoiding out-of-bounds reads;
 * all sums are taken modulo 1_000_000_007 to keep results within int range.
 */
class NumberOfSetsOfKNonOverlappingLineSegments {
    static int Mod = 1_000_000_007;

    public int numberOfSets(int n, int k) {
        Integer[][][] dp = new Integer[n][k + 1][2];

        return helper(dp, 0, k, 0, n);
    }

    private static int helper(Integer[][][] dp, int i, int j, int isDrawing, int n) {
        if (j == 0) {
            return 1;
        }

        if (i >= n && j > 0) {
            return 0;
        }

        if (dp[i][j][isDrawing] != null) {
            return dp[i][j][isDrawing];
        }

        if (isDrawing == 0) {
            int skip = helper(dp, i + 1, j, 0, n);

            int take = helper(dp, i + 1, j, 1, n);

            dp[i][j][isDrawing] = (skip + take) % Mod;

            return dp[i][j][isDrawing];
        } else {
            int extend = helper(dp, i + 1, j, 1, n);

            int finish = helper(dp, i, j - 1, 0, n);

            dp[i][j][isDrawing] = (extend + finish) % Mod;

            return dp[i][j][isDrawing];
        }
    }
}
