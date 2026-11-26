/**
 * 701365. Fractional Knapsack
 * Difficulty: Easy | Tags: Greedy
 * https://www.geeksforgeeks.org/problems/fractional-knapsack-1587115620/1
 *
 * Pattern: Greedy (Descending Value-to-Weight Ratio)
 *
 * Key insight: Every unit of capacity contributes independently, so spending it
 * on the item with the highest value per unit weight is always optimal; sorting
 * by value/weight descending enforces that order, and the first non-fitting
 * item is taken fractionally so no capacity is wasted.
 *
 * Time Complexity: O(N log N) - Sorting the N items by ratio dominates the
 * single linear greedy pass (N = val.length).
 *
 * Space Complexity: O(N) - Builds an auxiliary N x 3 table storing value,
 * weight, and ratio for each item.
 *
 * Edge Cases Handled: Empty arrays (returns 0.0); zero capacity (zero-fraction
 * break); exact-fit items consumed whole when weight is at most the remaining
 * capacity; partial final item fills the leftover capacity then breaks; integer
 * division avoided by casting to double before ratio/fraction math. Null arrays
 * and negative weights/values are not validated.
 */
class FractionalKnapsack {
    public double fractionalKnapsack(int[] val, int[] wt, int capacity) {
        double[][] arr = new double[val.length][3];
        double res = 0;

        for (int i = 0; i < arr.length; i++) {
            arr[i][0] = val[i];
            arr[i][1] = wt[i];
            arr[i][2] = (double) val[i] / wt[i];
        }

        Arrays.sort(arr, (a, b) -> Double.compare(b[2], a[2]));

        for (int i = 0; i < arr.length; i++) {

            if (arr[i][1] <= capacity) {
                capacity -= arr[i][1];
                res += arr[i][0];
            } else {
                double temp = (double) capacity / arr[i][1];
                res += arr[i][0] * temp;

                break;
            }
        }

        return res;
    }
}
