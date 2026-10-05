/**
 * 119. Pascal's Triangle II
 * Difficulty: Easy | Tags: Array, Dynamic Programming
 * https://leetcode.com/problems/pascals-triangle-ii/
 *
 * Pattern: Row-by-row DP constructing Pascal's Triangle
 *
 * Key insight: Each row is built from the previous by summing adjacent pairs;
 * edges are always 1. The full triangle is constructed up to rowIndex, then the
 * last row is returned.
 *
 * Time Complexity: O(rowIndex^2) - Builds all rows 0..rowIndex, row i has i+1
 * elements
 *
 * Space Complexity: O(rowIndex^2) - Stores the entire triangle up to the
 * requested row
 *
 * Edge Cases Handled: rowIndex=0 returns [1]; rowIndex=1 returns [1,1]; no
 * modular arithmetic needed
 */
class PascalsTriangleII {
    public List<Integer> getRow(int rowIndex) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> firstRow = new ArrayList<>();
        firstRow.add(1);
        res.add(firstRow);

        for (int i = 1; i <= rowIndex; i++) {
            List<Integer> prev = res.get(i - 1);
            List<Integer> row = new ArrayList<>();
            row.add(1);
            for (int j = 0; j < i - 1; j++) {
                row.add(prev.get(j) + prev.get(j + 1));
            }

            row.add(1);
            res.add(row);
        }
        return res.get(rowIndex);
    }
}
