/**
 * 303. Range Sum Query - Immutable
 * Difficulty: Easy | Tags: Array, Design, Prefix Sum
 * https://leetcode.com/problems/range-sum-query-immutable/
 *
 * Pattern: Prefix Sum Array (Offline Construction / Online Query)
 *
 * Key insight: Precompute prefix[i] = sum of nums[0..i-1] once in the
 * constructor. Each query sumRange(left, right) is then a single subtraction
 * prefix[right+1] - prefix[left], making all queries O(1) after O(N)
 * preprocessing.
 *
 * Time Complexity: O(N) - Constructor builds the prefix array in one pass; each
 * sumRange query is O(1)
 *
 * Space Complexity: O(N) - Prefix array stores N+1 cumulative sums
 *
 * Edge Cases Handled: empty query bounds (left == right returns the single
 * element), zero-length input (prefix has one zero entry), left == 0
 * (prefix[right+1] - 0), full-array range (0..N-1)
 */
class NumArray {
    int[] prefix;

    public NumArray(int[] nums) {
        this.prefix = new int[nums.length + 1];

        for (int i = 0; i < nums.length; i++) {
            prefix[i + 1] = prefix[i] + nums[i];
        }
    }

    public int sumRange(int left, int right) {
        return prefix[right + 1] - prefix[left];
    }
}

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * int param_1 = obj.sumRange(left,right);
 */
