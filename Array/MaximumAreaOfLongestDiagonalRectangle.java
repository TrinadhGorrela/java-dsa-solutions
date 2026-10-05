/**
 * 3000. Maximum Area of Longest Diagonal Rectangle
 * Difficulty: Easy | Tags: Array
 * https://leetcode.com/problems/maximum-area-of-longest-diagonal-rectangle/
 *
 * Pattern: Single-Pass Tracking with Tie-Breaking
 *
 * Key insight: Comparing squared diagonals (l²+w²) avoids floating-point sqrt
 * entirely while preserving ordering; when diagonals tie, the larger area wins
 * — both comparisons are done in integer arithmetic.
 *
 * Time Complexity: O(N) - Single pass over all rectangles
 *
 * Space Complexity: O(1) - Two tracking variables (maxDiag, maxArea)
 *
 * Edge Cases Handled: single rectangle (returned directly), equal diagonal
 * lengths (tie-break on area), large dimension values (squared sums stay within
 * int range for typical inputs)
 */
class MaximumAreaOfLongestDiagonalRectangle {
    public int areaOfMaxDiagonal(int[][] dimensions) {
        int n = dimensions.length;
        int maxDiag = 0;
        int maxArea = 0;
        for (int i = 0; i < n; i++) {
            int l = dimensions[i][0];
            int w = dimensions[i][1];
            int dia = l * l + w * w;
            int area = l * w;
            if (dia > maxDiag) {
                maxDiag = dia;
                maxArea = area;
            } else if (dia == maxDiag) {
                maxArea = Math.max(area, maxArea);
            }
        }
        return maxArea;
    }
}
