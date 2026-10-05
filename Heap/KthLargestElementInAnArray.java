/**
 * 215. Kth Largest Element in an Array
 * Difficulty: Medium | Tags: Array, Divide and Conquer, Sorting, Heap (Priority
 * Queue), Quickselect
 * https://leetcode.com/problems/kth-largest-element-in-an-array/
 *
 * Pattern: Min-Heap of Size K (Top-K)
 *
 * Key insight: Maintain a min-heap that holds exactly K elements. As each
 * element is inserted, if the heap grows larger than K the smallest element is
 * ejected; once all elements are processed, the heap root is the Kth largest.
 * This works because ejecting smaller elements guarantees only K largest
 * survive.
 *
 * Time Complexity: O(N log K) - Each of N elements does at most one push and
 * one poll, each taking O(log K) heap operations.
 *
 * Space Complexity: O(K) - The heap stores at most K elements at any time.
 *
 * Edge Cases Handled: k equals array length (heap grows to N; root is the
 * overall minimum); k equals 1 (returns the largest element); array has all
 * equal elements; single element array.
 */
class KthLargestElementInAnArray {
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> queue = new PriorityQueue<>();

        for (int i = 0; i < nums.length; i++) {
            queue.offer(nums[i]);

            if (queue.size() > k) {
                queue.poll();
            }
        }
        return queue.peek();
    }
}
