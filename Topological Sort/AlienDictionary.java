/**
 * 269. Alien Dictionary
 * Difficulty: Hard | Tags: Array, String, Depth-First Search, Breadth-First
 * Search, Graph Theory, Topological Sort, Directed Acyclic Graph
 * https://leetcode.com/problems/alien-dictionary/
 *
 * Pattern: Topological Sort (DFS with Cycle Detection)
 *
 * Key insight: The alien alphabet is a topological ordering of a character
 * graph built by comparing adjacent words: the first differing character gives
 * a directed edge (earlier -> later). A DFS post-order pushes a node only after
 * all its successors are processed, so reversing the stack yields a valid
 * order, and a back edge found during the same recursion reveals a
 * contradictory (cyclic) ordering.
 *
 * Time Complexity: O(C + V + E) - O(C) scans every character across all words
 * to build edges (C = total input length), plus O(V + E) for the DFS over at
 * most V <= 26 characters and E edges.
 *
 * Space Complexity: O(V + E) - Adjacency list, visited/recursion-path arrays,
 * and the output stack all scale with the character graph (V <= 26).
 *
 * Edge Cases Handled: invalid prefix ordering (a longer word placed before its
 * own prefix returns ""), cyclic/contradictory constraints (returns ""),
 * single-word input, equal adjacent words, disconnected graphs and isolated
 * characters, duplicate letters handled via a set; assumes lowercase 'a'-'z'
 * only.
 */
class AlienDictionary {
    public String alienOrder(String[] words) {
        List<List<Integer>> adjList = new ArrayList<>();
        Set<Character> set = new HashSet<>();

        for (int i = 0; i < 26; i++) {
            adjList.add(new ArrayList<>());
        }

        for (int i = 0; i < words.length; i++) {
            char[] chars = words[i].toCharArray();
            for (char c : chars) {
                set.add(c);
            }
        }

        for (int i = 0; i < words.length - 1; i++) {
            String s1 = words[i];
            String s2 = words[i + 1];
            int len = Math.min(s1.length(), s2.length());
            boolean flag = false;

            for (int j = 0; j < len; j++) {
                if (s1.charAt(j) != s2.charAt(j)) {
                    adjList.get(s1.charAt(j) - 'a').add(s2.charAt(j) - 'a');
                    flag = true;
                    break;
                }
            }

            if (!flag && s1.length() > s2.length()) {
                return "";
            }

        }

        Stack<Integer> stack = new Stack<>();
        boolean[] visited = new boolean[26];
        boolean[] recPath = new boolean[26];

        for (char c : set) {
            if (!visited[c - 'a']) {
                if (topoSort(adjList, stack, visited, recPath, c - 'a')) {
                    return "";
                }
            }
        }

        StringBuilder res = new StringBuilder();

        while (!stack.isEmpty()) {
            res.append((char) (stack.pop() + 'a') + "");
        }

        return res.toString();
    }

    private boolean topoSort(List<List<Integer>> adjList, Stack<Integer> stack, boolean[] visited, boolean[] recPath,
            int curr) {
        visited[curr] = true;
        recPath[curr] = true;

        for (int neighbour : adjList.get(curr)) {
            if (!visited[neighbour]) {
                if (topoSort(adjList, stack, visited, recPath, neighbour)) {
                    return true;
                }
            } else if (recPath[neighbour]) {
                return true;
            }

        }
        stack.push(curr);
        recPath[curr] = false;

        return false;
    }
}
