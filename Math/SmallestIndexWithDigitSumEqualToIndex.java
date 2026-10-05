/**
 * 3550. Smallest Index With Digit Sum Equal to Index
 * Difficulty: Easy | Tags: Array, Math
 * https://leetcode.com/problems/smallest-index-with-digit-sum-equal-to-index/
 *
 * Pattern: Linear Scan + Digit Extraction (Modulo/Division)
 *
 * Key insight: The answer is the first index i whose value's digit sum equals
 * i, so a single left-to-right scan suffices — checking larger indices first
 * can never yield a smaller answer.
 *
 * Time Complexity: O(N * D) - One pass over N elements, each digit sum costs
 * O(D) where D <= 7 digits for a 32-bit int, i.e. effectively O(N)
 *
 * Space Complexity: O(1) - Only a handful of scalar locals; no auxiliary data
 * structures
 *
 * Edge Cases Handled: value 0 (getDigitSum(0) correctly returns 0, matching
 * index 0), single-element array, no valid index (returns -1), repeated digit
 * sums across indices (first/smallest wins); intended for non-negative input —
 * negative values produce negative digit sums via % 10, which never match a
 * non-negative index
 */
class SmallestIndexWithDigitSumEqualToIndex {
    public int smallestIndex(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            if (i == getDigitSum(nums[i])) {
                return i;
            }
        }

        return -1;
    }

    private int getDigitSum(int num) {
        int res = 0;
        while (num != 0) {
            int last = num % 10;
            res += last;
            num /= 10;
        }
        return res;
    }
}
