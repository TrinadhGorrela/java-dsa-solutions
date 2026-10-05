/**
 * 728. Self Dividing Numbers
 * Difficulty: Easy | Tags: Math
 * https://leetcode.com/problems/self-dividing-numbers/
 *
 * Pattern: Range Scan with Digit-by-Divisibility Validation
 *
 * Key insight: For each number in [left, right], extract every digit via mod 10
 * and reject the number immediately if any digit is 0 or does not evenly divide
 * the original number.
 *
 * Time Complexity: O((right - left + 1) * d) - Linear scan of the range times
 * at most 5 digits per number (since right <= 10^4)
 *
 * Space Complexity: O(1) - Output list is excluded; only temp/int variables for
 * digit extraction
 *
 * Edge Cases Handled: numbers containing a 0 digit (instantly rejected),
 * single-digit numbers (always self-dividing for 1-9), left = right (single
 * candidate tested)
 */
class SelfDividingNumbers {
    public List<Integer> selfDividingNumbers(int left, int right) {
        List<Integer> res = new ArrayList<>();

        for (int i = left; i <= right; i++) {
            if (isSelfDividing(i)) {
                res.add(i);
            }
        }
        return res;
    }

    private static boolean isSelfDividing(int num) {
        int original = num;
        while (num != 0) {
            int last = num % 10;
            if (last == 0) {
                return false;
            } else if (last != 0 && original % last != 0) {
                return false;
            }
            num /= 10;
        }
        return true;
    }
}
