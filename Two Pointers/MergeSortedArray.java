/**
 * 88. Merge Sorted Array
 * Difficulty: Easy | Tags: Array, Two Pointers, Sorting
 * https://leetcode.com/problems/merge-sorted-array/
 *
 * Pattern: Reverse-Direction Three-Pointer Merge
 *
 * Key insight: Filling from the tail avoids overwriting nums1 elements that
 * haven't been merged yet; comparing from the larger end means remaining nums2
 * elements can be bulk-copied when nums1 is exhausted.
 *
 * Time Complexity: O(m+n) - Each element examined once; remaining tail copied
 * in O(n) at most
 *
 * Space Complexity: O(1) - Three index variables, merges in-place into nums1's
 * pre-allocated buffer
 *
 * Edge Cases Handled: m = 0 (all elements from nums2), n = 0 (nums1 already
 * correct), nums1 all smaller than nums2 (bulk copy), nums2 all smaller
 * (element-by-element merge), equal values across arrays
 */
class MergeSortedArray {
  public void merge(int[] nums1, int m, int[] nums2, int n) {
    int i = m - 1;
    int j = n - 1;
    int k = m + n - 1;
    while (i >= 0 && j >= 0) {
      if (nums1[i] > nums2[j]) {
        nums1[k--] = nums1[i--];
      } else {
        nums1[k--] = nums2[j--];
      }
    }
    while (j >= 0) {
      nums1[k--] = nums2[j--];
    }
  }
}
