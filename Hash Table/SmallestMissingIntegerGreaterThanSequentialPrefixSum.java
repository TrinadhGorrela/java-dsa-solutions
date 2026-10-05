/**
 * 2996. Smallest Missing Integer Greater Than Sequential Prefix Sum
 * Difficulty: Easy | Tags: Array, Hash Table, Sorting
 * https://leetcode.com/problems/smallest-missing-integer-greater-than-sequentia
 * l-prefix-sum/
 *
 * Pattern: HashSet + Sequential Prefix Walk
 *
 * Key insight: Walk the sorted-adjacent prefix (where nums[i] - nums[i-1] == 1)
 * accumulating its sum, then increment until the sum is absent from the element
 * set. The break-on-gap ensures we only sum a true sequential run.
 *
 * Time Complexity: O(N) - One pass to populate the set, one pass to walk the
 * sequential prefix, then a bounded increment loop
 *
 * Space Complexity: O(N) - HashSet stores all array elements for O(1) lookups
 * during the increment phase
 *
 * Edge Cases Handled: no sequential prefix (break at i=1, sum = nums[0]),
 * single element array, sum value colliding with an existing element requiring
 * further increments
 */
class SmallestMissingIntegerGreaterThanSequentialPrefixSum {
    public int missingInteger(int[] nums) {
        Set<Integer> seen = new HashSet<>();

        for (int i : nums) {
            seen.add(i);
        }

        int sum = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] - nums[i - 1] == 1) {
                sum += nums[i];
            } else {
                break;
            }
        }

        while (seen.contains(sum)) {
            sum++;
        }

        return sum;
    }
}
