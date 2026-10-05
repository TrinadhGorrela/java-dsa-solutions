/**
 * 3592. Inverse Coin Change
 * Difficulty: Medium | Tags: Array, Dynamic Programming
 * https://leetcode.com/problems/inverse-coin-change/
 *
 * Pattern: Reverse unbounded knapsack reconstruction
 *
 * Key insight: Maintain a running DP of reachable amounts; at each step the
 * "missing" way count (numWays[i] - dp[i+1]) must be exactly 1 to justify
 * adding coin (i+1), after which the coin's contribution is folded into dp via
 * forward accumulation.
 *
 * Time Complexity: O(coins * amount) - Each discovered coin triggers a forward
 * sweep of the DP array
 *
 * Space Complexity: O(amount) - Single 1D DP array
 *
 * Edge Cases Handled: missing ways <0 or >1 at any step means impossible,
 * returning empty list; first element numWays[0] is implicitly 1; single-coin
 * input
 */
class InverseCoinChange {
    public List<Integer> findCoins(int[] numWays) {
        List<Integer> res = new ArrayList<>();
        int[] dp = new int[numWays.length + 1];
        dp[0] = 1;

        for (int i = 0; i < numWays.length; i++) {
            int ways = dp[i + 1];
            int difference = numWays[i] - ways;

            if (difference == 1) {
                for (int amt = i + 1; amt <= numWays.length; amt++) {
                    int remaining = amt - (i + 1);
                    dp[amt] += dp[remaining];
                }
                res.add(i + 1);
            } else if (difference < 0 || difference > 1) {
                return new ArrayList<>();
            }
        }

        return res;
    }
}
