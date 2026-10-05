/**
 * 917. Reverse Only Letters
 * Difficulty: Easy | Tags: Two Pointers, String
 * https://leetcode.com/problems/reverse-only-letters/
 *
 * Pattern: Letter-Only Two-Pointer Reversal
 *
 * Key insight: Advance each pointer past non-letter characters (digits,
 * symbols, spaces) using Character.isLetter(), then swap only when both land on
 * letters—non-letters maintain their original positions.
 *
 * Time Complexity: O(N) - Each character examined at most twice (once per
 * pointer); skip loops don't revisit
 *
 * Space Complexity: O(N) - char[] for in-place swaps
 *
 * Edge Cases Handled: No alphabetic characters (string unchanged), all letters
 * (full reverse), digits and hyphens interleaved with letters, Unicode letters
 * handled by Character.isLetter()
 */
class ReverseOnlyLetters {
    public static String reverseOnlyLetters(String s) {
        char[] arr = s.toCharArray();
        int left = 0, right = s.length() - 1;
        while (left < right) {
            if (Character.isLetter(arr[left]) && Character.isLetter(arr[right])) {
                swap(arr, left, right);
                left++;
                right--;
            }
            if (!Character.isLetter(arr[left])) {
                left++;
            }
            if (!Character.isLetter(arr[right])) {
                right--;
            }
        }
        return new String(arr);
    }

    public static void swap(char[] arr, int left, int right) {
        char temp = arr[left];
        arr[left] = arr[right];
        arr[right] = temp;
    }

}
