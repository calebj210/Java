import java.util.LinkedList;

/**
 * Personal buffer queue data structure.
 *
 * Date Last Modified: November 28, 2020
 * @author Caleb Jacobs
 *
 * CS1131 Fall 2020
 * Lab Section: L03
 */
public class Queue<E> implements QueueInterface<E> {
    private int sizeLimit = 0;                           // Max size of queue
    private LinkedList<E> queue = new LinkedList<E>();      // Buffer queue

    /**
     * Set max size of buffer queue
     *
     * @param sizeLimit Limit on the number of elements in queue
     */
    Queue(int sizeLimit) {
        this.sizeLimit = sizeLimit;
    }

    /**
     * Adds one element to the rear of this queue.
     *
     * @param element the element to be added to the rear of the queue
     */
    @Override
    public void enqueue(E element) throws QueueFullException {
        if (queue.size() < sizeLimit) {
            queue.addFirst(element);
        } else {
            throw new QueueFullException();
        }
    }

    /**
     * Removes and returns the element at the front of this queue.
     *
     * @return the element at the front of the queue
     */
    @Override
    public E dequeue() throws QueueEmptyException {
        if (!queue.isEmpty()) {
            return queue.removeLast();
        } else {
            throw new QueueEmptyException();
        }
    }

    /**
     * Returns without removing the element at the front of this queue.
     *
     * @return the first element in the queue
     */
    @Override
    public E peek() throws QueueEmptyException {
        if (!queue.isEmpty()) {
            return queue.getLast();
        } else {
            throw new QueueEmptyException();
        }
    }

    /**
     * Returns true if this queue contains no elements.
     *
     * @return true if this queue is empty
     */
    @Override
    public boolean isEmpty() {
        return queue.isEmpty();
    }

    /**
     * Returns true if this queue contains the maximum number of elements.
     *
     * @return true if this queue is full
     */
    @Override
    public boolean isFull() {
        return (!(queue.size() < sizeLimit));
    }

    /**
     * Returns the number of elements in this queue.
     *
     * @return the integer representation of the size of the queue
     */
    @Override
    public int size() {
        return queue.size();
    }

    /**
     * Get string of buffer queue
     *
     * @return string of buffer queue
     */
    public String toString() {
        return queue.toString();
    }
}
