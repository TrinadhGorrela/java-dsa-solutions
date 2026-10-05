/**
 * 645. Set Mismatch
 * Difficulty: Easy | Tags: Array, Hash Table, Bit Manipulation, Sorting
 * https://leetcode.com/problems/set-mismatch/
 *
 * Pattern: HashSet Lookup + Sum Formula
 *
 * Key insight: The HashSet catches the duplicate on the second visit; the
 * missing value is recovered algebraically via expectedSum(1..n) - actualSum +
 * duplicate, avoiding a second data structure.
 *
 * Time Complexity: O(N) - Single pass to populate the set and accumulate
 * actualSum, then O(1) arithmetic
 *
 * Space Complexity: O(N) - HashSet stores up to N distinct values for O(1)
 * membership checks
 *
 * Edge Cases Handled: n=1 (array is [1,1], duplicate=1, missing=1), duplicate
 * at first or last position, missing number is n (the largest value)
 */
class SetMismatch {
    public int[] findErrorNums(int[] nums) {
        Set<Integer> set = new HashSet<>();
        int[] result = new int[2];
        int actualSum = 0;
        for (int i : nums) {
            if (set.contains(i))
                result[0] = i;
            set.add(i);
            actualSum = actualSum + i;
        }
        int n = nums.length;
        int expectedSum = n * (n + 1) / 2;

        result[1] = expectedSum - actualSum + result[0];
        return result;
    }
}
