/**
 * 705. Design HashSet
 * Difficulty: Easy | Tags: Array, Hash Table, Linked List, Design, Hash
 * Function
 * https://leetcode.com/problems/design-hashset/
 *
 * Pattern: Separate Chaining with Modulo Bucketing
 *
 * Key insight: Partition keys into 1000 buckets via key % 1000, with each
 * bucket backed by a LinkedList. Add/remove/contains operate by scanning only
 * the relevant bucket's list, keeping per-operation cost proportional to the
 * bucket's chain length.
 *
 * Time Complexity: O(N/k) average, O(N) worst case - With k = 1000 buckets,
 * average chain length is N/1000; worst case is all keys colliding into one
 * bucket
 *
 * Space Complexity: O(N) - 1000 bucket references plus LinkedList nodes for
 * each stored key
 *
 * Edge Cases Handled: hash collisions (keys sharing a bucket), duplicate add
 * (idempotent, checked before insert), remove of absent key (no-op), keys at
 * bucket boundaries (999 and 1999 map to same bucket)
 */
class MyHashSet {
    LinkedList<Integer>[] bucketArray;

    public MyHashSet() {
        bucketArray = new LinkedList[1000];
        for (int i = 0; i < 1000; i++) {
            bucketArray[i] = new LinkedList<Integer>();
        }
    }

    public void add(int key) {
        int index = key % 1000;
        if (!bucketArray[index].contains(key)) {
            bucketArray[index].add(key);
        }
    }

    public void remove(int key) {
        int index = key % 1000;
        if (bucketArray[index].contains(key)) {
            bucketArray[index].remove((Integer) key);
        }
    }

    public boolean contains(int key) {
        return bucketArray[key % 1000].contains(key);
    }
}

/**
 * Your MyHashSet object will be instantiated and called as such:
 * MyHashSet obj = new MyHashSet();
 * obj.add(key);
 * obj.remove(key);
 * boolean param_3 = obj.contains(key);
 */
