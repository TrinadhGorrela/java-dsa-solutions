/**
 * 258. Add Digits
 * Difficulty: Easy | Tags: Math, Simulation, Number Theory
 * https://leetcode.com/problems/add-digits/
 *
 * Pattern: Digital Root (Modulo 9 Closed Form)
 *
 * Key insight: Repeatedly summing a number's digits yields its digital root,
 * expressible in closed form: 0 for n = 0, 9 when n % 9 == 0, and n % 9
 * otherwise — avoiding all iteration.
 *
 * Time Complexity: O(1) - Single modulo check, no digit loop
 *
 * Space Complexity: O(1) - No auxiliary data structures
 *
 * Edge Cases Handled: n = 0 (returned as 0, not 9), exact multiples of 9 (digit
 * root 9, handled by the explicit check since n % 9 would give 0), single-digit
 * n already at its root
 */
class AddDigits {
    public int addDigits(int num) {
        if (num == 0) {
            return 0;
        }
        return (num % 9 == 0) ? 9 : num % 9;
    }
}
