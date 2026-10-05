/**
 * 1079. Letter Tile Possibilities
 * Difficulty: Medium | Tags: Hash Table, String, Backtracking, Counting
 * https://leetcode.com/problems/letter-tile-possibilities/
 *
 * Pattern: Full Permutation Enumeration with Set-Based Deduplication
 *
 * Key insight: The input may contain duplicate letters (e.g., "AAB"), so
 * permuting the indices still produces identical strings; a
 * HashSet<List<String>> on the prefix snapshots eliminates these duplicates at
 * the cost of some overhead, yielding the count of distinct non-empty
 * arrangements of all lengths.
 *
 * Time Complexity: O(n! * n) worst case - generates all permutations of n
 * indices, with O(n) per set insertion; duplicate-heavy inputs reduce this
 * significantly
 *
 * Space Complexity: O(n! * n) for the HashSet storing up to n! distinct
 * permutation snapshots
 *
 * Edge Cases Handled: all tiles identical (only n distinct non-empty prefixes),
 * single tile (count = 1), no duplicate tiles (set is redundant but correct),
 * empty-length prefix excluded by the size != 0 guard
 */
class LetterTilePossibilities {
    public int numTilePossibilities(String tiles) {
        Set<List<String>> res = new HashSet<>();
        boolean[] used = new boolean[tiles.length()];
        solve(res, new ArrayList<>(), used, tiles);
        return res.size();
    }

    public static void solve(Set<List<String>> res, List<String> temp, boolean[] used, String tiles) {
        if (temp.size() != 0) {
            res.add(new ArrayList<>(temp));
        }

        if (temp.size() == tiles.length()) {
            return;
        }

        for (int i = 0; i < tiles.length(); i++) {
            if (!used[i]) {
                temp.add(tiles.charAt(i) + "");
                used[i] = true;
                solve(res, temp, used, tiles);
                temp.remove(temp.size() - 1);
                used[i] = false;
            }
        }
    }
}
