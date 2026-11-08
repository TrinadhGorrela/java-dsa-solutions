/**
 * 1209. Remove All Adjacent Duplicates in String II
 * Difficulty: Medium | Tags: String, Stack
 * https://leetcode.com/problems/remove-all-adjacent-duplicates-in-string-ii/
 *
 * Pattern: Stack of Character-Count Pairs
 *
 * Key insight: A removal can only expose the run stored directly beneath the
 * top of the stack, so tracking each run's character and count in a stack
 * collapses duplicates in one pass; incrementing the top count and popping at k
 * reproduces repeated deletion without rescanning the string.
 *
 * Time Complexity: O(N) - Each character is pushed, merged, or popped at most
 * once, and the final rebuild touches every surviving character once.
 *
 * Space Complexity: O(N) - The stack may hold up to N entries (e.g. all
 * distinct characters), plus a StringBuilder of the same order.
 *
 * Edge Cases Handled: k == 1 (every character is removed immediately), empty
 * string, strings with no adjacent duplicates, runs longer than k that leave a
 * remainder, and cascading duplicates created after a pop (merged with the
 * newly exposed stack top).
 */
class RemoveAllAdjacentDuplicatesInStringII {
    public String removeDuplicates(String s, int k) {
        Stack<int[]> stack = new Stack<>();

        for (char c : s.toCharArray()) {
            if (!stack.isEmpty() && stack.peek()[0] == c) {
                stack.peek()[1] += 1;

                if (stack.peek()[1] == k) {
                    stack.pop();
                }

            } else {
                stack.push(new int[] { c, 1 });
            }
        }

        StringBuilder res = new StringBuilder();

        while (!stack.isEmpty()) {
            int[] temp = stack.pop();

            for (int i = 0; i < temp[1]; i++) {
                res.append((char) temp[0]);
            }
        }

        return res.reverse().toString();
    }
}
