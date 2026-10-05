/**
 * 542. 01 Matrix
 * Difficulty: Medium | Tags: Array, Dynamic Programming, Breadth-First Search,
 * Matrix
 * https://leetcode.com/problems/01-matrix/
 *
 * Pattern: Two-pass DP propagation (top-left then bottom-right)
 *
 * Key insight: Seed 0-cells with 0 and 1-cells with m*n (impossible distance);
 * a forward pass propagates distances from top/left neighbors, then a backward
 * pass corrects from bottom/right neighbors, converging to the true
 * nearest-zero distance.
 *
 * Time Complexity: O(m*n) - Two full grid scans
 *
 * Space Complexity: O(1) auxiliary - Output grid is given; no extra allocation
 * beyond it
 *
 * Edge Cases Handled: all-zeros grid stays all-zeros; isolated 1-cells at
 * corners get corrected by the reverse pass; sentinel value m*n is safely
 * larger than any real distance
 */
class ZeroOneMatrix {
    public int[][] updateMatrix(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (mat[i][j] == 1) {
                    dp[i][j] = m * n;
                }
            }
        }

        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[i].length; j++) {
                if (dp[i][j] != 0) {
                    if (i > 0) {
                        dp[i][j] = Math.min(dp[i][j], dp[i - 1][j] + 1);
                    }

                    if (j > 0) {
                        dp[i][j] = Math.min(dp[i][j], dp[i][j - 1] + 1);
                    }
                }
            }
        }

        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if (dp[i][j] != 0) {
                    if (i < m - 1) {
                        dp[i][j] = Math.min(dp[i][j], dp[i + 1][j] + 1);
                    }

                    if (j < n - 1) {
                        dp[i][j] = Math.min(dp[i][j], dp[i][j + 1] + 1);
                    }
                }
            }
        }
        return dp;
    }
}
