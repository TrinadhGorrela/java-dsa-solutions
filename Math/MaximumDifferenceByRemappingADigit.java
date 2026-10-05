/**
 * 2566. Maximum Difference by Remapping a Digit
 * Difficulty: Easy | Tags: Math, Greedy
 * https://leetcode.com/problems/maximum-difference-by-remapping-a-digit/
 *
 * Pattern: Two-Scan Digit Remapping
 *
 * Key insight: The maximum is achieved by replacing the first non-9 digit with
 * 9 (greedy leftmost swap), and the minimum by replacing the leading digit with
 * 0 (since leading zeros are stripped by parseInt). Both are independent,
 * single-character global replacements.
 *
 * Time Complexity: O(D) - D = number of digits; two string scans and two
 * String.replace calls
 *
 * Space Complexity: O(D) - Two string copies for the max and min candidates
 *
 * Edge Cases Handled: all digits already 9 (max equals input, no replacement),
 * single-digit input, leading digit replacement produces effective leading zero
 * (parseInt handles it), digits like 100 (max=900, min=0)
 */
class MaximumDifferenceByRemappingADigit {
    public int minMaxDifference(int num) {
        String maxStr = Integer.toString(num);
        String minStr = maxStr;
        int i = 0;
        while (i < maxStr.length() && maxStr.charAt(i) == '9') {
            i++;
        }
        if (i < maxStr.length()) {
            maxStr = maxStr.replace(maxStr.charAt(i), '9');
        }
        minStr = minStr.replace(minStr.charAt(0), '0');
        return Integer.parseInt(maxStr) - Integer.parseInt(minStr);
    }
}
