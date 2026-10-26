/**
 * 898. Bitwise ORs of Subarrays
 * Difficulty: Medium | Tags: Array, Dynamic Programming, Bit Manipulation
 * https://leetcode.com/problems/bitwise-ors-of-subarrays/
 *
 * Pattern: Dynamic Programming with Set Optimization
 * 
 * Key insight: For each element `arr[i]`, the set of bitwise ORs ending at `arr[i]` can be efficiently derived by OR-ing `arr[i]` with all unique bitwise ORs ending at `arr[i-1]`. The critical observation is that the number of unique bitwise ORs ending at any given index is at most 32 (for 32-bit integers), as each bit can only flip from 0 to 1 once. This dramatically limits the size of the set maintained.
 * 
 * Time Complexity: O(N) - The outer loop runs N times. The inner loop iterates over a set whose maximum size is approximately 32 (number of bits in an integer). Set operations (add, addAll) on HashSets take average O(1) time. Thus, the overall complexity is O(N).
 * 
 * Space Complexity: O(N) - In the worst case, the `total` set can store up to N distinct OR-sums. The `prev` and `curr` sets are O(1) as their size is bounded by the number of bits (at most 32 unique values). Thus, the overall space complexity is dominated by `total`, leading to O(N).
 * 
 * Edge Cases Handled: Empty input array (returns 0); single element array; arrays with all elements identical; arrays with large integer values (up to Integer.MAX_VALUE); all elements zero (returns 1 for {0}).
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
