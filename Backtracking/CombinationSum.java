/**
 * 39. Combination Sum
 * Difficulty: Medium | Tags: Array, Backtracking
 * https://leetcode.com/problems/combination-sum/
 *
 * Pattern: Backtracking with Unbounded Reuse (Start Index Never Advances on
 * Pick)
 *
 * Key insight: At each position the recursive branch that picks the current
 * candidate does NOT advance the index, enabling unlimited reuse; only the skip
 * branch advances, which also prevents generating duplicate permutations of the
 * same combination.
 *
 * Time Complexity: O(N^(T/M)) where T = target, M = minimum candidate - bounded
 * by the depth of repeated picks of the smallest value
 *
 * Space Complexity: O(T/M) recursion depth - at most T/M stack frames when
 * repeatedly picking the minimum candidate
 *
 * Edge Cases Handled: single candidate that divides target evenly (repeated
 * use), target equals one candidate value exactly, candidates all larger than
 * target (pruned immediately by target < 0 check), empty candidates array
 */
class CombinationSum {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> combi = new ArrayList<>();
        backtrack(candidates, res, 0, combi, target);
        return res;
    }

    public void backtrack(int[] candidates, List<List<Integer>> res, int startIndex, List<Integer> combi, int target) {
        if (startIndex == candidates.length || target < 0) {
            return;
        }
        if (target == 0) {
            res.add(new ArrayList<>(combi));
            return;
        }
        combi.add(candidates[startIndex]);
        backtrack(candidates, res, startIndex, combi, target - candidates[startIndex]);
        combi.remove(combi.size() - 1);
        backtrack(candidates, res, startIndex + 1, combi, target);
    }
}
