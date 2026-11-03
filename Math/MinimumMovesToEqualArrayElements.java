/**
 * 453. Minimum Moves to Equal Array Elements
 * Difficulty: Medium | Tags: Array, Math
 * https://leetcode.com/problems/minimum-moves-to-equal-array-elements/
 *
 * Pattern: Array Traversal + Math (Complementary Moves)
 *
 * Key insight: Incrementing n-1 elements is equivalent to decrementing the
 * single untouched element, so the moves required equal the sum of each
 * element's distance below the minimum. Since only relative gaps matter and the
 * largest elements can always be shrunk to the minimum, this lower bound is
 * achievable in exactly that many moves.
 *
 * Time Complexity: O(N) - Two linear passes: one to find the minimum and one to
 * sum the gaps below it.
 *
 * Space Complexity: O(1) - Only a few scalar variables are maintained.
 *
 * Edge Cases Handled: single element (returns 0), all elements already equal
 * (returns 0), negative and mixed-sign values (minimum and differences are
 * computed correctly); empty or null input is not handled because nums[0] is
 * read directly.
 */
class MinimumMovesToEqualArrayElements {
    public int minMoves(int[] nums) {
        int min = nums[0];

        for (int num : nums) {
            if (num < min) {
                min = num;
            }
        }

        int moves = 0;
        for (int num : nums) {
            moves += num - min;
        }

        return moves;
    }
}
