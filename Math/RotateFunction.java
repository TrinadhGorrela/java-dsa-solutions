/**
 * 396. Rotate Function
 * Difficulty: Medium | Tags: Array, Math, Dynamic Programming
 * https://leetcode.com/problems/rotate-function/
 *
 * Pattern: Math (Recurrence Relation)
 *
 * Key insight: Each rotation shifts one element from the weighted sum's tail to
 * subtract n*value, yielding F(k+1) = F(k) + totalSum - n*A[n-k] — avoiding
 * recomputation of every rotation from scratch.
 *
 * Time Complexity: O(N) - First pass computes F(0) and totalSum; second pass
 * applies the recurrence for N-1 rotations
 *
 * Space Complexity: O(1) - Only long accumulators (max, sum, prev) beyond the
 * input array
 *
 * Edge Cases Handled: single element (F(0) is the only rotation), negative
 * values in the array, integer overflow via long arithmetic, all equal values
 * (all rotations yield the same product)
 */
class RotateFunction {
    public int maxRotateFunction(int[] nums) {
        long max = 0;
        long sum = 0;
        for (int i = 0; i < nums.length; i++) {
            max += nums[i] * i;
            sum += nums[i];
        }

        long prev = max;
        int n = nums.length;
        for (int i = 1; i < n; i++) {
            prev = prev + sum - (long) n * nums[n - i];
            max = Math.max(max, prev);

        }
        return (int) max;
    }
}
