/**
 * 2916. Subarrays Distinct Element Sum of Squares II
 * Difficulty: Hard | Tags: Array, Hash Table, Binary Indexed Tree, Segment Tree
 * https://leetcode.com/problems/subarrays-distinct-element-sum-of-squares-ii/
 *
 * Pattern: Segment Tree + Lazy Propagation (Range Add on Sum of Squares)
 *
 * Key insight: For subarrays ending at index i, the distinct count rises by 1
 * exactly for those starting after the previous occurrence of nums[i], so a
 * single range add of +1 over [lastOccurrence+1, i] updates all of them at
 * once; storing both count and count^2 lets the lazy update apply (x+v)^2 = x^2
 * + 2vx + v^2 in O(1) per node, so querying [0, i] yields the sum of squares of
 * distinct counts for every subarray ending at i.
 *
 * Time Complexity: O(N log N) - Each of the N elements performs one range
 * update and one range query on the segment tree, each costing O(log N)
 *
 * Space Complexity: O(N) - Three 4N-sized arrays (treeSum, treeCount, lazy)
 * plus a last-occurrence map that stores at most one entry per distinct value
 *
 * Edge Cases Handled: empty input (loop never runs, returns 0), single element,
 * first occurrence of a value (lastIdx defaults to -1, updating [0, i]),
 * all-same / heavily duplicated input (updates collapse to the single index
 * after the previous occurrence), negative values (handled by the HashMap key),
 * and large intermediate sums / squares guarded by long arithmetic with modulo
 * reduction at every step (safe under the N <= 10^5 constraint, where lazy
 * increments stay small)
 */
class SubarraysDistinctElementSumOfSquaresII {
    long MOD = 1_000_000_007;

    class SegmentTree {
        int n;
        long[] treeSum;
        long[] treeCount;
        long[] lazy;

        public SegmentTree(int n) {
            this.n = n;
            treeSum = new long[4 * n];
            treeCount = new long[4 * n];
            lazy = new long[4 * n];
        }

        private void applyLazy(int node, int start, int end, long val) {
            long len = end - start + 1;

            long newSum = (treeSum[node] + (2 * val * treeCount[node]) + (val * val * len)) % MOD;
            treeSum[node] = newSum;
            treeCount[node] = (treeCount[node] + val * len) % MOD;
            lazy[node] = (lazy[node] + val) % MOD;
        }

        private void pushDown(int node, int start, int end) {
            if (lazy[node] != 0) {
                int mid = start + (end - start) / 2;
                applyLazy(2 * node, start, mid, lazy[node]);
                applyLazy(2 * node + 1, mid + 1, end, lazy[node]);
                lazy[node] = 0;
            }
        }

        public void update(int node, int start, int end, int l, int r, long val) {
            if (start > r || end < l) {
                return;
            }

            if (start >= l && end <= r) {
                applyLazy(node, start, end, val);
                return;
            }

            int mid = start + (end - start) / 2;

            pushDown(node, start, end);

            update(2 * node, start, mid, l, r, val);
            update(2 * node + 1, mid + 1, end, l, r, val);

            treeSum[node] = (treeSum[2 * node] + treeSum[2 * node + 1]) % MOD;
            treeCount[node] = (treeCount[2 * node] + treeCount[2 * node + 1]) % MOD;
        }

        public long query(int node, int start, int end, int l, int r) {
            if (start > r || end < l) {
                return 0;
            }

            if (start >= l && end <= r) {
                return treeSum[node];
            }

            int mid = start + (end - start) / 2;

            pushDown(node, start, end);

            long leftSum = query(2 * node, start, mid, l, r);
            long rightSum = query(2 * node + 1, mid + 1, end, l, r);

            return (leftSum + rightSum) % MOD;
        }
    }

    public int sumCounts(int[] nums) {
        SegmentTree tree = new SegmentTree(nums.length);
        Map<Integer, Integer> map = new HashMap<>();
        long total = 0;

        for (int i = 0; i < nums.length; i++) {
            int lastIdx = map.getOrDefault(nums[i], -1);

            tree.update(1, 0, nums.length - 1, lastIdx + 1, i, 1);

            long curr = tree.query(1, 0, nums.length - 1, 0, i);

            total = (total + curr) % MOD;

            map.put(nums[i], i);
        }

        return (int) total;
    }
}
