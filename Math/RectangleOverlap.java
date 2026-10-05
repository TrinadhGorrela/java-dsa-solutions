/**
 * 836. Rectangle Overlap
 * Difficulty: Easy | Tags: Math, Geometry
 * https://leetcode.com/problems/rectangle-overlap/
 *
 * Pattern: 1D Interval Overlap Projection (Separable Geometry)
 *
 * Key insight: Two axis-aligned rectangles overlap if and only if their
 * projections overlap on BOTH the x-axis and y-axis simultaneously. Project
 * each rectangle onto each axis as a 1D interval, then verify max(lefts) <
 * min(rights) on both axes — a strict inequality ensures shared edges
 * (touching) are not counted as overlap.
 *
 * Time Complexity: O(1) - Fixed number of comparisons and subtractions with no
 * loops or recursion.
 *
 * Space Complexity: O(1) - Uses only a handful of local integer variables.
 *
 * Edge Cases Handled: Rectangles sharing only an edge/corner (touching returns
 * false via strict >), fully contained rectangles, identical rectangles,
 * non-overlapping on one axis only, degenerate zero-width or zero-height
 * rectangles, negative coordinates, large coordinates.
 */
class RectangleOverlap {
    public boolean isRectangleOverlap(int[] rec1, int[] rec2) {
        int minHt = Math.max(rec1[0], rec2[0]);
        int maxHt = Math.min(rec1[2], rec2[2]);

        int ht = maxHt - minHt;

        int minWt = Math.max(rec1[1], rec2[1]);
        int maxWt = Math.min(rec1[3], rec2[3]);

        int wt = maxWt - minWt;

        return (ht > 0) && (wt > 0);
    }
}
