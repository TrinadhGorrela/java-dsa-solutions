/**
 * 841. Keys and Rooms
 * Difficulty: Medium | Tags: Depth-First Search, Breadth-First Search, Graph
 * Theory
 * https://leetcode.com/problems/keys-and-rooms/
 *
 * Pattern: BFS Reachability from Source Node
 *
 * Key insight: Model rooms as nodes and keys as directed edges; BFS from room 0
 * explores the reachable subgraph. If the visited set covers all n rooms, every
 * room is reachable. Keys in already-visited rooms are redundant and skipped.
 *
 * Time Complexity: O(N + E) - N rooms visited at most once; E is total number
 * of keys across all rooms
 *
 * Space Complexity: O(N) - Visited boolean array and BFS queue each hold at
 * most N entries
 *
 * Edge Cases Handled: single room (room 0 opens itself, returns true), room
 * with no keys (dead end, no neighbors enqueued), unreachable room in a
 * disconnected component (returns false)
 */
class KeysAndRooms {
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        Queue<Integer> queue = new ArrayDeque<>();
        boolean[] visited = new boolean[rooms.size()];

        queue.offer(0);
        visited[0] = true;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size != 0) {
                int curr = queue.poll();

                for (int neighbor : rooms.get(curr)) {
                    if (!visited[neighbor]) {
                        visited[neighbor] = true;
                        queue.offer(neighbor);
                    }
                }
                size--;
            }
        }

        for (int i = 0; i < visited.length; i++) {
            if (!visited[i]) {
                return false;

            }
        }
        return true;
    }
}
