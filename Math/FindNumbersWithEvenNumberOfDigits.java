/**
 * 1295. Find Numbers with Even Number of Digits
 * Difficulty: Easy | Tags: Array, Math
 * https://leetcode.com/problems/find-numbers-with-even-number-of-digits/
 *
 * Pattern: Per-Element Digit Counting
 *
 * Key insight: Integer digit count equals floor(log10(n))+1; computed by
 * repeated division by 10, then checked for evenness — no string conversion
 * needed.
 *
 * Time Complexity: O(N * D) - N elements, each requiring D = O(log10(M))
 * divisions where M is the max value
 *
 * Space Complexity: O(1) - Counter and digit-count helper use only scalar
 * variables
 *
 * Edge Cases Handled: single-digit numbers (1 digit, odd — excluded), value 0
 * (0 divisions, digit count 0, even — included), max value 10^5 (5 digits, odd
 * — excluded)
 */
class FindNumbersWithEvenNumberOfDigits {
    public int findNumbers(int[] nums) {
        int count = 0;
        for (int i : nums) {
            if (countDigits(i) % 2 == 0) {
                count++;
            }
        }
        return count;
    }

    public static int countDigits(int n) {
        int digitCount = 0;
        while (n != 0) {
            digitCount++;
            n /= 10;
        }
        return digitCount;
    }
}
