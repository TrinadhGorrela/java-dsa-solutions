/**
 * 1137. N-th Tribonacci Number
 * Difficulty: Easy | Tags: Math, Dynamic Programming, Memoization
 * https://leetcode.com/problems/n-th-tribonacci-number/
 *
 * Pattern: Iterative DP with three rolling variables (constant space)
 *
 * Key insight: T(n) = T(n-1) + T(n-2) + T(n-3); instead of an array, maintain
 * three sliding variables (prev1, prev2, prev3) and shift them each iteration,
 * giving O(1) space.
 *
 * Time Complexity: O(n) - Single loop from 3 to n
 *
 * Space Complexity: O(1) - Only three tracking variables plus result
 *
 * Edge Cases Handled: n=0 returns 0; n=1 or n=2 returns 1; n=3 is the first
 * computed sum (0+1+1=2)
 */
class NThTribonacciNumber {
    public int tribonacci(int n) {
        if (n == 0)
            return 0;

        if (n == 1 || n == 2)
            return 1;

        int prev1 = 0, prev2 = 1, prev3 = 1;
        int res = 0;

        for (int i = 3; i <= n; i++) {
            res = prev1 + prev2 + prev3;
            prev1 = prev2;
            prev2 = prev3;
            prev3 = res;
        }
        return res;
    }
}
