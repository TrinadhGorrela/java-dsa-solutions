/**
 * 42. Trapping Rain Water
 * Difficulty: Hard | Tags: Array, Two Pointers, Dynamic Programming, Stack,
 * Monotonic Stack
 * https://leetcode.com/problems/trapping-rain-water/
 *
 * Pattern: Two-Pointer Water-Level Sweep
 *
 * Key insight: At each step the shorter-side bar is the bottleneck—water above
 * it is determined by its own historical max and the opposite side's max, so
 * advancing the shorter pointer is always safe and avoids needing precomputed
 * leftMax/rightMax arrays.
 *
 * Time Complexity: O(N) - Each pointer advances at most N steps; single pass
 * with no nested loops
 *
 * Space Complexity: O(1) - Only four variables (left, right, leftMax, rightMax)
 * regardless of input size
 *
 * Edge Cases Handled: Fewer than 3 bars (zero water), monotonic
 * ascending/descending heights, plateaus where left == right height, all bars
 * at equal height
 */
class TrappingRainWater {
    public int trap(int[] height) {
        int water = 0;
        int left = 0;
        int right = height.length - 1;
        int leftMax = 0;
        int rightMax = 0;

        while (left < right) {
            if (height[left] <= height[right]) {
                leftMax = Math.max(leftMax, height[left]);
                if (height[left] < leftMax) {
                    water += leftMax - height[left];
                }
                left++;
            } else {
                rightMax = Math.max(rightMax, height[right]);
                if (height[right] < rightMax) {
                    water += rightMax - height[right];
                }
                right--;
            }
        }
        return water;
    }
}
