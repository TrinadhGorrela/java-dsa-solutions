/**
 * 2958. Length of Longest Subarray With at Most K Frequency
 * Difficulty: Medium | Tags: Array, Hash Table, Sliding Window
 * https://leetcode.com/problems/length-of-longest-subarray-with-at-most-k-frequ
 * ency/
 *
 * Pattern: Variable Sliding Window with Frequency Constraint (max k per
 * element)
 *
 * Key insight: Expand right, incrementing each element's frequency in a map.
 * When nums[right]'s frequency exceeds k, shrink from the left (decrementing
 * and advancing) until that specific element's count returns to exactly k. Only
 * the triggering element needs correction, not all elements.
 *
 * Time Complexity: O(N) - Right advances once per iteration; left advances at
 * most N times total; each HashMap operation is O(1) amortized
 *
 * Space Complexity: O(N) - HashMap holds frequency counts for up to N distinct
 * elements
 *
 * Edge Cases Handled: k = 0 with any repeating element (window collapses to 0),
 * single element (result 1), one element repeated > k times (window bounded by
 * k occurrences), all distinct elements (entire array is valid)
 */
class LengthOfLongestSubarrayWithAtMostKFrequency {
    public int maxSubarrayLength(int[] nums, int k) {
        Map<Integer, Integer> freq = new HashMap<>();
        int left = 0;
        int max = 0;
        for (int right = 0; right < nums.length; right++) {
            freq.put(nums[right], freq.getOrDefault(nums[right], 0) + 1);
            while (freq.get(nums[right]) > k) {
                freq.put(nums[left], freq.get(nums[left]) - 1);
                left++;
            }
            max = Math.max(max, right - left + 1);
        }
        return max;
    }
}
