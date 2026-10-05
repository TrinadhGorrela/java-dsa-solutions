/**
 * 18. 4Sum
 * Difficulty: Medium | Tags: Array, Two Pointers, Sorting
 * https://leetcode.com/problems/4sum/
 *
 * Pattern: Sort + Fix-Two + Two-Pointer Pair Search
 *
 * Key insight: Sorting enables a 4-sum decomposition: fix i and j, then
 * two-pointer the remaining range. Using long for the accumulated sum prevents
 * integer overflow when adding large int values near Integer.MAX_VALUE.
 *
 * Time Complexity: O(N^3) - O(N log N) sort, then N × N × O(N) for the
 * two-pointer inner loops
 *
 * Space Complexity: O(N^3) - HashSet storing unique quadruplet lists in the
 * worst case
 *
 * Edge Cases Handled: Fewer than 4 elements, integer overflow (long cast),
 * duplicate quadruplets (HashSet dedup), negative and positive target sums,
 * array with extreme int values
 */
class FourSum {
   public static List<List<Integer>> fourSum(int[] nums, int target) {
		if (nums == null || nums.length < 4)
			return new ArrayList<>();

		Arrays.sort(nums);
		Set<List<Integer>> res = new HashSet<>();
		for (int i = 0; i < nums.length; i++) {
			for (int j = i+1; j < nums.length; j++) {
				int left = j + 1;
				int right = nums.length - 1;

				while (left < right) {
					long sum = (long)nums[i] + nums[j] + nums[left] + nums[right];

					if (sum == target) {
						res.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));
						left++;
						right--;
					} else if (sum < target) {
						left++;
					} else
						right--;
				}
			}
		}

		return new ArrayList<>(res);
	}
}
