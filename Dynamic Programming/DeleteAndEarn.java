/**
 * 740. Delete and Earn
 * Difficulty: Medium | Tags: Array, Hash Table, Dynamic Programming
 * https://leetcode.com/problems/delete-and-earn/
 *
 * Pattern: Dynamic Programming (House Robber reduction on aggregated values)
 *
 * Key insight: Aggregating each value's total points collapses duplicates into
 * a single weight, and deleting v removes every v-1/v+1 entry, so adjacent
 * unique values become mutually exclusive - exactly House Robber (take =
 * points[i] + dp[i-2]). A gap greater than 1 implies no conflict, so such a
 * value is always added on top of dp[i-1].
 *
 * Time Complexity: O(N log N) - O(N) hash aggregation plus sorting the U <= N
 * unique values in O(U log U), which dominates.
 *
 * Space Complexity: O(U) where U = distinct values (<= N) - The points map,
 * sorted key list, and dp array all scale with the number of unique values.
 *
 * Edge Cases Handled: single element; duplicate values (merged into one point
 * total); consecutive values vs. gaps > 1 (gaps added independently);
 * first/second unique value via the i >= 2 guard before dp[i-2]; large totals
 * held in long before the final int cast; empty input is NOT handled (throws at
 * uniqueNums.get(0)) and negative values are not meaningful under the
 * positive-value constraint.
 */
class DeleteAndEarn {
    public int deleteAndEarn(int[] nums) {
        Map<Integer, Long> pointsMap = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            pointsMap.put(nums[i], pointsMap.getOrDefault(nums[i], 0l) + nums[i]);
        }

        List<Integer> uniqueNums = new ArrayList<>(pointsMap.keySet());
        long[] dp = new long[uniqueNums.size()];

        Collections.sort(uniqueNums);

        dp[0] = pointsMap.get(uniqueNums.get(0));
        long max = dp[0];

        for (int i = 1; i < uniqueNums.size(); i++) {
            if (uniqueNums.get(i) == uniqueNums.get(i - 1) + 1) {
                long take = pointsMap.get(uniqueNums.get(i));
                long skip = dp[i - 1];

                if (i >= 2) {
                    take += dp[i - 2];
                }

                dp[i] = Math.max(take, skip);
            } else if (uniqueNums.get(i) > uniqueNums.get(i - 1) + 1) {
                dp[i] = pointsMap.get(uniqueNums.get(i)) + dp[i - 1];
            }

            max = Math.max(max, dp[i]);
        }

        return (int) max;
    }
}
