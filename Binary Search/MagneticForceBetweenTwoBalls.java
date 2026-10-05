/**
 * 1552. Magnetic Force Between Two Balls
 * Difficulty: Medium | Tags: Array, Binary Search, Sorting
 * https://leetcode.com/problems/magnetic-force-between-two-balls/
 *
 * Pattern: Binary Search on the Answer (Greedy Feasibility Check)
 *
 * Key insight: If a minimum force of d is feasible, then any force less than d
 * is also feasible (monotonicity), so the answer space can be binary searched.
 * For each candidate force, a greedy linear scan places balls from left to
 * right only when the gap meets the threshold; if all m balls are placed, the
 * candidate is feasible.
 *
 * Time Complexity: O(N log R) where R is the coordinate range (position[N-1] -
 * position[0]) - Sorting costs O(N log N); the binary search executes O(log R)
 * iterations, each with an O(N) feasibility scan.
 *
 * Space Complexity: O(1) - Only constant extra space is used beyond the input
 * array.
 *
 * Edge Cases Handled: m == 2 (binary search over full range, returning the
 * maximum possible gap); m equals array length (every position used, minimum
 * gap equals the min of adjacent sorted gaps); all positions equal (zero range,
 * force 0 always returned); unsorted input (sorted at the start before binary
 * search).
 */
class MagneticForceBetweenTwoBalls {
    public int maxDistance(int[] position, int m) {
        Arrays.sort(position);
        int left = 1;
        int right = position[position.length - 1] - position[0];
        int res = 0;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (countBalls(position, mid) >= m) {
                res = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return res;
    }

    private static int countBalls(int[] position, int k) {
        int placed = 1;
        int lastPlaced = position[0];
        for (int i = 1; i < position.length; i++) {
            if (position[i] - lastPlaced >= k) {
                placed++;
                lastPlaced = position[i];
            }
        }
        return placed;
    }
}
