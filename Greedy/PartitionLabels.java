/**
 * 763. Partition Labels
 * Difficulty: Medium | Tags: Hash Table, Two Pointers, String, Greedy
 * https://leetcode.com/problems/partition-labels/
 *
 * Pattern: Last-Index Greedy Partitioning
 *
 * Key insight: Each character's last occurrence defines the minimum right
 * boundary of any partition containing it; by greedily extending the current
 * partition's end to the maximum last-index seen, we guarantee all characters
 * in the partition appear nowhere outside it.
 *
 * Time Complexity: O(N) - Two linear passes: one to build last-index map, one
 * to scan and partition
 *
 * Space Complexity: O(1) - Fixed 26-entry HashMap for lowercase letters
 *
 * Edge Cases Handled: single character (one partition of length 1), all
 * identical characters (entire string is one partition), string with all unique
 * characters (each character gets its own partition), repeated characters
 * spanning the whole string
 */
class PartitionLabels {
    public List<Integer> partitionLabels(String s) {
        Map<Character, Integer> map = new HashMap<>();
        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < s.length(); i++) {
            map.put(s.charAt(i), i);
        }

        int start = 0;
        int end = 0;

        for (int i = 0; i < s.length(); i++) {
            end = Math.max(end, map.get(s.charAt(i)));

            if (end == i) {
                list.add(end - start + 1);
                start = i + 1;
            }
        }
        return list;
    }
}
