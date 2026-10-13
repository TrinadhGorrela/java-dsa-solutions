/**
 * 340. Longest Substring with At Most K Distinct Characters
 * Difficulty: Medium | Tags: Hash Table, String, Sliding Window
 * https://leetcode.com/problems/longest-substring-with-at-most-k-distinct-chara
 * cters/
 *
 * Pattern: Sliding Window (Variable Length)
 *
 * Key insight: The "at most K distinct characters" constraint is monotonic -
 * extending the window can only add distinct characters, so the moment the
 * window exceeds K the only valid repair is to shrink from the left; this
 * guarantees each character enters and leaves the window at most once.
 *
 * Time Complexity: O(N) - The right pointer advances N times and the left
 * pointer advances at most N times, with O(1) average HashMap operations.
 *
 * Space Complexity: O(K) - The frequency map holds at most K+1 distinct
 * characters while shrinking, bounded by the alphabet in practice.
 *
 * Edge Cases Handled: k = 0 (the while loop drains every character, returning
 * 0), empty string (returns 0), k greater than or equal to the number of
 * distinct characters (the whole string is valid), single-character input and
 * repeated characters; null input is not guarded and throws a
 * NullPointerException.
 */
class LongestSubstringWithAtMostKDistinctCharacters {
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        Map<Character, Integer> freq = new HashMap<>();
        int left = 0;
        int max = 0;

        for (int right = 0; right < s.length(); right++) {
            freq.put(s.charAt(right), freq.getOrDefault(s.charAt(right), 0) + 1);

            while (freq.size() > k) {
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
