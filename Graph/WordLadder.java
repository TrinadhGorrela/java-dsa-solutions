/**
 * 127. Word Ladder
 * Difficulty: Hard | Tags: Hash Table, String, Breadth-First Search,
 * Bidirectional Search
 * https://leetcode.com/problems/word-ladder/
 *
 * Pattern: BFS (Unweighted Shortest Path on a Word Graph)
 *
 * Key insight: Model each word as a node and two words as connected if they
 * differ by one letter; run level-order BFS where each level represents one
 * transformation, generating all single-letter variants per position and
 * stopping as soon as endWord is hit; return the level count (beginWord counts
 * as step 1).
 *
 * Time Complexity: O(N * M^2) - For each of N words, tries 26 letters at each
 * of M positions and builds an M-length string per candidate
 *
 * Space Complexity: O(N * M) - Stores the word set, visited set, and BFS queue
 *
 * Edge Cases Handled: endWord not in wordList, startWord to endWord transition
 * directly
 */
class WordLadder {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> words = new HashSet<>(wordList);
        Queue<String> queue = new ArrayDeque<>();
        queue.offer(beginWord);

        Set<String> visited = new HashSet<>();
        visited.add(beginWord);

        int level = 1;

        if (!words.contains(endWord)) {
            return 0;
        }

        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size != 0) {
                String word = queue.poll();
                if (word.equals(endWord)) {
                    return level;
                }

                for (int i = 0; i < word.length(); i++) {
                    char[] chars = word.toCharArray();
                    for (char c = 'a'; c <= 'z'; c++) {
                        chars[i] = c;
                        String nxt = new String(chars);
                        if (words.contains(nxt) && !visited.contains(nxt)) {
                            visited.add(nxt);
                            queue.offer(nxt);
                        }
                    }
                }
                size--;
            }
            level++;
        }
        return 0;
    }
}
