/**
 * 4. Median of Two Sorted Arrays
 * Difficulty: Hard | Tags: Array, Binary Search, Divide and Conquer
 * https://leetcode.com/problems/median-of-two-sorted-arrays/
 *
 * Pattern: Merge-Then-Pick Median
 * Key insight: Standard merge into a combined sorted array, then return arr[(len-1)/2] for odd length or average of
 * arr[len/2-1] and arr[len/2] for even—correct but O(m+n) vs. the optimal O(log(min(m,n))) binary partition approach.
 *
 * Time Complexity: O(m+n) - Linear merge fills the combined array; not the optimal O(log(min(m,n))) binary-search
 * solution
 * Space Complexity: O(m+n) - Combined result array sized m+n
 *
 * Edge Cases Handled: One or both arrays empty, one array entirely smaller than the other, all elements equal, odd vs.
 * even combined length selecting the correct median index
 */
class MedianOfTwoSortedArrays {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int i = 0, j = 0, k = 0;
        int n1 = nums1.length;
        int n2 = nums2.length;
        int[] res = new int[n1 + n2];
        while (i < n1 && j < n2) {
            if (nums1[i] < nums2[j]) {
                res[k++] = nums1[i++];
            } else {
                res[k++] = nums2[j++];
            }
        }
        while (i < n1) {
            res[k++] = nums1[i++];
        }
        while (j < n2) {
            res[k++] = nums2[j++];
        }
        int total = res.length;
        if (total % 2 != 0) {
            return res[total / 2];
        } else {
            return (double) (res[total / 2 - 1] + res[total / 2]) / 2;
        }
    }
}
