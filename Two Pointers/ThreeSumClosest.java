/**
 * 16. 3Sum Closest
 * Difficulty: Medium | Tags: Array, Two Pointers, Sorting
 * https://leetcode.com/problems/3sum-closest/
 *
 * Pattern: Sort + Two Pointers (Fixed Pivot, Converging Ends)
 *
 * Key insight: With the array sorted and one element fixed, advancing the left
 * pointer can only increase the sum and retreating the right pointer can only
 * decrease it; that monotonicity makes every candidate pair reachable in one
 * linear sweep, so the closest sum is found without checking all pairs.
 *
 * Time Complexity: O(N^2) - The N pivots each scan O(N) positions; the O(N log
 * N) sort is dominated, though an exact match can return early.
 *
 * Space Complexity: O(log N) - Arrays.sort(int[]) sorts in place using a
 * dual-pivot quicksort recursion stack; no auxiliary collections.
 *
 * Edge Cases Handled: Exact-target early return; negative values,
 * duplicates/all-equal input, and already-sorted arrays; trailing pivots with
 * no valid pair are harmless no-ops; fewer than 3 elements returns the initial
 * 0 because no triple exists (constraints assume at least 3); int-only
 * arithmetic means sums and Math.abs(Integer.MIN_VALUE) overflow are not
 * guarded.
 */
class ThreeSumClosest {
    public int threeSumClosest(int[] nums, int target) {
        int diff = Integer.MAX_VALUE;
        int res = 0;

        Arrays.sort(nums);

        for (int i = 0; i < nums.length; i++) {
            int left = i + 1;
            int right = nums.length - 1;

            while (left < right) {
                int curr = nums[i] + nums[left] + nums[right];
                int temp = Math.abs(target - curr);

                if (curr == target) {
                    return curr;
                }

                if (temp < diff) {
                    diff = temp;
                    res = curr;
                }

                if (curr < target) {
                    left++;
                } else {
                    right--;
                }
            }
        }

        return res;
    }
}
