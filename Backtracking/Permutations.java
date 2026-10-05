/**
 * 46. Permutations
 * Difficulty: Medium | Tags: Array, Backtracking
 * https://leetcode.com/problems/permutations/
 *
 * Pattern: Full Permutation Construction via Used-Element Tracking
 *
 * Key insight: At each recursion level, iterate over all nums and skip any
 * already in the current partial permutation (using list.contains()); when the
 * partial list reaches length n, a complete permutation is recorded.
 *
 * Time Complexity: O(n * n!) - n! permutations, each requiring an O(n)
 * contains() check per position
 *
 * Space Complexity: O(n) recursion depth + O(n * n!) for storing all
 * permutations
 *
 * Edge Cases Handled: single element (returns [[element]]), all distinct values
 * (n! results), n = 0 (returns empty list via size check), elements at every
 * position in the partial list are checked for reuse
 */
class Permutations {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        backtrack(new ArrayList<>(), nums, res);
        return res;
    }

    public void backtrack(List<Integer> current, int[] nums, List<List<Integer>> res) {
        if (current.size() == nums.length) {
            res.add(new ArrayList<>(current));
            return;
        }
        for (int i : nums) {
            if (current.contains(i))
                continue;
            current.add(i);
            backtrack(current, nums, res);
            current.remove(current.size() - 1);
        }
    }
}
