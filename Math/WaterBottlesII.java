/**
 * 3100. Water Bottles II
 * Difficulty: Medium | Tags: Math, Simulation
 * https://leetcode.com/problems/water-bottles-ii/
 *
 * Pattern: Two-Phase Simulation (Drink Then Exchange with Growing Rate)
 *
 * Key insight: In each outer loop iteration, drink all full bottles at once
 * (adding them to the total and converting to empties), then repeatedly spend
 * empties at the current numExchange rate to buy new full bottles, incrementing
 * the rate after every purchase.
 *
 * Time Complexity: O(sqrt(N)) - The exchange rate grows with each trade, so
 * total purchases are bounded by roughly sqrt(2N)
 *
 * Space Complexity: O(1) - Only four int variables (fullBottles, empty,
 * BottlesDrunk, numExchange)
 *
 * Edge Cases Handled: no empties left to exchange (outer loop exits), exchange
 * rate grows after every single trade preventing cycles, initial numBottles = 0
 * (returns 0 immediately)
 */
class WaterBottlesII {
    public int maxBottlesDrunk(int numBottles, int numExchange) {
        int fullBottles = numBottles, empty = 0, bottlesDrunk = 0;
        while (fullBottles > 0) {

            empty = empty + fullBottles;
            bottlesDrunk = bottlesDrunk + fullBottles;
            fullBottles = 0;

            while (empty >= numExchange) {
                empty = empty - numExchange;
                fullBottles++;
                numExchange++;
            }

        }
        return bottlesDrunk;
    }
}
