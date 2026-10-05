/**
 * 541. Reverse String II
 * Difficulty: Easy | Tags: Two Pointers, String
 * https://leetcode.com/problems/reverse-string-ii/
 *
 * Pattern: 2k-Chunked Two-Pointer Reversal
 *
 * Key insight: Iterate in steps of 2k; within each block, reverse only the
 * first min(k, remaining) chars via a two-pointer swap from each chunk's
 * boundaries—no extra buffers needed.
 *
 * Time Complexity: O(N) - Each character is swapped at most once; outer loop
 * jumps in 2k increments
 *
 * Space Complexity: O(N) - char[] copy required for in-place swaps (Java
 * Strings immutable)
 *
 * Edge Cases Handled: k >= string length (whole string reversed), last chunk
 * shorter than k (Math.min clamps boundary), empty string, k = 1 (every other
 * char reversed)
 */
class ReverseStringII {
    public static String reverseStr(String s, int k) {
        char[] chars = s.toCharArray();

        for (int i = 0; i < s.length(); i = i + 2 * k) {
            swap(chars, i, Math.min(i + k - 1, s.length() - 1));
        }

        return new String(chars);
    }

    public static void swap(char[] chars, int st, int en) {
        while (st < en) {
            char temp = chars[st];
            chars[st] = chars[en];
            chars[en] = temp;
            st++;
            en--;
        }
    }
}
