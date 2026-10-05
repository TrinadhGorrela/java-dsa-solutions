/**
 * 1929. Concatenation of Array
 * Difficulty: Easy | Tags: Array, Simulation
 * https://leetcode.com/problems/concatenation-of-array/
 *
 * Pattern: Double-Write Loop
 *
 * Key insight: Allocate a 2N result array and write each nums[i] to both
 * position i and position i+N in a single pass, producing nums+nums without
 * string concatenation.
 *
 * Time Complexity: O(N) - One pass writing two result slots per iteration
 *
 * Space Complexity: O(N) - Output array of exactly 2N elements
 *
 * Edge Cases Handled: single element (writes to positions 0 and 1), empty array
 * (2*0 = 0-length output)
 */
class ConcatenationOfArray {
    public int[] getConcatenation(int[] nums) {
        int n = nums.length;
        int[] result = new int[2 * n];
        for (int i = 0; i < n; i++) {
            result[i] = nums[i];
            result[n + i] = nums[i];
        }
        return result;
    }
}
