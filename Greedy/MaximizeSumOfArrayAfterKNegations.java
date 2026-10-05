/**
 * 1005. Maximize Sum Of Array After K Negations
 * Difficulty: Easy | Tags: Array, Greedy, Sorting
 * https://leetcode.com/problems/maximize-sum-of-array-after-k-negations/
 *
 * Pattern: Sort-First Greedy Negation
 *
 * Key insight: After sorting, flip negatives from smallest (most negative)
 * upward since each flip adds the most to the sum. If flips remain and k is
 * odd, one final flip on the smallest absolute element costs exactly 2×min — an
 * unavoidable penalty since even remaining flips cancel out.
 *
 * Time Complexity: O(N log N) - Sorting dominates; the negation and sum passes
 * are O(N)
 *
 * Space Complexity: O(1) - In-place sort and scalar accumulators
 *
 * Edge Cases Handled: k = 0 (no flips, original sum), all negatives with k > N
 * (all flipped, then odd-flip adjustment), no negatives (only odd-k adjustment
 * applies), k exactly equals count of negatives
 */
class MaximizeSumOfArrayAfterKNegations {
    public int largestSumAfterKNegations(int[] nums, int k) {
        Arrays.sort(nums);

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < 0 && k != 0) {
                nums[i] *= -1;
                k--;
            }
        }

        k = k % 2;

        int sum = 0;
        for (int i : nums) {
            sum += i;
        }

        if (k == 0) {
            return sum;
        }

        int min = nums[0];
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < min) {
                min = nums[i];
            }
        }

        return sum - 2 * min;
    }
}
