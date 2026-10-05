/**
 * 347. Top K Frequent Elements
 * Difficulty: Medium | Tags: Array, Hash Table, Divide and Conquer, Sorting,
 * Heap (Priority Queue), Bucket Sort, Counting, Quickselect
 * https://leetcode.com/problems/top-k-frequent-elements/
 *
 * Pattern: Frequency Map + Descending Sort
 *
 * Key insight: Counting with a HashMap reduces the problem to "find top k by
 * value" — a full sort of the distinct entries is simpler than a heap and
 * acceptable since distinct count <= N.
 *
 * Time Complexity: O(N log N) - O(N) to build frequency map, O(D log D) to sort
 * D distinct entries (D <= N)
 *
 * Space Complexity: O(N) - HashMap + ArrayList of D entries
 *
 * Edge Cases Handled: k equals distinct count (returns all), all elements
 * identical (single entry, k=1), ties in frequency (any valid top-k order), k =
 * 1
 */
class TopKFrequentElements {
    public int[] topKFrequent(int[] nums, int k) {
        int[] res = new int[k];
        Map<Integer, Integer> freq = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            freq.put(nums[i], freq.getOrDefault(nums[i], 0) + 1);
        }

        List<Map.Entry<Integer, Integer>> list = new ArrayList<>(freq.entrySet());
        Collections.sort(list, (a, b) -> Integer.compare(b.getValue(), a.getValue()));
        for (int i = 0; i < k; i++) {
            res[i] = list.get(i).getKey();
        }
        return res;
    }
}
