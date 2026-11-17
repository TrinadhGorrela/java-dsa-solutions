/**
 * 704556. Matrix Chain Multiplication
 * Difficulty: Hard | Tags: Dynamic Programming, Matrix
 * https://www.geeksforgeeks.org/problems/matrix-chain-multiplication0303/1
 *
 * Pattern: Dynamic Programming (Bottom-Up Interval DP)
 *
 * Key insight: The last multiplication of a chain split at k costs the product
 * of the two resulting dimensions plus the already-optimal costs of the left
 * and right subchains. Filling shorter intervals first makes every split
 * available in O(1), so overlapping subproblems are solved once.
 *
 * Time Complexity: O(N^3) - N = number of matrices; three nested loops
 * enumerate every interval [i, j] and every split point k.
 *
 * Space Complexity: O(N^2) - One entry per interval in the N x N dp table.
 *
 * Edge Cases Handled: Single matrix (returns 0 multiplications); empty/null
 * input and a single dimension are not handled (array access throws); products
 * must fit in int or the step count overflows.
 */
class MatrixChainMultiplication {
    static int matrixMultiplication(int arr[]) {
        int n = arr.length;
        int[][] dp = new int[n][n];

        for (int i = 1; i < n; i++) {
            dp[i][i] = 0;
        }

        for (int i = n - 1; i >= 1; i--) {
            for (int j = i + 1; j < n; j++) {
                int min = Integer.MAX_VALUE;

                for (int k = i; k < j; k++) {
                    int steps = (arr[i - 1] * arr[k] * arr[j]) + dp[i][k] + dp[k + 1][j];
                    min = Math.min(min, steps);
                }

                dp[i][j] = min;
            }
        }

        return dp[1][n - 1];
    }
}
