/**
 * 1283. Find the Smallest Divisor Given a Threshold
 * Difficulty: Medium | Tags: Array, Binary Search
 * https://leetcode.com/problems/find-the-smallest-divisor-given-a-threshold/
 *
 * Pattern:
 * Key insight:
 *
 * Time Complexity: O(?)
 * Space Complexity: O(?)
 *
 * Edge Cases Handled: Per LeetCode constraints
 */
class FindTheSmallestDivisorGivenAThreshold {
    public int smallestDivisor(int[] nums, int threshold) {
        int left = 1;
        int right = 0;
        int res = -1;

        for (int i : nums) {
            right = Math.max(right, i);
        }

        while (left <= right) {
            int mid = left + (right - left) / 2;
            int temp = sumCalc(nums, mid);

            if (temp <= threshold) {
                res = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return res;
    }

    public static int sumCalc(int[] nums, int n) {
        int sum = 0;
        for (int i : nums) {
            sum += Math.ceil((double) i / n);
        }

        return sum;
    }
}
