/**
 * 11. Container With Most Water
 * Difficulty: Medium | Tags: Array, Two Pointers, Greedy
 * https://leetcode.com/problems/container-with-most-water/
 *
 * Pattern: Greedy Two-Pointer Shrink
 *
 * Key insight: The area is bounded by the shorter wall and the width; moving
 * the taller wall inward can only decrease width without improving height, so
 * always advancing the shorter pointer is provably optimal.
 *
 * Time Complexity: O(N) - Each pointer moves at most N steps; single pass
 *
 * Space Complexity: O(1) - Two pointers, one max tracker, one temp area
 * variable
 *
 * Edge Cases Handled: Minimum two-element array, equal heights (either pointer
 * can move), all walls same height, tallest walls at the ends, progressively
 * decreasing heights
 */
class ContainerWithMostWater {
    public int maxArea(int[] height) {
        int left = 0;
        int right = height.length - 1;
        int maxAreaValue = 0, area = 0;
        while (left < right) {
            area = Math.min(height[left], height[right]) * (right - left);
            maxAreaValue = Math.max(maxAreaValue, area);
            if (height[left] < height[right])
                left++;
            else
                right--;
        }
        return maxAreaValue;
    }
}
