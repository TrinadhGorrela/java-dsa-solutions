/**
 * 703. Kth Largest Element in a Stream
 * Difficulty: Easy | Tags: Tree, Design, Binary Search Tree, Heap (Priority
 * Queue), Binary Tree, Data Stream
 * https://leetcode.com/problems/kth-largest-element-in-a-stream/
 *
 * Pattern: Min-Heap of Size K (Top-K, Persistent)
 *
 * Key insight: Maintain a min-heap of exactly K elements at all times. On each
 * add(), the new value is inserted and the smallest element is ejected if size
 * exceeds K, so the heap root always equals the Kth largest seen so far. This
 * is exact because ejecting a value smaller than K existing values cannot
 * affect the Kth rank.
 *
 * Time Complexity: O(M log K) total across constructor and M add() calls - Each
 * of M insertions costs O(log K); the constructor processes the initial N
 * elements in O(N log K) time.
 *
 * Space Complexity: O(N + K) - The heap holds up to K elements; the initial
 * array is also processed but not retained.
 *
 * Edge Cases Handled: k equals 1 (heap root is the overall largest); initial
 * array smaller than k (all elements kept, the smallest is the Kth largest);
 * all identical values; add() called with very large or very small values.
 */
class KthLargest {
    PriorityQueue<Integer> queue = new PriorityQueue<>();
    int k = 0;

    public KthLargest(int k, int[] nums) {
        this.k = k;

        for (int i = 0; i < nums.length; i++) {
            queue.offer(nums[i]);
            if (queue.size() > k) {
                queue.poll();
            }
        }
    }

    public int add(int val) {
        queue.offer(val);

        if (queue.size() > k) {
            queue.poll();
        }

        return queue.peek();
    }
}

/**
 * Your KthLargest object will be instantiated and called as such:
 * KthLargest obj = new KthLargest(k, nums);
 * int param_1 = obj.add(val);
 */
