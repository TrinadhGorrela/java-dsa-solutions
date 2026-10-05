/**
 * 650. 2 Keys Keyboard
 * Difficulty: Medium | Tags: Math, Dynamic Programming
 * https://leetcode.com/problems/2-keys-keyboard/
 *
 * Pattern: Trial-Division Prime Factorization
 *
 * Key insight: Building n characters from 1 requires copy-paste operations
 * whose count equals the sum of n's prime factors (with multiplicity). Each
 * factor p corresponds to one copy + (p-1) pastes = p operations; trial
 * division from 2 upward extracts these factors efficiently.
 *
 * Time Complexity: O(√n) - Trial division loops up to √n; after dividing out
 * all small factors, at most one large prime remains
 *
 * Space Complexity: O(1) - Two integer variables (steps, factors)
 *
 * Edge Cases Handled: n = 1 (returns 0, no operations needed), prime n (single
 * factor = n itself, returns n), n = 4 (2+2 = 4 operations, optimal
 * copy-paste-copy-paste), composite like 12 = 2×2×3 (returns 7)
 */

class TwoKeysKeyboard {
    public int minSteps(int n) {
        int steps = 0;
        int divisor = 2;

        while (n > 1) {
            while (n % divisor == 0) {
                steps += divisor;
                n /= divisor;
            }
            divisor++;
        }
        return steps;
    }
}
