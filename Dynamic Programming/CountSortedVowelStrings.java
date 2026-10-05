/**
 * 1641. Count Sorted Vowel Strings
 * Difficulty: Medium | Tags: Math, Dynamic Programming, Combinatorics
 * https://leetcode.com/problems/count-sorted-vowel-strings/
 *
 * Pattern: 2D counting DP with suffix-sum recurrence
 *
 * Key insight: dp[i][j] = number of sorted strings of length i using
 * vowels[j..4]; the backward inner loop computes a suffix sum so each position
 * accumulates all valid extensions from equal or later vowels.
 *
 * Time Complexity: O(n) - Outer loop n, inner loop is constant 5
 *
 * Space Complexity: O(n*5) = O(n) - 2D array with 5 columns
 *
 * Edge Cases Handled: n=1 returns 5; base row dp[1][j]=1 for each vowel; answer
 * is sum of dp[n][0..4]
 */
class CountSortedVowelStrings {
    public int countVowelStrings(int n) {
        if (n == 1) {
            return 5;
        }

        int[][] dp = new int[n + 1][5];

        for (int i = 0; i < 5; i++) {
            dp[1][i] = 1;
        }

        for (int i = 2; i <= n; i++) {
            dp[i][4] = 1;
            for (int j = 3; j >= 0; j--) {
                dp[i][j] = dp[i][j + 1] + dp[i - 1][j];
            }
        }

        int totalWays = 0;
        for (int i = 0; i < 5; i++) {
            totalWays += dp[n][i];
        }

        return totalWays;
    }
}
