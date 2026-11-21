/**
 * 1160. Find Words That Can Be Formed by Characters
 * Difficulty: Easy | Tags: Array, Hash Table, String, Counting
 * https://leetcode.com/problems/find-words-that-can-be-formed-by-characters/
 *
 * Pattern: Frequency Counting (Fixed 26-Bucket Letter Frequencies)
 *
 * Key insight: A word is buildable exactly when no letter appears more often in
 * it than in chars, so two fixed-size count arrays reduce the test to 26
 * comparisons. Only lengths of valid words are summed into the result.
 *
 * Time Complexity: O(N) - Scans chars and every word once, plus a constant
 * 26-slot comparison per word; N is the total number of input characters.
 *
 * Space Complexity: O(1) - Two fixed 26-element int arrays regardless of input
 * size.
 *
 * Edge Cases Handled: empty chars string; empty word (valid but adds 0);
 * repeated letters in chars; words longer than chars or containing unavailable
 * letters; letters used exactly the available count; empty words array (returns
 * 0). Input is assumed lowercase 'a'-'z'; other characters are not validated.
 */
class FindWordsThatCanBeFormedByCharacters {
    public int countCharacters(String[] words, String chars) {
        int[] freq1 = new int[26];

        for (char c : chars.toCharArray()) {
            freq1[c - 'a']++;
        }

        int res = 0;

        for (int i = 0; i < words.length; i++) {
            int[] freq2 = new int[26];

            for (char c : words[i].toCharArray()) {
                freq2[c - 'a']++;
            }

            boolean isValid = true;

            for (int j = 0; j < freq2.length; j++) {
                if (freq1[j] < freq2[j]) {
                    isValid = false;
                    break;
                }
            }

            if (isValid) {
                res += words[i].length();
            }
        }

        return res;
    }
}
