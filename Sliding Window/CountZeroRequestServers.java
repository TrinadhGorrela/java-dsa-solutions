/**
 * 2747. Count Zero Request Servers
 * Difficulty: Medium | Tags: Array, Hash Table, Sliding Window, Sorting
 * https://leetcode.com/problems/count-zero-request-servers/
 *
 * Pattern: Sliding Window (Variable Length) + Offline Query Sorting
 *
 * Key insight: Sort both logs and queries by time so the window [queryTime - x,
 * queryTime] only moves forward; because both boundaries are monotonic, every
 * log enters and leaves the window at most once, so n minus the number of
 * active servers answers each query in linear sweep time.
 *
 * Time Complexity: O(M log M + Q log Q) - Dominated by sorting the M logs and Q
 * queries; the two-pointer sweep afterward is O(M + Q).
 *
 * Space Complexity: O(n + Q) - A count array of size n + 1 for per-server
 * activity, plus re-indexed query and result arrays of size Q; sorting is done
 * in place.
 *
 * Edge Cases Handled: empty logs (all n servers report zero), queries earlier
 * or later than every log, duplicate timestamps in logs and queries, a server
 * logging repeatedly within one window (counts exceed 1), x = 0 exact-timestamp
 * window, and all servers active (returns 0); assumes server IDs stay within
 * [1, n] because they index directly into the count array.
 */
class CountZeroRequestServers {
    public int[] countServers(int n, int[][] logs, int x, int[] queries) {
        int[][] query = new int[queries.length][2];
        int[] res = new int[queries.length];
        int[] counts = new int[n + 1];

        for (int i = 0; i < res.length; i++) {
            query[i][0] = i;
            query[i][1] = queries[i];
        }

        Arrays.sort(logs, (a, b) -> Integer.compare(a[1], b[1]));
        Arrays.sort(query, (a, b) -> Integer.compare(a[1], b[1]));

        int left = 0;
        int right = 0;
        int activeServers = 0;

        for (int i = 0; i < queries.length; i++) {
            int startTime = query[i][1] - x;
            int endTime = query[i][1];

            while (right < logs.length && logs[right][1] <= endTime) {
                counts[logs[right][0]] += 1;

                if (counts[logs[right][0]] == 1) {
                    activeServers++;
                }

                right++;
            }

            while (left < logs.length && logs[left][1] < startTime) {
                counts[logs[left][0]] -= 1;

                if (counts[logs[left][0]] == 0) {
                    activeServers--;
                }

                left++;
            }

            res[query[i][0]] = n - activeServers;
        }

        return res;
    }
}
