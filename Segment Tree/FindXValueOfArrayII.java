/**
 * 3525. Find X Value of Array II
 * Difficulty: Hard | Tags: Array, Math, Segment Tree
 * https://leetcode.com/problems/find-x-value-of-array-ii/
 *
 * Pattern: Segment Tree (Modular Product + Residue Count Counting)
 * Key insight: Each node stores its range's product mod K plus cnt[r] = the number of
 * subarrays that start at the range's left edge whose product ≡ r (mod K). Because every
 * such subarray is either fully inside the left child or spans the whole left child and
 * continues into the right child, merging is just cnt[left] plus right residues shifted by
 * left.prod (mod K) — so point updates and "subarrays starting at index start" queries
 * both resolve in O(K) per segment-tree level.
 *
 * Time Complexity: O((N + Q) * K * log N) - Build touches O(N) nodes with O(K) work each;
 * every query performs one O(K log N) point update and one O(K log N) range query
 *
 * Space Complexity: O(N * K) - The segment tree allocates 4 * N nodes, each holding a
 * length-K residue count array
 *
 * Edge Cases Handled: single-element array (leaf build/update base case); update index at
 * exact segment boundaries (mid split uses <= / > correctly); query range fully inside one
 * node vs. split across children (both merge paths); residue arithmetic stays non-negative
 * via % K on non-negative inputs; assumes k >= 1 (K = 0 would divide by zero), nums is
 * non-empty, start and update indices are in bounds, x < k, and values are non-negative
 * (negative vals would yield negative Java % results)
 */
class FindXValueOfArrayII {
    int[] tree_prod;
    int[][] tree_cnt;
    int K;

    class Node {
        int prod;
        int[] cnt;
        Node() {
            cnt = new int[K];
        }
    }

    public int[] resultArray(int[] nums, int k, int[][] queries) {
        int n = nums.length;
        this.K = k;
        tree_prod = new int[4 * n];
        tree_cnt = new int[4 * n][k];
        build(1, 0, n - 1, nums);

        int[] result = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int index = queries[i][0];
            int value = queries[i][1];
            int start = queries[i][2];
            int x = queries[i][3];


            update(1, 0, n - 1, index, value);


            Node resNode = query(1, 0, n - 1, start, n - 1);
            result[i] = resNode.cnt[x];
        }

        return result;
    }

    private void build(int node, int l, int r, int[] nums) {
        if (l == r) {
            tree_prod[node] = nums[l] % K;
            tree_cnt[node][nums[l] % K] = 1;
            return;
        }
        int mid = l + (r - l) / 2;
        build(2 * node, l, mid, nums);
        build(2 * node + 1, mid + 1, r, nums);
        pushUp(node);
    }

    private void pushUp(int node) {
        int left = 2 * node;
        int right = 2 * node + 1;


        tree_prod[node] = (tree_prod[left] * tree_prod[right]) % K;


        for (int i = 0; i < K; i++) {
            tree_cnt[node][i] = tree_cnt[left][i];
        }


        for (int i = 0; i < K; i++) {
            if (tree_cnt[right][i] > 0) {
                int new_val = (tree_prod[left] * i) % K;
                tree_cnt[node][new_val] += tree_cnt[right][i];
            }
        }
    }

    private void update(int node, int l, int r, int idx, int val) {
        if (l == r) {
            tree_prod[node] = val % K;
            for (int i = 0; i < K; i++) {
                tree_cnt[node][i] = 0;
            }
            tree_cnt[node][val % K] = 1;
            return;
        }

        int mid = l + (r - l) / 2;
        if (idx <= mid) {
            update(2 * node, l, mid, idx, val);
        } else {
            update(2 * node + 1, mid + 1, r, idx, val);
        }
        pushUp(node);
    }

    private Node query(int node, int l, int r, int ql, int qr) {
        if (ql <= l && r <= qr) {
            Node res = new Node();
            res.prod = tree_prod[node];
            for (int i = 0; i < K; i++) {
                res.cnt[i] = tree_cnt[node][i];
            }
            return res;
        }

        int mid = l + (r - l) / 2;
        if (qr <= mid) {
            return query(2 * node, l, mid, ql, qr);
        }
        if (ql > mid) {
            return query(2 * node + 1, mid + 1, r, ql, qr);
        }


        Node leftNode = query(2 * node, l, mid, ql, qr);
        Node rightNode = query(2 * node + 1, mid + 1, r, ql, qr);

        Node res = new Node();
        res.prod = (leftNode.prod * rightNode.prod) % K;
        for (int i = 0; i < K; i++) {
            res.cnt[i] = leftNode.cnt[i];
        }
        for (int i = 0; i < K; i++) {
            if (rightNode.cnt[i] > 0) {
                int new_val = (leftNode.prod * i) % K;
                res.cnt[new_val] += rightNode.cnt[i];
            }
        }
        return res;
    }
}
