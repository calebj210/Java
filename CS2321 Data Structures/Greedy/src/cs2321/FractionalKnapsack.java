package cs2321;

import net.datastructures.*;
/**
 * Greedy Fractional Knapsack solver
 * 
 * @author: Caleb Jacobs (cajacobs)
 * Date last modified: 03/15/2021
 * Course: CS2321 Spring 2021
 */
public class FractionalKnapsack {
	/**
	 * Goal: Choose items with maximum total benefit but with weight at most W.
	 *       You are allowed to take fractional amounts from items.
	 *       
	 * @param items items[i][0] is weight for item i
	 *              items[i][1] is benefit for item i
	 * @param knapsackWeight - maximum weight of knapsack
	 * @return The maximum total benefit.
	 * 		 
	 */
	public static double MaximumValue(int[][] items, int knapsackWeight) {
		// Check for null and empty items list
		if (items == null || items.length == 0) {
			return 0.0;
		}

		// Create and populate priority queue with items
		HeapAPQ<Double, Integer> Q = new HeapAPQ<Double, Integer>();
		for (int i = 0; i < items.length; i++) {
			Q.insert(-(double)items[i][1] / items[i][0], i); // Know, the unit value has been negative to correct for the comparator
		}
		
		double w = 0;								// Current weight of knapsack
		int W = knapsackWeight;						// Max weight of knapsack
		double maxBenefit = 0;						// Total benefit of items in knapsack
		
		// Begin filling the knapsack
		while (w < W && !(Q.isEmpty())) {
			Entry<Double, Integer> tmp = Q.removeMin();	// Current item to add to knapsack
			int w_i = items[tmp.getValue()][0];         // Total weight of item
			double x_i = w_i;							// Weight to be added to the knapsack
			if (x_i > W - w) {
				x_i = W - w;
			}
			w += x_i;									// Increment weight of knapsack
			maxBenefit -= tmp.getKey() * x_i;			// Increment benefit of knapsack
			
		}
		
		return maxBenefit;
	}
	
	public static void main(String[] args) {
		int[][] inputItems = {{4,12},{8,32},{2,40},{6,30},{1,50}};
		int inputWeight = 10;
		System.out.print("" + MaximumValue(inputItems, inputWeight));
	}
}
