/**
 * 1833. Maximum Ice Cream Bars
 * Difficulty: Medium | Tags: Array, Greedy, Sorting, Counting Sort
 * https://leetcode.com/problems/maximum-ice-cream-bars/
 *
 * Pattern: Sort-Ascending Greedy Selection
 *
 * Key insight: Every unit of coin spent on a cheaper bar "frees up" capacity
 * for more bars than spending on an expensive one; sorting ascending and taking
 * greedily maximizes the total count — this is the unbounded subset-sum
 * optimality for unit-profit items.
 *
 * Time Complexity: O(N log N) - Sorting dominates; the scan is O(N) with early
 * termination
 *
 * Space Complexity: O(1) - Running counters (coins, count) plus in-place sort
 *
 * Edge Cases Handled: zero coins (returns 0 immediately), can afford all bars
 * (count = N), coins exhausted mid-array (loop continues but condition fails),
 * all bars same price
 */
class MaximumIceCreamBars {
    public int maxIceCream(int[] costs, int coins) {
        int count = 0;
        Arrays.sort(costs);
        for (int cost : costs) {
            if (coins >= cost) {
                coins -= cost;
                count++;
            }
        }
        return count;
    }
}
