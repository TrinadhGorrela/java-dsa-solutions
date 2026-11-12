/**
 * 1642. Furthest Building You Can Reach
 * Difficulty: Medium | Tags: Array, Greedy, Heap (Priority Queue)
 * https://leetcode.com/problems/furthest-building-you-can-reach/
 *
 * Pattern: Greedy + Min-Heap (Priority Queue)
 *
 * Key insight: Ladders should be spent on the largest upward gaps, so keep
 * every climb in a min-heap and, once climbs exceed the ladder count, pay the
 * smallest climb with bricks; paying the cheapest gap with bricks preserves
 * ladders for the biggest jumps, and the moment that cheapest gap exceeds the
 * remaining bricks the current building is the furthest reachable.
 *
 * Time Complexity: O(N log L) - Each climb is pushed into a heap bounded by the
 * ladder count L, with at most one poll per building; effectively O(N log N).
 *
 * Space Complexity: O(L) - The priority queue holds at most the L
 * ladder-assigned climbs.
 *
 * Edge Cases Handled: single building (returns index 0/height length - 1), no
 * ladders (every climb paid by bricks), no bricks (only ladders used), zero or
 * descending gaps (skipped, no cost), enough bricks and ladders for every gap
 * (returns last index), climb exactly equal to remaining bricks (uses `>` so
 * equality is payable), first unaffordable climb (returns the last reachable
 * index); assumes non-negative heights, bricks, and ladders.
 */
class FurthestBuildingYouCanReach {
    public int furthestBuilding(int[] heights, int bricks, int ladders) {
        Queue<Integer> queue = new PriorityQueue<>((a, b) -> Integer.compare(a, b));

        for (int i = 0; i < heights.length - 1; i++) {
            if (heights[i] < heights[i + 1]) {
                queue.add(heights[i + 1] - heights[i]);

                if (queue.size() > ladders) {
                    if (queue.peek() > bricks) {
                        return i;
                    }

                    bricks -= queue.peek();
                    queue.poll();
                }
            }
        }

        return heights.length - 1;
    }
}
