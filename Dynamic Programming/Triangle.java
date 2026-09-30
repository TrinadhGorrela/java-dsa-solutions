/**
 * 120. Triangle
 * Difficulty: Medium | Tags: Array, Dynamic Programming
 * https://leetcode.com/problems/triangle/
 *
 * Pattern: Bottom-Up Dynamic Programming (Space-Optimized Row Buffers)
 * Key insight: The minimum path sum from cell (i,j) depends only on the row below it, so walking the
 * triangle upward collapses each row into a single best value per column. Because row i only reads
 * row i+1, the whole triangle collapses into one rolling array whose length-1 shrinking tail keeps the
 * out-of-bounds entries permanently at infinity.
 *
 * Time Complexity: O(N^2) - N rows are processed, each costing O(i) work, summing to O(N^2)
 * Space Complexity: O(N) - Only the current and previous row buffers are held at any time
 *
 * Edge Cases Handled: Single-row triangle (single loop iteration); N == 0 (array of size 1 left
 * uninitialized, returns 0); minimal two-row triangle; highly asymmetric rows where left/right
 * neighbors differ; values at both int extremes are safe since only pairwise sums are compared;
 * negative values (min selection handles them uniformly). NOT handled: null input (would throw
 * NullPointerException), and empty row i shorter than i+1 is assumed valid per LeetCode guarantees.
 */
class Triangle {
    public int minimumTotal(List<List<Integer>> triangle) {
        int[] prev = new int[triangle.size() + 1];

        for (int i = triangle.size() - 1; i >= 0; i--) {
            int[] curr = new int[triangle.size() + 1];

            for (int j = 0; j <= i; j++) {
                curr[j] = Math.min(prev[j] + triangle.get(i).get(j),
                        prev[j + 1] + triangle.get(i).get(j));
            }
            prev = curr;
        }

        return prev[0];
    }
}
