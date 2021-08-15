package cs2321;

/**
 * Heap PQ Sort algorithm for sorting comparable data.
 * 
 * @author Caleb Jacobs
 * Date last modified: 03/23/2021
 * CS2321 Spring 2021
 *
 * @param <E> Type of data to be sorted
 */

public class HeapPQSort<E extends Comparable<E>> implements Sorter<E> {

	/**
	 * sort - Perform an PrioiryQueue Sort using the heap implementation of PQ.
	 * @param array - Array to sort
	 */
	@TimeComplexity("O(n log(n))")
	public void sort(E[] array) {
		/* TCJ
		 * The time complexity of heap-PQ sort is O(n log(n)). To understand 
		 * this complexity note that we have two loops. The first loop loads
		 * each element into a heap based priority queue (PQ). Inserting items
		 * into a PQ takes O(log(k)) where k is the number of entries in the PQ.
		 * We have to load n elements into the PQ which will roughly take
		 * log(1) + log(2) + ... + log(n) = O(n * log(n)) time. The next loop
		 * Does something very similar, except now we are removing elements from
		 * the PQ. At each removal, we have to downheap which take O(log(n)). So
		 * doing this for each element take log(n - 1) + log(n - 2) + ... + log(1)
		 * which is on the order of O(n log(n)). Combining the two loops is still
		 * O(n log(n)) time complexity.
		 */
		HeapAPQ<E, String> PQ = new HeapAPQ<E, String>();
		
		// Load each element into the PQ
		for (int i = 0; i < array.length; i++) {
			PQ.insert(array[i], "");
		}
		
		// Remove each element from PQ into the array
		for (int i = 0; i < array.length; i++) {
			array[i] = PQ.removeMin().getKey();
		}
	}

}
