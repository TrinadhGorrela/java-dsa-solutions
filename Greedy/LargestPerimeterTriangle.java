/**
 * 976. Largest Perimeter Triangle
 * Difficulty: Easy | Tags: Array, Math, Greedy, Sorting, Quicksort, Polygons
 * https://leetcode.com/problems/largest-perimeter-triangle/
 *
 * Pattern: Sort-Descending First-Fit Triple
 *
 * Key insight: After sorting descending, the first consecutive triple
 * satisfying `nums[i-2] + nums[i-1] > nums[i]` is
 * guaranteed to be the maximum perimeter: any non-consecutive valid triple
 * would use a smaller largest side and thus a smaller perimeter.
 *
 * Time Complexity: O(N log N) - Sorting dominates; the triple scan is at most
 * O(N)
 *
 * Space Complexity: O(1) - In-place sort and loop index
 *
 * Edge Cases Handled: fewer than 3 elements (returns 0), degenerate case where
 * a+b == c (not a triangle, skipped), all equal sides (valid triangle, returns
 * 3×side), no valid triple exists (returns 0)
 */
class LargestPerimeterTriangle {
    public int largestPerimeter(int[] nums) {
        Arrays.sort(nums);

        for (int i = nums.length - 1; i >= 2; i--) {
            if (nums[i - 2] + nums[i - 1] > nums[i]) {
                return nums[i - 2] + nums[i - 1] + nums[i];
            }
        }
        return 0;
    }
}
