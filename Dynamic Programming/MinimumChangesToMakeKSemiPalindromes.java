/**
 * 2911. Minimum Changes to Make K Semi-palindromes
 * Difficulty: Hard | Tags: Two Pointers, String, Dynamic Programming
 * https://leetcode.com/problems/minimum-changes-to-make-k-semi-palindromes/
 *
 * Pattern: Memoized DP (Optimal Partitioning) + Two Pointers (Periodic
 * Matching)
 *
 * Key insight: A semi-palindrome has period d dividing its length, so its
 * minimum edit cost depends only on the endpoints and can be precomputed by
 * trying each divisor. The remaining task is optimal partitioning into K parts
 * of length at least 2 using those precomputed costs.
 *
 * Time Complexity: O(N^3 log N) - O(N^2) substrings each scan O(length) periods
 * with O(length) matching work (sum of length x divisors is O(N^2 log N));
 * memoized splitting adds O(N^2 * K), K <= N/2.
 *
 * Space Complexity: O(N^2) - Stores the N x N cost table and an N x (K+1) memo
 * table.
 *
 * Edge Cases Handled: K = 1 (cost of the whole string), minimum-length parts
 * when N = 2K, already-semi-palindromic and repeated-character strings; assumes
 * N >= 2K and non-null input, and single-character parts are never evaluated
 * (would yield the MAX_VALUE sentinel).
 */
class MinimumChangesToMakeKSemiPalindromes {
    public int minimumChanges(String s, int k) {
        Integer[][] memo = new Integer[s.length()][k + 1];
        int[][] cost = new int[s.length()][s.length()];

        for (int i = 0; i < cost.length; i++) {
            for (int j = i + 1; j < cost.length; j++) {
                cost[i][j] = getMinChangesForSubstring(s, i, j);
            }
        }

        return helper(0, k, s, cost, memo);
    }

    private int helper(int index, int partitions, String s, int[][] cost, Integer[][] memo) {
        if (partitions == 1) {
            return cost[index][s.length() - 1];
        }

        if (memo[index][partitions] != null) {
            return memo[index][partitions];
        }

        int min = Integer.MAX_VALUE;

        for (int j = index + 1; j < memo.length - (partitions - 1) * 2; j++) {
            int steps = cost[index][j] + helper(j + 1, partitions - 1, s, cost, memo);
            min = Math.min(min, steps);
        }

        return memo[index][partitions] = min;
    }

    private int getMinChangesForSubstring(String s, int start, int end) {
        int len = end - start + 1;
        int min = Integer.MAX_VALUE;

        for (int d = 1; d < len; d++) {
            if (len % d == 0) {
                int count = getMismatchCount(s, start, end, d);

                min = Math.min(count, min);
            }
        }

        return min;
    }

    private int getMismatchCount(String s, int start, int end, int d) {
        int count = 0;

        for (int i = start; i < start + d; i++) {
            int left = i;
            int right = i + ((end - start + 1) / d - 1) * d;

            while (left < right) {
                if (s.charAt(left) != s.charAt(right)) {
                    count++;
                }
                left += d;
                right -= d;
            }
        }

        return count;
    }
}
