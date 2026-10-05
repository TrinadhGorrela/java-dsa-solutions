/**
 * 3959. Check Good Integer
 * Difficulty: Easy | Tags: Math, Simulation
 * https://leetcode.com/problems/check-good-integer/
 *
 * Pattern: Single-Pass Digit Aggregation (Sum of Digits vs Sum of Squares)
 *
 * Key insight: Extract each digit once via mod 10 and, in the same pass,
 * accumulate both the digit sum and the sum of digit squares; classify the
 * number by whether squareSum - digitSum clears the threshold of 50.
 *
 * Time Complexity: O(log10 n) - One iteration per decimal digit
 *
 * Space Complexity: O(1) - Only digitSum and squareSum accumulators
 *
 * Edge Cases Handled: n = 0 (loop skipped; both sums 0, fails the check),
 * single-digit n, contributions from digit 0 are zero for both sums
 */
class CheckGoodInteger {
    public boolean checkGoodInteger(int n) {
        int digitSum = 0;
        int squareSum = 0;
        while (n != 0) {
            int last = n % 10;
            digitSum = digitSum + last;
            squareSum = squareSum + (last * last);
            n /= 10;
        }

        return squareSum - digitSum >= 50;
    }
}
