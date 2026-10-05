/**
 * 5. Longest Palindromic Substring
 * Difficulty: Medium | Tags: Two Pointers, String, Dynamic Programming,
 * Manacher
 * https://leetcode.com/problems/longest-palindromic-substring/
 *
 * Pattern: Expand-Around-Center (Odd + Even)
 *
 * Key insight: Every palindrome has a center; testing all 2N-1 centers (N
 * single-char + N-1 char-pair) and expanding outward gives the longest
 * palindrome without needing DP or Manacher's.
 *
 * Time Complexity: O(N^2) - 2N-1 centers, each expansion up to O(N) in the
 * worst case (all same chars)
 *
 * Space Complexity: O(1) - Only start index, max length, and loop counters; no
 * auxiliary data structures
 *
 * Edge Cases Handled: Single character (length 1), all identical characters
 * (entire string), longest palindrome at the very end of the string,
 * even-length palindromes found via pair centers
 */
class LongestPalindromicSubstring {

    public static int helper(int left, int right, String s) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        return right - left - 1;
    }

    public String longestPalindrome(String s) {
        int st = 0;
        int end = 0;

        for (int i = 0; i < s.length(); i++) {
            int len1 = helper(i, i, s);
            int len2 = helper(i, i + 1, s);
            int len = Math.max(len1, len2);
            if (len > end - st) {
                st = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        return s.substring(st, end + 1);
    }
}
