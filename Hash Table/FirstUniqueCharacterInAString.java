/**
 * 387. First Unique Character in a String
 * Difficulty: Easy | Tags: Hash Table, String, Queue, Counting
 * https://leetcode.com/problems/first-unique-character-in-a-string/
 *
 * Pattern: Two-Pass Frequency Count
 *
 * Key insight: First pass builds a frequency map for every character; second
 * pass scans left to right and returns the first index whose character has
 * count exactly 1.
 *
 * Time Complexity: O(n) - Two linear passes; map operations are O(1) for
 * lowercase letters.
 *
 * Space Complexity: O(1) - Map holds at most 26 entries.
 *
 * Edge Cases Handled: no unique character (returns -1), all characters
 * identical, single character (returns 0), unique character at the end
 */
class FirstUniqueCharacterInAString {
    public int firstUniqChar(String s) {
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0) + 1);
        }
        for (int i = 0; i < s.length(); i++) {
            if (map.get(s.charAt(i)) == 1) {
                return i;
            }
        }
        return -1;
    }

}
