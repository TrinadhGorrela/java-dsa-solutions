/**
 * 9. Palindrome Number
 * Difficulty: Easy | Tags: Math
 * https://leetcode.com/problems/palindrome-number/
 *
 * Pattern: Reverse Digits and Compare
 *
 * Key insight: Pop each digit off x via mod 10 and rebuild a reversed integer
 * in a separate accumulator; at the end, the number is a palindrome iff the
 * reversed value equals the original.
 *
 * Time Complexity: O(log n) - One iteration per decimal digit of x
 *
 * Space Complexity: O(1) - Only digit, reversed, and original accumulators
 *
 * Edge Cases Handled: negative x (rejected by the x > -1 guard), x = 0 (loop
 * skipped, reversed stays 0, matches), single-digit numbers (always
 * palindromes)
 */
class PalindromeNumber {
    public boolean isPalindrome(int x) {
        int digit;
        int reversed = 0;
        int original = x;
        if (x > -1) {

            while (x != 0) {
                digit = x % 10;
                reversed = reversed * 10 + digit;
                x = x / 10;
            }
            if (original == reversed) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}
