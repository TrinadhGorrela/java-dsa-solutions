/**
 * 1569. Number of Ways to Reorder Array to Get Same BST
 * Difficulty: Hard | Tags: Array, Math, Divide and Conquer, Dynamic
 * Programming, Tree, Union-Find, Binary Search Tree, Memoization,
 * Combinatorics, Binary Tree, Fermat's Little Theorem
 * https://leetcode.com/problems/number-of-ways-to-reorder-array-to-get-same-bst
 * /
 *
 * Pattern: Divide and Conquer + Combinatorics (Pascal's Triangle)
 *
 * Key insight: A BST is uniquely determined by the relative order of its left
 * and right subtrees, so at each node with L left and R right children you
 * choose which L positions those nodes occupy among the L+R interleavings —
 * C(L+R, L) ways — then multiply by the recursive counts of each subtree.
 * Precomputing Pascal's triangle mod 1e9+7 gives O(1) combination lookups.
 *
 * Time Complexity: O(N^2) - Pascal's triangle precomputation is sum(1..N) ~
 * O(N^2), and each recursive partition scans its sublist; total partition work
 * is O(N^2) across all depths
 *
 * Space Complexity: O(N^2) - The 1001x1001 Pascal table plus O(N)
 * recursion/list depth
 *
 * Edge Cases Handled: single-element array (base case returns 1, then subtracts
 * 1 to exclude the original order → answer 0 as required); two-element array;
 * subtraction guarded by +MOD before % MOD to avoid negative result; balanced
 * vs fully skewed (chain) subtrees; all-distinct values assumed per problem
 * constraints
 */
class NumberOfWaysToReorderArrayToGetSameBst {
    private static final int MOD = 1_000_000_007;

    public int numOfWays(int[] nums) {
        List<Integer> list = new ArrayList<>();
        long[][] table = new long[1001][1001];

        for (int n = 0; n <= 1000; n++) {
            table[n][0] = 1;
            table[n][n] = 1;

            for (int k = 1; k < n; k++) {
                table[n][k] = (table[n - 1][k - 1] + table[n - 1][k]) % MOD;
            }
        }

        for (int i : nums) {
            list.add(i);
        }

        return (int) (calculateWays(list, table) - 1+MOD) % MOD;
    }

    private long calculateWays(List<Integer> list, long[][] table) {
        if (list.size() <= 1) {
            return 1;
        }

        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();
        int root = list.get(0);

        for (int j = 1; j < list.size(); j++) {
            if (root > list.get(j)) {
                left.add(list.get(j));
            } else {
                right.add(list.get(j));
            }
        }

        long leftSide = calculateWays(left, table);
        long rightSide = calculateWays(right, table);

        return (((leftSide * rightSide) % MOD) * table[left.size() + right.size()][left.size()]) % MOD;

    }
}
