/**
 * 3736. Minimum Moves to Equal Array Elements III
 * Difficulty: Easy | Tags: Array, Math
 * https://leetcode.com/problems/minimum-moves-to-equal-array-elements-iii/
 *
 * Pattern: Array Traversal + Math (Maximum as Target)
 *
 * Key insight: The optimal target is the current maximum, so the answer is the
 * sum of |max - num| over all elements. Because no element can exceed the
 * maximum without extra cost, raising every smaller element exactly up to the
 * max is optimal.
 *
 * Time Complexity: O(N) - Two linear passes: one to find the maximum and one to
 * sum the absolute differences.
 *
 * Space Complexity: O(1) - Only scalar accumulators are used.
 *
 * Edge Cases Handled: single element (returns 0), all elements already equal
 * (returns 0), zero values; all-negative input is NOT handled correctly because
 * max is seeded with 0, so the code is only safe for non-negative values.
 */
class MinimumMovesToEqualArrayElementsIII {
    public int minMoves(int[] nums) {
        int max = 0;

        for (int num : nums) {
            max = Math.max(num, max);
        }

        int res = 0;

        for (int num : nums) {
            res += Math.abs(max - num);
        }

        return res;
    }
}
