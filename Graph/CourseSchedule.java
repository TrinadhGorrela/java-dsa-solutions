/**
 * 207. Course Schedule
 * Difficulty: Medium | Tags: Depth-First Search, Breadth-First Search, Graph
 * Theory, Topological Sort, Directed Acyclic Graph
 * https://leetcode.com/problems/course-schedule/
 *
 * Pattern: DFS Cycle Detection in Directed Graph
 *
 * Key insight: Build the prerequisite graph as a DAG check: if DFS encounters a
 * node currently on the recursion stack (recPath), a back edge exists meaning a
 * cycle is present and scheduling is impossible. Nodes fully explored are
 * marked visited to avoid redundant traversal across disconnected components.
 *
 * Time Complexity: O(V + E) - Each course and prerequisite edge visited once
 * across all DFS calls
 *
 * Space Complexity: O(V + E) - Adjacency list for E edges; visited and recPath
 * arrays of size V; recursion stack up to V deep
 *
 * Edge Cases Handled: no prerequisites (vacuously acyclic, returns true), cycle
 * detection via recPath back-edge (returns false), disconnected prerequisite
 * graph (outer loop starts DFS from each unvisited course), single course with
 * no prerequisites (returns true)
 */
class CourseSchedule {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        boolean[] visited = new boolean[numCourses + 1];
        boolean[] recPath = new boolean[numCourses + 1];

        List<List<Integer>> adj = new ArrayList<>();

        for (int i = 0; i < numCourses; i++) {
            adj.add(new ArrayList<>());
        }

        for (int[] req : prerequisites) {
            adj.get(req[1]).add(req[0]);
        }

        for (int i = 0; i < numCourses; i++) {
            if (!visited[i]) {
                if (dfs(adj, visited, recPath, i)) {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean dfs(List<List<Integer>> adj, boolean[] visited, boolean[] recPath, int curr) {
        visited[curr] = true;
        recPath[curr] = true;

        for (int neighbor : adj.get(curr)) {
            if (!visited[neighbor]) {
                if (dfs(adj, visited, recPath, neighbor)) {
                    return true;
                }
            } else if (recPath[neighbor]) {
                return true;
            }
        }

        recPath[curr] = false;
        return false;
    }
}
