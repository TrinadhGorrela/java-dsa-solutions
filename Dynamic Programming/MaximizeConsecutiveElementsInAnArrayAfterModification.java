/**
 * 3041. Maximize Consecutive Elements in an Array After Modification
 * Difficulty: Hard | Tags: Array, Dynamic Programming, Sorting
 * https://leetcode.com/problems/maximize-consecutive-elements-in-an-array-after
 * -modification/
 *
 * Pattern: Dynamic Programming (Longest consecutive chain on sorted values, two
 * transitions per element)
 *
 * Key insight: Sorting means a value x can only extend the chain ending at x-1
 * (keep x) or the chain ending at x (increment x by 1), so every predecessor is
 * already computed and one pass suffices; having only these two states enforces
 * the "increment by at most one" rule.
 *
 * Time Complexity: O(N log N) - Arrays.sort dominates; the single pass does
 * O(1) hash-map work per element.
 *
 * Space Complexity: O(N) - The dp map stores up to two entries (x and x+1) per
 * input element.
 *
 * Edge Cases Handled: empty input (returns 0); single element (returns 1);
 * duplicates (repeated values keep extending the chain); already sorted or
 * reverse-sorted input (normalized by Arrays.sort); negative values (x-1 / x+1
 * arithmetic still applies); input array is sorted in place; no guard against
 * Integer.MAX_VALUE overflow in x + 1, relying on the value constraint.
 */
class MaximizeConsecutiveElementsInAnArrayAfterModification {
    public int maxSelectedElements(int[] nums) {
        Map<Integer, Integer> dp = new HashMap<>();
        int max = 0;
        Arrays.sort(nums);

        for (int i = 0; i < nums.length; i++) {
            dp.put(nums[i] + 1, dp.getOrDefault(nums[i], 0) + 1);
            dp.put(nums[i], dp.getOrDefault(nums[i] - 1, 0) + 1);

            max = Math.max(max, Math.max(dp.get(nums[i]), dp.get(nums[i] + 1)));
        }

        return max;
    }
}
