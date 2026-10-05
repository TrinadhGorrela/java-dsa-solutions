/**
 * 151. Reverse Words in a String
 * Difficulty: Medium | Tags: Two Pointers, String
 * https://leetcode.com/problems/reverse-words-in-a-string/
 *
 * Pattern: Two Pointers (Opposing Ends of a Split Token Array)
 *
 * Key insight: Once the string is tokenized, reversing the word order is
 * nothing more than swapping the ends of the array - the k-th word from the
 * front pairs with the k-th word from the back, and each pair is touched
 * exactly once, so a single left/right walk that stops when the pointers cross
 * is the entire reversal. All the
 * messy whitespace work is pushed into the delimiter: \s+ is greedy, so runs of
 * spaces collapse into a single separator for free.
 *
 * Time Complexity: O(N) - one trim, one regex split, and a swap loop touching
 * at most N/2 words, each swap O(1).
 *
 * Space Complexity: O(N) - trim(), split() and toString/join each materialize
 * new strings plus the word array, all proportional to the input length.
 *
 * Edge Cases Handled: leading and trailing whitespace stripped by trim();
 * multiple consecutive spaces (and tabs/newlines, since \s matches them)
 * collapsed by the \s+ delimiter; a single word makes the swap loop a no-op;
 * single-character input; all-whitespace or empty input still returns ""
 * because split("") yields [""] and join reproduces it. Honest limitation: the
 * reversal is in-place on the token array, not on the original string, and
 * whitespace is interpreted strictly as regex \s.
 */
class ReverseWordsInAString {
    public String reverseWords(String s) {
        s = s.trim();
        String[] words = s.split("\\s+");
        int left = 0;
        int right = words.length - 1;

        while (left < right) {
            String temp = words[left];
            words[left] = words[right];
            words[right] = temp;
            left++;
            right--;
        }

        return String.join(" ", words);
    }
}
