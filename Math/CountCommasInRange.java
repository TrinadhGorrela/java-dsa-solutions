/**
 * 3870. Count Commas in Range
 * Difficulty: Easy | Tags: Math
 * https://leetcode.com/problems/count-commas-in-range/
 *
 * Pattern: Direct Formula (Brain Teaser)
 *
 * Key insight: Since n ≤ 10^5, every number in [1, n] has at most one comma
 * (only numbers with 4+ digits get a comma). Numbers 1–999 contribute zero
 * commas; each number from 1000–n contributes exactly one. Thus the total is n
 * − 999 when n ≥ 1000, and 0 otherwise — a simple subtraction, no iteration
 * needed.
 *
 * Time Complexity: O(1) — single conditional and subtraction
 *
 * Space Complexity: O(1) — no auxiliary data structures
 *
 * Edge Cases Handled: n < 1000 (returns 0, no numbers with commas), n = 1000
 * (returns 1), n = 999 (boundary, returns 0), minimum input n = 1 (returns 0)
 */
class CountCommasInRange {
    public int countCommas(int n) {
        if (n < 1000) {
            return 0;
        }

        return n - 999;
    }
}
