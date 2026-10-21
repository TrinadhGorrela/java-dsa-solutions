/**
 * 712183. Geek's Training
 * Difficulty: Medium | Tags: Dynamic Programming, Arrays
 * https://www.geeksforgeeks.org/problems/geeks-training/1
 *
 * Pattern: Dynamic Programming (3-State Matrix DP / House Robber on 3 Columns)
 *
 * Key insight: dp[i][j] is the best score over the first i days when day i is
 * spent in column j, and consecutive days cannot use the same column, so each
 * state is simply today's points plus the best of the two OTHER columns from
 * the previous day; the answer is then the largest value in the last row.
 *
 * Time Complexity: O(N) - N days with three states per day, each a
 * constant-time max over two previous values
 *
 * Space Complexity: O(N) - The (N+1) x 4 table keeps every day's three states
 * even though only the last row is needed for the answer
 *
 * Edge Cases Handled: single training day (the base row seeds dp[1][*] from
 * mat[0][*] and the answer is the best of the three columns), the no-repeat
 * column rule (each state consults only the other two columns of the previous
 * row), the input matrix is left unmodified, and the answer is the max over the
 * last row rather than one fixed column. Note: exactly three columns are
 * assumed (mat[0][1] and mat[0][2] are indexed directly), and negative points
 * are not special-cased since one column is always counted per day.
 */
class GeekSTraining {
    public int maximumPoints(int mat[][]) {
        int[][] dp = new int[mat.length + 1][mat[0].length + 1];

        dp[1][0] = mat[0][0];
        dp[1][1] = mat[0][1];
        dp[1][2] = mat[0][2];

        for (int i = 2; i < dp.length; i++) {
            dp[i][0] = mat[i - 1][0] + Math.max(dp[i - 1][1], dp[i - 1][2]);
            dp[i][1] = mat[i - 1][1] + Math.max(dp[i - 1][0], dp[i - 1][2]);
            dp[i][2] = mat[i - 1][2] + Math.max(dp[i - 1][0], dp[i - 1][1]);
        }

        int max = Math.max(dp[dp.length - 1][0], Math.max(dp[dp.length - 1][1], dp[dp.length - 1][2]));
        return max;
    }
}
