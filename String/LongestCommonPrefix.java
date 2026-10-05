/**
 * 14. Longest Common Prefix
 * Difficulty: Easy | Tags: Array, String, Trie
 * https://leetcode.com/problems/longest-common-prefix/
 *
 * Pattern: Horizontal Scanning (Prefix Trim)
 *
 * Key insight: Initialize prefix = strs[0]; for each subsequent string,
 * repeatedly trim the last character from prefix until it is a prefix of that
 * string. The loop terminates with the longest common prefix.
 *
 * Time Complexity: O(s) - s = total characters across all strings; each
 * character is compared at most once.
 *
 * Space Complexity: O(1) - Only the prefix string and loop variables.
 *
 * Edge Cases Handled: single string (returned as-is), no common prefix (prefix
 * trims to empty), empty string in the array, all strings identical
 */
class LongestCommonPrefix {
    public String longestCommonPrefix(String[] strs) {
        String prefix = strs[0];
        for (int i = 1; i < strs.length; i++) {
            String t = strs[i];
            while (!t.startsWith(prefix)) {
                prefix = prefix.substring(0, prefix.length() - 1);
            }
        }
        return prefix;
    }
}
