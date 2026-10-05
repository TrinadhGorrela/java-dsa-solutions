/**
 * 3. Longest Substring Without Repeating Characters
 * Difficulty: Medium | Tags: Hash Table, String, Sliding Window
 * https://leetcode.com/problems/longest-substring-without-repeating-characters/
 *
 * Pattern: Sliding Window with HashSet (First-Occurrence Erasure)
 *
 * Key insight: When a duplicate is found at right, the set may contain many
 * characters between left and the previous occurrence of s[right]. Rather than
 * tracking positions, simply advance left one-by-one removing from the set
 * until the duplicate is gone. Each character is added and removed at most
 * once, keeping O(N).
 *
 * Time Complexity: O(N) - Right advances N times; left advances at most N times
 * total; HashSet ops are O(1)
 *
 * Space Complexity: O(min(N, alphabet)) - Set holds at most the size of the
 * character set (26 lowercase, 128 ASCII, etc.)
 *
 * Edge Cases Handled: empty string (returns 0), single character (returns 1),
 * all identical characters (window size stays 1), all unique characters (window
 * spans entire string), duplicate at the very start of the window
 */
class LongestSubstringWithoutRepeatingCharacters {
    public int lengthOfLongestSubstring(String s) {
        Set<Character> set = new HashSet<>();
        int left = 0;
        int max = 0;

        for (int right = 0; right < s.length(); right++) {
            while (set.contains(s.charAt(right))) {
                set.remove(s.charAt(left));
                left++;
            }
            set.add(s.charAt(right));
            max = Math.max(max, right - left + 1);
        }
        return max;
    }
}
