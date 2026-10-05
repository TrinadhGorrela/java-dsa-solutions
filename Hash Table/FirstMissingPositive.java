/**
 * 41. First Missing Positive
 * Difficulty: Hard | Tags: Array, Hash Table
 * https://leetcode.com/problems/first-missing-positive/
 *
 * Pattern: HashSet + Linear Probe from 1
 *
 * Key insight: The answer is at most n+1 (array of size n can hold at most n
 * consecutive positives starting at 1), so probing from 1 with a HashSet gives
 * O(n) time — suboptimal space, but simple and correct.
 *
 * Time Complexity: O(N) - One pass to build the set, at most N+1 contains
 * checks
 *
 * Space Complexity: O(N) - HashSet stores all N elements
 *
 * Edge Cases Handled: no positive integers in array (answer is 1), all 1..n
 * present (answer is n+1), negatives and zeros (ignored by probe), duplicates
 * (absorbed by set)
 */
class FirstMissingPositive {
    public int firstMissingPositive(int[] nums) {
        Set<Integer> set = new HashSet<>();
        int small = 1;

        for (int i : nums) {
            set.add(i);
        }

        while (set.contains(small)) {
            small++;
        }

        return small;
    }
}
