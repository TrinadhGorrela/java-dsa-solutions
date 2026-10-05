/**
 * 90. Subsets II
 * Difficulty: Medium | Tags: Array, Backtracking, Bit Manipulation
 * https://leetcode.com/problems/subsets-ii/
 *
 * Pattern: Forward-Index Subset Enumeration with Same-Level Duplicate Skip
 *
 * Key insight: Sort the input, then within the for-loop at each recursion
 * level, skip nums[i] when it equals nums[i-1] and i > startIndex -- this
 * prevents the same value from being chosen at the same depth/position, which
 * is what causes duplicate subsets.
 *
 * Time Complexity: O(2^n * n) - at most 2^n subsets generated, each copied in
 * O(n); duplicate skipping reduces branching for non-distinct inputs
 *
 * Space Complexity: O(n) recursion depth + O(n * 2^n) for output
 *
 * Edge Cases Handled: all elements identical (only one subset per size), no
 * duplicates (skip condition never triggers), single element, empty subset
 * always included via the initial res.add snapshot
 */
class SubsetsII {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        backtrack(0, nums, new ArrayList<>(), res);
        return res;
    }

    public void backtrack(int st, int[] nums, List<Integer> subset, List<List<Integer>> res) {

        res.add(new ArrayList<>(subset));
        for (int i = st; i < nums.length; i++) {
            if (i > st && nums[i] == nums[i - 1])
                continue;
            subset.add(nums[i]);
            backtrack(i + 1, nums, subset, res);
            subset.remove(subset.size() - 1);
        }
    }
}
