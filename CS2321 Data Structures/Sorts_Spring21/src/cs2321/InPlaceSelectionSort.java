package cs2321;

/**
 * In Place Selection Sort algorithm for sorting comparable data.
 * 
 * @author Caleb Jacobs
 * Date last modified: 03/23/2021
 * CS2321 Spring 2021
 *
 * @param <E> Type of data to be sorted
 */

public class InPlaceSelectionSort<E extends Comparable<E>> implements Sorter<E> {

	/**
	 * sort - Perform an in-place selection sort
	 * @param array - Array to sort
	 */
	@TimeComplexity("O(n^2)")
	public void sort(E[] array) {
		 /* TCJ
		  * Selection sort requires two loops which requires k steps for the inner loop. Here, k
		  * is the number of unsorted elements at the beginning of each inner loop. At each
		  * inner loop, we sort 1 new element and so the net number of comparison is the sum
		  * of each k which is roughly n-1 + n-2 + n-3 +... + 1 = O(n^2). Thus, selection sort 
		  * has a time complexity of O(n^2).
		 */
		int n = array.length;      		// Length of array
		
		// Begin selecting elements to sort
		for (int i = 0; i < n - 1; i++) {
			int minIdx = i;				// Index of smallest unsorted element
			
			// Find index of smallest value in unsorted data
			for (int j = i + 1; j < n; j++) {
				if (array[minIdx].compareTo(array[j]) > 0) {
					minIdx = j;
				}
			}
			
			// Swap if allowed
			if (minIdx != 1) {
				swap(array, minIdx, i);
			}
		}
	}
	
	/**
	 * Helper: Simple array element swapper 
	 * 
	 * @param array - array to swap elements in
	 * @param i - index of first element to swap
	 * @param j - index of second element to swap
	 */
	@TimeComplexity("O(1)")
	private void swap(E[] array, int i, int j) {
		E tmp = array[i];
		array[i] = array[j];
		array[j] = tmp;
	}

}
