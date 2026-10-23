/**
 * 3644. Maximum K to Sort a Permutation
 * Difficulty: Medium | Tags: Array, Bit Manipulation
 * https://leetcode.com/problems/maximum-k-to-sort-a-permutation/
 *
 * Pattern: Bit Manipulation (Bitwise AND of Misplaced Elements)
 *
 * Key insight: A bit can be set in k only if every element that must move has
 * that bit set, since all swapped values must satisfy a & b == k. Therefore the
 * maximum feasible k is the bitwise AND of all values not already at their
 * correct index; any larger mask would require a bit that some misplaced value
 * lacks.
 *
 * Time Complexity: O(N) - Single pass over the permutation, doing one bitwise
 * AND per misplaced element
 *
 * Space Complexity: O(1) - Only a single accumulator variable is used
 *
 * Edge Cases Handled: already-sorted permutation (no misplaced elements, -1
 * sentinel maps to 0), single element (sorted, returns 0), misplaced value 0
 * (AND with 0 correctly yields k = 0), all values reversed/producing no common
 * bits (returns 0); relies on the guaranteed valid permutation and non-null
 * input
 */
class MaximumKToSortAPermutation {
    public int sortPermutation(int[] nums) {
        int res = -1;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i) {
                if (res == -1) {
                    res = nums[i];
                } else {
                    res &= nums[i];
                }
            }
        }

        return res == -1 ? 0 : res;
    }
}
