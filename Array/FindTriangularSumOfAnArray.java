/**
 * 2221. Find Triangular Sum of an Array
 * Difficulty: Medium | Tags: Array, Math, Simulation, Combinatorics, Number
 * Theory
 * https://leetcode.com/problems/find-triangular-sum-of-an-array/
 *
 * Pattern: Iterative Reduction (Pascal's Triangle Mod 10)
 *
 * Key insight: Each pass reduces the array by one element by computing (nums[i]
 * + nums[i+1]) % 10 for every adjacent pair. After N-1 passes, a single value
 * remains — equivalent to the apex of a Pascal's triangle weighted by the
 * initial values, modulo 10.
 *
 * Time Complexity: O(N^2) - N-1 reduction passes, each processing a
 * progressively shorter array (summing to N*(N-1)/2 operations)
 *
 * Space Complexity: O(N) - Each pass allocates a temporary array of size N-1-i
 *
 * Edge Cases Handled: single element array (returned immediately), carry
 * handled by modulo 10 at each pairwise sum
 */
class FindTriangularSumOfAnArray {
       public static int triangularSum(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n - 1; i++) {
            int[] reduced = sum(nums);
            for (int t = 0; t < reduced.length; t++) {
                nums[t] = reduced[t];
            }
        }
        return nums[0];
    }

    public static int[] sum(int[] nums) {
        int[] pairSums = new int[nums.length - 1];
        for (int i = 0; i < nums.length - 1; i++) {
            pairSums[i] = (nums[i] + nums[i + 1]) % 10;
        }
        return pairSums;
    }
}
