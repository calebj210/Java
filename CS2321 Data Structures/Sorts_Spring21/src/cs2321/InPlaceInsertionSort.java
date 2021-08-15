package cs2321;

/**
 * In Place Insertion Sort algorithm for sorting comparable data.
 * 
 * @author Caleb Jacobs
 * Date last modified: 03/23/2021
 * CS2321 Spring 2021
 *
 * @param <E> Type of data to be sorted
 */

public class InPlaceInsertionSort<E extends Comparable<E>> implements Sorter<E> {

	/**
	 * sort - Perform an in-place insertion sort
	 * @param array - Array to sort
	 */
	@TimeComplexity("O(n^2)")
	public void sort(E[] array) {
		/* TCJ
		 * Similarly to Selection Sort, Insertion Sort has a time complexity of
		 * O(n^2). To see this, recognize that insertion sort requires a nested 
		 * which can compare each sorted element at each loop. A maximum of n 
		 * loops will be needed and each loop can have k comparisons which leads 
		 * to a time cost of 1 + 2 + ... + n - 1 = O(n^2).
		 */
		int n = array.length;			// Length of array
		
		for (int i = 1; i < n; i++) {
			E cur = array[i];			// Array element to be sorted
			
			int j = i - 1;				// Index of last sorted element
			
			// Swap elements until the jth element is in the right spot
			while (j >= 0 && array[j].compareTo(cur) > 0) {
				array[j+1] = array[j];
				j--;
			}
			array[j + 1] = cur;
		}
	}

}
