/**
 * 91. Decode Ways
 * Difficulty: Medium | Tags: String, Dynamic Programming
 * https://leetcode.com/problems/decode-ways/
 *
 * Pattern: Dynamic Programming (1D Bottom-Up)
 *
 * Key insight: Every prefix of length i decodes either by taking digit i alone
 * (valid 1-9, adding dp[i-1]) or by pairing it with digit i-1 as a two-digit
 * number (valid 10-26, adding dp[i-2]). Because a '0' can only be decoded as
 * the second digit of 10 or 20, the single-digit branch is skipped for zeros
 * while the pair branch still validates them.
 *
 * Time Complexity: O(N) - Single pass over the string with O(1) work per
 * position; the two-character substring parseInt is constant length.
 *
 * Space Complexity: O(N) - The dp array stores one running count per prefix
 * length.
 *
 * Edge Cases Handled: Leading zero returns 0; embedded zeros contribute only
 * through valid 10/20 pairs; two-digit values outside 10-26 are rejected;
 * single-character input is covered by the dp[1] base case; note an empty
 * string is NOT handled and would throw at s.charAt(0).
 */
class DecodeWays {
    public int numDecodings(String s) {
        if (s.charAt(0) == '0') {
            return 0;
        }

        int[] dp = new int[s.length() + 1];
        dp[0] = 1;
        dp[1] = 1;

        for (int i = 2; i < dp.length; i++) {

            if (s.charAt(i - 1) != '0') {
                dp[i] += dp[i - 1];
            }

            int digits = Integer.parseInt(s.substring(i - 2, i));

            if (digits >= 10 && digits <= 26) {
                dp[i] += dp[i - 2];
            }
        }

        return dp[s.length()];
    }
}
