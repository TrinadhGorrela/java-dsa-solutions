/**
 * 509. Fibonacci Number
 * Difficulty: Easy | Tags: Math, Dynamic Programming, Recursion, Memoization
 * https://leetcode.com/problems/fibonacci-number/
 *
 * Pattern: Pure Recursion (Exponential Recurrence)
 *
 * Key insight: Express fib(n) directly as the sum fib(n-1) + fib(n-2) with base
 * cases 0 and 1; every call re-expands into two subproblems, so no result is
 * memoized and the work grows exponentially.
 *
 * Time Complexity: O(2^n) - The recurrence tree branches into two calls at
 * every level
 *
 * Space Complexity: O(n) - Maximum recursion depth is n on the call stack
 *
 * Edge Cases Handled: n = 0 and n = 1 as terminating base cases, large n where
 * the exponential blow-up dominates
 */
class FibonacciNumber {
    public int fib(int n) {
        if (n == 0 || n == 1) {
            return n;
        }
        return fib(n - 1) + fib(n - 2);
    }
}
