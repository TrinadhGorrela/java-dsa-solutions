/**
 * 3622. Check Divisibility by Digit Sum and Product
 * Difficulty: Easy | Tags: Math
 * https://leetcode.com/problems/check-divisibility-by-digit-sum-and-product/
 *
 * Pattern: Digit Extraction with Simultaneous Accumulators
 *
 * Key insight: Walk the digits via mod 10, accumulating the sum and the product
 * in one pass, then test whether the original number is divisible by (sum +
 * product); the divisor is always positive since sum >= 1 for any n > 0 and
 * product is folded in.
 *
 * Time Complexity: O(log10 n) - One iteration per decimal digit
 *
 * Space Complexity: O(1) - Only sum, product, temp, and digit variables
 *
 * Edge Cases Handled: any digit equal to 0 (product collapses to zero but the
 * divisor remains the sum), single-digit n (sum + product = n + n... depends on
 * digit), divisor always > 0 so modulo never divides by zero
 */
class CheckDivisibilityByDigitSumAndProduct {
    public boolean checkDivisibility(int n) {
        int temp = n;
        int sum = 0;
        int product = 1;

        while (n != 0) {
            int digit = n % 10;
            sum += digit;
            product *= digit;
            n /= 10;
        }

        return temp % (sum + product) == 0;
    }
}
