/**
 * Remove duplicates in a sorted linked list
 * 
 * Date Last Modified: 01/24/2021
 * @author Caleb Jacobs
 * 
 * CS2321
 * Spring 2021
 */

public class LinkedListProblems {

	/*
	Given a sorted (SORTED!!!) linked list, delete all duplicates, keep the first appearance for each element only 

	Example 1:
	Input: 1->1->2
	Output: 1->2

	Example 2:
	Input: 1->1->2->3->3->3
	Output: 1->2->3
	 */
	public static void deleteDuplicates(ListNode head) {
		// Handle empty list
		if (head == null) {
			return;
		}
		
		// Begin removing duplicates
		ListNode tail = head.next;				// Tail of head node
		while (tail != null) {
			if (head.val == tail.val) {
				head.next = tail.next;			// Remove duplicate node
			} else {
				head = head.next;				// Advance head to next node
			}
			tail = tail.next;					// Advance tail to next node
		}
	}
}
