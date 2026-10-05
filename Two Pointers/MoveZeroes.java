/**
 * 283. Move Zeroes
 * Difficulty: Easy | Tags: Array, Two Pointers
 * https://leetcode.com/problems/move-zeroes/
 *
 * Pattern: Stable In-Place Zero Partition
 *
 * Key insight: Swapping each non-zero to the write pointer's position (instead
 * of just overwriting) preserves the relative order of non-zero elements—a
 * critical constraint that distinguishes this from an unstable partition.
 *
 * Time Complexity: O(N) - One pass; writeIndex only advances, never retreats
 *
 * Space Complexity: O(1) - Two index variables and one temp, in-place on input
 * array
 *
 * Edge Cases Handled: No zeros in array (swap is identity), all zeros (no swaps
 * execute), leading zeros, trailing zeros, zeros interleaved with non-zeros
 * throughout
 */
class MoveZeroes {
    public void moveZeroes(int[] nums) {
        int writeIndex = 0, temp;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                temp = nums[i];
                nums[i] = nums[writeIndex];
                nums[writeIndex] = temp;
                writeIndex++;
            }
        }
    }
}
