/**
 * 2326. Spiral Matrix IV
 * Difficulty: Medium | Tags: Array, Linked List, Matrix, Simulation
 * https://leetcode.com/problems/spiral-matrix-iv/
 *
 * Pattern: Spiral Traversal - Layer-by-Layer Boundary Shrinking (Simulation)
 *
 * Key insight: Four shrinking boundaries (rowBegin/rowEnd/colBegin/colEnd)
 * define the current outer layer, and each of the four edges is walked
 * left-to-right/right-to-left while consuming one node per cell. Because every
 * cell is pre-filled with -1 and the loop breaks the instant head runs out,
 * leftover cells automatically keep -1 when the list is shorter than the
 * matrix.
 *
 * Time Complexity: O(M*N) - Every cell is visited exactly once, plus the O(M*N)
 * fill with -1
 *
 * Space Complexity: O(M*N) - The result matrix itself (required output); no
 * other auxiliary structure
 *
 * Edge Cases Handled: list shorter than the matrix (remaining cells stay -1 via
 * the three early breaks); single-row or single-column matrices (the
 * inverted-range checks keep edge walks bounded); 1x1 matrix. Relies on the
 * constraint that the list holds exactly M*N nodes - if it were longer, head
 * would never become null and the loop would not terminate.
 */
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class SpiralMatrixIV {
    public int[][] spiralMatrix(int m, int n, ListNode head) {
        int[][] res = new int[m][n];
        int rowBegin = 0;
        int rowEnd = m - 1;
        int colBegin = 0;
        int colEnd = n - 1;

        for (int[] i : res) {
            Arrays.fill(i, -1);
        }

        while (head != null) {
            for (int i = colBegin; i <= colEnd; i++) {
                res[rowBegin][i] = head.val;
                head = head.next;
                if (head == null) {
                    break;
                }
            }
            rowBegin++;

            if (head == null) {
                break;
            }

            for (int j = rowBegin; j <= rowEnd; j++) {
                res[j][colEnd] = head.val;
                head = head.next;
                if (head == null) {
                    break;
                }
            }
            colEnd--;

            if (head == null) {
                break;
            }

            for (int k = colEnd; k >= colBegin; k--) {
                res[rowEnd][k] = head.val;
                head = head.next;
                if (head == null) {
                    break;
                }
            }
            rowEnd--;

            if (head == null) {
                break;
            }

            for (int s = rowEnd; s >= rowBegin; s--) {
                res[s][colBegin] = head.val;
                head = head.next;
                if (head == null) {
                    break;
                }
            }
            colBegin++;
        }

        return res;
    }
}
