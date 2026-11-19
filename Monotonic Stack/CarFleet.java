/**
 * 853. Car Fleet
 * Difficulty: Medium | Tags: Array, Stack, Sorting, Monotonic Stack
 * https://leetcode.com/problems/car-fleet/
 *
 * Pattern: Priority Queue (Position Descending) + Monotonic Decreasing Fleet
 * Times
 *
 * Key insight: Process cars from closest to farthest from the target: a car
 * only forms a new fleet when its arrival time exceeds the fleet ahead, since
 * any car with time <= that leader always catches it and merges. Keeping just
 * the strictly decreasing fleet times means each car is considered once, so
 * sorting is the only superlinear cost.
 *
 * Time Complexity: O(N log N) - N heap insertions plus extractions at O(log N)
 * each dominate the O(N) double-division time computations
 *
 * Space Complexity: O(N) - The priority queue stores one (position, speed) pair
 * per car
 *
 * Edge Cases Handled: Empty input (returns 0), single car, car already at the
 * target (zero arrival time), cars that catch up and merge into one fleet
 * (arrival time <= lead fleet time), fractional arrival times via double
 * division (no integer truncation); assumes non-zero speeds and distinct
 * positions per constraints
 */
class CarFleet {
    public int carFleet(int target, int[] position, int[] speed) {
        Queue<int[]> queue = new PriorityQueue<>((a, b) -> Integer.compare(b[0], a[0]));

        for (int i = 0; i < speed.length; i++) {
            queue.add(new int[] { position[i], speed[i] });
        }

        int fleets = 0;

        while (!queue.isEmpty()) {
            int[] temp = queue.poll();
            double time = (double) (target - temp[0]) / temp[1];

            while (!queue.isEmpty() && (((double) target - queue.peek()[0]) / queue.peek()[1]) <= time) {
                queue.poll();
            }

            fleets++;
        }

        return fleets;
    }
}
