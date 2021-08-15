package cs2321;

import net.datastructures.Stack;

/**
 * Stack data structure implemented with a doubly linked list.
 * 
 * @author Caleb Jacobs (cajacobs)
 * Date last modified: 02/16/2021
 * Course: CS2321 Spring 2021
 * 
 * @param <E> Element type to be stored in the stack
 */

public class DLLStack<E> implements Stack<E> {
	private DoublyLinkedList<E> stack;       // Doubly linked list to store stack
	
	/**
	 * Constructor for initializing stack
	 */
	public DLLStack() {
		stack = new DoublyLinkedList<E>();
	}
	
	/**
	 * Get number of elements in the stack
	 * 
	 * @return - number of elements in the stack
	 */
	@Override
	public int size() {
		return stack.size();
	}

	/**
	 * Check if stack is empty
	 * 
	 * @return - true if stack is empty
	 */
	@Override
	public boolean isEmpty() {
		return stack.isEmpty();
	}
	
	/**
	 * Add an element to the top of the stack
	 * 
	 * @param e - element to add to stack
	 */
	@Override
	public void push(E e) {
		stack.addLast(e);
	}

	/**
	 * Return the element on the top of the stack
	 * 
	 * @return - the top stack element, returns null if stack is empty
	 */
	@Override
	public E top() {
		if (!stack.isEmpty()) {
			return stack.last().getElement();
		} else {
			return null;
		}
	}

	/**
	 * Return and remove an element from the top of the stack
	 * 
	 * @return - the to stack element, returns null if stack is empty
	 */
	@Override
	public E pop() {
		if (!stack.isEmpty()) {
			return stack.removeLast();
		} else {
			return null;
		}
	}

}
