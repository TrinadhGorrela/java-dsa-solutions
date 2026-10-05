/**
 * 47. Permutations II
 * Difficulty: Medium | Tags: Array, Backtracking, Sorting
 * https://leetcode.com/problems/permutations-ii/
 *
 * Pattern: Backtracking with Sort-Then-Skip Duplicate Suppression
 *
 * Key insight: After sorting, identical values are adjacent; at each recursion
 * level, skip nums[i] when nums[i] == nums[i-1] and nums[i-1] was NOT used in
 * the current branch -- this ensures the second copy is only placed after the
 * first, preventing structurally duplicate permutations.
 *
 * Time Complexity: O(n * n!) worst case - n! permutations with O(n) each;
 * duplicate skipping reduces this for non-distinct inputs
 *
 * Space Complexity: O(n) recursion depth + O(n * n!) for output storage
 *
 * Edge Cases Handled: all elements identical (returns exactly one permutation),
 * no duplicates (behaves identically to Permutations I), single element, mixed
 * duplicates and distinct values
 */
class PermutationsII {
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        boolean[] used = new boolean[nums.length];
        backtrack(new ArrayList<>(), nums, res, used);
        return res;
    }

    public void backtrack(List<Integer> current, int[] nums, List<List<Integer>> res, boolean[] used) {
        if (current.size() == nums.length) {
            res.add(new ArrayList<>(current));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (used[i])
                continue;
            if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1])
                continue;
            used[i] = true;
            current.add(nums[i]);
            backtrack(current, nums, res, used);
            used[i] = false;
            current.remove(current.size() - 1);
        }
    }
}
