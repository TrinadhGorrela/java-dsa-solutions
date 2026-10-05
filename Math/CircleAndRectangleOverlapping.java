/**
 * 1401. Circle and Rectangle Overlapping
 * Difficulty: Medium | Tags: Math, Geometry
 * https://leetcode.com/problems/circle-and-rectangle-overlapping/
 *
 * Pattern: Closest-Point Projection (Euclidean Distance)
 *
 * Key insight: A circle overlaps a rectangle iff the distance from its center
 * to the nearest point on the rectangle is <= radius, and that nearest point is
 * found by clamping each center coordinate to the rectangle's bounds. Comparing
 * squared distances avoids an expensive sqrt.
 *
 * Time Complexity: O(1) - A constant number of comparisons and arithmetic
 * operations, independent of input size.
 *
 * Space Complexity: O(1) - Uses only a handful of local int variables.
 *
 * Edge Cases Handled: Center inside the rectangle (clamped to itself, distance
 * 0, always overlapping), center outside on each of the four sides and near the
 * four corners (diagonal nearest point), and tangent contact where the circle
 * just touches an edge or corner (uses <=).
 */
class CircleAndRectangleOverlapping {
    public boolean checkOverlap(int radius, int xCenter, int yCenter, int x1, int y1, int x2, int y2) {
        int nearX = helper(xCenter, x1, x2);
        int nearY = helper(yCenter, y1, y2);

        int deltaX = Math.abs(nearX - xCenter);
        int deltaY = Math.abs(nearY - yCenter);

        int dis = (deltaX * deltaX) + (deltaY * deltaY);

        return dis <= (radius * radius);
    }

    private int helper(int center, int min, int max) {
        if (center >= min && center <= max) {
            return center;
        } else if (center < min) {
            return min;
        } else {
            return max;
        }
    }
}
