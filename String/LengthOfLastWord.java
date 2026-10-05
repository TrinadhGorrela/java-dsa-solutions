/**
 * 58. Length of Last Word
 * Difficulty: Easy | Tags: String
 * https://leetcode.com/problems/length-of-last-word/
 *
 * Pattern: Split-and-Count
 *
 * Key insight: Trim leading/trailing whitespace, split on whitespace
 * boundaries, and return the length of the last element in the resulting array.
 *
 * Time Complexity: O(n) - Trim and split each scan the string once.
 *
 * Space Complexity: O(n) - Split array holds all words.
 *
 * Edge Cases Handled: trailing spaces, leading spaces, single word, multiple
 * spaces between words
 */
class LengthOfLastWord {
    public int lengthOfLastWord(String s) {
        String[] words = s.trim("\\s+");
        String last = words[words.length - 1];
        return last.length();
    }
}
