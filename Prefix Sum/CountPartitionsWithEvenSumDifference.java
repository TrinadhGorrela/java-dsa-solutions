/**
 * 3432. Count Partitions with Even Sum Difference
 * Difficulty: Easy | Tags: Array, Math, Prefix Sum
 * https://leetcode.com/problems/count-partitions-with-even-sum-difference/
 *
 * Pattern: Running Prefix Sum with Parity Check
 *
 * Key insight: leftSum - rightSum = 2*leftSum - total, so the difference is
 * even exactly when total is even — meaning either all N-1 splits are valid or
 * none are. The code still enumerates splits but the parity of the total sum is
 * the decisive factor.
 *
 * Time Complexity: O(N) - One pass to compute total, one pass to evaluate each
 * of the N-1 split positions
 *
 * Space Complexity: O(1) - Only an accumulator for total (long to prevent
 * overflow) and a running left sum
 *
 * Edge Cases Handled: odd-length arrays producing no valid split when total is
 * odd, negative values, large sums requiring long arithmetic
 */
class CountPartitionsWithEvenSumDifference {
    public int countPartitions(int[] nums) {
        int leftSum = 0, rightSum = 0, partitionCount = 0;
        long total = 0;
        for (int i : nums) {
            total = total + i;
        }
        for (int i = 0; i < nums.length - 1; i++) {
            leftSum = leftSum + nums[i];
            rightSum = (int) (total - leftSum);
            if ((leftSum - rightSum) % 2 == 0)
                partitionCount++;
        }
        return partitionCount;
    }
}
