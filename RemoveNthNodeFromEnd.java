


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
 * add a dummy node to the front of the list to make handling corner cases easier,
 * run two pointers, one that follows n nodes behind the other
 * and keeps track of the node that is n + 1 nodes behind the current one,
 * at the end, remove the nth node by linking the n-1 node to the n+1 node
 */

// definition for singly-linked list
class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}

class RemoveNthNodeFromEnd {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode front = dummy, behind = dummy;

        // assumption: n is always valid (otherwise we'd put some error detection in here)

        // move the front pointer n nodes ahead
        for (int i = 0; i < n; i++) {
            front = front.next;
        }

        // continue, while moving both nodes, until we read the end
        while (front.next != null) {
            front = front.next;
            behind = behind.next;
        }

        // remove the node
        behind.next = behind.next.next;
        return dummy.next;
    }

    public static void main(String[] args) {
        RemoveNthNodeFromEnd rnfe = new RemoveNthNodeFromEnd();

        ListNode one = new ListNode(1);
        ListNode two = new ListNode(2);
        ListNode three = new ListNode(3);
        ListNode four = new ListNode(4);
        ListNode five = new ListNode(5);
        one.next = two;
        two.next = three;
        three.next = four;
        four.next = five;


        ListNode result = rnfe.removeNthFromEnd(one, 2);
        ListNode current = result;
        System.out.print("[ ");
        while (current != null) {
            System.out.print(current.val + " ");
            current = current.next;
        }
        System.out.println("]");


        result = rnfe.removeNthFromEnd(five, 1);
        current = result;
        System.out.print("[ ");
        while (current != null) {
            System.out.print(current.val + " ");
            current = current.next;
        }
        System.out.println("]");
    }
}