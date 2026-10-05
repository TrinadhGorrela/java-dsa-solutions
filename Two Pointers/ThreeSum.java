/**
 * 15. 3Sum
 * Difficulty: Medium | Tags: Array, Two Pointers, Sorting
 * https://leetcode.com/problems/3sum/
 *
 * Pattern: Sort + Fix-One + Two-Pointer Pair Search
 *
 * Key insight: Sorting lets us binary-reason about the remaining pair: for a
 * fixed nums[i], if the pair sum is too negative we advance left, too positive
 * we retreat right—O(N) per i. A HashSet of Lists handles duplicate triplets
 * without skipping logic.
 *
 * Time Complexity: O(N^2) - O(N log N) sort dominated by N iterations × O(N)
 * two-pointer scan each
 *
 * Space Complexity: O(N^2) - Worst case all triplets are unique; HashSet stores
 * each triplet list
 *
 * Edge Cases Handled: Input shorter than 3 elements, all zeros, target
 * reachable only by duplicate values, array with positive and negative mix
 * requiring both pointer directions
 */
class ThreeSum {
    public List<List<Integer>> threeSum(int[] nums) {
        if (nums == null || nums.length < 3)
            return new ArrayList<>();
        Set<List<Integer>> res = new HashSet<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            int left = i + 1;
            int right = nums.length - 1;

            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];

                if (sum == 0) {
                    res.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    left++;
                    right--;
                } else if (sum < 0) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        return new ArrayList<>(res);
    }
}
