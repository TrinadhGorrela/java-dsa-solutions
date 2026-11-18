/**
 * 871. Minimum Number of Refueling Stops
 * Difficulty: Hard | Tags: Array, Dynamic Programming, Greedy, Heap (Priority
 * Queue)
 * https://leetcode.com/problems/minimum-number-of-refueling-stops/
 *
 * Pattern: Greedy + Max-Heap (Priority Queue of Reachable Stations)
 *
 * Key insight: Every station enters the max-heap once, when fuel first reaches
 * it, and the largest-tank station is consumed whenever the car would run dry;
 * any smaller choice cannot reach farther, so this locally optimal refuel is
 * globally optimal, with each station entering and leaving the heap at most
 * once.
 *
 * Time Complexity: O(N log N) - All N stations are inserted and removed at most
 * once across the two priority queues, and every heap operation costs O(log N).
 *
 * Space Complexity: O(N) - Between them, the two heaps can hold all stations
 * from the input array.
 *
 * Edge Cases Handled: Target already reachable on the initial fuel (loop never
 * runs, returns 0), empty stations array, unreachable target once no station is
 * available (returns -1), a single station, stations beyond current reach that
 * must wait in the min-heap, and stations positioned past the target that are
 * never consumed.
 */
class MinimumNumberOfRefuelingStops {
    public int minRefuelStops(int target, int startFuel, int[][] stations) {
        Queue<int[]> areas = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));
        Queue<int[]> available = new PriorityQueue<>((a, b) -> Integer.compare(b[1], a[1]));

        int stops = 0;

        for (int[] depot : stations) {
            areas.add(depot);
        }

        while (startFuel < target) {

            while (!areas.isEmpty() && startFuel >= areas.peek()[0]) {
                available.add(areas.poll());
            }

            if (available.isEmpty()) {
                return -1;
            }

            startFuel += available.peek()[1];
            stops++;

            available.poll();
        }

        return stops;
    }
}
