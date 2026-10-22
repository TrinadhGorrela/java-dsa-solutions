/**
 * 3942. Minimum Operations to Sort a Permutation
 * Difficulty: Medium | Tags: Array
 * https://leetcode.com/problems/minimum-operations-to-sort-a-permutation/
 *
 * Pattern: Case Analysis on Circular Order (Rotation + Reversal)
 *
 * Key insight: The only two operations are reversing the whole array and
 * rotating left by one, so every reachable arrangement is either a rotation of
 * nums or a rotation of reverse(nums). Sorting is therefore possible exactly
 * when the cyclic order anchored at 0 ascends in one of the two directions - a
 * single circular descent scanning left-to-right (frontDrops == 1) or a single
 * circular ascent (backDrops == 1) - and the cost is then just the cheapest mix
 * of rotations and reversals around that anchor, whose position is the index of
 * 0 that the code tracks as minIn (the index of the minimum element).
 *
 * Time Complexity: O(N) - Two linear passes (circular descent/ascent counting
 * and minimum tracking); the operations themselves are never simulated
 *
 * Space Complexity: O(1) - Only scalar counters (frontDrops, backDrops, minIn);
 * the input array is only read, never copied or modified
 *
 * Edge Cases Handled: n = 1 (the lone element is already sorted, so frontDrops
 * stays 0 and the answer is 0); an already sorted permutation (the answer
 * collapses to 0 because minIn == 0); a cyclic rotation of increasing order
 * (frontDrops == 1 branch: rotate left minIn times, or reverse, rotate left and
 * reverse back for n - minIn + 2); a cyclic rotation of decreasing order
 * (backDrops == 1 branch: n - minIn, or rotate then reverse for minIn + 2);
 * permutations that ascend in neither direction (both counters exceed 1, so -1
 * is returned); values are a permutation of 0..n-1, so every comparison is
 * exact with no duplicates, negatives or overflow. Not handled: an empty array
 * (nums[n - 1] throws ArrayIndexOutOfBoundsException; LeetCode guarantees n >=
 * 1)
 */
class MinimumOperationsToSortAPermutation {
    public int minOperations(int[] nums) {
        int n = nums.length;
        int frontDrops = 0;
        int backDrops = 0;
        int minIn = 0;

        for (int i = 1; i < n; i++) {
            if (nums[i - 1] > nums[i]) {
                frontDrops++;
            }

            if (nums[minIn] > nums[i]) {
                minIn = i;
            }
        }

        if (nums[n - 1] > nums[0]) {
            frontDrops++;
        }

        for (int i = n - 2; i >= 0; i--) {
            if (nums[i + 1] > nums[i]) {
                backDrops++;
            }
        }

        if (nums[0] > nums[n - 1]) {
            backDrops++;
        }

        if (frontDrops == 0) {
            return 0;
        }

        if (frontDrops > 1 && backDrops > 1) {
            return -1;
        }

        if (frontDrops == 1) {
            return Math.min(minIn, n - minIn + 2);
        }

        return Math.min(n - minIn, (minIn + 1) + 1);
    }
}
