/**
 * 3727. Maximum Alternating Sum of Squares
 * Difficulty: Medium | Tags: Array, Greedy, Sorting
 * https://leetcode.com/problems/maximum-alternating-sum-of-squares/
 *
 * Pattern: Greedy + Sorting (Two Pointers)
 *
 * Key insight: Squaring makes every term non-negative, so the maximum
 * alternating sum assigns the largest squares to the positive (+) positions and
 * the smallest to the negative (-) positions; sorting and pairing the two ends
 * (largest minus smallest) implements exactly this greedy exchange.
 *
 * Time Complexity: O(N log N) - Dominated by the sort of the squared array.
 *
 * Space Complexity: O(N) - An auxiliary long[] stores the squares before
 * sorting.
 *
 * Edge Cases Handled: empty array (loop is skipped and 0 is returned), single
 * element (its square is added directly), negative numbers (squared to
 * positive), values up to the integer max (casting to long before squaring
 * prevents overflow), all-equal elements, odd-length arrays (the middle square
 * is added on the diagonal); null input is not guarded and throws a
 * NullPointerException.
 */
class MaximumAlternatingSumOfSquares {
    public long maxAlternatingSum(int[] nums) {
        long[] squared = new long[nums.length];

        for (int i = 0; i < nums.length; i++) {
            squared[i] = (long) nums[i] * nums[i];
        }

        Arrays.sort(squared);

        int left = 0;
        int right = nums.length - 1;
        long sum = 0;

        while (left < right) {
            sum -= squared[left];
            sum += squared[right];
            left++;
            right--;
        }

        if (left == right) {
            sum += squared[left];
        }

        return sum;
    }
}
