import java.util.ArrayList;

/**
 * Program: Implementing Sort
 * 1. Implement Insertion Sort.
 * 2. Implement Shell Sort.
 * 3. Develop your own efficient sorting algorithm.
 * 4. Analyze the algorithmic efficiency ofthe above sorts.
 *
 * Course:
 * Lab Section:
 * @author
 */
public interface SortInterface {
   /**
    * Implementation of the Insertion Sort algorithm
    * Sort data in the range lowindex to highindex, inclusive.
    * Sort in ascending order unless reversed is true.
    * @param list - ArrayList containing data to be sorted.
    * @param lowindex - lower index of elements to be sorted
    * @param highindex - upper index of elements to be sorted
    * @param reversed - if true, sort in descending order, otherwise sort in ascending order.
    *
    * Growth Function:
    * Big-Oh:
    * Explanation of analysis:
    */
   public void insertionsort ( ArrayList< Integer > list, int lowindex, int highindex, boolean reversed );

   /**
    * Implementation of the Shell Sort algorithm
    * Sort data in the range lowindex to highindex, inclusive.
    * Sort in ascending order unless reversed is true.
    * @param list - ArrayList containing data to be sorted.
    * @param lowindex - lower index of elements to be sorted
    * @param highindex - upper index of elements to be sorted
    * @param reversed - if true, sort in descending order, otherwise sort in ascending order.
    *
    * Growth Function:
    * Big-Oh:
    * Explanation of analysis:
    */
   public void shellsort ( ArrayList< Integer > list, int lowindex, int highindex, boolean reversed );

   /**
    * Custom implementation of an original, efficient Sort algorithm
    * Sort data in the range lowindex to highindex, inclusive.
    * Sort in ascending order unless reversed is true.
    * @param list - ArrayList containing data to be sorted.
    * @param lowindex - lower index of elements to be sorted
    * @param highindex - upper index of elements to be sorted
    * @param reversed - if true, sort in descending order, otherwise sort in ascending order.
    *
    * Growth Function:
    * Big-Oh:
    * Explanation of analysis:
    */
   public void mysort ( ArrayList< Integer > list, int lowindex, int highindex, boolean reversed );
}