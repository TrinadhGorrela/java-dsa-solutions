/**
 * 146. LRU Cache
 * Difficulty: Medium | Tags: Hash Table, Linked List, Design, Doubly-Linked
 * List
 * https://leetcode.com/problems/lru-cache/
 *
 * Pattern: HashMap + Doubly-Linked List with dummy head/tail sentinel nodes.
 *
 * Key insight: The HashMap provides O(1) node lookup while the doubly-linked
 * list encodes recency order by position. Since moving a node to the head is
 * pure pointer rewiring, every get/put stays O(1) and the true LRU (tail.prev)
 * is always the node evicted when the cache fills.
 *
 * Time Complexity: O(1) per get/put - each call does a hash map lookup plus a
 * constant number of doubly-linked-list pointer operations, independent of
 * cache size.
 *
 * Space Complexity: O(capacity) - the map and the linked list each hold exactly
 * one node per cached key.
 *
 * Edge Cases Handled: get() on a missing key returns -1; put() updating an
 * existing key refreshes its recency without evicting; eviction when full
 * removes the LRU node at tail.prev without breaking the sentinels, including a
 * single-entry cache where that node is adjacent to both head and tail. Only
 * safe for capacity >= 1 - a capacity of 0 would corrupt the sentinel wiring
 * (LeetCode guarantees capacity >= 1).
 */
class LRUCache {

    class Node {
        int key;
        int value;
        Node prev;
        Node next;

        Node() {
        }

        Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    Map<Integer, Node> map = new HashMap<>();
    int capacity;
    Node head;
    Node tail;

    public LRUCache(int capacity) {
        this.capacity = capacity;

        this.head = new Node();
        this.tail = new Node();

        head.next = tail;
        tail.prev = head;
    }

    private void deleteNode(Node node) {
        Node prev = node.prev;
        Node next = node.next;

        prev.next = next;
        next.prev = prev;
    }

    private void insertNode(Node node) {
        Node next = head.next;

        head.next = node;
        node.prev = head;
        node.next = next;
        next.prev = node;
    }

    public int get(int key) {
        if (!map.containsKey(key)) {
            return -1;
        }

        Node node = map.get(key);
        deleteNode(node);
        insertNode(node);

        return node.value;
    }

    public void put(int key, int value) {
        if (map.containsKey(key)) {
            Node node = map.get(key);
            node.value = value;
            deleteNode(node);
            insertNode(node);
            return;
        } else if (map.size() >= capacity) {
            map.remove(tail.prev.key);
            deleteNode(tail.prev);
        }

        Node node = new Node(key, value);
        map.put(key, node);

        insertNode(node);
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
