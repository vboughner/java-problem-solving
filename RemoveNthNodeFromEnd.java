


/*
 https://leetcode.com/problems/remove-nth-node-from-end-of-list/description/

 Given a linked list, remove the nth node from the end of list and return its head.

 For example,

    Given linked list: 1->2->3->4->5, and n = 2.

    After removing the second node from the end, the linked list becomes 1->2->3->5.

 Note:
 Given n will always be valid.
 Try to do this in one pass.

 * Algorithm:
 * run two pointers, one that follows n nodes behind the other
 * and keeps track of the one that n nodes behind the current one,
 * to deal with the beginning of the list, count how many nodes we pass,
 * and use the head to get going once we pass n nodes
 */

// definition for singly-linked list
class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}

class RemoveNthNodeFromEnd {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode nthFromEndPrev = head, nthFromEndNext = head; // nthFromEnd is the node to remove
        ListNode current = head;
        int count = 0;

        while (current != null) {
            count++;
            if (count >= n) {
                nthFromEndNext = nthFromEndNext.next;
            }
            if (count > n + 1) {
                nthFromEndPrev = nthFromEndPrev.next;
            }
            current = current.next;
        }

        if (count < n) {
            System.out.println("Error: there are not at least n nodes");
            return head;

        }
        else if (count == n) {
            return head.next;
        }
        else {
            nthFromEndPrev.next = nthFromEndNext;
            return head;
        }
    }

    public static void main(String[] args) {
        ListNode one = new ListNode(1);
        ListNode two = new ListNode(2);
        ListNode three = new ListNode(3);
        ListNode four = new ListNode(4);
        ListNode five = new ListNode(5);
        one.next = two;
        two.next = three;
        three.next = four;
        four.next = five;

        RemoveNthNodeFromEnd rnfe = new RemoveNthNodeFromEnd();
        ListNode result = rnfe.removeNthFromEnd(one, 2);
        ListNode current = result;
        System.out.print("[ ");
        while (current != null) {
            System.out.print(current.val + " ");
            current = current.next;
        }
        System.out.println("]");
    }
}