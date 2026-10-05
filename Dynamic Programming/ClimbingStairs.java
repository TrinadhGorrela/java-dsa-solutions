/**
 * 70. Climbing Stairs
 * Difficulty: Easy | Tags: Math, Dynamic Programming, Memoization
 * https://leetcode.com/problems/climbing-stairs/
 *
 * Pattern: Bottom-up 1D DP, Fibonacci recurrence
 *
 * Key insight: ways[i] = ways[i-1] + ways[i-2] because from step i you could
 * have arrived via a 1-step or 2-step jump, making this exactly the Fibonacci
 * sequence shifted by one.
 *
 * Time Complexity: O(n) - Single loop from 3 to n
 *
 * Space Complexity: O(n) - DP array of size n+1
 *
 * Edge Cases Handled: n<=1 returns 1 (no climbing needed); n=2 returns 2
 */
class ClimbingStairs {
    public int climbStairs(int n) {
        if (n <= 1)
            return 1;

        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 2;

        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        
        return dp[n];
    }
}
