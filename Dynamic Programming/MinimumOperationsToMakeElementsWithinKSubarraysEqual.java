/**
 * 3505. Minimum Operations to Make Elements Within K Subarrays Equal
 * Difficulty: Hard | Tags: Array, Hash Table, Math, Dynamic Programming,
 * Sliding Window, Heap (Priority Queue)
 * https://leetcode.com/problems/minimum-operations-to-make-elements-within-k-su
 * barrays-equal/
 *
 * Pattern: Sliding Window Median (Two Heaps with Lazy Deletion) + Memoized
 * Dynamic Programming
 *
 * Key insight: The cheapest way to equalize a window is to move every element
 * to its median, since the median minimizes the sum of absolute deviations;
 * precompute that cost for every window, then DP over positions choosing either
 * to take a window (jump x ahead) or skip one element to pick k non-overlapping
 * windows.
 *
 * Time Complexity: O(N log N + N * k) - Each window's median is maintained with
 * heaps in O(log N) amortized, and the memoized DP visits O(N * k) states with
 * O(1) transitions.
 *
 * Space Complexity: O(N * k + N) - The memo table dominates at N * (k + 1)
 * entries, alongside the O(N) cost array and lazy-deletion heaps/maps.
 *
 * Edge Cases Handled: k == 0 returns 0 immediately; insufficient remaining
 * elements for k windows returns the 1e15 sentinel safely; x == 1 yields
 * zero-cost windows; duplicate values are handled by lazy-deletion maps;
 * already-equal windows correctly cost 0; large sums use long to avoid
 * overflow; negative values are supported. Note: x > nums.length is not guarded
 * and would allocate a negative-length costs array.
 */
class MinimumOperationsToMakeElementsWithinKSubarraysEqual {
    public long minOperations(int[] nums, int x, int k) {
        long[][] memo = new long[nums.length][k + 1];

        for (long[] row : memo) {
            Arrays.fill(row, -1);
        }

        long[] costs = calculateCosts(nums, x);

        return solve(0, k, costs, x, memo);
    }

    private long solve(int i, int kLeft, long[] costs, int x, long[][] memo) {
        if (kLeft == 0) {
            return 0;
        }

        if (i >= costs.length && kLeft > 0) {
            return (long) 1e15;
        }

        if (memo[i][kLeft] != -1) {
            return memo[i][kLeft];
        }

        long pick = costs[i] + solve(i + x, kLeft - 1, costs, x, memo);
        long skip = solve(i + 1, kLeft, costs, x, memo);

        memo[i][kLeft] = Math.min(pick, skip);

        return memo[i][kLeft];
    }

    private long[] calculateCosts(int[] nums, int x) {
        int n = nums.length;
        long[] costs = new long[n - x + 1];

        Queue<Integer> small = new PriorityQueue<>((a, b) -> b - a);
        Queue<Integer> large = new PriorityQueue<>((a, b) -> a - b);

        Map<Integer, Integer> deadSmallMap = new HashMap<>();
        Map<Integer, Integer> deadLargeMap = new HashMap<>();

        int deadSmall = 0;
        int deadLarge = 0;

        long sumSmall = 0;
        long sumLarge = 0;

        for (int i = 0; i < n; i++) {
            small.add(nums[i]);
            sumSmall += nums[i];

            int num = small.poll();
            sumSmall -= num;
            sumLarge += num;
            large.add(num);

            while ((large.size() - deadLarge) > (small.size() - deadSmall)) {
                int val = large.poll();

                if (deadLargeMap.getOrDefault(val, 0) > 0) {
                    deadLargeMap.put(val, deadLargeMap.get(val) - 1);
                    deadLarge--;
                    continue;
                }

                small.add(val);
                sumSmall += val;
                sumLarge -= val;
            }

            if (i >= x - 1) {
                while (!small.isEmpty() && deadSmallMap.getOrDefault(small.peek(), 0) > 0) {
                    int val = small.poll();
                    deadSmallMap.put(val, deadSmallMap.get(val) - 1);
                    deadSmall--;
                }

                while (!large.isEmpty() && deadLargeMap.getOrDefault(large.peek(), 0) > 0) {
                    int val = large.poll();
                    deadLargeMap.put(val, deadLargeMap.get(val) - 1);
                    deadLarge--;
                }

                if (small.isEmpty()) {
                    continue;
                }

                long median = small.peek();

                long smallMed = (median * (small.size() - deadSmall)) - sumSmall;
                long largeMed = sumLarge - (median * (large.size() - deadLarge));

                costs[i - x + 1] = smallMed + largeMed;

                int outVal = nums[i - x + 1];
                if (median >= outVal) {
                    sumSmall -= outVal;
                    deadSmall++;
                    deadSmallMap.put(outVal, deadSmallMap.getOrDefault(outVal, 0) + 1);
                } else {
                    sumLarge -= outVal;
                    deadLarge++;
                    deadLargeMap.put(outVal, deadLargeMap.getOrDefault(outVal, 0) + 1);
                }

                while ((large.size() - deadLarge) > (small.size() - deadSmall)) {
                    int val = large.poll();

                    if (deadLargeMap.getOrDefault(val, 0) > 0) {
                        deadLargeMap.put(val, deadLargeMap.get(val) - 1);
                        deadLarge--;
                        continue;
                    }

                    small.add(val);
                    sumSmall += val;
                    sumLarge -= val;
                }

                while ((small.size() - deadSmall) > (large.size() - deadLarge) + 1) {
                    int val = small.poll();

                    if (deadSmallMap.getOrDefault(val, 0) > 0) {
                        deadSmallMap.put(val, deadSmallMap.get(val) - 1);
                        deadSmall--;
                        continue;
                    }

                    large.add(val);
                    sumLarge += val;
                    sumSmall -= val;
                }
            }
        }

        return costs;
    }
}
