/**
 * 31. Next Permutation
 * Difficulty: Medium | Tags: Array, Two Pointers
 * https://leetcode.com/problems/next-permutation/
 *
 * Pattern: Pivot-Swap-Suffix-Reverse
 *
 * Key insight: Scanning right-to-left finds the first descent (pivot). The
 * suffix is already in descending order, so swapping pivot with the rightmost
 * element greater than it and then reversing the suffix yields the
 * lexicographically next permutation.
 *
 * Time Complexity: O(N) - Right-to-left pivot scan + right-to-left swap search
 * + in-place suffix reverse; all linear
 *
 * Space Complexity: O(1) - Three index variables and one temp, in-place on
 * input array
 *
 * Edge Cases Handled: Entirely descending input (no pivot found, full reverse
 * yields ascending), all elements equal (no pivot, array unchanged), single
 * element (no-op), pivot at index 0 with full suffix reversal
 */
class NextPermutation {
    public void nextPermutation(int[] nums) {
        int pivot = -1;
        for (int i = nums.length - 2; i >= 0; i--) {
            if (nums[i] < nums[i + 1]) {
                pivot = i;
                int swapWith = pivot + 1;
                for (int j = nums.length - 1; j >= 0; j--) {
                    if (nums[pivot] < nums[j]) {
                        swapWith = j;
                        break;
                    }
                }

                int temp1 = nums[pivot];
                nums[pivot] = nums[swapWith];
                nums[swapWith] = temp1;

                int left = pivot + 1;
                int right = nums.length - 1;

                while (left < right) {
                    int temp2 = nums[left];
                    nums[left] = nums[right];
                    nums[right] = temp2;
                    left++;
                    right--;
                }
                break;
            }
        }
        if (pivot == -1) {
            int left = 0;
            int right = nums.length - 1;
            while (left < right) {
                int temp3 = nums[left];
                nums[left] = nums[right];
                nums[right] = temp3;
                left++;
                right--;
            }
        }

    }
}
