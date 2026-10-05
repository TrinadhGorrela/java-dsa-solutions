/**
 * 1051. Height Checker
 * Difficulty: Easy | Tags: Array, Sorting, Counting Sort, Bubble Sort
 * https://leetcode.com/problems/height-checker/
 *
 * Pattern: Clone + Sort + Positional Comparison
 *
 * Key insight: The expected sorted order is the only valid non-decreasing
 * arrangement; cloning and sorting the array, then comparing element-by-element
 * with the original, directly counts misplaced students.
 *
 * Time Complexity: O(N log N) - Arrays.sort on the cloned array dominates the
 * linear comparison pass
 *
 * Space Complexity: O(N) - A full clone of the heights array is created before
 * sorting
 *
 * Edge Cases Handled: already sorted (returns 0), all equal heights (returns
 * 0), fully reversed order (returns N), single element (returns 0)
 */
class HeightChecker {
    public int heightChecker(int[] heights) {
        int res = 0;
        int[] arr = heights.clone();
        Arrays.sort(arr);
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != heights[i]) {
                res++;
            }
        }
        return res;
    }
}
