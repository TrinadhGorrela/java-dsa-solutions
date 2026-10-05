/**
 * 3471. Find the Largest Almost Missing Integer
 * Difficulty: Easy | Tags: Array, Hash Table
 * https://leetcode.com/problems/find-the-largest-almost-missing-integer/
 *
 * Pattern: Brute-Force Subarray Enumeration
 *
 * Key insight: Enumerate every length-k window and collect elements into
 * subarray lists. For each array position, count how many subarrays contain
 * that value; if exactly one does and the value exceeds the current best,
 * update the answer.
 *
 * Time Complexity: O((N-K+1) * N) - For each of the N-K+1 windows, scanning all
 * elements across all subarrays to count occurrences per value
 *
 * Space Complexity: O((N-K+1) * K) - Stores N-K+1 subarray lists, each of
 * length K
 *
 * Edge Cases Handled: k equals array length (only one subarray), k = 1 (each
 * element is its own subarray), no value appears in exactly one subarray
 * (returns -1), duplicate values spanning multiple windows
 */
class FindTheLargestAlmostMissingInteger {
    public int largestInteger(int[] nums, int k) {
        List<List<Integer>> list = new ArrayList<>();
        int res = -1;
        for (int i = 0; i <= nums.length - k; i++) {
            add(nums, list, i, k);
        }

        for (int i = 0; i < nums.length; i++) {
            int count = 0;
            for (List<Integer> window : list) {
                if (window.contains(nums[i])) {
                    count++;
                }
            }
            if (count == 1 && res < nums[i]) {
                res = nums[i];
            }
        }
        return res;
    }

    public static void add(int[] nums, List<List<Integer>> list, int st, int k) {
        List<Integer> subarray = new ArrayList<>();
        for (int i = st; i < st + k; i++) {
            subarray.add(nums[i]);
        }
        list.add(subarray);
    }
}
