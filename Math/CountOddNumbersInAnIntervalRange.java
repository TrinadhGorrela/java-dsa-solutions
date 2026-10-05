/**
 * 1523. Count Odd Numbers in an Interval Range
 * Difficulty: Easy | Tags: Math
 * https://leetcode.com/problems/count-odd-numbers-in-an-interval-range/
 *
 * Pattern: Arithmetic Counting (Prefix Formula, No Iteration)
 *
 * Key insight: The number of odd integers up to x is (x + 1) / 2; the count in
 * [low, high] is prefix(high) - prefix(low - 1), computed in O(1) via the low /
 * 2 and (high + 1) / 2 closed forms.
 *
 * Time Complexity: O(1) - Constant number of arithmetic operations
 *
 * Space Complexity: O(1) - Only the two prefix-count variables
 *
 * Edge Cases Handled: low == high (single number), even or odd values at either
 * endpoint, low = 0 (prefix up to -1 is correctly 0), full range boundaries
 */
class CountOddNumbersInAnIntervalRange {
    public int countOdds(int low, int high) {
        int lowCount = low / 2;
        int highCount = (high + 1) / 2;

        return highCount - lowCount;
    }
}
