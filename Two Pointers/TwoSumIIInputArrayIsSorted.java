/**
 * 167. Two Sum II - Input Array Is Sorted
 * Difficulty: Medium | Tags: Array, Two Pointers, Binary Search
 * https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/
 *
 * Pattern: Sorted Two-Pointer Convergence
 *
 * Key insight: Sorted array means incrementing left increases sum and
 * decrementing right decreases it—each comparison eliminates one element,
 * guaranteeing a single-pass O(N) search.
 *
 * Time Complexity: O(N) - Each pointer moves at most N times total; no element
 * is revisited
 *
 * Space Complexity: O(1) - Two index variables plus one sum variable, no
 * allocations
 *
 * Edge Cases Handled: Pair spanning the full array length, duplicate values at
 * adjacent indices, target requiring the very last comparison, guaranteed
 * exactly one solution per problem contract
 */
class TwoSumIIInputArrayIsSorted {
    public static int[] twoSum(int[] numbers, int target) {
        int[] res = new int[2];
        int left = 0;
        int right = numbers.length - 1;
        while (left < right) {
            int sum = numbers[left] + numbers[right];
            if (sum == target) {
                res[0] = left + 1;
                res[1] = right + 1;
                return res;
            } else if (sum < target)
                left++;
            else
                right--;
        }
        return res;
    }
}
