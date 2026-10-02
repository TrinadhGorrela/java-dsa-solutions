/**
 * 128. Longest Consecutive Sequence
 * Difficulty: Medium | Tags: Array, Hash Table, Union-Find
 * https://leetcode.com/problems/longest-consecutive-sequence/
 *
 * Pattern: Hash Set with Run-Start Detection (disjoint-set alternative to Union-Find)
 * Key insight: Only begin counting from a value whose predecessor is absent (num - 1 not in the
 * set), because that value is by definition the leftmost element of its run. Starting a walk from
 * any other value would re-count the same elements, so restricting to run starts visits every
 * distinct value exactly once overall - the nested while loop stays linear, not quadratic.
 *
 * Time Complexity: O(N) expected - Every distinct value is stepped through by the inner while loop
 * at most once, plus one O(1) expected hash lookup per run-start check
 * Space Complexity: O(N) - The HashSet stores every distinct value of the input
 *
 * Edge Cases Handled: empty array (returns 0 via the max = 0 seed); single element (returns 1);
 * duplicates and repeated runs (collapsed by the HashSet, so no element is counted twice);
 * unsorted input (ordering is irrelevant to set membership); negative numbers, 0 and
 * Integer.MAX_VALUE (no array offset or index arithmetic, and the walk terminates on wraparound
 * since Integer.MIN_VALUE is absent). Relies on HashSet lookups being O(1) expected; no
 * Union-Find parent/rank structure is built.
 */
class LongestConsecutiveSequence {
    public int longestConsecutive(int[] nums) {
        Set<Integer> set = new HashSet<>();
        int max = 0;

        for (int i : nums) {
            set.add(i);
        }

        for (int num : set) {
            if (!set.contains(num - 1)) {
                int temp = num;
                int count = 0;
                while (set.contains(temp)) {
                    temp++;
                    count++;
                }
                max = Math.max(max, count);
            }
        }

        return max;
    }
}
