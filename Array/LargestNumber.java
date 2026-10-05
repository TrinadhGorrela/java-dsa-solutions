/**
 * 179. Largest Number
 * Difficulty: Medium | Tags: Array, String, Greedy, Sorting
 * https://leetcode.com/problems/largest-number/
 *
 * Pattern: Custom Concatenation Sort
 *
 * Key insight: The comparator (b+a).compareTo(a+b) is transitive and encodes
 * which digit arrangement is lexicographically larger — e.g., "34" > "3" but
 * "3" > "30", so the sort produces the globally optimal concatenation.
 *
 * Time Complexity: O(N log N) - Sorting N string conversions; each comparison
 * is O(L) where L is digit count
 *
 * Space Complexity: O(N) - String array for N converted numbers
 *
 * Edge Cases Handled: all zeros (leading-zero guard returns "0"), single
 * element, numbers sharing common prefixes (e.g., 3 and 34), leading zeros
 * avoided by integer-to-string conversion
 */
class LargestNumber {
    public String largestNumber(int[] nums) {
        String[] strNums = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            strNums[i] = nums[i] + "";
        }
        Arrays.sort(strNums, (a, b) -> (b + a).compareTo(a + b));
        StringBuilder builder = new StringBuilder();

        if (strNums[0].equals("0")) {
            return "0";
        }
        for (int i = 0; i < strNums.length; i++) {
            builder.append(strNums[i] + "");
        }
        return builder.toString();
    }
}
