/**
 * 1518. Water Bottles
 * Difficulty: Easy | Tags: Math, Simulation
 * https://leetcode.com/problems/water-bottles/
 *
 * Pattern: Iterative Bottle Exchange (Fixed Exchange Rate)
 *
 * Key insight: Each round, divide current bottles by numExchange to get newly
 * drinkable bottles, then fold in the modulo remainder as leftover empties;
 * repeat until the batch is too small to exchange.
 *
 * Time Complexity: O(log_{numExchange}(numBottles)) - Bottles shrink by roughly
 * the exchange rate each iteration
 *
 * Space Complexity: O(1) - Only three int variables (res, t, numBottles)
 *
 * Edge Cases Handled: exact multiples of numExchange (remainder = 0, no
 * leftovers folded in), numBottles < numExchange on first iteration (returns
 * initial count immediately)
 */
class WaterBottles {
    public static int numWaterBottles(int numBottles, int numExchange) {
        int res=numBottles;
        while(numBottles>=numExchange){
        int bottlesFromExchange=numBottles/numExchange;
           if(numBottles%numExchange==0){
            res=res+bottlesFromExchange;
            numBottles=bottlesFromExchange;
           }
           else{
            res=res+bottlesFromExchange;
            numBottles=bottlesFromExchange+(numBottles%numExchange);
           }
        }
        return res;
    }
}
