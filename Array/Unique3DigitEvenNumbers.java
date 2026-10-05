/**
 * 3483. Unique 3-Digit Even Numbers
 * Difficulty: Easy | Tags: Array, Hash Table, Recursion, Enumeration
 * https://leetcode.com/problems/unique-3-digit-even-numbers/
 *
 * Pattern: Frequency Counting + Brute-Force Enumeration
 *
 * Key insight: Only 450 three-digit even numbers exist (100–998, step 2);
 * enumerate every candidate and check digit availability via a frequency array
 * — avoids generating all permutations while naturally excluding leading zeros.
 *
 * Time Complexity: O(D + 1) = O(D) - Frequency array built in one pass over D
 * digits, then at most 450 candidates checked in O(1) each
 *
 * Space Complexity: O(1) - Two fixed-size int[10] arrays regardless of input
 * size
 *
 * Edge Cases Handled: fewer than 3 digits (no valid number possible → returns
 * 0), all identical digits (frequency check prevents over-counting), digits
 * with no even number possible (returns 0), leading zeros naturally excluded by
 * enumeration starting at 100
 */
class Unique3DigitEvenNumbers {
    public int totalNumbers(int[] digits) {
        int[] freq = new int[10];

        for (int i : digits) {
            freq[i]++;
        }

        int count = 0;

        for (int i = 100; i < 1000; i += 2) {
            int n = i;

            int a = n % 10;
            n /= 10;
            int b = n % 10;
            n /= 10;
            int c = n % 10;

            int[] need = new int[10];

            need[a]++;
            need[b]++;
            need[c]++;

            boolean isPos = true;

            for (int j = 0; j < 10; j++) {
                if (need[j] > freq[j]) {
                    isPos = false;
                    break;
                }
            }

            if (isPos) {
                count++;
            }
        }

        return count;
    }
}
