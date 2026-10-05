/**
 * 1710. Maximum Units on a Truck
 * Difficulty: Easy | Tags: Array, Greedy, Sorting
 * https://leetcode.com/problems/maximum-units-on-a-truck/
 *
 * Pattern: Fractional Knapsack (Greedy by Value Density)
 *
 * Key insight: Since partial boxes are allowed, the optimal strategy is purely
 * greedy: always fill from the highest-unit-per-box type first. The only
 * decision point is the last (possibly partial) batch when capacity runs out.
 *
 * Time Complexity: O(N log N) - Sort by unit count descending; single scan is
 * O(N)
 *
 * Space Complexity: O(1) - Accumulator plus loop variables (sort in place)
 *
 * Edge Cases Handled: truckSize = 0 (returns 0), fewer total boxes than
 * capacity (all loaded), exact capacity match with no partial box, single box
 * type filling the entire truck
 */
class MaximumUnitsOnATruck {
    public int maximumUnits(int[][] boxTypes, int truckSize) {
        Arrays.sort(boxTypes, (a, b) -> b[1] - a[1]);
        int total = 0;
        for (int i = 0; i < boxTypes.length; i++) {
            int boxCount = boxTypes[i][0];
            int units = boxTypes[i][1];
            if (truckSize >= boxCount) {
                truckSize -= boxCount;
                total += boxCount * units;
            } else {
                total += truckSize * units;
                break;
            }
        }
        return total;
    }
}
