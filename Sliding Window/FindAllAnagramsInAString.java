/**
 * 438. Find All Anagrams in a String
 * Difficulty: Medium | Tags: Hash Table, String, Sliding Window
 * https://leetcode.com/problems/find-all-anagrams-in-a-string/
 *
 * Pattern: Fixed-Size Sliding Window with Frequency Array Comparison
 *
 * Key insight: Build frequency arrays for both t and the first window of s
 * (both of size |t|). Slide the window one position at a time, updating only
 * two counts (evict left, add right), then compare the full 26-element
 * frequency arrays via Arrays.compare. A match means an anagram was found at
 * that position.
 *
 * Time Complexity: O(26 * N) = O(N) - N slide steps each with a O(26) frequency
 * comparison
 *
 * Space Complexity: O(1) - Two fixed-size int[26] arrays independent of input
 *
 * Edge Cases Handled: s shorter than t (early return empty list), anagram at
 * index 0 (checked before the loop), anagram at the very end of s, no anagram
 * present, duplicate characters in t requiring exact frequency match
 */
class FindAllAnagramsInAString {
    public List<Integer> findAnagrams(String s, String t) {
        int[] freqS = new int[26];
        int[] freqT = new int[26];
        List<Integer> res = new ArrayList<>();

        if (s.length() < t.length()) {
            return res;
        }

        for (int i = 0; i < t.length(); i++) {
            freqS[s.charAt(i) - 'a']++;
            freqT[t.charAt(i) - 'a']++;
        }

        if (Arrays.compare(freqS, freqT) == 0) {
            res.add(0);
        }

        for (int i = t.length(); i < s.length(); i++) {
            freqS[s.charAt(i - t.length()) - 'a']--;
            freqS[s.charAt(i) - 'a']++;
            if (Arrays.compare(freqS, freqT) == 0) {
                res.add(i - t.length() + 1);
            }
        }
        return res;
    }
}
