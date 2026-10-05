/**
 * 706. Design HashMap
 * Difficulty: Easy | Tags: Array, Hash Table, Linked List, Design, Hash
 * Function
 * https://leetcode.com/problems/design-hashmap/
 *
 * Pattern: Direct-Address Table (Bounded Key Space)
 *
 * Key insight: Keys are bounded to [0, 10^6], so a flat int[1000001] array
 * serves as a perfect hash. Values are stored as value+1 so that 0 maps to
 * index 0 (distinguishing "value 0" from "empty slot" which stores 0 after
 * removal).
 *
 * Time Complexity: O(1) per operation - Direct array index access for put, get,
 * and remove
 *
 * Space Complexity: O(1) - Fixed 1000001-element array regardless of how many
 * keys are used
 *
 * Edge Cases Handled: storing and retrieving value 0 (offset by +1 avoids
 * ambiguity with the empty marker), removing a key then querying it (returns
 * -1), re-putting the same key with a different value
 */
class MyHashMap {
    int[] map;

    public MyHashMap() {
        map = new int[1000001];
    }

    public void put(int key, int value) {
        map[key] = value + 1;
    }

    public int get(int key) {
        return map[key] - 1;
    }

    public void remove(int key) {
        map[key] = 0;
    }
}

/**
 * Your MyHashMap object will be instantiated and called as such:
 * MyHashMap obj = new MyHashMap();
 * obj.put(key,value);
 * int param_2 = obj.get(key);
 * obj.remove(key);
 */
