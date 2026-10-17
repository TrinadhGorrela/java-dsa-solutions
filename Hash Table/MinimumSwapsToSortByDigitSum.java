/**
 * 3551. Minimum Swaps to Sort by Digit Sum
 * Difficulty: Medium | Tags: Array, Hash Table, Sorting
 * https://leetcode.com/problems/minimum-swaps-to-sort-by-digit-sum/
 *
 * Pattern: Sorting + Permutation Cycle Decomposition (Cycle Sort Swap Counting)
 *
 * Key insight: Sorting the (index, value, digitSum) records by (digitSum,
 * value) defines the target permutation, and any permutation splits into
 * disjoint cycles where a cycle of length k is unwound in exactly k-1 swaps -
 * so the answer is the sum of (cycleSize - 1) over every cycle, walked via the
 * original indices.
 *
 * Time Complexity: O(N log N) - Dominated by sorting the N records; digit-sum
 * computation and the single cycle-walk pass are both linear in N
 *
 * Space Complexity: O(N) - The N x 3 record array and the visited[] boolean
 * tracking processed cycles both scale with input size
 *
 * Edge Cases Handled: already-sorted input (identity permutation, all cycles of
 * size 1, 0 swaps); single-element array; value 0 (digit-sum loop exits on num
 * == 0, returning 0); duplicate values (comparator falls back to the value and
 * the stable sort yields a consistent permutation); values up to 1e9 (the a[1]
 * - b[1] tie-break stays inside int range); negative numbers are NOT handled
 * correctly - the digit sum would go negative, so this assumes positive input
 * per constraints
 */
class MinimumSwapsToSortByDigitSum {
    public int minSwaps(int[] nums) {
        int[][] arr = new int[nums.length][3];

        for (int i = 0; i < nums.length; i++) {
            arr[i][0] = i;
            arr[i][1] = nums[i];
            arr[i][2] = getDigitSum(nums[i]);
        }

        Arrays.sort(arr, (a, b) -> {
            if (a[2] == b[2]) {
                return a[1] - b[1];
            }

            return a[2] - b[2];
        });

        boolean[] visited = new boolean[nums.length];
        int swaps = 0;

        for (int i = 0; i < visited.length; i++) {
            if (arr[i][0] != i && !visited[i]) {
                int curr = i;
                int cycleSize = 0;

                while (!visited[curr]) {
                    cycleSize++;
                    visited[curr] = true;
                    curr = arr[curr][0];
                }
                swaps += cycleSize - 1;

            }

            visited[i] = true;
        }

        return swaps;
    }

    private int getDigitSum(int num) {
        int sum = 0;
        while (num != 0) {
            int last = num % 10;
            sum += last;
            num /= 10;
        }

        return sum;
    }
}
