/**
 * 345. Reverse Vowels of a String
 * Difficulty: Easy | Tags: Two Pointers, String
 * https://leetcode.com/problems/reverse-vowels-of-a-string/
 *
 * Pattern: Converging Two-Pointer Vowel Swap
 *
 * Key insight: A HashSet of vowels gives O(1) membership checks; left and right
 * pointers skip non-vowels independently, so only the vowel subsequence is
 * reversed while all consonants stay fixed.
 *
 * Time Complexity: O(N) - Each character visited at most once by either
 * pointer; HashSet ops are O(1)
 *
 * Space Complexity: O(N) - char[] for in-place swaps plus O(1) vowel set (5
 * entries)
 *
 * Edge Cases Handled: No vowels in string (returned unchanged), all vowels
 * (full reverse), mixed case vowels (both 'aeiou' and 'AEIOU' in set), string
 * of length 0 or 1
 */
class ReverseVowelsOfAString {
    public String reverseVowels(String s) {

		char[] chars = s.toCharArray();
		int left = 0;
		int right = chars.length - 1;

		while (left < right) {
			if (!isVowel(chars[left])) {
				left++;
			}
			if (!isVowel(chars[right])) {
				right--;;
			}
			if(isVowel(chars[left]) && isVowel(chars[right])) {
				char temp=chars[left];
				chars[left]=chars[right];
				chars[right]=temp;
				left++;
				right--;
			}
		}

		return new String(chars);
	}

	public static boolean isVowel(char c) {
		if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u' || c=='A' || c=='E' || c=='I' || c=='O' || c=='U') {
			return true;
		}
		return false;

	}
    
}
