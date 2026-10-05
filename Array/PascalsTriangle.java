/**
 * 118. Pascal's Triangle
 * Difficulty: Easy | Tags: Array, Dynamic Programming
 * https://leetcode.com/problems/pascals-triangle/
 *
 * Pattern: Row-by-row iterative DP building Pascal's Triangle
 *
 * Key insight: Row i is derived from row i-1 by summing each adjacent pair;
 * both edges of every row are 1. Each new row is appended to the result list as
 * it's computed.
 *
 * Time Complexity: O(numRows^2) - Row i has i elements, total elements =
 * numRows*(numRows+1)/2
 *
 * Space Complexity: O(numRows^2) - Full triangle stored in the output
 *
 * Edge Cases Handled: numRows=0 returns empty list; numRows=1 returns [[1]]
 */
class PascalsTriangle {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> res = new ArrayList<>();
        if (numRows == 0)
            return res;
        List<Integer> firstRow = new ArrayList<>();
        firstRow.add(1);
        res.add(firstRow);
        if (numRows == 1)
            return res;
        for (int i = 1; i < numRows; i++) {
            List<Integer> prev = res.get(i - 1);
            List<Integer> row = new ArrayList<>();
            row.add(1);
            for (int j = 0; j < i - 1; j++) {
                row.add(prev.get(j) + prev.get(j + 1));
            }
            row.add(1);
            res.add(row);
        }
        return res;
    }
}
