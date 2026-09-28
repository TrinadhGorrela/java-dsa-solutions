/**
 * 739. Daily Temperatures
 * Difficulty: Medium | Tags: Array, Stack, Monotonic Stack
 * https://leetcode.com/problems/daily-temperatures/
 *
 * Pattern: Monotonic Decreasing Stack (Next Greater Distance)
 * Key insight: Scan right-to-left, maintaining a stack of indices with strictly decreasing temperatures. For each day,
 * pop all temperatures ≤ today; the stack top (if any) is the next warmer day, and the distance is top − i.
 *
 * Time Complexity: O(n) - Each index pushed and popped at most once.
 * Space Complexity: O(n) - Stack and result array each hold up to n entries.
 *
 * Edge Cases Handled: last day always gets 0, strictly decreasing sequence (all zeros), single day (returns [0]), all
 * equal temperatures (all zeros)
 */
class DailyTemperatures {
    public int[] dailyTemperatures(int[] temperatures) {
        Stack<Integer> stack = new Stack<>();
        int[] res = new int[temperatures.length];
        for (int i = temperatures.length - 1; i >= 0; i--) {
            while (!stack.isEmpty() && temperatures[stack.peek()] <= temperatures[i]) {
                stack.pop();
            }

            if (stack.isEmpty()) {
                res[i] = 0;
            } else {
                res[i] = stack.peek() - i;
            }
            stack.push(i);
        }
        return res;
    }
}
