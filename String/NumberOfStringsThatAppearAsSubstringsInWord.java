/**
 * 1967. Number of Strings That Appear as Substrings in Word
 * Difficulty: Easy | Tags: Array, String
 * https://leetcode.com/problems/number-of-strings-that-appear-as-substrings-in-
 * word/
 *
 * Pattern: Linear Substring Check
 *
 * Key insight: For each pattern in the array, use String.contains() to test if
 * it appears as a substring of word; increment a counter for each match.
 *
 * Time Complexity: O(p·w) - p patterns, each contains() scan is O(w) in the
 * worst case.
 *
 * Space Complexity: O(1) - Only a counter variable.
 *
 * Edge Cases Handled: pattern equal to word (match), pattern longer than word
 * (no match), empty pattern list, duplicate patterns (each counted
 * independently)
 */
class NumberOfStringsThatAppearAsSubstringsInWord {
    public int numOfStrings(String[] patterns, String word) {
        int count = 0;
        for (int i = 0; i < patterns.length; i++) {
            if (word.contains(patterns[i])) {
                count++;
            }
        }
        return count;
    }
}
