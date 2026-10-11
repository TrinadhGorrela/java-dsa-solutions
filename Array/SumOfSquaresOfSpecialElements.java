/**
 * 2778. Sum of Squares of Special Elements
 * Difficulty: Easy | Tags: Array, Enumeration
 * https://leetcode.com/problems/sum-of-squares-of-special-elements/
 *
 * Pattern: Enumeration (Single-Pass Linear Scan)
 *
 * Key insight: An element is special when its 1-indexed position i divides n,
 * so one pass tests n % (i + 1) == 0 and accumulates nums[i] * nums[i]. The
 * divisibility test is O(1), so no auxiliary structure is required.
 *
 * Time Complexity: O(N) - A single pass over the array with O(1) work per
 * element.
 *
 * Space Complexity: O(1) - Only a running sum accumulator is used.
 *
 * Edge Cases Handled: n == 1 (position 1 is always special), no special
 * elements (returns 0), the always-special final position i == n, and arrays of
 * all equal values; uses int arithmetic, so it is only overflow-safe within the
 * stated value/length constraints.
 */
class SumOfSquaresOfSpecialElements {
    public int sumOfSquares(int[] nums) {
        int sum = 0;
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            if (n % (i + 1) == 0) {
                sum += nums[i] * nums[i];
            }
        }

        return sum;
    }
}
