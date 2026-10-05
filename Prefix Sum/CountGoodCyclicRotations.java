/**
 * 4044. Count Good Cyclic Rotations
 * Difficulty: Medium | Tags: None
 * https://leetcode.com/problems/count-good-cyclic-rotations/
 *
 * Pattern: Prefix Sum Array (Doubled Array + Sliding Range Query)
 *
 * Key insight: Duplicating the array and building a prefix sum over length 2N
 * lets each cyclic rotation be queried as two contiguous window ranges; any
 * rotation starting at i splits into the range [i, i+n/2) and [i+n/2, i+n)
 * without special-casing the wrap-around. Because prefix sums answer each
 * window in O(1), comparing the two halves is constant work per rotation.
 *
 * Time Complexity: O(N) - One pass builds the 2N-length prefix array, then a
 * single O(N) loop checks all rotations.
 *
 * Space Complexity: O(N) - Stores a prefix array of length 2N + 1.
 *
 * Edge Cases Handled: single element (n=1, both halves are equal-length
 * singletons and are compared via prefix sums); rotations whose halves wrap
 * past the end of the array (handled by the doubled prefix array); all-equal
 * elements (both halves equal, never counted as good); partitions where
 * firstHalf <= lastHalf (not counted).
 */
class CountGoodCyclicRotations {
    public int countGoodRotations(int[] nums) {
        int n = nums.length;
        long[] prefix = new long[2 * n + 1];
        int count = 0;

        for (int i = 0; i < prefix.length - 1; i++) {
            prefix[i + 1] = prefix[i] + nums[i % n];
        }

        for (int i = 0; i < n; i++) {
            long firstHalf = prefix[i + n / 2] - prefix[i];
            long lastHalf = prefix[i + n] - prefix[i + n / 2];

            if (firstHalf > lastHalf) {
                count++;
            }
        }

        return count;
    }
}
