/**
 * 1090. Largest Values From Labels
 * Difficulty: Medium | Tags: Array, Hash Table, Greedy, Sorting, Counting
 * https://leetcode.com/problems/largest-values-from-labels/
 *
 * Pattern: Greedy + Max-Heap (Priority Queue with Per-Label Counting)
 *
 * Key insight: Scanning items in descending value is optimal because swapping a
 * higher-value admissible item into any chosen set can only increase the total,
 * so the first numWanted items respecting each label's useLimit form the best
 * selection. The heap maintains that best-first order while the map enforces
 * the per-label cap.
 *
 * Time Complexity: O(N log N) - Each of the N items is inserted into and
 * removed from the binary heap once (log N per operation); hash-map lookups are
 * O(1) on average.
 *
 * Space Complexity: O(N) - The heap holds N [value, label] pairs and the
 * label-count map can hold up to N distinct labels.
 *
 * Edge Cases Handled: Empty arrays, numWanted = 0 or useLimit = 0 (returns 0),
 * more candidates than numWanted (early stop), one label shared by every item
 * (cap enforced), repeated values (tie order is harmless), and fewer admissible
 * items than numWanted.
 */
class LargestValuesFromLabels {
    public int largestValsFromLabels(int[] values, int[] labels, int numWanted, int useLimit) {
        Queue<int[]> queue = new PriorityQueue<>((a, b) -> Integer.compare(b[0], a[0]));
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < labels.length; i++) {
            queue.add(new int[] { values[i], labels[i] });
        }

        int res = 0;

        while (!queue.isEmpty() && numWanted != 0 && useLimit != 0) {
            int type = queue.peek()[1];
            map.putIfAbsent(type, 0);

            if (map.get(type) < useLimit) {
                res += queue.peek()[0];
                numWanted--;
                map.put(type, map.get(type) + 1);
            }

            queue.poll();
        }

        return res;
    }
}
