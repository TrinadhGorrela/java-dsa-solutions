/**
 * 1096. Brace Expansion II
 * Difficulty: Hard | Tags: Hash Table, String, Backtracking, Stack,
 * Breadth-First Search, Sorting
 * https://leetcode.com/problems/brace-expansion-ii/
 *
 * Pattern: Stack-Based Brace Reduction + Set Cartesian Product
 *
 * Key insight: Any sub-expression stands for a SET of strings, in which
 * juxtaposition means a cartesian product of the two operand sets and a comma
 * means their union. Pushing a singleton set per character and folding the
 * stack the instant a closing brace arrives means each innermost group is fully
 * expanded before it feeds its parent, so arbitrarily deep nesting collapses in
 * one left-to-right pass with no recursion.
 *
 * Time Complexity: O(N + R * L * log R) - The scan is O(N), but the work is
 * dominated by materializing R expanded strings of max length L plus the final
 * lexicographic sort (R log R comparisons at O(L) each); the exponential
 * blow-up of nested groups shows up here as R, not as extra passes.
 *
 * Space Complexity: O(N + R * L) - The stack holds at most O(N) sets of
 * partially expanded strings, and the result list holds R strings of length at
 * most L.
 *
 * Edge Cases Handled: plain literal with no braces ("abc" -> ["abc"]);
 * multi-level nesting ({{a,b},{c,d}}); duplicate alternatives collapsed by the
 * HashSet; empty group "{}" degenerating to the empty string; multiple
 * top-level groups such as "a{b,c}d{e,f}" separated only by product; required
 * lexicographic output guaranteed by the final Collections.sort. Relies on the
 * well-formed-expression guarantee - unbalanced braces are not validated, and
 * comma-separated empty alternatives ("{a,}") would produce a spurious empty
 * string.
 */
class BraceExpansionII {
    public List<String> braceExpansionII(String expression) {
        List<String> res = new ArrayList<>();
        Stack<Set<String>> stack = new Stack<>();

        for (char c : expression.toCharArray()) {
            if (c == '}') {
                List<Set<String>> list = new ArrayList<>();

                while (!stack.peek().contains("{")) {
                    list.add(stack.pop());
                }

                stack.pop();
                stack.push(helper(list));
            } else {
                Set<String> temp = new HashSet<>();
                temp.add(c + "");
                stack.push(temp);
            }
        }

        List<Set<String>> list = new ArrayList<>();
        while (!stack.isEmpty()) {
            list.add(stack.pop());
        }

        res.addAll(helper(list));
        Collections.sort(res);
        return res;
    }

    private Set<String> helper(List<Set<String>> list) {
        Set<String> res = new HashSet<>();
        Set<String> curr = new HashSet<>();
        curr.add("");

        Collections.reverse(list);

        for (int i = 0; i < list.size(); i++) {

            if (list.get(i).contains(",")) {
                res.addAll(curr);
                curr.clear();
                curr.add("");
            } else {
                Set<String> nxtCurr = new HashSet<>();
                for (String s : list.get(i)) {
                    for (String t : curr) {
                        String temp = t + s;
                        nxtCurr.add(temp);
                    }
                }

                curr.clear();
                curr.addAll(nxtCurr);
            }
        }
        res.addAll(curr);

        return res;
    }
}
