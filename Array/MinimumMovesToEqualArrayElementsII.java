/**
 * 462. Minimum Moves to Equal Array Elements II
 * Difficulty: Medium | Tags: Array, Math, Sorting
 * https://leetcode.com/problems/minimum-moves-to-equal-array-elements-ii/
 *
 * Pattern: Sort + Median Target
 *
 * Key insight: The L1-optimal meeting point (minimizing sum of absolute
 * differences) is the median; after sorting, the middle element is the target
 * and the answer is the sum of |nums[i] - median| for all i.
 *
 * Time Complexity: O(N log N) - Arrays.sort dominates; the subsequent
 * accumulation loop is O(N)
 *
 * Space Complexity: O(1) - In-place sort with only a mid-value and an
 * accumulator beyond the input
 *
 * Edge Cases Handled: single element (0 moves), all elements equal (0 moves),
 * even-length array (uses upper median at N/2), negative values (absolute
 * difference handles sign)
 */
class MinimumMovesToEqualArrayElementsII {
    public int minMoves2(int[] nums) {
        Arrays.sort(nums);
        int mid = nums[nums.length / 2];
        int res = 0;

        for (int i = 0; i < nums.length; i++) {
            res += Math.abs(nums[i] - mid);
        }

        return res;
    }
}
