/**
 * 3414. Maximum Score of Non-overlapping Intervals
 * Difficulty: Hard | Tags: Array, Binary Search, Dynamic Programming, Sorting
 * https://leetcode.com/problems/maximum-score-of-non-overlapping-intervals/
 *
 * Pattern: Dynamic Programming (Top-Down Memoization) + Binary Search
 *
 * Key insight: Once intervals are sorted by start, the earliest interval that
 * begins after the current one ends can be located with binary search, so each
 * DP state (index, count) greedily explores only take-or-skip and reuses
 * memoized subproblems. Because intervals are processed in sorted order, every
 * path is non-overlapping by construction and at most 4 picks are allowed.
 *
 * Time Complexity: O(N log N) - Dominated by sorting plus one binary search per
 * memoized state; there are O(4N) states, so the total is O(N log N + 4N log N)
 * = O(N log N).
 *
 * Space Complexity: O(N) - The memo table holds O(4N) states and each stores a
 * list of at most four indices, so the memory scales linearly with the input.
 *
 * Edge Cases Handled: empty intervals list (returns an empty result), fewer
 * than four eligible intervals (the count >= 4 guard stops recursion), a single
 * interval, intervals with equal start points (tie-broken by original index
 * during the sort), equal total weights (tie-broken by lexicographically
 * smallest index list), and large weights (summed as long to avoid integer
 * overflow when adding up to four values).
 */
class MaximumScoreOfNonOverlappingIntervals {
    class State {
        long weight;
        List<Integer> indices;

        State(long weight, List<Integer> indices) {
            this.weight = weight;
            this.indices = indices;
        }
    }

    public int[] maximumWeight(List<List<Integer>> intervals) {
        int[][] arr = new int[intervals.size()][4];

        for (int i = 0; i < intervals.size(); i++) {
            arr[i][0] = intervals.get(i).get(0);
            arr[i][1] = intervals.get(i).get(1);
            arr[i][2] = intervals.get(i).get(2);
            arr[i][3] = i;
        }

        Arrays.sort(arr, (a, b) -> {
            if (a[0] == b[0]) {
                return Integer.compare(a[3], b[3]);
            }
            return Integer.compare(a[0], b[0]);
        });

        State[][] dp = new State[arr.length][5];

        State finalState = helperMethod(dp, arr, 0, 0);

        int[] res = new int[finalState.indices.size()];
        int in = 0;
        for (int i : finalState.indices) {
            res[in++] = i;
        }
        return res;
    }

    private State helperMethod(State[][] dp, int[][] arr, int index, int count) {
        if (count >= 4 || index >= dp.length) {
            return new State(0, new ArrayList<>());
        }

        if (dp[index][count] != null) {
            return dp[index][count];
        }

        State skip = helperMethod(dp, arr, index + 1, count);

        int nextIndex = findNext(arr, arr[index][1]);
        State take = helperMethod(dp, arr, nextIndex, count + 1);

        long takenWeight = arr[index][2] + take.weight;
        List<Integer> takeIndices = new ArrayList<>(take.indices);
        takeIndices.add(arr[index][3]);

        Collections.sort(takeIndices);

        State currTakeState = new State(takenWeight, takeIndices);

        if ((skip.weight > takenWeight) || (skip.weight == currTakeState.weight
                && isLexicographicallySmaller(skip.indices, currTakeState.indices))) {
            dp[index][count] = skip;
        } else {
            dp[index][count] = currTakeState;
        }

        return dp[index][count];
    }

    private static int findNext(int[][] arr, int in) {
        int left = 0;
        int right = arr.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr[mid][0] > in) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }

    private static boolean isLexicographicallySmaller(List<Integer> list1, List<Integer> list2) {
        int n = Math.min(list1.size(), list2.size());

        for (int i = 0; i < n; i++) {
            if (list1.get(i) < list2.get(i)) {
                return true;
            } else if (list1.get(i) > list2.get(i)) {
                return false;
            }
        }

        return list1.size() < list2.size();
    }
}
