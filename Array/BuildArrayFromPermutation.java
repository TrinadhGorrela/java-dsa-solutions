/**
 * 1920. Build Array from Permutation
 * Difficulty: Easy | Tags: Array, Simulation
 * https://leetcode.com/problems/build-array-from-permutation/
 *
 * Pattern: Direct Index Chaining
 *
 * Key insight: Since nums is a permutation of [0, N-1], nums[i] is always a
 * valid index, so result[i] = nums[nums[i]] can be computed directly without
 * bounds checks.
 *
 * Time Complexity: O(N) - Single pass writing one result element per index
 *
 * Space Complexity: O(N) - Allocates the output array of length N
 *
 * Edge Cases Handled: single element (result[0] = nums[nums[0]]),
 * self-referencing cycles (e.g., nums[i] = i)
 */
class BuildArrayFromPermutation {
    public int[] buildArray(int[] nums) {
        int[] result = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            result[i] = nums[nums[i]];
        }
        return result;
    }
}
