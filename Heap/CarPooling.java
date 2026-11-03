/**
 * 1094. Car Pooling
 * Difficulty: Medium | Tags: Array, Sorting, Heap (Priority Queue), Simulation,
 * Prefix Sum
 * https://leetcode.com/problems/car-pooling/
 *
 * Pattern: Sorting + Min-Heap (Sweep Line Simulation)
 *
 * Key insight: Process pickups in increasing start order and keep active trips
 * in a min-heap keyed by drop-off, so every passenger who leaves at or before
 * the current pickup is removed before boarding. Because each trip is added and
 * removed at most once, the simulation stays linear apart from heap operations.
 *
 * Time Complexity: O(N log N) - Sorting the trips plus one heap add/poll per
 * trip
 *
 * Space Complexity: O(N) - The priority queue can hold all trips concurrently
 * in the worst case
 *
 * Edge Cases Handled: empty/single-trip input, passengers dropped off exactly
 * at the next pickup (<= evicts them first), zero-passenger trips, capacity
 * reached exactly (rejects only when >), fully overlapping trips; only safe for
 * non-negative coordinates, and it sorts the input array in place
 */
class CarPooling {
    public boolean carPooling(int[][] trips, int capacity) {
        PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> a[2] - b[2]);
        Arrays.sort(trips, (a, b) -> a[1] - b[1]);

        int mem = 0;

        for (int i = 0; i < trips.length; i++) {
            while (!queue.isEmpty() && queue.peek()[2] <= trips[i][1]) {
                mem -= queue.peek()[0];
                queue.poll();
            }

            mem += trips[i][0];
            if (mem > capacity) {
                return false;
            }

            queue.add(trips[i]);
        }

        return true;
    }
}
