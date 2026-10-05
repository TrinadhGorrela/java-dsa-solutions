/**
 * 682. Baseball Game
 * Difficulty: Easy | Tags: Array, Stack, Simulation
 * https://leetcode.com/problems/baseball-game/
 *
 * Pattern: Stack-Based Score Simulation
 *
 * Key insight: Maintain a stack of valid round scores; "C" pops the last valid
 * score, "D" pushes 2× the top, "+" pushes the sum of the top two (without
 * removing them), and integers push directly. Return the stack sum at the end.
 *
 * Time Complexity: O(n) - Each operation is O(1) (push/pop); final sum is O(k)
 * where k is stack size.
 *
 * Space Complexity: O(n) - Stack holds up to n valid scores.
 *
 * Edge Cases Handled: negative integer scores, consecutive "C" operations
 * emptying the stack, "+" when only two valid scores exist, integer parsing of
 * multi-digit negatives
 */
class BaseballGame {
    public int calPoints(String[] operations) {
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < operations.length; i++) {
            if (operations[i].equals("C")) {
                stack.pop();
            } else if (operations[i].equals("+")) {
                int temp1 = stack.pop();
                int temp2 = stack.pop();
                stack.add(temp2);
                stack.add(temp1);
                stack.add(temp1 + temp2);
            } else if (operations[i].equals("D")) {
                int temp = stack.peek();
                stack.add(2 * temp);
            } else {
                stack.add(Integer.parseInt(operations[i]));
            }
        }

        int res = 0;
        
        while (!stack.isEmpty()) {
            res += stack.pop();
        }

        return res;
    }
}
