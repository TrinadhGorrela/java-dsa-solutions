/**
 * 1837. Sum of Digits in Base K
 * Difficulty: Easy | Tags: Math
 * https://leetcode.com/problems/sum-of-digits-in-base-k/
 *
 * Pattern: Repeated Modulo Base Conversion
 *
 * Key insight: Peel off the least significant base-k digit via n % k,
 * accumulate it, then integer-divide n by k; repeat until n is exhausted.
 *
 * Time Complexity: O(log_k(n)) - Number of digits when n is expressed in base k
 *
 * Space Complexity: O(1) - Only sum and a temporary last-digit variable
 *
 * Edge Cases Handled: n = 0 (loop never executes, returns 0), k > n (single
 * digit returned as-is), k = 2 (binary digit sum)
 */
class SumOfDigitsInBaseK {
    public int sumBase(int n, int k) {
        int sum = 0;
        while (n > 0) {
            int last = n % k;
            sum = sum + last;
            n = n / k;
        }
        return sum;
    }
}
