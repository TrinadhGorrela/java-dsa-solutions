/**
 * 268. Missing Number
 * Difficulty: Easy | Tags: Array, Hash Table, Math, Binary Search, Bit
 * Manipulation, Sorting
 * https://leetcode.com/problems/missing-number/
 *
 * Pattern: Sort-and-Match Index
 *
 * Key insight: After sorting [0..n] with one missing, every element should
 * satisfy nums[i] == i; the first mismatch reveals the gap — or n itself if no
 * mismatch is found.
 *
 * Time Complexity: O(N log N) - Arrays.sort dominates the single post-sort scan
 *
 * Space Complexity: O(1) - In-place sort with only an index variable
 *
 * Edge Cases Handled: missing number is n (loop completes, returns n), missing
 * number is 0 (mismatch at index 0), single element array
 */
class MissingNumber {
    public int missingNumber(int[] nums) {
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i)
                return i;
        }
        return nums.length;
    }
}
