/**
 * 2161. Partition Array According to Given Pivot
 * Difficulty: Medium | Tags: Array, Two Pointers, Simulation
 * https://leetcode.com/problems/partition-array-according-to-given-pivot/
 *
 * Pattern: Three-Sweep Stable Partition (Simulation)
 *
 * Key insight: Because each of the three sweeps visits elements in their
 * original order, appending the groups < pivot, == pivot, and > pivot one pass
 * at a time preserves the relative order within every group automatically - no
 * in-place partitioning or sorting is required.
 *
 * Time Complexity: O(N) - Three linear passes over the input array
 *
 * Space Complexity: O(N) - Allocates a result array of the same size as the
 * input
 *
 * Edge Cases Handled: empty array; all elements below, at, or above pivot (a
 * group sweep simply fills everything or nothing); pivot absent from the array
 * (the equality sweep is a no-op); duplicate elements (stability keeps their
 * relative order intact)
 */
class PartitionArrayAccordingToGivenPivot {
    public int[] pivotArray(int[] nums, int pivot) {
        int[] res = new int[nums.length];
        int in = 0;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < pivot) {
                res[in] = nums[i];
                in++;
            }
        }

        for (int i = 0; i < res.length; i++) {
            if (nums[i] == pivot) {
                res[in] = nums[i];
                in++;
            }
        }

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > pivot) {
                res[in] = nums[i];
                in++;
            }
        }

        return res;
    }
}
