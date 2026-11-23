/**
 * 703201. Longest Common Substring
 * Difficulty: Medium | Tags: Strings, Dynamic Programming
 * https://www.geeksforgeeks.org/problems/longest-common-substring1452/1
 *
 * Pattern: 2D Dynamic Programming (Diagonal Suffix Extension)
 *
 * Key insight: dp[i][j] is the length of the common suffix ending at s1[i-1]
 * and s2[j-1]. Matching characters extend the diagonal by one, while any
 * mismatch resets to 0 because a substring must be contiguous, so the largest
 * cell value is the longest common substring.
 *
 * Time Complexity: O(M*N) - Every cell of the (M+1)x(N+1) table is filled once,
 * where M and N are the string lengths
 *
 * Space Complexity: O(M*N) - The full 2D dp table is kept; no rolling-row
 * optimization is used
 *
 * Edge Cases Handled: empty string (loops are skipped, returns 0), no common
 * characters (every cell stays 0), identical strings (returns full length),
 * repeated characters (diagonal runs accumulate), assumes non-null inputs
 * (s1.length()/s2.length() would throw on null)
 */
class LongestCommonSubstring {
    public int longCommSubstr(String s1, String s2) {
        int[][] dp = new int[s1.length() + 1][s2.length() + 1];
        int max = 0;

        for (int i = 1; i <= s1.length(); i++) {
            for (int j = 1; j <= s2.length(); j++) {

                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {
                    dp[i][j] = 0;
                }

                max = Math.max(max, dp[i][j]);
            }
        }

        return max;
    }
}
