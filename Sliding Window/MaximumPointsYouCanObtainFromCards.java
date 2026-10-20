/**
 * 1423. Maximum Points You Can Obtain from Cards
 * Difficulty: Medium | Tags: Array, Sliding Window, Prefix Sum
 * https://leetcode.com/problems/maximum-points-you-can-obtain-from-cards/
 *
 * Pattern: Sliding Window (Fixed Length) — sliding the complement window over
 * the middle
 *
 * Key insight: Choosing k cards from the two ends is exactly equivalent to
 * excluding one contiguous block of n−k middle cards. Start with the sum of the
 * first k cards, then slide that k-sized "excluded" block one step right at a
 * time — each shift swaps one card out of the prefix sum and one in from the
 * suffix — so all k+1 valid end-split combinations are evaluated with no
 * recomputation.
 *
 * Time Complexity: O(k) — one O(k) pass to seed the initial sum plus k in-place
 * window shifts
 *
 * Space Complexity: O(1) — only primitive accumulators (leftSum, maxScore); no
 * auxiliary structures
 *
 * Edge Cases Handled: k = 0 (returns 0), k equals the full array length (every
 * shift subtracts and re-adds the same element, preserving the total sum),
 * single-element array with k = 1, all-same card values; relies on the
 * constraint 0 ≤ k ≤ cardPoints.length (k > length would index out of bounds)
 */
class MaximumPointsYouCanObtainFromCards {
    public int maxScore(int[] cardPoints, int k) {
        int n = cardPoints.length - 1;
        int leftSum = 0;

        for (int i = 0; i < k; i++) {
            leftSum += cardPoints[i];
        }

        int maxScore = leftSum;

        for (int i = 0; i < k; i++) {
            leftSum = leftSum - cardPoints[k - 1 - i] + cardPoints[n - i];
            maxScore = Math.max(maxScore, leftSum);
        }

        return maxScore;
    }
}
