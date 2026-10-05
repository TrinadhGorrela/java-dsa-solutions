/**
 * 414. Third Maximum Number
 * Difficulty: Easy | Tags: Array, Sorting
 * https://leetcode.com/problems/third-maximum-number/
 *
 * Pattern: Single-Pass Three-Variable Tracking
 *
 * Key insight: Maintain three nullable Integer references (first, second,
 * third); on each new distinct value, cascade the old values downward (third =
 * second, second = first) and assign the new max — no sorting or set needed.
 *
 * Time Complexity: O(N) - One pass with constant-time comparisons and shifts
 * per element
 *
 * Space Complexity: O(1) - Three nullable Integer references and loop index
 * only
 *
 * Edge Cases Handled: fewer than 3 distinct values (third remains null, returns
 * first), duplicates explicitly skipped via equality checks, Integer.MIN_VALUE
 * as a valid candidate, all elements identical
 */
class ThirdMaximumNumber {
    public int thirdMax(int[] nums) {
        Integer first = null;
        Integer second = null;
        Integer third = null;

        for (int i = 0; i < nums.length; i++) {
            if ((first != null && first == nums[i]) || (second != null && second == nums[i])
                    || (third != null && third == nums[i])) {
                continue;
            }
            if (first == null || nums[i] > first) {
                third = second;
                second = first;
                first = nums[i];
            } else if (second == null || nums[i] > second) {
                third = second;
                second = nums[i];
            } else if (third == null || nums[i] > third) {
                third = nums[i];
            }
        }
        return third == null ? first : third;
    }
}
