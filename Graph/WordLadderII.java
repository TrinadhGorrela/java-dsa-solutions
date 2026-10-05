/**
 * 126. Word Ladder II
 * Difficulty: Hard | Tags: Hash Table, String, Backtracking, Breadth-First
 * Search, Bidirectional Search
 * https://leetcode.com/problems/word-ladder-ii/
 *
 * Pattern: BFS Parent-Map + DFS Backtracking
 *
 * Key insight: BFS explores the word graph level-by-level, building a reverse
 * parent map (child -> list of parents) at each level; once endWord is reached,
 * DFS backtracks from endWord to beginWord through the parent map to
 * reconstruct all shortest paths without revisiting shorter-path candidates,
 * since nodes are only added to the map on first encounter per level.
 *
 * Time Complexity: O(N * M * 26) - BFS generates up to 26 single-letter
 * variants for each of M positions across N words per level; DFS reconstructs
 * paths through the parent map
 *
 * Space Complexity: O(N * M) - Parent map stores up to N words each of length
 * M; recursion stack for path reconstruction
 *
 * Edge Cases Handled: endWord not in wordList (empty result), beginWord equals
 * endWord (handled by BFS termination), no valid transformation path exists
 * (BFS exhausts queue without finding endWord)
 */
class WordLadderII {
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        Map<String, List<String>> map = new HashMap<>();
        List<List<String>> res = new ArrayList<>();
        Set<String> words = new HashSet<>(wordList);
        Set<String> visited = new HashSet<>();
        Queue<String> queue = new ArrayDeque<>();
        queue.offer(beginWord);
        visited.add(beginWord);

        if (!words.contains(endWord)) {
            return res;
        }

        boolean found = false;

        while (!queue.isEmpty()) {
            Set<String> levelVisited = new HashSet<>();
            int size = queue.size();
            while (size != 0) {
                String curr = queue.poll();

                for (int i = 0; i < curr.length(); i++) {
                    char[] chars = curr.toCharArray();
                    for (char c = 'a'; c <= 'z'; c++) {
                        chars[i] = c;
                        String temp = new String(chars);

                        if (temp.equals(endWord)) {
                            found = true;
                        }

                        if (words.contains(temp) && !visited.contains(temp)) {
                            if (!levelVisited.contains(temp)) {
                                queue.offer(temp);
                            }

                            if (!map.containsKey(temp)) {
                                map.put(temp, new ArrayList<>());
                            }

                            map.get(temp).add(curr);

                            levelVisited.add(temp);
                        }
                    }
                }
                size--;
            }

            if (found) {
                break;
            }
            
            visited.addAll(levelVisited);
        }

        if (!map.containsKey(endWord)) {
            return res;
        }

        helper(map, res, new ArrayList<>(), beginWord, endWord, endWord);
        return res;
    }

    private static void helper(Map<String, List<String>> map, List<List<String>> res, List<String> list,
            String beginWord, String endWord, String word) {
        if (word.equals(beginWord)) {
            List<String> temp = new ArrayList<>(list);
            Collections.reverse(temp);
            temp.add(endWord);
            res.add(temp);
            return;
        }

        for (String s : map.get(word)) {
            list.add(s);
            helper(map, res, list, beginWord, endWord, s);
            list.remove(list.size() - 1);
        }

    }
}
