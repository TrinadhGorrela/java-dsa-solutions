/**
 * 3946. Maximum Number of Items From Sale I
 * Difficulty: Medium | Tags: Array, Dynamic Programming, Greedy
 * https://leetcode.com/problems/maximum-number-of-items-from-sale-i/
 *
 * Pattern: 0/1 Knapsack (Backward DP) + Unbounded Budget Relaxation (Forward
 * DP) with Divisibility-Derived Value
 *
 * Key insight: Buying item i makes every other item whose price is divisible by
 * items[i][0] free, so its paid value is 1 + free-count; backward DP picks each
 * item at most once with this bonus, then a forward pass fills leftover budget
 * by adding affordable items for +1 each, reusing already-improved states
 * because dp[rem] may be updated earlier in the same pass.
 *
 * Time Complexity: O(N^2 + N*B) - O(N^2) divisibility precomputation effect per
 * item scan, plus O(N*B) backward and O(N*B) forward DP passes (N =
 * items.length, B = budget)
 *
 * Space Complexity: O(B) - Single 1D dp array of length budget + 1
 *
 * Edge Cases Handled: empty items array returns 0, zero budget returns 0,
 * budget below all item costs returns 0, single item yields no free companions,
 * duplicate prices count each other as free (i != j); zero-price items cause
 * division by zero, and null/negative inputs are not handled
 */
class MaximumNumberOfItemsFromSaleI {
    public int maximumSaleItems(int[][] items, int budget) {
        int[] dp = new int[budget + 1];

        for (int i = 0; i < items.length; i++) {
            int freeItems = 0;

            for (int j = 0; j < items.length; j++) {
                if (items[j][0] % items[i][0] == 0 && i != j) {
                    freeItems++;
                }
            }

            for (int b = budget; b >= items[i][1]; b--) {
                dp[b] = Math.max(dp[b - items[i][1]] + 1 + freeItems, dp[b]);
            }
        }

        for (int b = 0; b < dp.length; b++) {
            for (int i = 0; i < items.length; i++) {

                if (b >= items[i][1]) {
                    int rem = b - items[i][1];

                    dp[b] = Math.max(dp[b], dp[rem] + 1);
                }
            }
        }

        return dp[budget];
    }
}
