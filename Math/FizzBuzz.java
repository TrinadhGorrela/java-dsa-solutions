/**
 * 412. Fizz Buzz
 * Difficulty: Easy | Tags: Math, String, Simulation
 * https://leetcode.com/problems/fizz-buzz/
 *
 * Pattern: Ordered Modulo Dispatch
 *
 * Key insight: Iterate 1..n, testing divisibility in descending precedence — by
 * 15 first (FizzBuzz), then 3 (Fizz), then 5 (Buzz) — and fall back to the
 * numeric string when none apply.
 *
 * Time Complexity: O(n) - One pass over all n numbers, constant work each
 *
 * Space Complexity: O(n) - Result list must hold one string per number
 *
 * Edge Cases Handled: multiples of both 3 and 5 (FizzBuzz branch checked first
 * so it wins), n = 1 (only "1" produced), non-divisible numbers emitted as
 * their own value
 */
class FizzBuzz {
    public List<String> fizzBuzz(int n) {
        List<String> res = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            if (i % 3 == 0 && i % 5 == 0) {
                res.add("FizzBuzz");
            } else if (i % 3 == 0) {
                res.add("Fizz");
            } else if (i % 5 == 0) {
                res.add("Buzz");
            } else {
                res.add("" + i);
            }
        }
        return res;
    }
}
