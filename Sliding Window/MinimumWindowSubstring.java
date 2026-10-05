/**
 * 76. Minimum Window Substring
 * Difficulty: Hard | Tags: Hash Table, String, Sliding Window
 * https://leetcode.com/problems/minimum-window-substring/
 *
 * Pattern: Sliding Window with Character Frequency Dominance Check
 *
 * Key insight: Build a frequency array for t (freq1) and a sliding window
 * frequency array for s (freq2). Expand right until freq2 dominates freq1 for
 * every ASCII character (the isPos check). Then shrink left as long as
 * dominance holds, updating the minimum. The isPos scan over 128 chars is
 * constant since the alphabet is fixed.
 *
 * Time Complexity: O(128 * N) = O(N) - Right advances N times; left advances at
 * most N times; each isPos check scans a fixed 128-element array
 *
 * Space Complexity: O(1) - Two fixed-size int[128] arrays regardless of input
 * length
 *
 * Edge Cases Handled: s shorter than t (early return ""), no valid window
 * exists (returns ""), t contains repeated characters (freq1 has counts > 1), s
 * equals t (entire string is the minimum window), t has all unique characters
 */
class MinimumWindowSubstring {
    public String minWindow(String s, String t) {
        int[] freq1 = new int[128];
        int[] freq2 = new int[128];

        int min = Integer.MAX_VALUE;
        int left = 0;
        String res = "";

        if (s.length() < t.length()) {
            return "";
        }

        for (int i = 0; i < t.length(); i++) {
            freq1[t.charAt(i)]++;
        }

        for (int right = 0; right < s.length(); right++) {
            freq2[s.charAt(right)]++;
            while (isWindowValid(freq1, freq2)) {
                if (min > right - left + 1) {
                    min = right - left + 1;
                    res = s.substring(left, right + 1);
                }
                freq2[s.charAt(left)]--;
                left++;
            }

        }
        return res;
    }

    public static boolean isWindowValid(int[] arr1, int[] arr2) {
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] > arr2[i]) {
                return false;
            }
        }
        return true;
    }
}
