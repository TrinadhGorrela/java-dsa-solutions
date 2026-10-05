/**
 * 507. Perfect Number
 * Difficulty: Easy | Tags: Math
 * https://leetcode.com/problems/perfect-number/
 *
 * Pattern: Divisor Pair Summation (Square-Root Bound)
 *
 * Key insight: Each proper divisor d pairs with num/d, so scanning up to
 * sqrt(num) captures all pairs in half the work; carefully skip double-counting
 * the square root and exclude the number itself from the sum.
 *
 * Time Complexity: O(sqrt(num)) - Loop bounded by square root of num
 *
 * Space Complexity: O(1) - Only the running divisorSum accumulator
 *
 * Edge Cases Handled: num = 1 (only divisor 1 == num, excluded, returns false),
 * perfect-square divisors (i == num/i, not double-counted), the divisor pair
 * equal to num itself skipped via num / i != num
 */
class PerfectNumber {
    public boolean checkPerfectNumber(int num) {

        int divisorSum = 0;
        for (int i = 1; i <= Math.sqrt(num); i++) {
            if (num % i == 0) {
                divisorSum = divisorSum + i;
                if (num / i != i && num / i != num) {
                    divisorSum = divisorSum + num / i;
                }
            }
        }

        return divisorSum == num ? true : false;
    }
}