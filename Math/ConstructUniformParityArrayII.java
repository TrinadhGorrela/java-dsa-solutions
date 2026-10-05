/**
 * 3876. Construct Uniform Parity Array II
 * Difficulty: Medium | Tags: Array, Math
 * https://leetcode.com/problems/construct-uniform-parity-array-ii/
 *
 * Pattern: Parity Analysis (Two-Configuration Check)
 *
 * Key insight: If the array is already all-even or all-odd, it is trivially
 * uniform. Otherwise, try two candidate uniform parities: check if every
 * element's difference from the smallest odd element is a valid positive even
 * number (targeting all-even), or a valid positive odd number (targeting
 * all-odd). At least one configuration must succeed if the answer exists.
 *
 * Time Complexity: O(N) - Two linear scans of the array, one to count parities
 * and two more in the helper checks
 *
 * Space Complexity: O(1) - Only scalar counters and a few local variables used
 *
 * Edge Cases Handled: all elements already same parity (returns true
 * immediately); single element array; array with exactly one odd and rest even
 * (or vice versa); the smallest odd equals another element
 */
class ConstructUniformParityArrayII {
    public boolean uniformArray(int[] nums1) {
        int evenCount = 0;
        int oddCount = 0;
        int smallOdd = Integer.MAX_VALUE;

        for (int i : nums1) {
            if (i % 2 == 0) {
                evenCount++;
            } else {
                if (smallOdd > i) {
                    smallOdd = i;
                }
                oddCount++;
            }
        }

        if (evenCount == nums1.length || oddCount == nums1.length) {
            return true;
        }

        return checkEvenParity(nums1, smallOdd) || checkOddParity(nums1, smallOdd);
    }

    private static boolean checkEvenParity(int[] nums, int smallOdd) {
        for (int i : nums) {
            if (i % 2 != 0) {
                int temp = i - smallOdd;

                if (temp % 2 == 0 && temp > 0) {
                    continue;
                } else {
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean checkOddParity(int[] nums, int smallOdd) {
        for (int i : nums) {
            if (i % 2 == 0 && i != smallOdd) {
                int temp = i - smallOdd;

                if (temp % 2 != 0 && temp > 0) {
                    continue;
                } else {
                    return false;
                }
            }
        }
        return true;
    }
}
