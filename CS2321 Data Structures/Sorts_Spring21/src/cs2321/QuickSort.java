package cs2321;

/**
 * QuickSort algorithm for sorting comparable data.
 * 
 * @author Caleb Jacobs
 * Date last modified: 03/23/2021
 * CS2321 Spring 2021
 *
 * @param <E> Type of data to be sorted
 */

public class QuickSort<E extends Comparable<E>> implements Sorter<E> {
	/**
	 * Perform quick sort.
	 * @param array - Array to sort
	 */
	@TimeComplexity("O(n^2)")
	@TimeComplexityExpected("O(n log(n))")
	public void sort(E[] array) {
		/* TCJ
		 * QuickSort worst and average time complexity is rooted in the
		 * complexity of the partition method. The partition method has 
		 * a worst case of O(n). Now, if the pivot chosen for each partition
		 * is the largest element in the "sub-array," the next partition that
		 * we need to work with is just one element smaller. If this repeats,
		 * until the end, we will have a time complexity of
		 * n + n-1 + n-2 + ... + 1 = O(n^2). If instead we want to focus on
		 * the average time complexity, let's look at the kind of pivots
		 * we would expect. On average, the pivot will be roughly in
		 * the middle of the values in the sub-array. In this case, the
		 * partition will be split into roughly equal parts. These parts
		 * on average will be split in half again. So, our call tree will
		 * similar to a complete binary tree and so there will have a log(n)
		 * height. At each layer, the partition calls will add up to O(n)
		 * per layer and so we a total time sum of log(n) O(n) terms which
		 * is equal to O(n log(n)).
		 */
		
		quickSort(array, 0, array.length - 1);
	}
	
	/**
	 * Sort a sub-array of a given array using a QuickSort algorithm
	 * 
	 * @param A - array to have a segment sorted
	 * @param p - left index of segment to sort
	 * @param q - right index of segment to sort
	 */
	@TimeComplexity("O(n^2)")
	@TimeComplexityExpected("O(n log(n))")
	private void quickSort(E[] A, int p, int q) {
		/* TCJ
		 * See time complexity for the initial sort function above.
		 */
		
		if (p < q) {
			int r = partition(A, p, q);
			quickSort(A, p, r - 1);
			quickSort(A, r + 1, q);
		}
	}
	
	/**
	 * Partition a sub-array of a larger array
	 * @param A - array that contains the sub-array to be partitioned
	 * @param p - lower index of sub-array
	 * @param q - upper index of sub-array
	 * @return the index of the pivot
	 */
	@TimeComplexity("O(n)")
	private int partition(E[] A, int p, int q) {
		/* TCJ
		 * The time complexity of partition is O(n). To see this,
		 * observe that we need to make roughly n comparisons
		 * to partition a segment of the array. The while loops
		 * can only run so many times before j < i in which case it
		 * stops. So the worst case is given by a single increase of i
		 * and a single decrease of j per loop. Then, after n/2 steps,
		 * we will have j < i where n is the number of elements in the 
		 * sub-array. n/2 steps is equivalent to O(n).
		 */
		
		E pivot = A[q];				// Pivot entry in array
		int i = p;					// Left scanning index
		int j = q - 1;				// Right scanning index
		
		// Begin flipping smaller and larger values to there respective sides
		while (i <= j) {
			// Find leftmost entry that is larger than pivot
			while (i <= j && A[i].compareTo(pivot) <= 0) {
				i++;
			}
			
			// Find rightmost entry that is smaller than pivot
			while (i <= j && A[j].compareTo(pivot) >= 0) {
				j--;
			}
			
			// Swap larger and smaller values
			if (i < j) {
				swap(A, i, j);
				i++;
				j--;
			}
		}
		
		// Swap the pivot into its correct position
		swap(A, q, i);
		
		return i;
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
