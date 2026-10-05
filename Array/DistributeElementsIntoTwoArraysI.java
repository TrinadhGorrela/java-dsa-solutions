/**
 * 3069. Distribute Elements Into Two Arrays I
 * Difficulty: Easy | Tags: Array, Simulation
 * https://leetcode.com/problems/distribute-elements-into-two-arrays-i/
 *
 * Pattern: Two-List Greedy Distribution
 *
 * Key insight: Seed arr1 and arr2 with the first two elements, then append each
 * subsequent element to whichever list has the larger tail. This greedy
 * comparison drives the distribution without any sorting.
 *
 * Time Complexity: O(N) - One pass from index 2 to N-1 for distribution, one
 * pass to flatten both lists back into the result array
 *
 * Space Complexity: O(N) - Two ArrayLists collectively hold all N elements
 *
 * Edge Cases Handled: exactly two elements (no loop iterations, just the two
 * seeds), equal tail values (tie goes to arr2 via the else branch), all
 * elements the same
 */
class DistributeElementsIntoTwoArraysI {
    public int[] resultArray(int[] nums) {
        List<Integer> arr1 = new ArrayList<>();
        List<Integer> arr2 = new ArrayList<>();

        arr1.add(nums[0]);
        arr2.add(nums[1]);

        for (int i = 2; i < nums.length; i++) {
            if (arr1.get(arr1.size() - 1) > arr2.get(arr2.size() - 1)) {
                arr1.add(nums[i]);
            } else {
                arr2.add(nums[i]);
            }
        }

        int insertPos = 0;
        for (int i = 0; i < arr1.size(); i++) {
            nums[insertPos++] = arr1.get(i);
        }

        for (int i = 0; i < arr2.size(); i++) {
            nums[insertPos++] = arr2.get(i);
        }

        return nums;
    }
}
