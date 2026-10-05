/**
 * 394. Decode String
 * Difficulty: Medium | Tags: String, Stack, Recursion
 * https://leetcode.com/problems/decode-string/
 *
 * Pattern: Stack-Based Nested Decoding
 *
 * Key insight: When ']' is found, pop back to the matching '[', read the
 * repetition count, expand the segment, and push it back — this ensures
 * innermost encodings resolve first, naturally handling arbitrary nesting
 * depth.
 *
 * Time Complexity: O(N) - N is the final decoded string length; each character
 * is pushed and popped from the stack at most a constant number of times across
 * all expansions.
 *
 * Space Complexity: O(N) - The stack and result builder both grow proportional
 * to the decoded string length.
 *
 * Edge Cases Handled: single-character input, no encoding present, multi-digit
 * repetition counts, adjacent encoded segments (e.g. "3[a]2[b]"), deeply nested
 * encodings (e.g. "2[2[2[a]]]").
 */
class DecodeString {
    public String decodeString(String s) {
        Stack<Character> stack = new Stack<>();
        StringBuilder res = new StringBuilder();
        String word = "";

        for (int i = 0; i < s.length(); i++) {
            stack.push(s.charAt(i));
            if (s.charAt(i) == ']') {
                stack.pop();
                StringBuilder temp = new StringBuilder();
                while (!stack.isEmpty() && stack.peek() != '[') {
                    temp.append(stack.pop());
                }

                stack.pop();
                StringBuilder numTemp = new StringBuilder();
                while (!stack.isEmpty() && Character.isDigit(stack.peek())) {
                    numTemp.append(stack.pop());
                }

                numTemp = numTemp.reverse();
                int num = Integer.parseInt(numTemp.toString());
                word = helper(temp.reverse(), num);

                for (char c : word.toCharArray()) {
                    stack.push(c);
                }
            }
        }

        while (!stack.isEmpty()) {
            res.append(stack.pop());
        }

        return res.reverse().toString();
    }

    private static String helper(StringBuilder s, int n) {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < n; i++) {
            res.append(s);
        }
        return res.toString();
    }
}
