/**
 * 1094. Car Pooling
 * Difficulty: Medium | Tags: Array, Sorting, Heap (Priority Queue), Simulation,
 * Prefix Sum
 * https://leetcode.com/problems/car-pooling/
 *
 * Pattern: Difference Array Sweep Line
 *
 * Key insight: Model each trip as +passengers at start and -passengers at end
 * on a fixed 1001-slot difference array (stops bounded to [0, 1000]). A single
 * prefix sweep reveals the running passenger count at every stop; if any
 * exceeds capacity, return false.
 *
 * Time Complexity: O(N + 1001) - O(N) to mark pickups and drop-offs, O(1001)
 * for the sweep (constant bound on stop range)
 *
 * Space Complexity: O(1) - Fixed 1001-element array independent of input size
 *
 * Edge Cases Handled: no trips (vacuously true), simultaneous pickup and
 * drop-off at the same stop, trips at stop 0 or stop 1000, capacity of zero
 */
class CarPooling {
    public boolean carPooling(int[][] trips, int capacity) {
        int[] passengerCount = new int[1001];
        for (int i = 0; i < trips.length; i++) {
            passengerCount[trips[i][1]] = passengerCount[trips[i][1]] + trips[i][0];
            passengerCount[trips[i][2]] = passengerCount[trips[i][2]] - trips[i][0];
        }
        int sum = 0;
        for (int i = 0; i < passengerCount.length; i++) {
            sum += passengerCount[i];
            if (sum > capacity) {
                return false;
            }
        }
        return true;
    }
}
