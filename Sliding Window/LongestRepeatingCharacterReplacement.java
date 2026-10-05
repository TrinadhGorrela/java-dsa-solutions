/**
 * 424. Longest Repeating Character Replacement
 * Difficulty: Medium | Tags: Hash Table, String, Sliding Window
 * https://leetcode.com/problems/longest-repeating-character-replacement/
 *
 * Pattern: Sliding Window (Variable Length) + Hash Map
 *
 * Key insight: Maintain a window where (window length - most-frequent character
 * count) <= k. Because maxFreq is never decreased (only overwritten when a new
 * maximum appears), the window only grows, ensuring correctness without
 * backtracking.
 *
 * Time Complexity: O(N) - Each character is visited once by the right pointer;
 * left pointer advances at most N times total.
 *
 * Space Complexity: O(1) - Hash map holds at most 26 uppercase-letter entries.
 *
 * Edge Cases Handled: single character string (returns 1), k = 0 (window must
 * already be uniform), all distinct characters (window shrinks to 1 when k <
 * 1), k >= string length (entire string is the answer).
 */
class LongestRepeatingCharacterReplacement {
    public int characterReplacement(String s, int k) {
        Map<Character, Integer> freq = new HashMap<>();
        int left = 0;
        int max = 0;
        int maxFreq = 0;

        for (int right = 0; right < s.length(); right++) {
            freq.put(s.charAt(right), freq.getOrDefault(s.charAt(right), 0) + 1);

            if (maxFreq < freq.get(s.charAt(right))) {
                maxFreq = freq.get(s.charAt(right));
            }

            while ((right - left + 1) - maxFreq > k) {
                freq.put(s.charAt(left), freq.get(s.charAt(left)) - 1);
                if (freq.get(s.charAt(left)) == 0) {
                    freq.remove(s.charAt(left));
                }
                left++;
            }

            max = Math.max(max, right - left + 1);
        }

        return max;
    }
}
