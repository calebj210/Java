package cs2321;

import net.datastructures.Queue;

/**
 * Circular array queue data structure
 * 
 * Date last modified: 02/02/2021
 * CS2321 Spring 2021
 * 
 * @author Caleb Jacobs (cajacobs)
 * @param <E> - Type to be stored
 */

public class CircularArrayQueue<E> implements Queue<E> {
	private E[] data;									// Data array
	private int front = 0;								// Index to front of array
	private int size = 0;								// Number of elements in array
	
	/**
	 * Array constructor with specified array size
	 * 
	 * @param queueSize - capacity of array to make
	 */
	public CircularArrayQueue(int queueSize) {
		data = (E[]) new Object[queueSize];
	}
	
	/**
	 * Default constructor which initializes an array of size 16
	 */
	public CircularArrayQueue() {
		data = (E[]) new Object[16];
	}
	
	/**
	 * Get current number of elements in queue
	 * 
	 * @return number of elements in queue
	 */
	@Override
	public int size() {
		return size;
	}

	/**
	 * Check if queue is empty
	 * 
	 * @return true if queue is empty
	 */
	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * Get the first element in the queue
	 * 
	 * @return the first element in the queue
	 */
	@Override
	public E first() {
		if (isEmpty()) {						// Return null if queue is empty
			return null;
		}
		return data[front];						// Return front element
	}

	/**
	 * Remove the front element in the queue
	 * 
	 * @return the element removed from queue
	 */
	@Override
	public E dequeue() {
		if (isEmpty()) {						// Return null if queue is empty
			return null;
		}
		E answer = data[front];					// Store front element in queue
		data[front] = null;						// Remove front element
		front = (front + 1) % data.length;		// Update the front index
		size--;									// Decrease size of array
		return answer;
	}
	
	/**
	 * Add an element to the queue
	 * 
	 * @param e - element to add to queue
	 * @throws IllegalStateException if queue is already full
	 */
	@Override
	public void enqueue(E e) throws IllegalStateException {
		if (size == data.length) {								// Check if queue has space
			throw new IllegalStateException("Queue is full");
		}
		int avail = (front + size) % data.length;				// Compute the index of the end of the queue
		data[avail] = e;										// Add element to end of queue
		size++;													// Increase size of queue
	}
}
