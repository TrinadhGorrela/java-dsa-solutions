/**
 * 344. Reverse String
 * Difficulty: Easy | Tags: Two Pointers, String
 * https://leetcode.com/problems/reverse-string/
 *
 * Pattern: In-Place Two-Pointer Swap
 *
 * Key insight: Swap s[left] and s[right] then advance both inward; the loop
 * terminates when left >= right, naturally handling both even and odd lengths
 * without separate logic.
 *
 * Time Complexity: O(N) - N/2 swaps, each O(1)
 *
 * Space Complexity: O(1) - One temp char variable; all swaps happen in the
 * input array
 *
 * Edge Cases Handled: Empty array (loop never enters), single element (left ==
 * right, no-op), even-length (pointers meet between elements), odd-length
 * (middle element stays put)
 */
class ReverseString {
    public void reverseString(char[] s) {
        int left = 0;
        int right = s.length - 1;

        while (left <= right) {
            char temp = s[left];
            s[left] = s[right];
            s[right] = temp;
            left++;
            right--;
        }
    }
}
