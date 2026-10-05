/**
 * 2235. Add Two Integers
 * Difficulty: Easy | Tags: Math
 * https://leetcode.com/problems/add-two-integers/
 *
 * Pattern: Direct Arithmetic Expression
 *
 * Key insight: The sum of two ints is returned by a single + expression; no
 * special logic is required since the Java operator handles the full range of
 * int arithmetic directly.
 *
 * Time Complexity: O(1) - One primitive operation
 *
 * Space Complexity: O(1) - No auxiliary state
 *
 * Edge Cases Handled: both addends zero, negative addends, mixed signs, values
 * near Integer extremes (the + operator performs two's-complement wrap
 * naturally)
 */
class AddTwoIntegers {
    public int sum(int num1, int num2) {
        return num1 + num2;
    }
}
