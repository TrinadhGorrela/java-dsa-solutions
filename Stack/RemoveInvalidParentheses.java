/**
 * 301. Remove Invalid Parentheses
 * Difficulty: Hard | Tags: String, Backtracking, Breadth-First Search
 * https://leetcode.com/problems/remove-invalid-parentheses/
 *
 * Pattern: BFS (Level-Order Deletion States) + Stack Validation
 *
 * Key insight: Every BFS level strips exactly one more character, so the first
 * level that contains a valid string achieves the minimum number of removals --
 * BFS stops there instead of descending deeper. A visited set guarantees each
 * distinct candidate is expanded once.
 *
 * Time Complexity: O(N * 2^N) - Worst case enumerates up to 2^N distinct
 * deletion states, each validated by an O(N) stack scan in isValid()
 *
 * Space Complexity: O(N * 2^N) - The visited set and queue can simultaneously
 * hold exponentially many strings, each up to N characters long
 *
 * Edge Cases Handled: empty input ("" passes isValid immediately and is
 * returned as-is), already-valid string (found at level 0 with zero deletions),
 * non-parenthesis characters (skipped during expansion, never disturb the
 * stack), single-character input like "(" (collapses to the valid empty
 * string), fully unmatched runs like "((((", duplicate candidates deduplicated
 * by the HashSet so each answer appears exactly once, and result is never empty
 * because deleting everything yields ""; no null guard (relies on the caller
 * never passing null)
 */
class RemoveInvalidParentheses {
    public List<String> removeInvalidParentheses(String s) {
        Queue<String> queue = new ArrayDeque<>();
        Set<String> set = new HashSet<>();
        List<String> result = new ArrayList<>();

        queue.add(s);
        set.add(s);

        while (!queue.isEmpty()) {
            int size = queue.size();
            boolean found = false;
            while (size-- != 0) {
                String curr = queue.poll();

                if (isValid(curr)) {
                    result.add(curr);
                    found = true;
                }

                if (found) {
                    continue;
                }

                for (int i = 0; i < curr.length(); i++) {
                    char c = curr.charAt(i);

                    if (c != '(' && c != ')') {
                        continue;
                    }

                    String temp = curr.substring(0, i) + curr.substring(i + 1);

                    if (!set.contains(temp)) {
                        set.add(temp);
                        queue.add(temp);
                    }
                }
            }

            if (found) {
                break;
            }
        }

        return result;
    }

    private boolean isValid(String s) {
        if (s.length() == 0) {
            return true;
        }

        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (c == '(') {
                stack.push(c);
            } else if (c == ')') {
                if (stack.isEmpty()) {
                    return false;
                }

                if (stack.peek() != '(') {
                    return false;
                }

                stack.pop();
            }
        }

        return stack.isEmpty();
    }
}
