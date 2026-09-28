/**
 * 67. Add Binary
 * Difficulty: Easy | Tags: Math, String, Bit Manipulation, Simulation
 * https://leetcode.com/problems/add-binary/
 *
 * Pattern: Right-to-Left Bitwise Addition with Carry
 * Key insight: Iterate both strings from their least significant bits while either string has an unread bit or a carry
 * remains; per position, sum the inbound carry with each available bit, append sum % 2, and carry sum / 2 forward.
 * Reverse the builder at the end since bits are emitted in reverse order.
 *
 * Time Complexity: O(max(len(a), len(b))) - One pass over the longer string; final carry adds at most one extra
 * iteration
 * Space Complexity: O(max(len(a), len(b))) - StringBuilder accumulates one char per result bit
 *
 * Edge Cases Handled: final carry of 1 after both strings are exhausted (loop continues while carry == 1, appending the
 * extra high bit), unequal string lengths (shorter string's index drives i/j below 0 and is skipped), all-zero operands
 * (single '0' appended), single-bit strings
 */
class AddBinary {
    public String addBinary(String a, String b) {
        StringBuilder res = new StringBuilder();
        int i = a.length() - 1;
        int j = b.length() - 1;
        int carry = 0;
        while (i >= 0 || j >= 0 || carry == 1) {
            int sum = carry;
            if (i >= 0) {
                sum = sum + a.charAt(i) - '0';
                i--;
            }
            if (j >= 0) {
                sum = sum + b.charAt(j) - '0';
                j--;
            }
            res.append(sum % 2);
            carry = sum / 2;
        }
        return res.reverse().toString();
    }
}
