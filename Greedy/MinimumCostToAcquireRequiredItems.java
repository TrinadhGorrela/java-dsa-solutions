/**
 * 3789. Minimum Cost to Acquire Required Items
 * Difficulty: Medium | Tags: Math, Greedy
 * https://leetcode.com/problems/minimum-cost-to-acquire-required-items/
 *
 * Pattern: Greedy Enumeration of Purchasing Strategies (Closed-Form Case
 * Analysis)
 *
 * Key insight: Only three purchase plans can ever be optimal - all bundles
 * (max(need1, need2) bundles), all separate items, or mixed (bundle the
 * min(need1, need2) overlap, buy the remainder separately) - so the answer is
 * simply the minimum of the three closed-form costs.
 *
 * Time Complexity: O(1) - A fixed number of arithmetic operations; no loops,
 * sorting, or data structures.
 *
 * Space Complexity: O(1) - A constant number of local variables.
 *
 * Edge Cases Handled: Overflow in products (every multiplication casts to long
 * before multiplying), need1 == need2 (bundle and mixed plans coincide), a zero
 * requirement (the mixed plan degrades gracefully to the separate plan), and
 * zero item costs.
 */
class MinimumCostToAcquireRequiredItems {
    public long minimumCost(int cost1, int cost2, int costBoth, int need1, int need2) {
        long bundleCost = (long) Math.max(need1, need2) * costBoth;

        long sepCost = ((long) cost1 * need1) + ((long) cost2 * need2);

        int min = Math.min(need1, need2);

        long mixCost = ((long) min * costBoth) + ((long) cost1 * (need1 - min)) + ((long) cost2 * (need2 - min));

        return Math.min(bundleCost, Math.min(sepCost, mixCost));
    }
}
