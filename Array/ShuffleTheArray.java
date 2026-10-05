/**
 * 1470. Shuffle the Array
 * Difficulty: Easy | Tags: Array
 * https://leetcode.com/problems/shuffle-the-array/
 *
 * Pattern: Index-Interleaved Construction
 *
 * Key insight: With array length 2n, element i from the first half goes to
 * position 2*i and element i+n from the second half goes to position 2*i+1 — a
 * single loop with computed indices suffices.
 *
 * Time Complexity: O(N) - One loop over n pairs, each writing two positions in
 * the result
 *
 * Space Complexity: O(N) - New result array of length 2n; no in-place shuffle
 *
 * Edge Cases Handled: n=1 (single pair, trivial), all equal values (indices
 * still alternate correctly), negative values (no parity or sign constraints)
 */
class ShuffleTheArray {
    public int[] shuffle(int[] nums, int n) {
        int[] res = new int[2 * n];
        int j = 1;
        for (int i = 0; i < n; i++) {
            res[i + i] = nums[i];
            res[i + j] = nums[i + n];
            j++;
        }
        return res;
    }
}
