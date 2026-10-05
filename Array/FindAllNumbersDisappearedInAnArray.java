/**
 * 448. Find All Numbers Disappeared in an Array
 * Difficulty: Easy | Tags: Array, Hash Table
 * https://leetcode.com/problems/find-all-numbers-disappeared-in-an-array/
 *
 * Pattern: In-place Negation Marking
 *
 * Key insight: Use the array as its own hash set: for each value v, negate
 * nums[|v|-1] to mark that the number |v| has been seen; after the pass, any
 * unmarked (positive) index i indicates that i+1 is missing.
 *
 * Time Complexity: O(N) - Two linear passes: one to mark by negation, one to
 * collect positive indices
 *
 * Space Complexity: O(1) - All marking is done in-place on the input array;
 * only the output list uses extra space
 *
 * Edge Cases Handled: no missing numbers (all indices negated), duplicates
 * (absolute value ensures re-negation is skipped), already-negative values from
 * prior marks (Math.abs prevents double-negation)
 */
class FindAllNumbersDisappearedInAnArray {
    public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> missingNumbers = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            int index = Math.abs(nums[i]) - 1;
            if (nums[index] > 0) {
                nums[index] = -nums[index];
            }
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0)
                missingNumbers.add(i + 1);
        }
        return missingNumbers;
    }
}
