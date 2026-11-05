/**
 * 1944. Number of Visible People in a Queue
 * Difficulty: Hard | Tags: Array, Stack, Monotonic Stack
 * https://leetcode.com/problems/number-of-visible-people-in-a-queue/
 *
 * Pattern: Monotonic Decreasing Stack (Right-to-Left)
 *
 * Key insight: Scanning from the back with a decreasing stack, a person sees
 * every shorter person popped off the stack plus the first person who is at
 * least as tall. That first taller-or-equal person blocks everyone beyond, and
 * since each height is pushed and popped at most once, the total work is
 * linear.
 *
 * Time Complexity: O(N) - Each height is pushed onto and popped from the stack
 * at most once.
 *
 * Space Complexity: O(N) - The monotonic stack and the result array each scale
 * with the number of people.
 *
 * Edge Cases Handled: single-person and empty input (returns a zeroed/empty
 * result), the last person in the queue (sees 0), strictly increasing and
 * strictly decreasing height orders, duplicate heights (strict '<' pop keeps
 * equal-height people as visible blockers).
 */
class NumberOfVisiblePeopleInAQueue {
    public int[] canSeePersonsCount(int[] heights) {
        Stack<Integer> stack = new Stack<>();
        int[] res = new int[heights.length];

        for (int i = heights.length - 1; i >= 0; i--) {
            int count = 0;

            while (!stack.isEmpty() && stack.peek() < heights[i]) {
                count++;
                stack.pop();
            }

            if (!stack.isEmpty()) {
                res[i] = count + 1;
            } else {
                res[i] = count;
            }

            stack.push(heights[i]);
        }

        return res;
    }
}
