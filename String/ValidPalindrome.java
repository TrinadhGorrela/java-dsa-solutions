/**
 * 125. Valid Palindrome
 * Difficulty: Easy | Tags: Two Pointers, String
 * https://leetcode.com/problems/valid-palindrome/
 *
 * Pattern: Sanitize + Compare with Reverse
 *
 * Key insight: Leverage StringBuilder.reverse() as a one-liner palindrome check
 * after normalizing case and stripping non-alnum chars via regex, trading a
 * short auxiliary string for simplicity.
 *
 * Time Complexity: O(N) - Regex replace, toLowerCase, and reverse each scan the
 * string once
 *
 * Space Complexity: O(N) - Three intermediate char buffers (lowercase, cleaned,
 * reversed)
 *
 * Edge Cases Handled: Empty/all-whitespace input, all punctuation, mixed-case
 * letters, digits mixed with letters, single-character string
 */
class ValidPalindrome {
    public boolean isPalindrome(String s) {
        String lowercase = s.toLowerCase();
        String cleaned = lowercase.replaceAll("[^a-z0-9]", "");
        String reversed = new StringBuilder(cleaned).reverse().toString();
        return cleaned.equals(reversed);
    }
}
