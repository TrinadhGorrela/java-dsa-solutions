/**
 * 3693. Climbing Stairs II
 * Difficulty: Medium | Tags: Array, Dynamic Programming
 * https://leetcode.com/problems/climbing-stairs-ii/
 *
 * Pattern: Bottom-Up Dynamic Programming (Tabulation, Fixed-Width Window)
 *
 * Key insight: dp[i] is the cheapest way to land on step i, and the last jump
 * into step i must start at step i-1, i-2 or i-3, so by optimal substructure
 * dp[i] = min over those three predecessors of dp[j] + costs[i-1] + (i-j)^2,
 * where costs[i-1] is the problem's 1-indexed costs[i] charged on landing and
 * (i-j)^2 is the penalty for the jump length. Each state depends on only three
 * earlier states, so one left-to-right pass settles them all.
 *
 * Time Complexity: O(N) - Each of the N+1 states evaluates at most 3 candidate
 * jumps; N = number of stairs
 *
 * Space Complexity: O(N) - A single DP array of size N+1 is kept, although only
 * the last 4 entries are ever read
 *
 * Edge Cases Handled: n = 0 (the array holds one entry, the answer is 0, and
 * the empty transition loop never touches costs[-1]); the first three steps,
 * where Math.max(0, i - 3) clamps the predecessor index so no negative step is
 * used and jumps stay within 1..3; n = 1 (one jump of length 1, costing
 * costs[0] + 1); the Integer.MAX_VALUE seed for unreachable states never enters
 * the arithmetic because step i-1 is always a finite candidate, so the sentinel
 * cannot overflow; requires costs.length == n (the code reads costs[i-1]) and n
 * >= 0 - a shorter costs array or negative n throws
 */
class ClimbingStairsII {
    public int climbStairs(int n, int[] costs) {
        int[] dp = new int[n + 1];

        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        for (int i = 0; i <= n; i++) {
            for (int j = Math.max(0, i - 3); j < i; j++) {
                dp[i] = Math.min(dp[i], dp[j] + costs[i - 1] + ((i - j) * (i - j)));
            }
        }

        return dp[n];
    }
}
