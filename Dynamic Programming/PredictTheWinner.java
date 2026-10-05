/**
 * 486. Predict the Winner
 * Difficulty: Medium | Tags: Array, Math, Dynamic Programming, Recursion,
 * Minimax, Game Theory, Zero-Sum Game
 * https://leetcode.com/problems/predict-the-winner/
 *
 * Pattern: Interval DP with minimax recursion and memoization
 *
 * Key insight: solve(start,end) returns the max score difference (current
 * player minus opponent) over nums[start..end]; picking left gives nums[start]
 * - solve(start+1,end), and the player picks whichever end maximizes this
 * difference.
 *
 * Time Complexity: O(n^2) - n^2 unique (start,end) states, each computed in
 * O(1)
 *
 * Space Complexity: O(n^2) - Memoization table of size n x n
 *
 * Edge Cases Handled: start==end returns nums[start] (last coin); difference >=
 * 0 means player 1 wins or ties
 */
class PredictTheWinner {
    public boolean predictTheWinner(int[] nums) {
        int[][] memo = new int[nums.length][nums.length];

        for (int[] i : memo) {
            Arrays.fill(i, -1);
        }

        return solve(memo, nums, 0, nums.length - 1) >= 0;
    }

    public static int solve(int[][] memo, int[] nums, int start, int end) {
        if (start >= end) {
            return nums[start];
        }

        if (memo[start][end] != -1) {
            return memo[start][end];
        }

        int left = nums[start] - solve(memo, nums, start + 1, end);
        int right = nums[end] - solve(memo, nums, start, end - 1);
        memo[start][end] = Math.max(left, right);
        return memo[start][end];
    }
}
