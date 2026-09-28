/**
 * 1863. Sum of All Subset XOR Totals
 * Difficulty: Easy | Tags: Array, Math, Backtracking, Bit Manipulation, Combinatorics, Enumeration
 * https://leetcode.com/problems/sum-of-all-subset-xor-totals/
 *
 * Pattern: Full Subset Enumeration with Post-Collection XOR Aggregation
 * Key insight: Build every subset via forward-index backtracking, then iterate through the collected subsets computing
 * each subset's cumulative XOR and summing into the answer; the empty subset contributes XOR = 0 (neutral) and is
 * harmlessly included.
 *
 * Time Complexity: O(2^n * n) - 2^n subsets generated, each XOR-accumulated in O(n) time
 * Space Complexity: O(2^n * n) for storing all subsets (reducible to O(n) with a running XOR sum during recursion)
 *
 * Edge Cases Handled: single element (empty subset XOR 0 + element itself), all elements zero (every subset XORs to 0),
 * repeated values (subsets are structurally distinct even if values repeat), n = 0 (returns 0 from empty subset)
 */
class SumOfAllSubsetXorTotals {
    public int subsetXORSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        backtrack(nums, res, new ArrayList<>(), 0);
        int ans = 0;

        for (List<Integer> list : res) {
            int subsetXor = 0;
            for (int i : list) {
                subsetXor ^= i;
            }
            ans += subsetXor;
        }
        return ans;
    }

    public static void backtrack(int[] nums, List<List<Integer>> res, List<Integer> temp, int startIndex) {
        res.add(new ArrayList<>(temp));
        if (startIndex == nums.length) {
            return;
        }

        for (int i = startIndex; i < nums.length; i++) {
            temp.add(nums[i]);
            backtrack(nums, res, temp, i + 1);
            temp.remove(temp.size() - 1);
        }
    }
}
