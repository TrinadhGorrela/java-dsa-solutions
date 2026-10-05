/**
 * 66. Plus One
 * Difficulty: Easy | Tags: Array, Math
 * https://leetcode.com/problems/plus-one/
 *
 * Pattern: Right-to-Left Carry Propagation
 *
 * Key insight: Incrementing from the least-significant digit stops as soon as a
 * non-9 digit is found (just bump it and return); only an all-9s number needs a
 * new array with a leading 1.
 *
 * Time Complexity: O(N) - Scans digits right-to-left; stops early when no carry
 * remains
 *
 * Space Complexity: O(N) - Worst case (all 9s) allocates length+1 array;
 * otherwise returns the input array in-place
 *
 * Edge Cases Handled: all 9s (e.g. 999 -> 1000 via new array), single digit 9,
 * no carry needed (last digit < 9), carry propagation through consecutive 9s
 */
class PlusOne {
    public int[] plusOne(int[] digits) {
        int length = digits.length;
        for (int i = length - 1; i >= 0; i--) {
            if (digits[i] < 9) {
                digits[i]++;
                return digits;
            }
            digits[i] = 0;
        }
        int[] result = new int[length + 1];
        result[0] = 1;
        return result;
    }
}
