/**
 * 55. Jump Game
 * Difficulty: Medium | Tags: Array, Dynamic Programming, Greedy
 * https://leetcode.com/problems/jump-game/
 *
 * Pattern: Greedy (Expanding Reachability Window)
 *
 * Key insight: Maintain the farthest reachable index; if the current index ever
 * exceeds that bound, no path forward exists — otherwise greedily extend the
 * window and short-circuit as soon as the last index is reachable.
 *
 * Time Complexity: O(N) - Single pass; early exit when farthestReach >= last
 * index
 *
 * Space Complexity: O(1) - Only one tracking variable (farthestReach)
 *
 * Edge Cases Handled: single element (trivially reachable), leading zero that
 * blocks progress (returns false), first jump already covers the entire array,
 * zero-length effective path from index 0
 */
class JumpGame {
    public boolean canJump(int[] nums) {
        int farthestReach = 0;
        for (int i = 0; i < nums.length; i++) {
            if (farthestReach < i)
                return false;
            farthestReach = Math.max(farthestReach, i + nums[i]);
            if (farthestReach >= nums.length - 1)
                return true;
        }
        return false;
    }
}
