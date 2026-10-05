/**
 * 77. Combinations
 * Difficulty: Medium | Tags: Backtracking
 * https://leetcode.com/problems/combinations/
 *
 * Pattern: Forward-Index Combination Enumeration
 *
 * Key insight: Only iterate from the current index forward, guaranteeing
 * strictly increasing selection order so every (n choose k) combination is
 * generated exactly once without duplicates; the pruning window narrows as i +
 * (k - path.size()) - 1 exceeds n.
 *
 * Time Complexity: O(C(n,k) * k) - Generates C(n,k) combinations, each copied
 * in O(k) time
 *
 * Space Complexity: O(k) auxiliary for recursion depth + O(C(n,k) * k) for
 * output
 *
 * Edge Cases Handled: k = 0 (returns single empty list), k = n (single
 * combination containing all elements), n = 1 with k = 1, k > n (returns empty
 * list)
 */
class Combinations {
    public List<List<Integer>> combine(int n, int k) {
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = i + 1;
        }

        List<List<Integer>> res = new ArrayList<>();
        backtrack(res, new ArrayList<>(), 0, nums, k);
        return res;
    }

    public static void backtrack(List<List<Integer>> res, List<Integer> temp, int startIndex, int[] nums, int k) {
        if (temp.size() == k) {
            res.add(new ArrayList<>(temp));
            return;
        }

        for (int i = startIndex; i < nums.length; i++) {
            temp.add(nums[i]);
            backtrack(res, temp, i + 1, nums, k);
            temp.remove(temp.size() - 1);
        }
    }
}
