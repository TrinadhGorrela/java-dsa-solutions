/**
 * 229. Majority Element II
 * Difficulty: Medium | Tags: Array, Hash Table, Sorting, Counting, Boyer–Moore
 * Majority Vote Algorithm
 * https://leetcode.com/problems/majority-element-ii/
 *
 * Pattern: HashMap Frequency Count
 *
 * Key insight: At most two elements can exceed n/3 frequency — a full frequency
 * map followed by a single filter pass identifies them with no need for
 * Boyer-Moore voting.
 *
 * Time Complexity: O(N) - One pass to populate counts, one pass over distinct
 * keys (at most N)
 *
 * Space Complexity: O(N) - HashMap holds one entry per distinct element
 *
 * Edge Cases Handled: no element exceeds n/3 (empty list returned), exactly one
 * or two elements qualify, n < 3 (at most one qualifier), all elements
 * identical
 */
class MajorityElementII {
    public List<Integer> majorityElement(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        List<Integer> res = new ArrayList<>();
        for (int i : nums) {
            map.put(i, map.getOrDefault(i, 0) + 1);
        }
        int n = nums.length;
        for (int i : map.keySet()) {
            if (map.get(i) > n / 3) {
                res.add(i);
            }
        }
        return res;
    }
}
