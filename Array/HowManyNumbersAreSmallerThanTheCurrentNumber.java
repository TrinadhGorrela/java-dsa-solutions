/**
 * 1365. How Many Numbers Are Smaller Than the Current Number
 * Difficulty: Easy | Tags: Array, Hash Table, Sorting, Counting Sort
 * https://leetcode.com/problems/how-many-numbers-are-smaller-than-the-current-n
 * umber/
 *
 * Pattern: Brute Force Pairwise Comparison
 *
 * Key insight: For each position i, a second pass over the entire array counts
 * elements strictly less than nums[i], skipping self-comparison via the i != j
 * guard.
 *
 * Time Complexity: O(N^2) - N elements each compared against all N-1 other
 * elements
 *
 * Space Complexity: O(N) - Result array of length N stores the count for each
 * position
 *
 * Edge Cases Handled: equal values excluded by strict less-than, single element
 * (result is [0]), all identical values (all counts 0), minimum and maximum
 * elements in the array
 */
class HowManyNumbersAreSmallerThanTheCurrentNumber {
    public int[] smallerNumbersThanCurrent(int[] nums) {
        int[] result = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            int smallerCount = 0;
            for (int j = 0; j < nums.length; j++) {
                if (i != j && nums[i] > nums[j]) {
                    smallerCount++;
                }

            }
            result[i] = smallerCount;
        }

        return result;
    }
}
