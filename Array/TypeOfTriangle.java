/**
 * 3024. Type of Triangle
 * Difficulty: Easy | Tags: Array, Math, Sorting, Polygons
 * https://leetcode.com/problems/type-of-triangle/
 *
 * Pattern: Conditional Triangle Classification
 *
 * Key insight: First reject degenerate triangles via the triangle inequality
 * (any side >= sum of the other two → "none"), then classify by equality: all
 * three equal → equilateral, exactly two → isosceles, zero → scalene.
 *
 * Time Complexity: O(1) - Three comparisons plus branch logic, no loops
 *
 * Space Complexity: O(1) - Three integer variables for the sides
 *
 * Edge Cases Handled: degenerate case where one side equals the sum of the
 * other two (returns "none"), equilateral (all equal), isosceles with two
 * equal, all sides distinct
 */
class TypeOfTriangle {
    public String triangleType(int[] nums) {
        int a = nums[0];
        int b = nums[1];
        int c = nums[2];
        if ((a + b) <= c || (a + c) <= b || (b + c) <= a)   {
            return "none";
        } else if ((a == b) && (b == c)) {
            return "equilateral";
        } else if ((a == b) || (b == c) || (c==a))   {
            return "isosceles";
        } else {
            return "scalene";
        }
    }
}
