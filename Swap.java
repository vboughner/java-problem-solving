/*
 *
 * Given a linked list, swap every two adjacent nodes and return its head.
 *
 * For example,
 * Given 1->2->3->4, you should return the list as 2->1->4->3.
 *
 * Your algorithm should use only constant space. You may not modify the values in the list, only nodes itself can be changed. 
 */

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
	val = x;
    }
}


public class Swap {
    public ListNode swapPairs(ListNode head) {
	// save the original head + 1
	// see if head is null
	// see if head + 1 is null
	// swap them and advance head to the next one
	// loop again
	if (head == null || head.next == null) {
	    return head;
	}
	ListNode retval = head.next;
	ListNode prev = null;

	while (head != null && head.next != null) {
	    ListNode first = head;
	    ListNode second = head.next;

	    // updatee the pointer from before these two
	    if (prev != null) {
		prev.next = second;
	    }

	    // swap the next pointers (order matters)
	    first.next = second.next;
	    second.next = first;

	    // get ready for the next round
	    head = first.next;
	    prev = first;
	}
	
	return retval;
    }

    public void printList(ListNode head) {
	if (head != null) {
	    do {
		System.out.print(head.val + " ");
		head = head.next;
	    } while (head != null);
	    System.out.println();
	}
	else {
	    System.out.println("empty list");
	}
    }

    public static void main(String[] args) {
	Swap s = new Swap();

	ListNode a1 = new ListNode(1);
	ListNode a2 = new ListNode(2);
	ListNode a3 = new ListNode(3);
	ListNode a4 = new ListNode(4);

	a1.next = a2;
	a2.next = a3;
	a3.next = a4;

	System.out.print(" starting with: ");
	s.printList(a1);
	ListNode result = s.swapPairs(a1);
	System.out.print("after swapping: ");
	s.printList(result);

	System.out.print(" starting with: ");
	s.printList(null);
	result = s.swapPairs(null);
	System.out.print("after swapping: ");
	s.printList(result);

	ListNode b1 = new ListNode(1);
	ListNode b2 = new ListNode(2);
	ListNode b3 = new ListNode(3);
	b1.next = b2;
	b2.next = b3;

	System.out.print(" starting with: ");
	s.printList(b1);
	result = s.swapPairs(b1);
	System.out.print("after swapping: ");
	s.printList(result);

	System.out.print(" starting with: ");
	s.printList(a2);
	result = s.swapPairs(a2);
	System.out.print("after swapping: ");
	s.printList(result);

	System.out.print(" starting with: ");
	s.printList(a4);
	result = s.swapPairs(a4);
	System.out.print("after swapping: ");
	s.printList(result);
    }
}