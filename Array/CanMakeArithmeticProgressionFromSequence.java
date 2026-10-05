/**
 * 1502. Can Make Arithmetic Progression From Sequence
 * Difficulty: Easy | Tags: Array, Sorting
 * https://leetcode.com/problems/can-make-arithmetic-progression-from-sequence/
 *
 * Pattern: Sort + Adjacent Difference Check
 *
 * Key insight: After sorting, an arithmetic progression requires a constant
 * difference between every consecutive pair. Compute diff = arr[1] - arr[0]
 * once, then verify all subsequent gaps match it.
 *
 * Time Complexity: O(N log N) - Sorting dominates; the subsequent linear scan
 * is O(N)
 *
 * Space Complexity: O(1) - Sorting is in-place; only a diff variable and loop
 * index are used
 *
 * Edge Cases Handled: exactly two elements (always forms a progression),
 * non-uniform gaps after sorting, negative differences between sorted elements
 */
class CanMakeArithmeticProgressionFromSequence {
    public boolean canMakeArithmeticProgression(int[] arr) {
        Arrays.sort(arr);
        int diff = arr[1] - arr[0];
        for (int i = 2; i < arr.length; i++) {
            if (arr[i] - arr[i - 1] != diff)
                return false;
        }
        return true;
    }
}
