/**
 * 1762. Buildings With an Ocean View
 * Difficulty: Medium | Tags: Array, Stack, Monotonic Stack
 * https://leetcode.com/problems/buildings-with-an-ocean-view/
 *
 * Pattern: Right-to-Left Traversal with a Running Maximum (stack-free monotonic scan)
 * Key insight: A building can see the ocean exactly when it is strictly taller than every
 * building to its right. Scanning from the end while tracking the tallest building seen so far
 * means the first building that beats that maximum is visible by definition, and every building
 * is examined exactly once - no stack storage is required for a strict visibility test.
 *
 * Time Complexity: O(N) - One reverse pass over heights plus one pass to reverse the collected indices
 * Space Complexity: O(N) - Output collection grows to the number of visible buildings (O(1) auxiliary besides output)
 *
 * Edge Cases Handled: empty array (returns int[0]); single element (always visible); equal-height
 * neighbours (strict `max < heights[i]` keeps only the rightmost of a tie, avoiding duplicates);
 * already-decreasing input where every building is visible; result indices are unshifted into
 * strictly increasing order by the final reversal. Assumes heights >= 1, so a height of exactly 0
 * would be skipped by the `max = 0` sentinel.
 */
class BuildingsWithAnOceanView {
    public int[] findBuildings(int[] heights) {
        List<Integer> result = new ArrayList<>();
        int max = 0;

        for (int i = heights.length - 1; i >= 0; i--) {
            if (max < heights[i]) {
                result.add(i);
                max = heights[i];
            }
        }

        int n = result.size();
        int[] res = new int[n];

        for (int i = 0; i < res.length; i++) {
            res[i] = result.get(n - i - 1);
        }

        return res;
    }
}
