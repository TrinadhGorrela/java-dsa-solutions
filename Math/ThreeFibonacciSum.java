/**
 * 4078. Three Fibonacci Sum
 * Difficulty: Easy | Tags: None
 * https://leetcode.com/problems/three-fibonacci-sum/
 *
 * Pattern: Math (Parity Pruning) + Iterative Fibonacci Scan
 *
 * Key insight: Three consecutive terms collapse to F_k + F_{k+1} + F_{k+2} =
 * 2 * F_{k+2}, so n must be even and exactly twice a Fibonacci number - the
 * loop walks consecutive pairs checking that triple sum, and can stop once curr
 * >= n because Fibonacci's exponential growth means later triples only
 * overshoot.
 *
 * Time Complexity: O(log n) - Fibonacci values grow by ~phi each step, so the
 * loop runs roughly log_phi(n) times (about 44 iterations at n = 10^9)
 *
 * Space Complexity: O(1) - Only three rolling integers (prev, curr, nxt) are
 * kept
 *
 * Edge Cases Handled: odd n rejected immediately (every consecutive triple sums
 * to an even number); n = 1 (odd, false); n = 2 via the first triple 0+1+1; n =
 * 8 correctly false (only non-consecutive terms sum to it); non-representable
 * even numbers return false when the scan passes n; n = 10^9 stays safe - every
 * sum that could match fits in int, and the single oversized intermediate wraps
 * negative, which can never equal a positive n
 */
class ThreeFibonacciSum {
    public boolean threeFibonacciSum(int n) {
        if (n % 2 != 0) {
            return false;
        }

        int prev = 0;
        int curr = 1;

        while (curr < n) {
            int nxt = prev + curr;
            if (prev + curr + nxt == n) {
                return true;
            }

            prev = curr;
            curr = nxt;
        }

        return false;
    }
}
