/**
 * 724. Find Pivot Index
 * Difficulty: Easy | Tags: Array, Prefix Sum
 * https://leetcode.com/problems/find-pivot-index/
 *
 * Pattern: Dual Prefix-Sum Arrays
 *
 * Key insight: Build a left prefix sum and a reversed right prefix sum, each of
 * length N+1 with index 0 = 0; for index i, leftSum[i] is the sum of elements
 * left of i and rightSum[N-i-1] is the sum right of i.
 *
 * Time Complexity: O(N) - Three linear passes: one forward (left sums), one
 * reverse (right sums), one comparison pass
 *
 * Space Complexity: O(N) - Two auxiliary arrays of length N+1 store the left
 * and right cumulative sums
 *
 * Edge Cases Handled: single element (leftSum[0]=rightSum[0]=0, pivot 0), pivot
 * at index 0 (empty left sum), pivot at last index (empty right sum), no valid
 * pivot (returns -1)
 */
class FindPivotIndex {
    public int pivotIndex(int[] nums) {
        int[] leftSum = new int[nums.length + 1];
        int[] rightSum = new int[nums.length + 1];

        leftSum[0] = 0;
        rightSum[0] = 0;
        for (int i = 0; i < nums.length; i++) {
            leftSum[i + 1] = leftSum[i] + nums[i];
        }

        for (int i = nums.length - 1; i >= 0; i--) {
            rightSum[nums.length - i] = rightSum[nums.length - i - 1] + nums[i];
        }

        for (int i = 0; i < nums.length; i++) {
            if (leftSum[i] == rightSum[nums.length - i - 1]) {
                return i;
            }
        }
        return -1;
    }
}
