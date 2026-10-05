/**
 * 1456. Maximum Number of Vowels in a Substring of Given Length
 * Difficulty: Medium | Tags: String, Sliding Window
 * https://leetcode.com/problems/maximum-number-of-vowels-in-a-substring-of-give
 * n-length/
 *
 * Pattern: Fixed-Size Sliding Window (Vowel Counter)
 *
 * Key insight: Pre-count vowels in the first window of size k, then slide by
 * subtracting the vowel status of the outgoing character and adding the
 * incoming one. Track the running maximum without needing a data structure
 * since the window size is fixed.
 *
 * Time Complexity: O(N) - First window counts k chars, then N - k slide steps,
 * each O(1) via indexOf on a 5-char string
 *
 * Space Complexity: O(1) - Only an integer count and max; no alphabet or map
 * needed
 *
 * Edge Cases Handled: k equals string length (single window), k = 1
 * (per-character check), no vowels anywhere (result 0), all vowels (result =
 * k), vowel count peaks mid-window
 */
class MaximumNumberOfVowelsInASubstringOfGivenLength {
    public int maxVowels(String s, int k) {
        int count = 0;
        int max = 0;
        for (int i = 0; i < k; i++) {
            if ("aeiou".indexOf(s.charAt(i)) != -1) {
                count++;
            }
        }
        max = count;

        for (int i = k; i < s.length(); i++) {
            if ("aeiou".indexOf(s.charAt(i - k)) != -1) {
                count--;
            }
            if ("aeiou".indexOf(s.charAt(i)) != -1) {
                count++;
            }
            max = Math.max(max, count);
        }
        return max;
    }
}
