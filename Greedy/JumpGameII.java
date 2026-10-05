/**
 * 45. Jump Game II
 * Difficulty: Medium | Tags: Array, Dynamic Programming, Greedy
 * https://leetcode.com/problems/jump-game-ii/
 *
 * Pattern: Greedy BFS Layer Counting
 *
 * Key insight: Treat each jump as a BFS layer: `currentEnd` marks the frontier
 * of the current layer, `farthestReach` tracks the next frontier. When the
 * sweep hits the frontier, a new jump begins and the frontier advances.
 *
 * Time Complexity: O(N) - Single linear scan; each index visited once
 *
 * Space Complexity: O(1) - Three integer variables (currentEnd, farthestReach,
 * jumps)
 *
 * Edge Cases Handled: single element (loop body never executes, returns 0),
 * one-jump solution (currentEnd starts at 0, farthestReach already past last
 * index), array requiring maximum possible jumps
 */
class JumpGameII {
    public int jump(int[] nums) {
        int currentEnd = 0;
        int farthestReach = 0;
        int jumps = 0;

        for (int i = 0; i < nums.length - 1; i++) {
            farthestReach = Math.max(farthestReach, i + nums[i]);
            if (i >= currentEnd) {
                jumps++;
                currentEnd = farthestReach;
            }
        }
        return jumps;
    }
}
