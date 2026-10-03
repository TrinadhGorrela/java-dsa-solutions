/**
 * 1283. Find the Smallest Divisor Given a Threshold
 * Difficulty: Medium | Tags: Array, Binary Search
 * https://leetcode.com/problems/find-the-smallest-divisor-given-a-threshold/
 *
 * Pattern: Binary Search on Answer
 * Key insight: The sum of divisions decreases monotonically as the divisor increases. Therefore, we can use binary search on the range [1, max(nums)] to find the smallest divisor that produces a sum less than or equal to the threshold.
 *
 * Time Complexity: O(N log M) - Where N is the length of the array and M is the maximum element in the array (the search space size).
 * Space Complexity: O(1) - Only a few integer variables are used.
 *
 * Edge Cases Handled: Threshold is exactly equal to the array length, minimal possible divisor is 1.
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

