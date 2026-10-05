/**
 * 2125. Number of Laser Beams in a Bank
 * Difficulty: Medium | Tags: Array, Math, String, Matrix
 * https://leetcode.com/problems/number-of-laser-beams-in-a-bank/
 *
 * Pattern: Adjacent Non-Empty Row Pairing
 *
 * Key insight: Empty rows between device rows do not generate beams themselves
 * but act as multipliers — only consecutive rows that both have devices
 * contribute `prev × curr` beams, and empty rows are simply skipped without
 * breaking the chain.
 *
 * Time Complexity: O(M × N) - M rows, N columns per row; every character is
 * visited once
 *
 * Space Complexity: O(1) - Two running counters (prev, curr) and an accumulator
 *
 * Edge Cases Handled: all rows empty (returns 0), only one non-empty row
 * (returns 0), consecutive non-empty rows (beams = product), interleaved empty
 * rows between device rows (beams skip empties correctly)
 */
class NumberOfLaserBeamsInABank {
    public int numberOfBeams(String[] bank) {
        int prev = 0;
        int curr = 0;
        int totalBeams = 0;
        for (int i = 0; i < bank.length; i++) {
            String s = bank[i];
            for (char t : s.toCharArray()) {
                if (t == '1') {
                    curr++;
                }
            }

            if (prev != 0 && curr != 0) {
                totalBeams += prev * curr;
                prev = curr;
                curr = 0;
            }

            if (curr != 0) {
                prev = curr;
                curr = 0;
            }
        }

        return totalBeams;
    }
}
