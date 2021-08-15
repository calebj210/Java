package cs2321;

import java.util.Iterator;
import net.datastructures.List;

/**
 * Array List data structure
 * 
 * Date last modified: 02/28/2021
 * CS2321 Spring 2021
 * 
 * @author Caleb Jacobs (cajacobs)
 * @param <E> - type of element to be stored
 */

public class ArrayList<E> implements List<E> {
	private int CAPACITY;				// Array list capacity
	private E[] data;					// Data stored in array list
	private int size = 0;				// Current number of elements in array list
	
	/**
	 * Default constructor for ArrayList
	 * Sets capacity to 16
	 */
	public ArrayList() {
		CAPACITY = 16;
		data = (E[]) new Object[16];
	}
	
	/**
	 * User specified constructor to manually set capacity
	 * 
	 * @param capactity - the capacity of the array list
	 */
	public ArrayList(int capacity) {
		CAPACITY = capacity;
		data = (E[]) new Object[capacity];
	}

	/**
	 * Get current number of elements in the array list
	 * 
	 * @return the size of the array list
	 */
	@Override
	public int size() {
		return size;
	}
	
	/**
	 * Check if array list is empty
	 * 
	 * @return true if the array is empty
	 */
	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * Fetch data at a given index
	 * 
	 * @param i - index to get data at
	 * @return data at index i
	 * @throws IndexOutOfBoundsException if the index is negative or greater than size
	 */
	@Override
	public E get(int i) throws IndexOutOfBoundsException {
		checkIndex(i, size);						// Check validity of index to get
		return data[i];
	}

	/**
	 * Set the value at a given index
	 * 
	 * @param i - index of element to be set
	 * @param e - value to be set to at the index
	 * @return the previous value at index i
	 * @throws IndexOutOfBoundsException if the index is negative or greater than size
	 */
	@Override
	public E set(int i, E e) throws IndexOutOfBoundsException {
		checkIndex(i, size);						// Check index to set
		E temp = data[i];							// Get current value at i
		data[i] = e;								// Set new value at i
		return temp;
	}
	
	/**
	 * Add a new element at the specified index and shift other elements
	 * 
	 * @param i - index of where to add element
	 * @param e - element to be added
	 * @throws IndexOutOfBoundsException if the index is negative or greater than size + 1
	 */
	@Override
	public void add(int i, E e) throws IndexOutOfBoundsException {
		checkIndex(i, size + 1);					// Check index to add at
		if (size == data.length) {					// Resize array if capacity is exceeded
			resize(2 * data.length);
		}
		for (int k = size - 1; k >= i; k--) {		// Shift data to the right
			data[k+1] = data[k];
		}
		data[i] = e;								// Set new element
		size++;										// Increase ArrayList size
	}
	
	/**
	 * Remove element at specified index and shift elements
	 * 
	 * @param i - index of element to be removed
	 * @return the element that was removed
	 * @throws IndexOutOfBoundsException if the index is negative or greater than size
	 */
	@Override
	public E remove(int i) throws IndexOutOfBoundsException {
		checkIndex(i, size);						// Check index to remove
		E temp = data[i];							// Store data to be removed
		for (int k = i; k < size - 1; k++) {		// Shift elements to fill the removed data
			data[k] = data[k+1];
		}
		data[size - 1] = null;						// Remove last entry in array to finish the shift
		size--;										// Reduce the size
		return temp;
	}

	/**
	 * Create a new instance of Iterator
	 * 
	 * @return iterator for ArrayList
	 */
	@Override
	public Iterator<E> iterator() {
		return new Iterate();
	}
	
	/**
	 * Add an element to the front of ArrayList
	 * 
	 * @param e - element to be added to the front
	 */
	public void addFirst(E e)  {
		this.add(0, e);
	}
	
	/**
	 * Add an element to the end of ArrayList
	 * 
	 * @param e - element to be added to the end
	 */
	public void addLast(E e)  {
		this.add(size, e);
	}
	
	/**
	 * Remove the element at the front of ArrayList
	 * 
	 * @return the element removed from the front
	 * @throws IndexOutOfBoundsException if there is no front element
	 */
	public E removeFirst() throws IndexOutOfBoundsException {
		return this.remove(0);
	}
	
	/**
	 * Remove the element at the end of ArrayList
	 * 
	 * @return the element removed from the end
	 * @throws IndexOutOfBoundsException if there is no end element
	 */
	public E removeLast() throws IndexOutOfBoundsException {
		return this.remove(size - 1);
	}
	
	/**
	 * Get the current capacity of ArrayList
	 * 
	 * @return the capacity
	 */
	public int capacity() {
		return CAPACITY;
	}
	
	/**
	 * Get the actual array storing the array list
	 * 
	 * @return array underlying the ArrayList
	 */
	public E[] getArray() {
		@SuppressWarnings("unchecked")
		E[] tmp = (E[])(new Object[size]);
		for (int i = 0; i < size; i++) {
			tmp[i] = data[i];
		}

		return tmp;
	}
	
	/**
	 * Check if a given index is valid for indexing in ArrayList
	 * 
	 * @param i - index to be checked
	 * @param n - size of array to check index
	 * @throws IndexOutOfBoundsException if the index is negative or greater or equal to n
	 */
	protected void checkIndex(int i, int n) throws IndexOutOfBoundsException {
		if (i < 0 || i >= n) {
			throw new IndexOutOfBoundsException("Illegal index: " + i);
		}
	}
	
	/**
	 * Resize array buffer and copy old elements into new array
	 * 
	 * @param capacity - capacity of new array buffer
	 */
	protected void resize(int capacity) {
		E[] temp = (E[]) new Object[capacity];		// Create new empty of array of new capacity
		for (int k = 0; k < size; k++) {			// Copy data
			temp[k] = data[k];
		}
		data = temp;								// Store new array
		
		CAPACITY = capacity;						// Update the capacity
	}
	
	/**
	 * Iterator class for indexing through ArrayList
	 */
	private class Iterate implements Iterator<E>{
		private int i = 0;							// Index to start at
		
		/**
		 * Check if the next element exists in ArrayList
		 * 
		 * @return true if there is a next element
		 */
		@Override
		public boolean hasNext() {
			return i < size;
		}

		/**
		 * Get next element in ArrayList
		 * 
		 * @return the next element
		 */
		@Override
		public E next() {
			return data[i++];
		}
	}
}
