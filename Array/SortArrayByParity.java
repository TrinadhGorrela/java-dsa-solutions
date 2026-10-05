/**
 * 905. Sort Array By Parity
 * Difficulty: Easy | Tags: Array, Two Pointers, Sorting
 * https://leetcode.com/problems/sort-array-by-parity/
 *
 * Pattern: In-place Bubble-Shift Partition
 *
 * Key insight: Maintain a `left` pointer marking where the next even element
 * belongs; when an even is found, bubble it leftward by swapping adjacent
 * elements until it reaches `left`.
 *
 * Time Complexity: O(N^2) - Each even element may bubble past all odd elements
 * preceding it (suboptimal; two-pointer swap is O(N))
 *
 * Space Complexity: O(1) - In-place swaps with only left, right, and temp
 * variables
 *
 * Edge Cases Handled: all odd (left stays 0), all even (each shifts one
 * position), zeros treated as even, negative evens/odds (parity via modulo)
 */
class SortArrayByParity {
    public int[] sortArrayByParity(int[] nums) {
        int left = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] % 2 == 0) {
                int right = i;
                while (right != left) {
                    int temp = nums[right];
                    nums[right] = nums[right - 1];
                    nums[right - 1] = temp;
                    right--;
                }
                left++;
            }
        }
        return nums;
    }
}
