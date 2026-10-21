/**
 * 712574. Get Longest Increasing Subsequence
 * Difficulty: Medium | Tags: Dynamic Programming, Hash
 * https://www.geeksforgeeks.org/problems/printing-longest-increasing-subsequenc
 * e/1
 *
 * Pattern: Dynamic Programming (Longest Increasing Subsequence with Parent
 * Pointer Reconstruction)
 *
 * Key insight: dp[i] is the length of the longest strictly increasing
 * subsequence ending exactly at i, and such a subsequence can only be extended
 * from some j < i with arr[j] < arr[i]; remembering the winning j in hash[]
 * lets the final subsequence be rebuilt by walking that parent chain backwards
 * from the best endpoint.
 *
 * Time Complexity: O(N^2) - The nested loop compares every pair (i, j) with j <
 * i; the parent-chain rebuild and the final reverse are linear
 *
 * Space Complexity: O(N) - Two auxiliary arrays (dp for chain lengths, hash for
 * predecessor indices) scale with the input size
 *
 * Edge Cases Handled: single-element input (returns that element alone),
 * strictly decreasing input (returns only the largest element, since no
 * extension is possible), duplicate values (the strict < comparison keeps the
 * subsequence strictly increasing, so equal elements never extend each other),
 * negative numbers (order-based comparisons only, no arithmetic on values),
 * already-sorted input (the whole array is returned as the subsequence).
 * Note: an empty array is not handled and throws ArrayIndexOutOfBoundsException
 * while reading the parent chain.
 */
class GetLongestIncreasingSubsequence {
    public ArrayList<Integer> getLIS(int arr[]) {
        ArrayList<Integer> res = new ArrayList<>();
        int[] dp = new int[arr.length];
        int[] hash = new int[arr.length];

        Arrays.fill(dp, 1);
        int lastIn = 0;

        for (int i = 1; i < hash.length; i++) {
            hash[i] = i;
            for (int j = 0; j < i; j++) {
                if (arr[j] < arr[i] && dp[i] < dp[j] + 1) {
                    dp[i] = dp[j] + 1;
                    hash[i] = j;
                }
            }

            if (dp[lastIn] < dp[i]) {
                lastIn = i;
            }
        }

        while (hash[lastIn] != lastIn) {
            res.add(arr[lastIn]);
            lastIn = hash[lastIn];
        }

        res.add(arr[lastIn]);
        Collections.reverse(res);

        return res;
    }
}
