/**
 * 2679. Sum in a Matrix
 * Difficulty: Medium | Tags: Array, Sorting, Heap (Priority Queue), Matrix,
 * Simulation
 * https://leetcode.com/problems/sum-in-a-matrix/
 *
 * Pattern: Sorting + Column-Wise Maximum Simulation
 *
 * Key insight: Removing a column's maximum each round is equivalent to sorting
 * every row ascending and, at each column position, taking the largest
 * remaining element across rows. Sorting once makes the greedy removal order
 * exact without simulating rounds.
 *
 * Time Complexity: O(M * N log N) - Dominated by sorting each of the M rows of
 * length N.
 *
 * Space Complexity: O(1) - Rows are sorted in place with only constant
 * auxiliary state (excluding the sort's internal stack).
 *
 * Edge Cases Handled: single row; single column; already-sorted rows; equal
 * values / duplicates; all-same matrix; zeros. Only safe for non-negative input
 * because `max` is initialized to 0, so an all-negative column would report 0
 * instead of its true maximum (LeetCode constraints guarantee non-negative
 * values).
 */
class SumInAMatrix {
    public int matrixSum(int[][] nums) {
        for (int[] row : nums) {
            Arrays.sort(row);
        }

        int score = 0;

        for (int col = nums[0].length - 1; col >= 0; col--) {
            int max = 0;

            for (int row = 0; row < nums.length; row++) {
                max = Math.max(max, nums[row][col]);
            }

            score += max;
        }

        return score;
    }
}
