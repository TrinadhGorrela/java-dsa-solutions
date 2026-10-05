/**
 * 912. Sort an Array
 * Difficulty: Medium | Tags: Array, Divide and Conquer, Sorting, Heap (Priority
 * Queue), Merge Sort, Bucket Sort, Radix Sort, Counting Sort
 * https://leetcode.com/problems/sort-an-array/
 *
 * Pattern: Top-Down Merge Sort
 *
 * Key insight: Recursively split the array at the midpoint until single-element
 * base cases, then merge two sorted halves back in-place using a temporary
 * buffer—classic divide-and-conquer yielding guaranteed O(n log n).
 *
 * Time Complexity: O(n log n) - Array is halved log n times; each merge level
 * scans all n elements.
 *
 * Space Complexity: O(n) - Temporary array used during each merge pass.
 *
 * Edge Cases Handled: single element (base case), already sorted input,
 * reverse-sorted input, all equal elements, negative values
 */
class SortAnArray {
    public int[] sortArray(int[] nums) {
        return divide(nums, 0, nums.length - 1);
    }

    public static int[] divide(int[] nums, int st, int end) {
        if (st >= end) {
            return nums;
        }

        int mid = st + (end - st) / 2;

        divide(nums, st, mid);
        divide(nums, mid + 1, end);
        return merge(nums, st, mid, end);
    }

    public static int[] merge(int[] nums, int st, int mid, int end) {
        int[] merged = new int[end - st + 1];
        int i = st;
        int j = mid + 1;
        int in = 0;

        while (i <= mid && j <= end) {
            if (nums[i] < nums[j]) {
                merged[in] = nums[i];
                in++;
                i++;
            } else {
                merged[in] = nums[j];
                in++;
                j++;
            }
        }

        while (i <= mid) {
            merged[in] = nums[i];
            in++;
            i++;
        }

        while (j <= end) {
            merged[in] = nums[j];
            in++;
            j++;
        }

        for (int s = 0; s < merged.length; s++) {
            nums[st] = merged[s];
            st++;
        }

        return nums;
    }
}
