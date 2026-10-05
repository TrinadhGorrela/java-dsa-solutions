/**
 * 1323. Maximum 69 Number
 * Difficulty: Easy | Tags: Math, Greedy
 * https://leetcode.com/problems/maximum-69-number/
 *
 * Pattern: Leftmost-Position Greedy Flip
 *
 * Key insight: Flipping any '6' to '9' adds 3 × 10^k where k is the position;
 * the most significant '6' has the largest k, so flipping it first is globally
 * optimal — scanning left-to-right and breaking on the first '6' achieves this.
 *
 * Time Complexity: O(D) - D = number of digits; single scan with early exit
 *
 * Space Complexity: O(D) - Char array copy of the digit string
 *
 * Edge Cases Handled: no '6' digit (number unchanged, loop exits without
 * modification), all '6's (only the leftmost is flipped), single-digit input,
 * number consisting of only '9's
 */
class Maximum69Number {
    public int maximum69Number(int num) {
        char[] digits = String.valueOf(num).toCharArray();
        for (int i = 0; i < digits.length; i++) {
            if (digits[i] == '6') {
                digits[i] = '9';
                break;
            }
        }
        return Integer.parseInt(new String(digits));
    }
}
