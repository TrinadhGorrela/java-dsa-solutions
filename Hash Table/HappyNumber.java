/**
 * 202. Happy Number
 * Difficulty: Easy | Tags: Hash Table, Math, Two Pointers, Floyd's Cycle
 * Finding Algorithm
 * https://leetcode.com/problems/happy-number/
 *
 * Pattern: HashSet Cycle Detection
 *
 * Key insight: Replace n with the sum of its digits' squares repeatedly; the
 * sequence must either reach 1 (happy) or enter a cycle (unhappy)—detect the
 * cycle by checking a HashSet of seen values.
 *
 * Time Complexity: O(log n) amortized - Digit-square sum shrinks rapidly; cycle
 * detection via set lookup is O(1) amortized.
 *
 * Space Complexity: O(log n) - HashSet stores values along the sequence before
 * cycling.
 *
 * Edge Cases Handled: n = 1 (already happy), n = 0, single-digit non-1 (cycle),
 * cycle detected early (returns false immediately)
 */
class HappyNumber {
    public boolean isHappy(int n) {
        Set<Integer> used = new HashSet<>();
        while (true) {
            int sum = square(n);
            n = sum;
            if (n == 1)
                return true;

            if (used.contains(n))
                return false;

            used.add(n);
        }
    }

    public int square(int n) {
        int sum = 0;
        int rem = 0;
        while (n != 0) {
            rem = n % 10;
            sum = sum + (rem * rem);
            n = n / 10;
        }
        return sum;
    }
}
