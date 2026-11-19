/**
 * 2567. Minimum Score by Changing Two Elements
 * Difficulty: Medium | Tags: Array, Greedy, Sorting
 * https://leetcode.com/problems/minimum-score-by-changing-two-elements/
 *
 * Pattern: Sorting + Greedy (Three Fixed Candidate Removals)
 *
 * Key insight: Changing an interior value can never shrink the max-min range,
 * so only the extremes matter. With at most two changes there are exactly three
 * optimal configurations - replace the two smallest, the smallest and largest,
 * or the two largest - and sorting reduces the search to those three ranges.
 *
 * Time Complexity: O(N log N) - Arrays.sort dominates; the three candidate
 * computations are O(1)
 *
 * Space Complexity: O(log N) - Java's dual-pivot quicksort for int[] uses O(log
 * N) recursion stack and no auxiliary collection
 *
 * Edge Cases Handled: All-equal input (score 0), duplicates, already-minimal
 * range (no change improves it); assumes n >= 3 so indices 0, 1, 2 and n-3 are
 * valid (no explicit guard) and values stay in constraint range, since int
 * subtraction could overflow for arbitrary extremes
 */
class MinimumScoreByChangingTwoElements {
    public int minimizeSum(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);

        int temp1 = nums[n - 1] - nums[2];
        int temp2 = nums[n - 2] - nums[1];
        int temp3 = nums[n - 3] - nums[0];

        return Math.min(temp1, Math.min(temp2, temp3));
    }
}
