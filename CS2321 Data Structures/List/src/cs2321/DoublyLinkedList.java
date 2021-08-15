package cs2321;
import java.util.Iterator;

import net.datastructures.Position;
import net.datastructures.PositionalList;

/**
 * Doubly linked list based implementation of a positional list data structure
 * Date last modified: 02:02:2021
 * CS2321 Spring 2021
 * 
 * @author Caleb Jacobs (cajacobs)
 * @param <E> - Type of elements to store in list
 */

public class DoublyLinkedList<E> implements PositionalList<E> {
	/**
	 * Position data structure implemented as a node in a doubly linked list
	 *
	 * @param <E> - type of element to be stored in a position
	 */
	private static class Node<E> implements Position<E>{
		private E element;								// Stored element
		private Node<E> prev;							// Previous position
		private Node<E> next;							// Next position
		
		/**
		 * Constructor for creating a new position
		 * 
		 * @param e	- element of position
		 * @param p	- previous position
		 * @param n - next position
		 */
		public Node(E e, Node<E> p, Node<E> n) {
			element = e;
			prev = p;
			next = n;
		}
		
		/**
		 * Get element at current position
		 * 
		 * @return element of current position
		 * @throws IllegalStateException if current position is no longer valid
		 */
		@Override
		public E getElement() throws IllegalStateException {
			// Check if position still exists
			if (next == null) {
				throw new IllegalStateException("Position no longer valid");
			}
			return element;
		}
		
		/**
		 * Get previous position/node
		 * 
		 * @return previous position
		 */
		public Node<E> getPrev() {
			return prev;
		}
		
		/**
		 * Get next position/node
		 * 
		 * @return next position
		 */
		public Node<E> getNext() {
			return next;
		}
		
		/**
		 * Set element of current position/node
		 * 
		 * @param e - value of element to be set
		 */
		public void setElement(E e) {
			element = e;
		}
		
		/**
		 * Set the previous node/position
		 * 
		 * @param p - position/node to set
		 */
		public void setPrev(Node <E> p) {
			prev = p;
		}
		
		/**
		 * Set the next node/position
		 * 
		 * @param p - position/node to set
		 */
		public void setNext(Node <E> n) {
			next = n;
		}
	}
	
	private Node<E> header;					// Head node of doubly linked list
	private Node<E> trailer;				// Tail node of doubly linked list
	private int size = 0;					// Number of nodes in linked list
	
	/**
	 * Default constructor for doubly linked list.
	 */
	public DoublyLinkedList() {
		header = new Node<>(null, null, null);
		trailer = new Node<>(null, header, null);
		header.setNext(trailer);
	}
	
	/**
	 * Check if position is a valid doubly linked list position
	 * 
	 * @param p - position to be validated
	 * @return Node of position
	 * @throws IllegalArgumentException if position is not a valid node
	 */
	private Node<E> validate(Position<E> p) throws IllegalArgumentException {
		if(!(p instanceof Node)) {
			throw new IllegalArgumentException("Invalid p");
		}
		Node<E> node = (Node<E>) p;
		if (node.getNext() == null) {
			throw new IllegalArgumentException("p is no longer in the list");
		}
		return node;
	}
	
	/**
	 * The the position of a node
	 * 
	 * @param node - node to be converted to a position
	 * @return null if node is the head or tail
	 * @return position of node
	 */
	private Position<E> position(Node<E> node) {
		if (node == header || node == trailer) {	// Check if we have a boundardy node
			return null;
		}
		return node;
	}
	
	/**
	 * Get current number of nodes/positions in the list
	 */
	@Override
	public int size() {
		return size;
	}

	/**
	 * Check if list contains any nodes
	 * 
	 * @return true if list contains no nodes
	 */
	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * Get the position of the element
	 * 
	 * @return position of first element
	 */
	@Override
	public Position<E> first() {
		return position(header.getNext());
	}

	/**
	 * Get the position of the last element
	 * 
	 * @return position of last element
	 */
	@Override
	public Position<E> last() {
		return position(trailer.getPrev());
	}
	
	/**
	 * Get the position of the element before the specified position
	 * 
	 * @return position of previous element
	 * @throws IllegalArgumentException if specified position is not a valid node
	 */
	@Override
	public Position<E> before(Position<E> p) throws IllegalArgumentException {
		Node<E> node = validate(p);					// Check validity of position	
		return position(node.getPrev());
	}

	/**
	 * Get the position of the element after the specified position
	 * 
	 * @return position of next element
	 * @throws IllegalArgumentException if specified position is not a valid node
	 */
	@Override
	public Position<E> after(Position<E> p) throws IllegalArgumentException {
		Node<E> node = validate(p);					// Check validity of position
		return position(node.getNext());
	}
	
	/**
	 * Utility method: Add an element between two positions
	 * 
	 * @param e - element to be added
	 * @param pred - preceding node
	 * @param succ - succeeding node
	 * @return position of added element
	 */
	private Position<E> addBetween(E e, Node<E> pred, Node<E> succ) {
		Node<E> newest = new Node<>(e, pred, succ);		// Create new node with specified element
		pred.setNext(newest);							// Update preceding node
		succ.setPrev(newest);							// Update succeeding node
		size++;											// Increment size
		return newest;
	}

	/**
	 * Add element to the front of the positional list
	 * 
	 * @param e - element to be added
	 * @return position of added element
	 */
	@Override
	public Position<E> addFirst(E e) {
		return addBetween(e, header, header.getNext());
	}

	/**
	 * Add element to the end of the positional list
	 * 
	 * @param e - element to be added
	 * @return position of added element
	 */
	@Override
	public Position<E> addLast(E e) {
		return addBetween(e, trailer.getPrev(), trailer);
	}

	/**
	 * Add element to the before the specified position
	 * 
	 * @param p - position to add before
	 * @param e - element to be added
	 * @return position of new added element
	 * @throws IllegalArgumentException if position is not a valid node
	 */
	@Override
	public Position<E> addBefore(Position<E> p, E e) 
			throws IllegalArgumentException {
		Node<E> node = validate(p);						// Check validity of position
		return addBetween(e, node.getPrev(), node);
	}
	
	/**
	 * Add element to the after the specified position
	 * 
	 * @param p - position to add after
	 * @param e - element to be added
	 * @return position of new added element
	 * @throws IllegalArgumentException if position is not a valid node
	 */
	@Override
	public Position<E> addAfter(Position<E> p, E e)
			throws IllegalArgumentException {
		Node<E> node = validate(p);						// Check validity of position
		return addBetween(e, node, node.getNext());
	}
	
	/**
	 * Set element at a specified position
	 * 
	 * @param p - position of element to set
	 * @param e - value to set element to
	 * @return the old element of the specified position
	 * @throws IllegalArgumentException if position is not a valid node
	 */
	@Override
	public E set(Position<E> p, E e) throws IllegalArgumentException {
		Node<E> node = validate(p);						// Check validity of position
		E answer = node.getElement();					// Get old value of at the position
		node.setElement(e);								// Set new element
		return answer;
	}

	/**
	 * Remove the current positional element from the list
	 * 
	 * @return element removed
	 * @throws IllegalArgumentException if position is not a valid node
	 */
	@Override
	public E remove(Position<E> p) throws IllegalArgumentException {
		Node<E> node = validate(p);						// Check validity of position
		Node<E> pred = node.getPrev();					// Previous position
		Node<E> succ = node.getNext();					// Succeeding position
		pred.setNext(succ);								// Set new next node
		succ.setPrev(pred);								// Set new previous node
		size--;											// Reduce size of list
		E answer = node.getElement();					// Get element of removed position
		
		// Deconstruct removed node
		node.setElement(null);
		node.setNext(null);
		node.setPrev(null);
		
		return answer;
	}
	
	/**
	 * Iterator for positional list
	 */
	private class PositionIterator implements Iterator<Position<E>> {
		private Position<E> cursor = first();			// Position of current element
		private Position<E> recent = null;				// Most recent element position
		
		/**
		 * Check if there is a next position
		 * 
		 * @return true if there is a non-null next position
		 */
		@Override
		public boolean hasNext() {
			return (cursor != null);
		}
		
		/**
		 * Get next position
		 * 
		 * @return position of next element in the positional list
		 */
		@Override
		public Position<E> next() {
			recent = cursor;					// Update most recent position
			cursor = after(cursor);				// Set cursor to next position
			return recent;
		}
	}
	
	/**
	 * Positional list iterable
	 */
	private class PositionIterable implements Iterable<Position<E>> {
		/**
		 * Create a new instance of a position iterator
		 */
		public Iterator<Position<E>> iterator() {
			return new PositionIterator();
		}
	}
	
	/**
	 * Instantiate a position iterable
	 */
	@Override
	public Iterable<Position<E>> positions() {
		return new PositionIterable();
	}
	
	/**
	 * Positional list element iterator
	 */
	private class ElementIterator implements Iterator<E>{
		Iterator<Position<E>> posIterator = new PositionIterator(); // Positional list iterator
		
		/**
		 * Check if there is a next element in a list
		 * 
		 * @return true if there is a next element
		 */
		@Override
		public boolean hasNext() {
			return posIterator.hasNext();
		}

		/**
		 * Get next element in positional list
		 */
		@Override
		public E next() {
			return posIterator.next().getElement();
		}	
	}
	
	/**
	 * Create an element iterator for the positional list
	 * 
	 * @return the iterator for the list
	 */
	@Override
	public Iterator<E> iterator() {
		return new ElementIterator();
	}
	
	/**
	 * Remove the first element in the doubly linked list
	 * 
	 * @return
	 * @throws IllegalArgumentException
	 */
	public E removeFirst() throws IllegalArgumentException {
		return remove(header.getNext());
	}
	
	/**
	 * Remove the last element in the doubly linked list
	 * 
	 * @return
	 * @throws IllegalArgumentException
	 */
	public E removeLast() throws IllegalArgumentException {
		return remove(trailer.getPrev());
	}

}
