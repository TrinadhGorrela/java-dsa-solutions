/**
 * 1283. Find the Smallest Divisor Given a Threshold
 * Difficulty: Medium | Tags: Array, Binary Search
 * https://leetcode.com/problems/find-the-smallest-divisor-given-a-threshold/
 *
 * Pattern: Binary Search on the Answer (monotonic predicate over divisor
 * values)
 *
 * Key insight: The cost sum(ceil(nums[i] / d)) is monotonically non-increasing
 * as the divisor d grows, so "cost(d) <= threshold" is a monotone predicate:
 * once it holds it holds for every larger d. That lets us binary search the
 * smallest satisfying d, discarding the upper half whenever the current mid
 * works and the lower half whenever it does not. The bracket [1, max(nums)] is
 * safe because d = max(nums) makes every term 1, so the total is just the
 * element count.
 *
 * Time Complexity: O(N log M) - Each of the O(log M) binary search steps
 * rescans all N elements to accumulate the ceiling divisions, where M =
 * max(nums).
 *
 * Space Complexity: O(1) - Only a fixed number of scalars (lo, hi, mid, running
 * sum, result); no extra collection is allocated, and the input array is
 * reused.
 *
 * Edge Cases Handled: single-element array (search collapses to that value);
 * all elements equal; divisor 1 (sum equals the raw array sum, so it fails
 * whenever that exceeds the threshold); threshold exactly equal to N so the
 * answer is max(nums); the answer-equals-mid case records the candidate and
 * keeps searching left, guaranteeing the minimum rather than any valid value;
 * ceiling division done in double via Math.ceil then accumulated in int, which
 * stays exact for LeetCode's value ranges. An empty or null array is not
 * guarded - it falls through the loop and returns the -1 sentinel.
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
