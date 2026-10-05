/**
 * 520. Detect Capital
 * Difficulty: Easy | Tags: String
 * https://leetcode.com/problems/detect-capital/
 *
 * Pattern: Uppercase Count + Position Check
 *
 * Key insight: Count uppercase letters; valid rules are: all uppercase, all
 * lowercase, or exactly 1 uppercase at position 0. Any other count/position
 * combination is invalid.
 *
 * Time Complexity: O(n) - Single pass counting uppercase characters.
 *
 * Space Complexity: O(1) - One integer counter.
 *
 * Edge Cases Handled: single character (always valid), all uppercase, all
 * lowercase, only first letter uppercase, mixed case with 1 uppercase not at
 * position 0 (rejected)
 */
class DetectCapital {
    public boolean detectCapitalUse(String word) {
        int count = 0;
        for (char c : word.toCharArray()) {
            if (Character.isUpperCase(c)) {
                count++;
            }
        }
        if (word.length() == count || count == 0) {
            return true;
        } else if (count == 1 && Character.isUpperCase(word.charAt(0))) {
            return true;
        } else {
            return false;
        }
    }
}
