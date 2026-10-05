/**
 * 1029. Two City Scheduling
 * Difficulty: Medium | Tags: Array, Greedy, Sorting, Hungarian Algorithm,
 * Successive Shortest Path Algorithm
 * https://leetcode.com/problems/two-city-scheduling/
 *
 * Pattern: Cost-Difference Sorting Greedy
 *
 * Key insight: The difference (costA - costB) captures how much cheaper city A
 * is relative to B; sorting by this difference and sending the first half to A
 * (biggest A advantage) and second half to B minimizes total cost under the N/2
 * split constraint.
 *
 * Time Complexity: O(N log N) - Sorting N people by cost difference dominates
 *
 * Space Complexity: O(N) - Auxiliary sortedCosts array storing the difference
 * as a third column
 *
 * Edge Cases Handled: exactly 2 people (one sent to each), all differences
 * equal (arbitrary but valid split), costA == costB for all (difference 0, any
 * assignment yields the same total)
 */
class TwoCityScheduling {
    public int twoCitySchedCost(int[][] costs) {
        int[][] sortedCosts = new int[costs.length][3];

        for (int i = 0; i < costs.length; i++) {
            sortedCosts[i][0] = costs[i][0];
            sortedCosts[i][1] = costs[i][1];
            sortedCosts[i][2] = costs[i][0] - costs[i][1];
        }

        Arrays.sort(sortedCosts, (a, b) -> a[2] - b[2]);
        int totalCost = 0;

        for (int i = 0; i < costs.length / 2; i++) {
            totalCost += sortedCosts[i][0];
        }

        for (int i = costs.length / 2; i < costs.length; i++) {
            totalCost += sortedCosts[i][1];
        }

        return totalCost;
    }
}
