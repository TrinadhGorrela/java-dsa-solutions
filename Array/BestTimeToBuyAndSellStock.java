/**
 * 121. Best Time to Buy and Sell Stock
 * Difficulty: Easy | Tags: Array, Dynamic Programming
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock/
 *
 * Pattern: Single-Pass Min Tracking
 *
 * Key insight: The optimal sell price is always evaluated against the cheapest
 * prior buy price; maintaining a running minimum lets each day compute its
 * potential profit in O(1), making the global maximum profit a single-pass
 * scan.
 *
 * Time Complexity: O(N) - One pass through prices with O(1) work per element
 *
 * Space Complexity: O(1) - Two variables (minPrice, maxProfit)
 *
 * Edge Cases Handled: single price (profit stays 0), monotonically decreasing
 * prices (minPrice updates but profit never beats 0), first day is also the
 * cheapest (profit = last - first)
 */
class BestTimeToBuyAndSellStock {
    public int maxProfit(int[] prices) {
        int minPrice = Integer.MAX_VALUE;
        int maxProfit = 0;
        for (int i = 0; i < prices.length; i++) {
            minPrice = Math.min(minPrice, prices[i]);
            maxProfit = Math.max(maxProfit, prices[i] - minPrice);
        }
        return maxProfit;
    }
}
