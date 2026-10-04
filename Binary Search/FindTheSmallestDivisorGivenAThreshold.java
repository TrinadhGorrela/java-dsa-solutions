/**
 * 1283. Find the Smallest Divisor Given a Threshold
 * Difficulty: Medium | Tags: Array, Binary Search
 * https://leetcode.com/problems/find-the-smallest-divisor-given-a-threshold/
 *
 * Pattern: Binary Search on the Answer (Monotonic Predicate)
 * Key insight: The total cost sum(ceil(nums[i] / divisor)) never increases as the divisor
 * *            grows, so the feasible divisors form a suffix of [1, max(nums)]. Binary search
 * *            therefore shrinks toward the leftmost feasible value, and max(nums) itself is
 * *            always feasible, which guarantees the search finds a solution.
 *
 * Time Complexity: O(N log M) - N summation passes, each halving the search range of size M = max(nums)
 * Space Complexity: O(1) - Only a handful of scalar counters; nums is scanned in place
 *
 * Edge Cases Handled: single-element arrays, arrays of all-same values, divisor bound 1 (the
 * lower end of the search) and max(nums) (the guaranteed-feasible upper end), integer overflow
 * avoided by computing the per-element ceiling in double precision, and the mid split written
 * as left + (right - left) / 2 so large ranges cannot overflow. If threshold were smaller than
 * nums.length the search would find no feasible divisor and the method would return -1, which
 * LeetCode's constraints (threshold >= nums.length) make unreachable.
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
