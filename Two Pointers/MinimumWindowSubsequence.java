/**
 * 727. Minimum Window Subsequence
 * Difficulty: Hard | Tags: String, Dynamic Programming, Sliding Window
 * https://leetcode.com/problems/minimum-window-subsequence/
 *
 * Pattern: Two Pointers (Forward Match + Backward Shrink)
 *
 * Key insight: Scan s1 forward to locate a complete subsequence match ending at
 * some index, then scan backward to slide the window's start as far right as
 * possible while still matching s2 - this produces the minimal window ending at
 * each match. Restarting just after that start discovers every candidate
 * window.
 *
 * Time Complexity: O(N * M) - Each forward match attempt scans s1 (N
 * characters) and a successful match triggers a backward scan bounded by
 * len(s2) = M.
 *
 * Space Complexity: O(1) - Only pointer variables are used; the returned
 * substring is the output itself, not auxiliary storage.
 *
 * Edge Cases Handled: empty s1 or s2 (loop never runs, returns ""), s2 longer
 * than s1 (returns ""), s2 not a subsequence of s1 (returns ""), repeated
 * characters that force the backward scan to find the tightest window, ties
 * broken by keeping the earlier (strict <) window; null input is not guarded
 * and throws a NullPointerException.
 */
class MinimumWindowSubsequence {
    public String minWindow(String s1, String s2) {
        String res = "";
        int p1 = 0;
        int p2 = 0;

        while (p2 < s2.length() && p1 < s1.length() && p1 >= 0 && p2 >= 0) {

            if (s1.charAt(p1) == s2.charAt(p2)) {
                p2++;

                if (p2 == s2.length()) {
                    p2--;
                    int temp = p1;

                    while (p1 >= 0 && p2 >= 0) {
                        if (s1.charAt(p1) == s2.charAt(p2)) {
                            p2--;
                        }

                        if (p2 < 0) {
                            if (res.isEmpty() || temp - p1 + 1 < res.length()) {
                                res = s1.substring(p1, temp + 1);
                            }
                            p2 = 0;
                            break;

                        }

                        p1--;
                    }

                }

            }

            p1++;
        }

        return res;
    }
}
