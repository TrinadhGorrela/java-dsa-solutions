/**
 * 1248. Count Number of Nice Subarrays
 * Difficulty: Medium | Tags: Array, Hash Table, Math, Sliding Window, Prefix
 * Sum
 * https://leetcode.com/problems/count-number-of-nice-subarrays/
 *
 * Pattern: Sliding Window (At-Most-K Transformation with Subarray Counting)
 *
 * Key insight: Count subarrays with at most k odd numbers and subtract those
 * with at most k-1 to obtain exactly k. Inside a window ending at right, every
 * start from left to right is valid, so adding right - left + 1 per step counts
 * them all in one linear pass.
 *
 * Time Complexity: O(N) - Two linear atMost passes; each pointer advances at
 * most N times.
 *
 * Space Complexity: O(1) - Only integer counters and pointers are used; no
 * auxiliary collection.
 *
 * Edge Cases Handled: k = 0 (counts all-even subarrays via the negative-k
 * guard); k larger than the total odd count; all-even and all-odd arrays;
 * single-element arrays; negative k (guard returns 0); empty input (returns 0).
 */
class CountNumberOfNiceSubarrays {
    public int numberOfSubarrays(int[] nums, int k) {
        int res = atMost(nums, k) - atMost(nums, k - 1);
        return res;
    }

    private int atMost(int[] nums, int k) {
        if (k < 0) {
            return 0;
        }

        int oddCount = 0;
        int sub = 0;
        int left = 0;

        for (int right = 0; right < nums.length; right++) {
            if (nums[right] % 2 != 0) {
                oddCount++;
            }

            while (left < nums.length && oddCount > k) {
                if (nums[left] % 2 != 0) {
                    oddCount--;
                }

                left++;
            }

            sub += right - left + 1;
        }

        return sub;
    }
}
