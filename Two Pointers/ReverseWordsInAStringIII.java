/**
 * 557. Reverse Words in a String III
 * Difficulty: Easy | Tags: Two Pointers, String
 * https://leetcode.com/problems/reverse-words-in-a-string-iii/
 *
 * Pattern: Single-Pass Per-Word In-Place Reversal
 *
 * Key insight: Convert to char[] and track each word's start index; on every
 * space (or end-of-string), reverse just that word's range with an inner
 * two-pointer swap—no String.split overhead.
 *
 * Time Complexity: O(N) - One outer pass + each character swapped at most once
 * across all inner reversals
 *
 * Space Complexity: O(N) - char[] copy needed since Java Strings are immutable
 *
 * Edge Cases Handled: Leading/trailing spaces (none per contract but code is
 * robust), multiple consecutive spaces, single-character words, entire string
 * as one word
 */
class ReverseWordsInAStringIII {
    public String reverseWords(String s) {
      char[] arr = s.toCharArray();
		int j = 0;
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == ' ') {
				swap(arr, j, i - 1);
				j = i + 1;
			}
		}
		swap(arr, j, s.length()-1);

		return new String(arr);

	}

	public static void swap(char[] arr, int st, int en) {
		while (st < en) {
			char temp = arr[st];
			arr[st] = arr[en];
			arr[en] = temp;
			st++;
			en--;
		}
	}   
}
