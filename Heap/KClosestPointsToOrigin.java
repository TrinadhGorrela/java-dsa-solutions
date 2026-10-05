/**
 * 973. K Closest Points to Origin
 * Difficulty: Medium | Tags: Array, Math, Divide and Conquer, Geometry,
 * Sorting, Heap (Priority Queue), Quickselect, K-D Tree
 * https://leetcode.com/problems/k-closest-points-to-origin/
 *
 * Pattern: Max-Heap (Priority Queue) of Size K
 *
 * Key insight: Maintain a max-heap of size K storing distance-pairs; inserting
 * each point and ejecting the farthest when size exceeds K leaves exactly the K
 * closest. Using a max-heap (descending comparator) ensures the largest
 * distance is at the heap root and is ejected first, which is correct because
 * we want to keep only the K smallest distances.
 *
 * Time Complexity: O(N log K) - Each of N points is inserted/possibly ejected
 * in O(log K) heap operations.
 *
 * Space Complexity: O(K) - The heap holds at most K entries, and the result
 * array is also O(K).
 *
 * Edge Cases Handled: k equals total number of points (all returned); k == 1
 * (single closest point); points at origin (distance zero, never ejected);
 * negative coordinates (sqrt of sum-of-squares is always non-negative).
 */
class KClosestPointsToOrigin {
    public int[][] kClosest(int[][] points, int k) {
        Queue<double[]> queue = new PriorityQueue<>((a, b) -> Double.compare(b[0], a[0]));

        for (int i = 0; i < points.length; i++) {
            int[] point = points[i];
            double distance = Math.sqrt((point[0] * point[0]) + (point[1] * point[1]));
            queue.offer(new double[] { distance, point[0], point[1] });
            if (queue.size() > k) {
                queue.poll();
            }
        }

        int[][] res = new int[k][2];

        for (int i = 0; i < k; i++) {
            double[] curr = queue.poll();
            res[i][0] = (int) curr[1];
            res[i][1] = (int) curr[2];
        }

        return res;
    }
}
