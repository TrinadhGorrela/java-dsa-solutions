/**
 * 788. Rotated Digits
 * Difficulty: Medium | Tags: Math, Dynamic Programming
 * https://leetcode.com/problems/rotated-digits/
 *
 * Pattern: Per-Number Digit Classification (Rotating vs Ambiguous)
 *
 * Key insight: A number is "good" iff it contains no ambiguous digit (3, 4, 7)
 * and at least one genuinely rotating digit (2, 5, 6, 9); digits 0, 1, 8 are
 * valid but rotate to themselves, so they never make a number good on their
 * own.
 *
 * Time Complexity: O(n * d) - d = digit count of numbers up to n, effectively
 * O(n log n)
 *
 * Space Complexity: O(1) - Only boolean valid flag and a temp variable for
 * digit extraction
 *
 * Edge Cases Handled: ambiguous digit encountered (early break, stays invalid),
 * numbers made of only 0/1/8 (valid but not good), single-digit 1 or 8 (not
 * good), small n where only 2,5,6,9 qualify
 */
class RotatedDigits {
    public int rotatedDigits(int n) {
        int res = 0;
        for (int i = 1; i <= n; i++) {
            boolean valid = false;
            int temp = i;
            while (temp != 0) {
                int last = temp % 10;
                if (last == 2 || last == 5 || last == 6 || last == 9) {
                    valid = true;
                } else if (last == 3 || last == 4 || last == 7) {
                    valid = false;
                    break;
                }
                temp /= 10;
            }
            if (valid) {
                res++;
            }
        }
        
        return res;
    }
}
