/**
 * Remove duplicates in an array
 * 
 * Date Last Modified: 01/23/2021
 * @author Caleb Jacobs
 * 
 * CS2321
 * Spring 2021
 */

public class ArrayProblems {

	/*
	Given an array of integers, generate a new array with all duplicates removed except the first appearance of each number.
	

	Example 1:
	Input:  [5, 2, 5, 3, 1]
	output: [5, 2, 3, 1]

	
	Example 2:
	Input:  [5, 1, 5, 1, 2, 1, 0, 4, 0]
	output: [5, 1, 2, 0, 4]
	*/
	
	public static int[] deleteDuplicates(int[] nums) {
		int uniqueCount = 0;							// Count of unique integers in given array
		int[] tmpNums = new int[nums.length];			// Unsized non-duplicate array
		
		for (int i = 0; i < nums.length; i++) {
			boolean dup = false;						// Duplicate check
			int tmp = nums[i];							// Array value to check
			
			// Check if current number is a duplicate
			for (int j = 0; j < uniqueCount; j++) {
				if (tmp == tmpNums[j]) {
					dup = true;
					break;
				}
			}
			
			// Store non-duplicate value
			if (!dup) {
				tmpNums[uniqueCount++] = tmp;
			}
		}
		
		// Trim array to proper size
		int[] newNums = new int[uniqueCount];
		for (int i = 0; i < uniqueCount; i++) {
			newNums[i] = tmpNums[i];
		}
		
		return newNums;
	}
}
