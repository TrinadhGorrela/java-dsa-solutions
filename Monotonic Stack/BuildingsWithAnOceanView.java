/**
 * 1762. Buildings With an Ocean View
 * Difficulty: Medium | Tags: Array, Stack, Monotonic Stack
 * https://leetcode.com/problems/buildings-with-an-ocean-view/
 *
 * Pattern: Monotonic Stack (Right-to-Left, Running Maximum)
 * Key insight: Walk the array from right to left keeping `max`, the tallest building already seen to the right. A building
 * can see the ocean exactly when it is strictly taller than everything east of it, so it qualifies iff heights[i] > max;
 * any shorter one is hidden behind a neighbour and is discarded forever. Because each index is visited once and the max
 * only ever climbs, no stack is needed to store survivors — the running scalar is the whole "stack".
 *
 * Time Complexity: O(n) - One right-to-left pass plus one reversal copy; every index is examined a constant number of times.
 * Space Complexity: O(n) - Collects visible indices into an ArrayList, then allocates the result array of the same size.
 *
 * Edge Cases Handled: strictly increasing heights (only the last building sees the ocean), strictly decreasing heights (every
 * building is visible), equal-height neighbours (strict `>` keeps only the rightmost of a tie), single-element input, empty
 * input (returns a zero-length array), index order (collected right-to-left, then reversed into ascending order). Note: input
 * is assumed non-null and non-negative — a height of 0 is skipped by the `max = 0` sentinel.
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
