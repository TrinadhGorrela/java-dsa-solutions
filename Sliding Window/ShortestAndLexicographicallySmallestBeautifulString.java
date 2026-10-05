/**
 * 2904. Shortest and Lexicographically Smallest Beautiful String
 * Difficulty: Medium | Tags: String, Sliding Window
 * https://leetcode.com/problems/shortest-and-lexicographically-smallest-beautif
 * ul-string/
 *
 * Pattern: Variable Sliding Window with Lexicographic Tie-Breaking
 *
 * Key insight: Expand right to accumulate exactly k ones; once reached, record
 * the candidate and shrink left to find shorter windows. When two windows tie
 * in length, use String.compareTo to keep the lexicographically smaller one.
 * Shrinking always produces shorter or equal candidates, so the first tie is
 * sufficient.
 *
 * Time Complexity: O(N) - Each index is visited by right once and left at most
 * once; string comparisons are bounded by the current window size which is at
 * most N
 *
 * Space Complexity: O(1) - Only indices, a count, and a result string reference
 * (output string excluded)
 *
 * Edge Cases Handled: no valid substring exists (returns ""), k exceeds total
 * number of ones, k = 1 with single '1' at various positions, equal-length
 * substrings resolved by lexicographic order
 */
class ShortestAndLexicographicallySmallestBeautifulString {
    public String shortestBeautifulSubstring(String s, int k) {
        int left = 0;
        int count = 0;
        int min = Integer.MAX_VALUE;
        String res = "";
        for (int right = 0; right < s.length(); right++) {
            if (s.charAt(right) == '1') {
                count++;
            }

            while (count == k) {
                if (min > right - left + 1) {
                    res = s.substring(left, right + 1);
                    min = right - left + 1;
                } else if (min == right - left + 1) {
                    String temp = s.substring(left, right + 1);
                    if (res.compareTo(temp) > 0) {
                        res = temp;
                    }
                }
                if (s.charAt(left) == '1') {
                    count--;
                }
                left++;
            }
        }

        if (min == Integer.MAX_VALUE) {
            return "";
        }
        return res;
    }
}
