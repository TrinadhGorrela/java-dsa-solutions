/**
 * 216. Combination Sum III
 * Difficulty: Medium | Tags: Array, Backtracking
 * https://leetcode.com/problems/combination-sum-iii/
 *
 * Pattern: Backtracking Over Fixed Pool [1..9] with Dual Constraint Check
 *
 * Key insight: Enumerate combinations by advancing only forward from the
 * current index (ensuring distinctness and no duplicates), and simultaneously
 * prune when the running sum exceeds the target or the remaining elements are
 * insufficient to fill k slots.
 *
 * Time Complexity: O(C(9,k)) - iterates over all k-combinations from a
 * 9-element pool
 *
 * Space Complexity: O(k) recursion stack depth + O(C(9,k) * k) for output
 *
 * Edge Cases Handled: k = 1 (single-number combinations), n < k*(k+1)/2 or n >
 * sum(9-k+1..9) (impossible by bounds), single valid combination, no valid
 * combination exists (empty result), k = 9 with n = 45 (exactly one
 * combination)
 */
class CombinationSumIII {
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> res = new ArrayList<>();
        int[] nums = new int[9];
        for (int i = 0; i < 9; i++) {
            nums[i] = i + 1;
        }
        backtrack(nums, res, new ArrayList<>(), 0, n, k);
        return res;
    }

    public static void backtrack(int[] nums, List<List<Integer>> res, List<Integer> temp, int startIndex, int target, int k) {
        if (target == 0) {
            if (temp.size() == k) {
                res.add(new ArrayList<>(temp));
            }
            return;
        }

        for (int i = startIndex; i < nums.length; i++) {
            temp.add(nums[i]);
            backtrack(nums, res, temp, i + 1, target - nums[i], k);
            temp.remove(temp.size() - 1);
        }
    }
}
