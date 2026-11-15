/**
 * 1524. Number of Sub-arrays With Odd Sum
 * Difficulty: Medium | Tags: Array, Math, Dynamic Programming, Prefix Sum
 * https://leetcode.com/problems/number-of-sub-arrays-with-odd-sum/
 *
 * Pattern: Prefix Sum (Parity Counting)
 *
 * Key insight: Since sum(i..j) = prefix[j] - prefix[i], a subarray has odd sum
 * exactly when its two prefix sums have opposite parity; so each new prefix
 * pairs with all earlier opposite-parity prefixes, and evenCount starts at 1 to
 * count the empty prefix.
 *
 * Time Complexity: O(N) - Single pass, with O(1) parity work per element.
 *
 * Space Complexity: O(1) - Only the running prefix sum and two parity counters
 * are kept.
 *
 * Edge Cases Handled: empty array (loop skipped, returns 0), single element
 * (odd gives 1, even gives 0), all-even and all-odd arrays, negative values
 * (Java's nonzero % 2 correctly takes the odd branch); running sum is int, so
 * it overflows if the total exceeds int range.
 */
class NumberOfSubArraysWithOddSum {
    public int numOfSubarrays(int[] arr) {
        int mod = 1_000_000_007;
        int evenCount = 1;
        int oddCount = 0;
        int total = 0;
        int res = 0;

        for (int i = 0; i < arr.length; i++) {
            total += arr[i];

            if (total % 2 == 0) {
                res = (res + oddCount) % mod;
                evenCount++;
            } else {
                res = (res + evenCount) % mod;
                oddCount++;
            }
        }

        return res;
    }
}
