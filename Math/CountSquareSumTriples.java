/**
 * 1925. Count Square Sum Triples
 * Difficulty: Easy | Tags: Math, Enumeration
 * https://leetcode.com/problems/count-square-sum-triples/
 *
 * Pattern: Nested Enumeration of Pythagorean Integer Triples
 *
 * Key insight: Iterate every (i, j) pair in [1, n], compute c = floor(sqrt(i^2
 * + j^2)), and count the pair only when c^2 reconstructs i^2 + j^2 exactly
 * (re-squaring the integer root avoids floating-point false positives) and c is
 * within range.
 *
 * Time Complexity: O(n^2) - One pass over all ordered pairs (i, j)
 *
 * Space Complexity: O(1) - Only the res counter and temp root computation
 *
 * Edge Cases Handled: floating-point sqrt not exact (integer root re-squared
 * and compared, rejecting near-misses), hypotenuse c > n (excluded), hypotenuse
 * at the boundary c == n (included), i = j pairs where 2i^2 is a perfect square
 */
class CountSquareSumTriples {
    public int countTriples(int n) {
        int res = 0;
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < n + 1; j++) {
                int hypotenuse = (int) Math.sqrt((i * i) + (j * j));
                if ((hypotenuse * hypotenuse) == i * i + j * j && hypotenuse < n + 1)
                    res++;
            }
        }
        return res;
    }
}
