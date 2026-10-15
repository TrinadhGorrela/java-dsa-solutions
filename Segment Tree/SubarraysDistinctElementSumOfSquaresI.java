/**
 * 2913. Subarrays Distinct Element Sum of Squares I
 * Difficulty: Easy | Tags: Array, Hash Table, Binary Indexed Tree, Segment Tree
 * https://leetcode.com/problems/subarrays-distinct-element-sum-of-squares-i/
 *
 * Pattern: Brute Force (Enumerate All Subarrays + HashSet)
 *
 * Key insight: Every subarray is defined by its start and end indices, so
 * enumerating all O(N^2) pairs (i, j) covers them all. Extending the window one
 * element at a time and re-adding nums[j] into a HashSet keeps the distinct
 * count for subarray nums[i..j] in O(1), whose square is accumulated directly.
 *
 * Time Complexity: O(N^2) - The double loop visits every subarray once, and
 * each HashSet insert/size query is O(1) amortized
 *
 * Space Complexity: O(N) - The set holds at most the N distinct values of the
 * current subarray, rebuilt for each starting index i
 *
 * Edge Cases Handled: empty input (outer loop never runs, returns 0), single
 * element, duplicate values (the HashSet deduplicates them), all-same input
 * (every subarray contributes 1). No overflow guard on sum — safe only under
 * the problem's constraints (N ≤ 100, values ≤ 100, max sum 10^6)
 */
class SubarraysDistinctElementSumOfSquaresI {
    public int sumCounts(List<Integer> nums) {
        int sum = 0;

        for (int i = 0; i < nums.size(); i++) {
            Set<Integer> set = new HashSet<>();
            for (int j = i; j < nums.size(); j++) {
                set.add(nums.get(j));
                sum += set.size() * set.size();
            }

        }

        return sum;
    }
}
