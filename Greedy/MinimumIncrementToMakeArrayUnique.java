/**
 * 945. Minimum Increment to Make Array Unique
 * Difficulty: Medium | Tags: Array, Greedy, Sorting, Counting
 * https://leetcode.com/problems/minimum-increment-to-make-array-unique/
 *
 * Pattern: Sort-Then-Bump Greedy
 *
 * Key insight: After sorting, the optimal strategy is to make each element
 * exactly one greater than its predecessor when a duplicate/collision occurs —
 * any larger increment wastes moves. The cost is simply `max(0, prev - curr +
 * 1)`.
 *
 * Time Complexity: O(N log N) - Sorting dominates; the linear scan is O(N) and
 * modifies nums in place
 *
 * Space Complexity: O(1) - In-place sort plus scalar accumulator (excluding
 * sort's internal stack)
 *
 * Edge Cases Handled: all elements identical (cascading bumps), already
 * strictly increasing (zero cost), long duplicate chains (each bump pushes only
 * the minimum needed), single element (no work)
 */
class MinimumIncrementToMakeArrayUnique {
    public int minIncrementForUnique(int[] nums) {
        Arrays.sort(nums);
        int totalMoves = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i - 1] >= nums[i]) {
                totalMoves += nums[i - 1] - nums[i] + 1;
                nums[i] = nums[i - 1] + 1;
            }
        }
        return totalMoves;
    }
}
