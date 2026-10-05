/**
 * 155. Min Stack
 * Difficulty: Medium | Tags: Stack, Design
 * https://leetcode.com/problems/min-stack/
 *
 * Pattern: Auxiliary Minimum Stack
 *
 * Key insight: Maintain a parallel `min` stack that mirrors only non-increasing
 * minimums: push onto `min` only when the new value ≤ current min, pop from
 * `min` when the main stack's popped element equals the current min. getMin()
 * is O(1) via min.peek().
 *
 * Time Complexity: O(1) - push, pop, top, and getMin are all constant time.
 *
 * Space Complexity: O(n) - Both stacks hold at most n elements in the worst
 * case.
 *
 * Edge Cases Handled: duplicate minimums (each pushed onto min stack), popping
 * the current minimum exposes the previous minimum, single-element stack, push
 * value equal to current minimum
 */
class MinStack {
    Stack<Integer> input;
    Stack<Integer> min;

    public MinStack() {
        this.input = new Stack<>();
        this.min = new Stack<>();
    }

    public void push(int value) {
        input.push(value);
        if (min.isEmpty() || min.peek() >= value) {
            min.push(value);
        }
    }

    public void pop() {
        if (input.peek().equals(min.peek())) {
            min.pop();
        }
        input.pop();
    }

    public int top() {
        return input.peek();
    }

    public int getMin() {
        return min.peek();
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(value);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */
