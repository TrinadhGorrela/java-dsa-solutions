/**
 * 503. Next Greater Element II
 * Difficulty: Medium | Tags: Array, Stack, Monotonic Stack
 * https://leetcode.com/problems/next-greater-element-ii/
 *
 * Pattern: Monotonic Decreasing Stack (Circular Array)
 * Key insight: Iterate right-to-left over a conceptual doubled array (indices 0..2n−1 mod n); maintain a decreasing
 * stack of values. For each position, pop everything ≤ nums[i%n]; the stack top is the next greater element. Writing
 * i%n handles the circular wrap naturally.
 *
 * Time Complexity: O(n) - Each element pushed and popped at most once across the 2n scan.
 * Space Complexity: O(n) - Stack and result array each hold up to n entries.
 *
 * Edge Cases Handled: single element (result −1), all equal values (all −1), maximum value at every position (all −1),
 * wrap-around where next greater is at an earlier index
 */
class NextGreaterElementII {
    public int[] nextGreaterElements(int[] nums) {
        Stack<Integer> stack = new Stack<>();
        int[] res = new int[nums.length];
        int n = nums.length;
        for (int i = 2 * n - 1; i > 0; i--) {
            while (!stack.isEmpty() && stack.peek() <= nums[i % n]) {
                stack.pop();
            }

            if (stack.isEmpty()) {
                res[i % n] = -1;
            } else {
                res[i % n] = stack.peek();
            }
            stack.push(nums[i % n]);
        }
        return res;
    }
}
