/**
 * 2244. Minimum Rounds to Complete All Tasks
 * Difficulty: Medium | Tags: Array, Hash Table, Greedy, Counting
 * https://leetcode.com/problems/minimum-rounds-to-complete-all-tasks/
 *
 * Pattern: Counting + Ceil-Division Greedy
 *
 * Key insight: Since each round processes 2 or 3 tasks, the minimum rounds for
 * count c is ⌈c/3⌉ — a count of 1 is impossible (returns -1), while counts ≥ 2
 * are always solvable because the remainder after dividing by 3 is always
 * coverable by 2s.
 *
 * Time Complexity: O(N) - One pass to count frequencies, one pass over unique
 * levels
 *
 * Space Complexity: O(U) - HashMap with U unique task levels
 *
 * Edge Cases Handled: any level with count 1 (impossible, returns -1), count
 * divisible by 3 (exact ⌈c/3⌉), count mod 3 = 1 (requires one fewer 3-group and
 * one more 2-group), single unique level
 */
class MinimumRoundsToCompleteAllTasks {
    public int minimumRounds(int[] tasks) {
        int rounds = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i : tasks) {
            map.put(i, map.getOrDefault(i, 0) + 1);
        }

        for (int i : map.keySet()) {
            if (map.get(i) == 1) {
                return -1;
            }

            int remainder = map.get(i) % 3;

            if (remainder == 0) {
                rounds += map.get(i) / 3;
            } else {
                rounds += map.get(i) / 3 + 1;
            }
        }
        return rounds;
    }
}
