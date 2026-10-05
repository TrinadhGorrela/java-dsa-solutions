/**
 * 134. Gas Station
 * Difficulty: Medium | Tags: Array, Greedy
 * https://leetcode.com/problems/gas-station/
 *
 * Pattern: Two-Pass Greedy Feasibility Check
 *
 * Key insight: A circular tour is possible only when total gas >= total cost;
 * within that guarantee, any prefix deficit proves every station in that prefix
 * is an invalid start, so resetting to the next station after a deficit is
 * sufficient.
 *
 * Time Complexity: O(N) - Two linear passes: one for the total check, one for
 * the start selection
 *
 * Space Complexity: O(1) - Four integer accumulators (totalGas, totalCost,
 * start, curr)
 *
 * Edge Cases Handled: total gas < total cost (returns -1 immediately), all
 * stations deficit-free (returns 0), single station with gas >= cost, deficit
 * resets mid-tour pushing start to the correct answer
 */
class GasStation {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int totalGas = 0;
        int totalCost = 0;
        for (int i = 0; i < gas.length; i++) {
            totalGas += gas[i];
            totalCost += cost[i];
        }

        if (totalGas >= totalCost) {
            int start = 0;
            int curr = 0;
            for (int i = 0; i < cost.length; i++) {
                curr += gas[i] - cost[i];
                if (curr < 0) {
                    start = i + 1;
                    curr = 0;
                }
            }

            return start;
        } else {
            return -1;
        }
    }
}
