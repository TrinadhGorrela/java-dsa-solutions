/**
 * 28. Find the Index of the First Occurrence in a String
 * Difficulty: Easy | Tags: Two Pointers, String, String Matching, Z Algorithm,
 * Knuth–Morris–Pratt Algorithm, Boyer–Moore String-Search Algorithm
 * https://leetcode.com/problems/find-the-index-of-the-first-occurrence-in-a-str
 * ing/
 *
 * Pattern: Sliding Window Character-by-Character Match
 *
 * Key insight: Slide a window of length M across the N-length haystack; at each
 * position, compare character-by-character and break early on mismatch—simple
 * but avoids the complexity of KMP/Boyer-Moore for interviews.
 *
 * Time Complexity: O((N-M+1) × M) - Up to N-M+1 starting positions, each
 * comparing up to M characters
 *
 * Space Complexity: O(1) - Only three integer indices (i, j, and the loop
 * counter)
 *
 * Edge Cases Handled: Needle longer than haystack (early -1 return), needle
 * equal to haystack (exact match), needle at position 0, needle at last valid
 * position, no match anywhere in haystack
 */
class FindTheIndexOfTheFirstOccurrenceInAString {
    public int strStr(String haystack, String needle) {
        if (haystack.length() < needle.length()) {
            return -1;
        }

        for (int i = 0; i <= haystack.length() - needle.length(); i++) {
            if (haystack.charAt(i) == needle.charAt(0)) {
                int hayIndex = i;
                int needleIndex = 0;

                while (hayIndex < haystack.length() && needleIndex < needle.length() && haystack.charAt(hayIndex) == needle.charAt(needleIndex)) {
                    hayIndex++;
                    needleIndex++;
                }

                if (needleIndex == needle.length()) {
                    return i;
                }
            }
        }

        return -1;
    }
}
