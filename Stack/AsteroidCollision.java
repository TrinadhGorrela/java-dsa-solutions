/**
 * 735. Asteroid Collision
 * Difficulty: Medium | Tags: Array, Stack, Simulation
 * https://leetcode.com/problems/asteroid-collision/
 *
 * Pattern: Stack (Monotonic Collision Simulation)
 *
 * Key insight: Collisions only happen when a left-moving asteroid meets a
 * right-moving one, and only the rightmost right-mover can be hit — so a stack
 * of "survivors so far" is enough. Each incoming asteroid repeatedly pops the
 * smaller right-movers on top; it is destroyed if an equal or larger
 * right-mover remains, otherwise it pushes itself as the new survivor.
 *
 * Time Complexity: O(N) - Amortized: each asteroid is pushed once and popped at
 * most once, so the total work across all collisions is linear
 *
 * Space Complexity: O(N) - The stack can hold up to N survivors (e.g. all
 * left-movers or no collisions at all)
 *
 * Edge Cases Handled: empty input (empty result), no collisions (all moving the
 * same direction), equal-mass collision annihilating both, a left-mover with an
 * empty stack (pushed directly), one left-mover destroying a chain of several
 * right-movers, multiple consecutive left-movers; result rebuilt in original
 * order. Uses i * -1 for magnitude — safe under LeetCode's constraints but
 * would overflow for Integer.MIN_VALUE
 */
class AsteroidCollision {
    public int[] asteroidCollision(int[] asteroids) {
        Stack<Integer> stack = new Stack<>();

        for (int i : asteroids) {
            if (i < 0 && !stack.isEmpty() && stack.peek() > 0) {
                int temp = i * -1;
                boolean survived = true;

                while (!stack.isEmpty() && stack.peek() < temp && stack.peek() > 0) {
                    stack.pop();
                }

                if (!stack.isEmpty() && temp == stack.peek()) {
                    stack.pop();
                    survived = false;
                } else if (!stack.isEmpty() && stack.peek() > temp) {
                    survived = false;
                }

                if (survived) {
                    stack.push(i);
                }

            } else {
                stack.push(i);
            }
        }

        int[] res = new int[stack.size()];

        for (int i = stack.size() - 1; i >= 0; i--) {
            res[i] = stack.pop();
        }

        return res;
    }
}
