/**
 * 1844. Replace All Digits with Characters
 * Difficulty: Easy | Tags: String
 * https://leetcode.com/problems/replace-all-digits-with-characters/
 *
 * Pattern: Pair-Wise Shift Transformation
 *
 * Key insight: Process the string in letter/digit pairs: for each pair (letter,
 * digit), append the letter as-is then the shifted character (letter + digit
 * value). An odd-length trailing letter is appended without a following digit.
 *
 * Time Complexity: O(n) - Each character processed once in the step-2 loop.
 *
 * Space Complexity: O(n) - StringBuilder holds the full output.
 *
 * Edge Cases Handled: single character input (no digit to shift), odd-length
 * string (final letter appended alone), digit '0' (shifts to the same
 * character)
 */
class ReplaceAllDigitsWithCharacters {
    public String replaceDigits(String s) {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < s.length(); i += 2) {
            char letter = s.charAt(i);
            if (i + 1 < s.length()) {
                char digitChar = s.charAt(i + 1);
                int digit = digitChar - '0';
                res.append(letter);
                res.append((char) (letter + digit));
            } else {
                res.append(letter);
            }
        }
        return res.toString();
    }
}
