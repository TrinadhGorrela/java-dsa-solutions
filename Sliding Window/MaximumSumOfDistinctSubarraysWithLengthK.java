/**
 * 2461. Maximum Sum of Distinct Subarrays With Length K
 * Difficulty: Medium | Tags: Array, Hash Table, Sliding Window
 * https://leetcode.com/problems/maximum-sum-of-distinct-subarrays-with-length-k
 * /
 *
 * Pattern: Sliding Window + Hash Set (Fixed Size k, Distinct Elements)
 *
 * Key insight: Before inserting nums[right], shrink from the left until the
 * window has fewer than k elements and contains no duplicate of nums[right], so
 * every full window visited is distinct. Each index enters and leaves the set
 * at most once, keeping the scan linear.
 *
 * Time Complexity: O(N) - Each element is added and removed at most once, with
 * O(1) average HashSet operations
 *
 * Space Complexity: O(K) - The HashSet holds at most k distinct values of the
 * current window
 *
 * Edge Cases Handled: k = 1; k equal to nums.length; k larger than nums.length
 * (returns 0); repeated values forcing multiple left-shrinks; all-identical
 * input (no valid length-k window, returns 0); only safe for non-negative nums
 * because max starts at 0; null input is not validated
 */
class MaximumSumOfDistinctSubarraysWithLengthK {
    public long maximumSubarraySum(int[] nums, int k) {
        Set<Integer> seen = new HashSet<>();
        long sum = 0;
        int left = 0;
        long max = 0;

        for (int right = 0; right < nums.length; right++) {

            while (seen.size() >= k || seen.contains(nums[right])) {
                seen.remove(nums[left]);
                sum -= nums[left];
                left++;
            }

            seen.add(nums[right]);
            sum += nums[right];

            if (seen.size() == k) {
                max = Math.max(max, sum);
            }
        }

        return max;
    }
}
