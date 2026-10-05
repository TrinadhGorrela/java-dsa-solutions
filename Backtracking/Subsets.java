/**
 * 78. Subsets
 * Difficulty: Medium | Tags: Array, Backtracking, Bit Manipulation
 * https://leetcode.com/problems/subsets/
 *
 * Pattern: DFS Subset Enumeration with Forward-Only Indexing
 *
 * Key insight: Record a snapshot of the current subset at every recursive call
 * (before iterating), then for each element from the current index forward,
 * include it and recurse -- this naturally generates all 2^n subsets without
 * explicit include/exclude branches.
 *
 * Time Complexity: O(2^n * n) - 2^n subsets, each copied in O(n) time
 *
 * Space Complexity: O(n) recursion depth + O(n * 2^n) for storing all subsets
 *
 * Edge Cases Handled: empty input (returns single empty subset), single element
 * (returns [[], [element]]), all elements equal (all 2^n subsets generated
 * including duplicates), n = 0 edge case
 */
class Subsets {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        backtrack(0, nums, new ArrayList<>(), res);
        return res;
    }

    public void backtrack(int st, int[] nums, List<Integer> subset, List<List<Integer>> res) {
        res.add(new ArrayList<>(subset));
        for (int i = st; i < nums.length; i++) {
            subset.add(nums[i]);
            backtrack(i + 1, nums, subset, res);
            subset.remove(subset.size() - 1);
        }
    }
}
