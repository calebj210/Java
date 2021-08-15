import java.util.ArrayList;

/**
 * Program: Implementing Sort
 * 1. Implement Insertion Sort.
 * 2. Implement Shell Sort.
 * 3. Develop your own efficient sorting algorithm.
 * 4. Analyze the algorithmic efficiency of the above sorts.
 *
 * Course: CS1131
 * Lab Section: L03
 * @author Caleb Jacobs
 */
public class Sort implements SortInterface {

    /**
     * Implementation of the Insertion Sort algorithm
     * Sort data in the range lowindex to highindex, inclusive.
     * Sort in ascending order unless reversed is true.
     *
     * @param list      - ArrayList containing data to be sorted.
     * @param lowindex  - lower index of elements to be sorted
     * @param highindex - upper index of elements to be sorted
     * @param reversed  - if true, sort in descending order, otherwise sort in ascending order.
     *                  <p>
     *                  Growth Function: T(n) = n^2 - n + cn - c = O(n^2)
     *                  Big-Oh: O(n^2)
     */
    @Override
    public void insertionsort(ArrayList<Integer> list, int lowindex, int highindex, boolean reversed) {
        for (int i = lowindex + 1; i <= highindex; i++) {
            int tmp = list.get(i);
            int j = i;
            while (j > lowindex && list.get(j - 1) > tmp) {
                list.set(j, list.get(j - 1));
                j--;
            }
            list.set(j, tmp);
        }

        // Reverse list if required
        if (reversed) {
            reverse(list, lowindex, highindex);
        }
    }

    /**
     * Implementation of the Shell Sort algorithm
     * Sort data in the range lowindex to highindex, inclusive.
     * Sort in ascending order unless reversed is true.
     *
     * @param list      - ArrayList containing data to be sorted.
     * @param lowindex  - lower index of elements to be sorted
     * @param highindex - upper index of elements to be sorted
     * @param reversed  - if true, sort in descending order, otherwise sort in ascending order.
     *                  <p>
     *                  Growth Function: T(n) = O(n^2)
     *                  Big-Oh: O(n^2)
     */
    @Override
    public void shellsort(ArrayList<Integer> list, int lowindex, int highindex, boolean reversed) {
        int size = highindex - lowindex;

        // Compute initial gap
        int gap = 0;
        while (gap < (size + 1)/2) {
            gap = 2*(gap + 1) - 1;
        }

        while (gap > 0) {
            for (int k = 0; k < gap; k++) {
                for (int i = lowindex + k + gap; i <= highindex; i += gap) {
                    int tmp = list.get(i);
                    int j = i;
                    for (; j >= lowindex + k + gap; j -= gap) {
                        if (list.get(j - gap) > tmp) {
                            list.set(j, list.get(j - gap));
                        } else {
                            break;
                        }
                    }

                    if (j != i) {
                        list.set(j, tmp);
                    }
                }
            }

            gap = (gap + 1)/2 - 1;
        }

        // Reverse list if required
        if (reversed) {
            reverse(list, lowindex, highindex);
        }
    }

    /**
     * Custom implementation of an original, efficient Sort algorithm
     * Sort data in the range lowindex to highindex, inclusive.
     * Sort in ascending order unless reversed is true.
     *
     * @param list      - ArrayList containing data to be sorted.
     * @param lowindex  - lower index of elements to be sorted
     * @param highindex - upper index of elements to be sorted
     * @param reversed  - if true, sort in descending order, otherwise sort in ascending order.
     *                  <p>
     *                  Growth Function: T(n) = O(n log(n)) + O(n/10) = O(n log(n) + n/10) = O(n log(n))
     *                  Big-Oh: O(n log(n))
     */
    @Override
    public void mysort(ArrayList<Integer> list, int lowindex, int highindex, boolean reversed) {
        modifiedQuicksort(list, lowindex, highindex);

        // Reverse list if required
        if (reversed) {
            reverse(list, lowindex, highindex);
        }
    }

    /**
     * Hybrid quicksort/insertionsort method that uses quicksort when the unsorted array is larger
     * than 10 elements and then switches to insertionsort to finish the sorting of each subarray.
     * @param list - list to be sorted.
     * @param low - low bound on array indices to be sorted.
     * @param high - upper bound on array indices to be sorted.
     */
    public void modifiedQuicksort(ArrayList<Integer> list, int low, int high) {
        // Use insertion sort if sorting space is less than 10
        if (high - low < 10) {
            insertionsort(list, low, high, false);
        } else if (high > low) {
            int pivotIdx = partition(list, low, high);
            modifiedQuicksort(list, low, pivotIdx - 1);
            modifiedQuicksort(list, pivotIdx + 1, high);
        }
    }

    /**
     * Array partitioning method for quicksort. Splits ArrayList into two partitions about a pivot.
     * @param list - list to be partitioned/pivoted.
     * @param first - lower bound on indices of array to be partitioned.
     * @param last - upper bound on indices of array to be partitioned.
     * @return - partition pivot index.
     */
    public int partition(ArrayList<Integer> list, int first, int last) {
        int pivot = list.get(first);
        int low = first + 1;
        int high = last;

        while (high > low) {
            while (low <= high && list.get(low) <= pivot) {
                low++;
            }

            while (low <= high && list.get(high) > pivot) {
                high--;
            }

            if (high > low) {
                int tmp = list.get(high);
                list.set(high, list.get(low));
                list.set(low, tmp);
            }
        }

        while (high > first && list.get(high) >= pivot) {
            high--;
        }

        if (pivot > list.get(high)) {
            list.set(first, list.get(high));
            list.set(high, pivot);
            return high;
        } else {
            return first;
        }
    }

    /**
     * Reverse the order of elements in a subarray.
     *
     * @param list - ArrayList to be reversed.
     * @param low - lower index of subarray.
     * @param high - upper index of subarray.
     */
    public void reverse(ArrayList<Integer> list, int low, int high) {
        for (int i = 0; i <= (high - low) / 2; i++) {
            int tmp = list.get(high - i);
            list.set(high - i, list.get(low + i));
            list.set(low + i, tmp);
        }
    }
}
