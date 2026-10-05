/**
 * 2000. Reverse Prefix of Word
 * Difficulty: Easy | Tags: Two Pointers, String, Stack
 * https://leetcode.com/problems/reverse-prefix-of-word/
 *
 * Pattern: Locate + Prefix Two-Pointer Reverse
 *
 * Key insight: Linear scan finds the target character's first occurrence (the
 * pivot), then a simple left/right swap from 0..pivot reverses exactly the
 * prefix in O(N) total.
 *
 * Time Complexity: O(N) - One forward scan to find ch + one reverse pass over
 * the prefix; both bounded by N
 *
 * Space Complexity: O(N) - char[] copy for in-place swaps
 *
 * Edge Cases Handled: ch not found anywhere (original string returned), ch at
 * index 0 (empty prefix, no-op), ch at last index (full string reversed), ch
 * appears multiple times (only first occurrence used)
 */
class ReversePrefixOfWord {
    public String reversePrefix(String word, char ch) {
        int left = 0;
        int right = 0;
        char[] arr = word.toCharArray();
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == ch) {
                right = i;
                break;
            }
        }

        while (left < right) {
            char temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;
            left++;
            right--;
        }
        return new String(arr);
    }
}
