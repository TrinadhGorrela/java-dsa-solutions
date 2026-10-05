/**
 * 189. Rotate Array
 * Difficulty: Medium | Tags: Array, Math, Two Pointers
 * https://leetcode.com/problems/rotate-array/
 *
 * Pattern: In-Place Triple Reversal
 *
 * Key insight: Reversing the entire array then reversing the two halves (first
 * k, then the rest) is equivalent to a right rotation by k — three O(N)
 * in-place reversals avoid any extra array.
 *
 * Time Complexity: O(N) - Three in-place reversals each touching every element
 * once
 *
 * Space Complexity: O(1) - Two-pointer swap with a temp variable; no allocation
 *
 * Edge Cases Handled: k > n (reduced via k % n), k == 0 or k == n (no-op after
 * modulo), single-element array
 */
class RotateArray {
    public static void rotate(int[] nums, int k) {
        int n = nums.length;
        k = k % n;
        swap(nums, 0, n - 1);
        swap(nums, 0, k - 1);
        swap(nums, k, n - 1);
    }

    public static void swap(int[] nums, int left, int right) {
        while (left < right) {
            int temp = nums[left];
            nums[left] = nums[right];
            nums[right] = temp;
            left++;
            right--;
        }
    }
}
