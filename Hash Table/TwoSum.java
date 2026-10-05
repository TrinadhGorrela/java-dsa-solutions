/**
 * 1. Two Sum
 * Difficulty: Easy | Tags: Array, Hash Table
 * https://leetcode.com/problems/two-sum/
 *
 * Pattern: One-Pass Hash Map Lookup
 *
 * Key insight: For each element, compute its complement (target - nums[i]) and
 * check if it already exists in the map; this avoids a nested loop by trading
 * space for a single-pass lookup.
 *
 * Time Complexity: O(N) - Single linear scan with O(1) average HashMap get/put
 *
 * Space Complexity: O(N) - HashMap stores at most N number-to-index entries
 *
 * Edge Cases Handled: duplicate values (later index correctly pairs with
 * earlier index via map overwrite), negative numbers and negative targets,
 * answer at array boundaries (first and last element)
 */
class TwoSum {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        int[] res = new int[2];
        for (int i = 0; i < nums.length; i++) {
            int comp = target - nums[i];
            if (map.containsKey(comp)) {
                res[0] = map.get(comp);
                res[1] = i;
                break;
            }
            map.put(nums[i], i);
        }
        return res;
    }
}
