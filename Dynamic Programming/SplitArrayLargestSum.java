/**
 * 410. Split Array Largest Sum
 * Difficulty: Hard | Tags: Array, Binary Search, Dynamic Programming, Greedy,
 * Prefix Sum
 * https://leetcode.com/problems/split-array-largest-sum/
 *
 * Pattern: Binary Search on Answer + Greedy Feasibility Check
 *
 * Key insight: The optimal largest split sum lies between the maximum element
 * and the total sum, and feasibility is monotonic: if a cap can split the array
 * into at most k parts, any larger cap can too. For a given cap, a greedy
 * left-to-right scan that starts a new part only when the running sum would
 * exceed the cap uses the fewest parts, so "splits <= k" is a sufficient test.
 *
 * Time Complexity: O(N log S) - Binary search over the answer range S (total
 * sum minus max element), with an O(N) greedy feasibility scan per step.
 *
 * Space Complexity: O(1) - Only scalar pointers and counters are used; the
 * feasibility check runs in place.
 *
 * Edge Cases Handled: k = 1 returns the total sum; k >= n returns the maximum
 * element (each element becomes its own part); single element; all-equal
 * values; assumes non-negative input, since the greedy part count is invalid
 * for negative numbers.
 */
class SplitArrayLargestSum {
    public int splitArray(int[] nums, int k) {
        int left = 0;
        int right = 0;

        for (int num : nums) {
            right += num;
            left = Math.max(left, num);
        }

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (canSplit(nums, k, mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }

        }

        return left;
    }

    private boolean canSplit(int[] nums, int k, int maxSum) {
        int curr = 0;
        int splits = 1;

        for (int num : nums) {
            curr += num;

            if (curr > maxSum) {
                curr = num;
                splits++;
            }
        }

        return splits <= k;
    }
}
