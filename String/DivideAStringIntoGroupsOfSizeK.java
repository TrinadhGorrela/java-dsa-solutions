/**
 * 2138. Divide a String Into Groups of Size k
 * Difficulty: Easy | Tags: String, Simulation
 * https://leetcode.com/problems/divide-a-string-into-groups-of-size-k/
 *
 * Pattern: Fixed-Size Chunking with Padding
 *
 * Key insight: Step through the string in k-sized strides; full chunks are
 * taken as-is, while the final possibly-short chunk is padded to length k with
 * the fill character. The result array size is ceil(n/k).
 *
 * Time Complexity: O(n) - Each character visited once; padding adds at most k−1
 * extra characters.
 *
 * Space Complexity: O(n) - Output array holds the full re-chunked string.
 *
 * Edge Cases Handled: n evenly divisible by k (no padding), final chunk shorter
 * than k (padded with fill), k > n (single padded chunk), k = 1 (each character
 * is its own group)
 */
class DivideAStringIntoGroupsOfSizeK {
    public String[] divideString(String s, int k, char fill) {
        int index = 0;
        int size = (s.length() + k - 1) / k;
        String[] arr = new String[size];
        for (int i = 0; i < s.length(); i = i + k) {
            String sub;
            if (i + k < s.length()) {
                sub = s.substring(i, i + k);
            } else {
                sub = s.substring(i);
                while (sub.length() < k) {
                    sub = sub + fill;
                }
            }
            arr[index] = sub;
            index++;
        }
        return arr;
    }
}
