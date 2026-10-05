/**
 * 2574. Left and Right Sum Differences
 * Difficulty: Easy | Tags: Array, Prefix Sum
 * https://leetcode.com/problems/left-and-right-sum-differences/
 *
 * Pattern: Bidirectional Prefix Sum
 *
 * Key insight: Compute the left prefix sum and right suffix sum simultaneously
 * in one pass (left fills forward, right fills backward using mirrored
 * indices), then take the absolute difference per index.
 *
 * Time Complexity: O(N) - One pass to build both sum arrays, one pass to
 * compute absolute differences
 *
 * Space Complexity: O(N) - Three auxiliary arrays: leftSum, rightSum, and
 * result, each of length N
 *
 * Edge Cases Handled: single element (both sums are 0, result 0), leftmost
 * index (empty left sum), rightmost index (empty right sum), negative values
 * handled by manual absolute difference
 */
class LeftAndRightSumDifferences {
    public int[] leftRightDifference(int[] nums) {
        int sum1 = 0, sum2 = 0, n = nums.length - 1;
        int[] leftSum = new int[nums.length];
        int[] rightSum = new int[nums.length];
        int[] res = new int[nums.length];
        for (int i = 0; i <= n; i++) {
            leftSum[i] += sum1;
            sum1 += nums[i];
            rightSum[n - i] += sum2;
            sum2 += nums[n - i];
        }
        for (int i = 0; i <= n; i++) {
            if (rightSum[i] - leftSum[i] > 0)
                res[i] = rightSum[i] - leftSum[i];
            else
                res[i] = leftSum[i] - rightSum[i];
        }
        return res;
    }
}
