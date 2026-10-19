/**
 * 2089. Find Target Indices After Sorting Array
 * Difficulty: Easy | Tags: Array, Binary Search, Sorting
 * https://leetcode.com/problems/find-target-indices-after-sorting-array/
 *
 * Pattern: Counting / Sort-Free Index Arithmetic (Two-Pass Frequency Count)
 *
 * Key insight: After sorting, every element < target lands before all target
 * copies, so the targets occupy the contiguous index range [small, small +
 * count - 1] - one linear scan for `small` and `count` replaces the sort
 * entirely.
 *
 * Time Complexity: O(N) - One scan to count elements below/equal to target,
 * plus O(count) <= O(N) to emit the answer
 *
 * Space Complexity: O(1) auxiliary - Only two counters besides the output list,
 * which holds `count` indices
 *
 * Edge Cases Handled: target absent from nums (returns empty list), all
 * elements smaller or all larger than target (range starts at 0 or never
 * starts), duplicate targets (contiguous run emitted), single-element and empty
 * input, negative numbers in nums
 */
class FindTargetIndicesAfterSortingArray {
    public List<Integer> targetIndices(int[] nums, int target) {
        List<Integer> res = new ArrayList<>();
        int small = 0;
        int count = 0;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < target) {
                small++;
            }

            if (nums[i] == target) {
                count++;
            }
        }

        for (int i = 0; i < count; i++) {
            res.add(small + i);
        }

        return res;
    }
}
