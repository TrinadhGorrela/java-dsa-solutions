/**
 * 242. Valid Anagram
 * Difficulty: Easy | Tags: Hash Table, String, Sorting
 * https://leetcode.com/problems/valid-anagram/
 *
 * Pattern: Fixed-Size Frequency Array
 *
 * Key insight: Use a single int[26] counter: increment for each char in s,
 * decrement for each char in t. If all slots return to zero (and lengths
 * match), the strings are anagrams.
 *
 * Time Complexity: O(n) - One pass through both strings, one pass through the
 * 26-slot array.
 *
 * Space Complexity: O(1) - Fixed 26-element array regardless of input size.
 *
 * Edge Cases Handled: different lengths (early false), empty strings (true),
 * all same character, single-character anagrams
 */
class ValidAnagram {
    public boolean isAnagram(String s, String t) {
        int m = s.length();
        int n = t.length();
        if (m != n)
            return false;

        int[] count = new int[26];

        for (int i = 0; i < m; i++) {
            count[s.charAt(i) - 'a']++;
            count[t.charAt(i) - 'a']--;
        }

        for (int res : count) {
            if (res != 0)
                return false;
        }

        return true;

    }
}
