/**
 * 40. Combination Sum II
 * Difficulty: Medium | Tags: Array, Backtracking
 * https://leetcode.com/problems/combination-sum-ii/
 *
 * Pattern: Backtracking with Duplicate Suppression via Sorting
 *
 * Key insight: Sort candidates so duplicates are adjacent, then skip over a
 * duplicate value at the same recursion level (same startIndex) to avoid
 * producing identical combinations; use early termination when the remaining
 * candidate cannot reach the target.
 *
 * Time Complexity: O(2^N) - Each element is either included or skipped; pruning
 * and dedup cut the practical branch factor
 *
 * Space Complexity: O(N) recursion stack + O(k) per valid combination - bounded
 * by recursion depth and combination length
 *
 * Edge Cases Handled: all candidates identical (dedup collapses to one
 * combination per valid size), target smaller than every candidate (early break
 * on sorted array), empty result when no subset sums to target, single
 * candidate equal to target
 */
class CombinationSumII {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(candidates);
        backtrack(candidates, res, new ArrayList<>(), 0, target);
        return res;
    }

    public static void backtrack(int[] nums, List<List<Integer>> res, List<Integer> temp, int startIndex, int target) {
        if (target == 0) {
            res.add(new ArrayList<>(temp));
            return;
        }

        for (int i = startIndex; i < nums.length; i++) {
            if (i > startIndex && nums[i] == nums[i - 1]) {
                continue;
            }

            if (target < nums[i]) {
                break;
            }

            temp.add(nums[i]);
            backtrack(nums, res, temp, i + 1, target - nums[i]);
            temp.remove(temp.size() - 1);
        }
    }
}
