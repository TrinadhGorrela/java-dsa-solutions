/**
 * 898. Bitwise ORs of Subarrays
 * Difficulty: Medium | Tags: Array, Dynamic Programming, Bit Manipulation
 * https://leetcode.com/problems/bitwise-ors-of-subarrays/
 *
 * Pattern: Dynamic Programming over Subarrays with Bitwise OR (Value-Set DP)
 *
 * Key insight: Because a bit, once set in OR, never clears and there are at
 * most 32 bits total, the set of distinct OR-values of all subarrays ending at
 * index i has size <= 32. By keeping a set of OR values from subarrays ending
 * at i-1 and generating curr = {arr[i]} union {x | arr[i] | x}, we can
 * accumulate all distinct OR values across all subarrays in O(NB) total.
 *
 * Time Complexity: O(NB) - For each of the N elements we do bitwise OR and
 * HashSet insertions over at most B distinct OR values per index, where B <=
 * 32.
 *
 * Space Complexity: O(NB) - Uses HashSets (prev, curr, total) to store distinct
 * OR values; the total number of distinct OR entries across all subarrays is at
 * most N*32 in the worst case.
 *
 * Edge Cases Handled: single element arrays; arrays with all identical values
 * (OR remains constant); duplicate OR values produced by different subarrays
 * (deduplicated via HashSets). No explicit null/empty checks (assumes valid
 * array per constraints); negative integers are not specially handled (relies
 * on Java int bitwise OR behavior).
 */
class BitwiseOrsOfSubarrays {
    public int subarrayBitwiseORs(int[] arr) {
        Set<Integer> prev = new HashSet<>();
        Set<Integer> total = new HashSet<>();

        for (int i = 0; i < arr.length; i++) {
            Set<Integer> curr = new HashSet<>();
            curr.add(arr[i]);

            for (int num : prev) {
                curr.add(num | arr[i]);
            }

            prev = curr;
            total.addAll(curr);
        }

        return total.size();
    }
}
