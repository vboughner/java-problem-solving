

/*
https://leetcode.com/problems/rotate-list/description/

Given a list, rotate the list to the right by k places, where k is non-negative.

Example:

Given 1->2->3->4->5->NULL and k = 2,

return 4->5->1->2->3->NULL.


 * My notes about this problem:
 *   - we're using a singly-linked list, the definition is given
 *   - note that the roating could wrap-around the end of the list if k is more than list length
 *   - it appears there are two other links to change:
 *       - making the old tail point to the old head
 *       - making the new tail point to NULL
 *   - we need to return the new head of the list
 *   - we'll need to transverse the entire list to get to the tail anyway, and to learn the length
 *   - this can be speed O(n), but we might need to transverse it twice
 *   - list returned is the same if k == len
 */

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}

public class ListRotator {

    public ListNode rotateRight(ListNode head, int k) {
        if (k <= 0 || head == null) {
            return head;
        }

        // find the length, and a pointer to old tail
        int len = 1;
        ListNode oldTail = head;
        while (oldTail.next != null) {
            oldTail = oldTail.next;
            len++;
        };

        // check to see if list returned would be the same as the one given
        if (k == len || k % len == 0) {
            return head;
        }

        // figure out which node is the new tail
        int nthFromEnd = k % len;
        int nthFromBeginning = len - nthFromEnd;
        ListNode newTail = head;
        for (int i = 1; i < nthFromBeginning; i++) {
            newTail = newTail.next;
        }

        // figure out which node is the new head
        ListNode newHead = newTail.next;

        // change the two links we need to change
        newTail.next = null;
        oldTail.next = head;

        return newHead;
    }

    public static void main(String[] args) {
        ListRotator lr = new ListRotator();

        ListNode one = new ListNode(1);
        ListNode two = new ListNode(2);
        ListNode three = new ListNode(3);
        ListNode four = new ListNode(4);
        ListNode five = new ListNode(5);
        one.next = two;
        two.next = three;
        three.next = four;
        four.next = five;

        ListNode printHead = lr.rotateRight(one, 2);
        System.out.print("[");
        while (printHead != null) {
            System.out.print(" " + printHead.val);
            printHead = printHead.next;
        }
        System.out.println(" ]");
    }
}