/**
 * 210. Course Schedule II
 * Difficulty: Medium | Tags: Depth-First Search, Breadth-First Search, Graph Theory, Topological Sort
 * https://leetcode.com/problems/course-schedule-ii/
 *
 * Pattern: DFS Topological Sort with Cycle Detection
 * Key insight: DFS pushes each node onto a stack after all its descendants are fully explored (post-order). If a back
 * edge (node on current recursion path) is detected, a cycle exists and an empty array is returned. Popping the stack
 * reverses the post-order into a valid topological sequence where every prerequisite appears before its dependent
 * course.
 *
 * Time Complexity: O(V + E) - DFS visits each course and prerequisite edge once; stack pop is O(V)
 * Space Complexity: O(V + E) - Adjacency list stores E edges; stack and recursion stack each hold up to V entries
 *
 * Edge Cases Handled: cycle in prerequisites (DFS detects back edge, returns empty array), no prerequisites (all
 * courses pushed to stack in arbitrary order), disconnected components (outer loop initiates DFS from each unvisited
 * course)
 */
class CourseScheduleII {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        List<List<Integer>> adj = new ArrayList<>();
        Stack<Integer> stack = new Stack<>();
        boolean[] visited = new boolean[numCourses];
        boolean[] recPath = new boolean[numCourses];
        
        for (int i = 0; i < numCourses; i++) {
            adj.add(new ArrayList<>());
        }

        for (int[] req : prerequisites) {
            adj.get(req[1]).add(req[0]);
        }

        for (int i = 0; i < numCourses; i++) {
            if (!visited[i]) {
                if (dfs(adj, stack, visited, recPath, i)) {
                    return new int[0];
                }
            }
        }

        int size = stack.size();
        int[] res = new int[size];
        int index = 0;

        while (size != 0) {
            res[index++] = stack.pop();
            size--;
        }

        return res;
    }

    public static boolean dfs(List<List<Integer>> adj, Stack<Integer> stack, boolean[] visited, boolean[] recPath,
            int curr) {
        visited[curr] = true;
        recPath[curr] = true;

        for (int neighbor : adj.get(curr)) {
            if (!visited[neighbor]) {
                if (dfs(adj, stack, visited, recPath, neighbor)) {
                    return true;
                }
            } else if (recPath[neighbor]) {
                return true;
            }
        }

        stack.push(curr);
        recPath[curr] = false;
        return false;
    }
}
