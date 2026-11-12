/**
 * 525. Contiguous Array
 * Difficulty: Medium | Tags: Array, Hash Table, Prefix Sum
 * https://leetcode.com/problems/contiguous-array/
 *
 * Pattern: Prefix Sum + Hash Map (First Occurrence)
 *
 * Key insight: Treat 0 as -1 and 1 as +1, so a subarray has equal counts
 * exactly when its prefix sum repeats. Recording only the earliest index for
 * each prefix sum maximizes the distance to a later repeat, which is the
 * longest balanced subarray.
 *
 * Time Complexity: O(N) - Two linear passes with O(1) average-cost hash map
 * operations.
 *
 * Space Complexity: O(N) - The prefix array and hash map grow with the number
 * of distinct prefix sums.
 *
 * Edge Cases Handled: empty array (returns 0), all-zero or all-one input
 * (returns 0), no balanced subarray (returns 0), balanced prefix starting at
 * index 0 (seeded map entry 0 -> 0); null input is NOT handled
 */
class ContiguousArray {
    public int findMaxLength(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        int[] prefix = new int[nums.length + 1];
        map.put(0, 0);

        for (int i = 1; i < prefix.length; i++) {
            if (nums[i - 1] == 0) {
                prefix[i] = prefix[i - 1] - 1;
            } else {
                prefix[i] = prefix[i - 1] + 1;
            }
        }

        int max = 0;

        for (int i = 0; i < prefix.length; i++) {
            if (map.containsKey(prefix[i])) {
                max = Math.max(max, i - map.get(prefix[i]));
            } else {
                map.put(prefix[i], i);
            }
        }

        return max;
    }
}
