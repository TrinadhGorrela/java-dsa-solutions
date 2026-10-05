/**
 * 997. FindTheTownJudge
 * Difficulty: Easy | Tags: Array, Hash Table, Graph
 * https://leetcode.com/problems/find-the-town-judge/
 *
 * Pattern: In-Degree / Out-Degree Counting
 *
 * Key insight: The town judge trusts nobody (out-degree 0) and is trusted by
 * all others (in-degree n-1). A single pass over trust pairs builds both degree
 * arrays; a second pass over candidates finds the unique person satisfying both
 * conditions.
 *
 * Time Complexity: O(N + E) - One pass over E trust pairs to build degree
 * arrays, one pass over N candidates
 *
 * Space Complexity: O(N) - Two integer arrays of size n+1 for in-degree and
 * out-degree
 *
 * Edge Cases Handled: n = 1 with no trust relations (sole person is judge,
 * returns 1), no person satisfies both degree conditions (returns -1), multiple
 * candidates with in-degree n-1 but one has out-degree > 0 (correctly excluded)
 */
class FindTheTownJudge {
    public int findJudge(int n, int[][] trust) {
        int[] inDegree = new int[n + 1];
        int[] outDegree = new int[n + 1];

        for (int[] pair : trust) {
            inDegree[pair[1]]++;
            outDegree[pair[0]]++;
        }

        for (int i = 1; i <= n; i++) {
            if (inDegree[i] == n - 1 && outDegree[i] == 0) {
                return i;
            }
        }

        return -1;
    }
}
