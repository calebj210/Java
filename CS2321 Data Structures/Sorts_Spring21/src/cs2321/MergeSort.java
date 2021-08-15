package cs2321;

import java.util.Arrays;

/**
 * A Merge Sort based sorting algorithm. 
 * @author Caleb Jacobs (cajacobs)
 * Date Last Modified: 03/23/2021
 * 
 * CS2321 Spring 2021
 */

public class MergeSort<E extends Comparable<E>> implements Sorter<E> {
	/**
	 * sort - Perform merge sort. -Feel free to create other methods. 
	 * @param array - Array to sort
	 */
	@TimeComplexity("O(n log(n))")
	public void sort(E[] array) {
		/* TCJ
		 * The time complexity of Merge Sort is O(n log(n)). To understand
		 * this time complexity, let's take a look at how merge sort works.
		 * Merge sort takes each element in an array and breaks it down into
		 * units on a binary tree. The height of the binary tree is at most
		 * log(n) where n is the number of elements in the array. Once the 
		 * tree is made, merge sort performs the merge method at each layer
		 * in the tree. Each layer has n elements leading to the total merge
		 * cost being O(n) as described under the merge method below. So we
		 * need to perform merge at each layer of the tree which has log(n)
		 * layers. So the total time cost of merge sort is given by
		 * O(log(n)) O(n) = O(n log(n)).
		 */
		int n = array.length;						// Length of array
		
		// Handle base case
		if (n == 1 || n == 0) {
			return;
		}
		
		int mid = n/2;								// Mid point in array
		E[] A1 = Arrays.copyOfRange(array, 0, mid); // Left half of array
		E[] A2 = Arrays.copyOfRange(array, mid, n); // Right half of array
		
		sort(A1);									// Sort left half of array
		sort(A2);									// Sort right half of array
		
		merge(A1, A2, array);						// Merge left and right arrays
	}
	
	/**
	 * Merge routine for combining two sorted arrays in order.
	 * 
	 * @param A1 - left sorted array
	 * @param A2 - right sorted array
	 * @param A  - total array to combine left and right arrays
	 */
	@TimeComplexity("O(n)")
	public void merge(E[] A1, E[] A2, E[] A) {
		/* TCJ
		 * The time complexity of combining two arrays with merge is O(n)
		 * where n is the total number of elements that we are merging.
		 * Two merge two elements we need to compare each element in the
		 * two arrays which leads to O(n) comparisons. 
		 */
		int n1 = A1.length;			// Length of left array
		int n2 = A2.length;			// Length of right array
		int n  =  A.length;			// Length of total array

		int i = 0;					// Left scanning index
		int j = 0;					// Right scanning index
		int k = 0;					// Total array index
		
		// Add the smallest elements to the total array from A1 and A2
		while (i < n1 && j < n2) {
			if (A1[i].compareTo(A2[j]) <= 0) {
				A[k] = A1[i];
				i++;
			} else {
				A[k] = A2[j];
				j++;
			}
			k++;
		}
		
		// Flush the rest of the left entries
		while (i < n1) {
			A[k] = A1[i];
			i++;
			k++;
		}
		
		// Flush the rest of the right entries
		while (j < n2) {
			A[k] = A2[j];
			j++;
			k++;
		}
	}
}