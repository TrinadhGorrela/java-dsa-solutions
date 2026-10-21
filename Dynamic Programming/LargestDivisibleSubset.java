/**
 * 368. Largest Divisible Subset
 * Difficulty: Medium | Tags: Array, Math, Dynamic Programming, Sorting
 * https://leetcode.com/problems/largest-divisible-subset/
 *
 * Pattern: Sorting + Dynamic Programming (Longest Divisible Subset with Parent
 * Pointer Reconstruction)
 *
 * Key insight: divisibility implies value order (a divides b and a != b means a
 * < b), so once the array is sorted ascending every divisible chain is a
 * subsequence of it; dp[i] then tracks the longest chain ending at i and hash[]
 * stores the parent index so the subset can be rebuilt.
 *
 * Time Complexity: O(N^2) - Sorting costs O(N log N) but the nested loop over
 * all pairs dominates; the parent-chain rebuild is linear
 *
 * Space Complexity: O(N) - Two auxiliary arrays (dp for chain lengths, hash for
 * parent indices) scale with the input size
 *
 * Edge Cases Handled: single-element input (returns that element), duplicate
 * values (a value is divisible by itself, so equal elements chain and can
 * appear more than once in the result), result ordering (the subset is rebuilt
 * from the largest element backwards, so it comes back in descending order),
 * already-sorted input (the in-place sort is a no-op). Note: the input array is
 * sorted in place, an empty array throws ArrayIndexOutOfBoundsException, and
 * the modulo divisibility test assumes positive values as guaranteed by the
 * problem.
 */
class LargestDivisibleSubset {
    public List<Integer> largestDivisibleSubset(int[] nums) {
        List<Integer> res = new ArrayList<>();
        int[] dp = new int[nums.length];
        int[] hash = new int[nums.length];
        int lastIn = 0;

        Arrays.sort(nums);
        Arrays.fill(dp, 1);

        for (int i = 1; i < nums.length; i++) {
            hash[i] = i;
            for (int j = 0; j < i; j++) {
                if (nums[i] % nums[j] == 0 && dp[i] < dp[j] + 1) {
                    dp[i] = dp[j] + 1;
                    hash[i] = j;
                }
            }

            if (dp[lastIn] < dp[i]) {
                lastIn = i;
            }
        }

        while (hash[lastIn] != lastIn) {
            res.add(nums[lastIn]);
            lastIn = hash[lastIn];
        }

        res.add(nums[lastIn]);

        return res;
    }
}
