/**
 * 1802. Maximum Value at a Given Index in a Bounded Array
 * Difficulty: Medium | Tags: Math, Binary Search, Greedy
 * https://leetcode.com/problems/maximum-value-at-a-given-index-in-a-bounded-arr
 * ay/
 *
 * Pattern: Binary Search on Answer + Arithmetic Series
 *
 * Key insight: Binary search the peak value at `index`; for each candidate,
 * compute the minimal array sum by summing a trapezoid-shaped decrease (max(1,
 * peak - distance)) on each side using closed-form arithmetic series, all in
 * long to avoid overflow.
 *
 * Time Complexity: O(log maxSum) - Search range is [1, maxSum], each check is
 * O(1) via formula.
 *
 * Space Complexity: O(1) - Only the peak candidate, two side counts, and long
 * accumulators.
 *
 * Edge Cases Handled: index at array edge (one side has zero cells), peak so
 * low that sides clamp to 1, maxSum too small to place any peak (returns 1),
 * long arithmetic prevents overflow on large n
 */
class MaximumValueAtAGivenIndexInABoundedArray {
    public int maxValue(int n, int index, int maxSum) {
        int left = 1;
        int right = maxSum;
        int res = 0;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            long leftCount = index;
            long rightCount = n - 1 - index;

            long total = mid + calcSideSum(mid, leftCount) + calcSideSum(mid, rightCount);

            if (total <= maxSum) {
                res = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return res;
    }

    public static long calcSideSum(int peakValue, long count) {
        if (count == 0) {
            return 0;
        }

        long decreasingCells = Math.min(count, peakValue - 1);
        if (decreasingCells > 0) {
            long first = peakValue - 1;
            long last = peakValue - 1 * decreasingCells;
            long decSum = decreasingCells * (first + last) / 2;
            long ones = count - decreasingCells;
            return decSum + ones;
        }
        return count;
    }
}
