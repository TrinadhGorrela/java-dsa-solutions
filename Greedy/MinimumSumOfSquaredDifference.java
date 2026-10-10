/**
 * 2333. Minimum Sum of Squared Difference
 * Difficulty: Medium | Tags: Array, Binary Search, Greedy, Sorting, Heap
 * (Priority Queue)
 * https://leetcode.com/problems/minimum-sum-of-squared-difference/
 *
 * Pattern: Greedy + Frequency Counting (Bucket Reduction)
 *
 * Key insight: Increasing a difference d by one unit raises its square by 2d +
 * 1, so the sum shrinks fastest by always attacking the largest differences
 * first. Because the differences are bounded and bucketed by value, cascading
 * surplus downward applies all k reductions in one reverse sweep with no
 * sorting.
 *
 * Time Complexity: O(N + maxDiff) - One pass builds the frequency buckets, then
 * a single reverse scan over the fixed 100001-length difference array (maxDiff
 * = 100000).
 *
 * Space Complexity: O(maxDiff) - A fixed 100001-length counting array indexed
 * by absolute difference; independent of N and of k.
 *
 * Edge Cases Handled: empty input (returns 0), k1 = k2 = 0 (no reduction), k
 * larger than the total available difference (surplus safely dropped at bucket
 * 0 rather than underflowing the index), negative values via Math.abs,
 * all-equal or already-identical arrays (all-zero differences), and
 * overflow-prone products (i * i * count accumulated in long). Not safe for
 * null array references.
 */
class MinimumSumOfSquaredDifference {
    public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {
        int k = k1 + k2;
        int[] count = new int[100001];

        for (int i = 0; i < nums1.length; i++) {
            count[Math.abs(nums1[i] - nums2[i])]++;
        }

        for (int i = count.length - 1; i > 0; i--) {
            if (count[i] != 0) {
                if (k > count[i]) {
                    int temp = count[i];
                    count[i] = 0;
                    count[i - 1] += temp;
                    k -= temp;
                } else {
                    count[i] -= k;
                    count[i - 1] += k;
                    break;
                }
            }
        }

        long sum = 0;

        for (int i = 0; i < count.length; i++) {
            if (count[i] != 0) {
                long temp = (long) i * i;
                temp *= count[i];
                sum += temp;
            }
        }

        return sum;
    }
}
