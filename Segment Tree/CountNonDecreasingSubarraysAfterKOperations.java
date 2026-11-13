/**
 * 3420. Count Non-Decreasing Subarrays After K Operations
 * Difficulty: Hard | Tags: Array, Stack, Segment Tree, Queue, Sliding Window,
 * Monotonic Stack, Monotonic Queue
 * https://leetcode.com/problems/count-non-decreasing-subarrays-after-k-operatio
 * ns/
 *
 * Pattern: Sliding Window (Variable Length) + Monotonic Stack
 *
 * Key insight: For a fixed left endpoint, the cost to make [left, right]
 * non-decreasing is the sum of (running prefix maximum - element), because only
 * increments are allowed. Adding a new left value raises only the earlier peaks
 * smaller than it, and a monotonic stack keeps those peak segments so each
 * delta is applied once, while the right pointer shrinks whenever the
 * accumulated cost exceeds k.
 *
 * Time Complexity: O(N) - Each index is pushed and popped from the stack at
 * most once, and the right pointer only moves left, so the total work across
 * both pointers is linear.
 *
 * Space Complexity: O(N) - The monotonic stack can hold up to one index per
 * element in the worst case.
 *
 * Edge Cases Handled: Single element (always cost-free and counted once); k = 0
 * (only already non-decreasing subarrays counted); strictly increasing input
 * (all subarrays valid, cost stays 0); strictly decreasing input (peaks
 * repeatedly raised); large subarray counts avoided via long for total and
 * result; empty input returns 0; assumes non-negative k.
 */
class CountNonDecreasingSubarraysAfterKOperations {
    public long countNonDecreasingSubarrays(int[] nums, int k) {
        Deque<Integer> stack = new ArrayDeque<>();
        int right = nums.length - 1;
        long total = 0;
        long res = 0;

        for (int left = nums.length - 1; left >= 0; left--) {
            while (!stack.isEmpty() && nums[stack.peek()] < nums[left]) {
                int curr = stack.poll();
                int nxt = stack.isEmpty() ? right + 1 : stack.peek();

                total += ((long) nums[left] - nums[curr]) * ((long) nxt - curr);
            }

            stack.addFirst(left);

            while (total > k) {
                total -= nums[stack.getLast()] - nums[right];
                right--;

                if (stack.getLast() > right) {
                    stack.removeLast();
                }
            }

            res += right - left + 1;
        }

        return res;
    }
}
