package cs2321;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Tests for FractionalKnapsack packer
 * 
 * @author: Caleb Jacobs (cajacobs)
 * Date last modified: 03/15/2021
 * Course: CS2321 Spring 2021
 */

public class FractionalKnapsackTest {
	/**
	 * Test null knapsack
	 */
	@Test
	public void nullTest() {
		int[][] inputItems = null;
		int inputWeight = 10;
		double output = FractionalKnapsack.MaximumValue(inputItems, inputWeight);
		double expected = 0.0;
		
		assertEquals(output, expected, 0.0000001);
	}
	
	/**
	 * Test empty knapsack
	 */
	@Test
	public void emptyTest() {
		int[][] inputItems = {};
		int inputWeight = 10;
		double output = FractionalKnapsack.MaximumValue(inputItems, inputWeight);
		double expected = 0.0;
		
		assertEquals(output, expected, 0.0000001);
	}

	/**
	 * Test one item with excess weight capacity
	 */
	@Test
	public void oneItemTest() {
		int[][] inputItems = {{3,10}};
		int inputWeight = 15;
		double output = FractionalKnapsack.MaximumValue(inputItems, inputWeight);
		double expected = 10.0;
		
		assertEquals(output, expected, 0.0000001);
	}
	
	/**
	 * Test one item with exactly enough weight
	 */
	@Test
	public void oneItemExactTest() {
		int[][] inputItems = {{3,10}};
		int inputWeight = 3;
		double output = FractionalKnapsack.MaximumValue(inputItems, inputWeight);
		double expected = 10.0;
		
		assertEquals(output, expected, 0.0000001);
	}
	
	/**
	 * Test one item with not enough weight
	 */
	@Test
	public void oneItemToBigTest() {
		int[][] inputItems = {{20,10}};
		int inputWeight = 10;
		double output = FractionalKnapsack.MaximumValue(inputItems, inputWeight);
		double expected = 5.0;
		
		assertEquals(output, expected, 0.0000001);
	}
	
	/**
	 * Many items test
	 */
	@Test
	public void manyItemsTest() {
		int[][] inputItems = {{4,12},{8,32},{2,40},{6,30},{1,50}};
		int inputWeight = 10;
		double output = FractionalKnapsack.MaximumValue(inputItems, inputWeight);
		double expected = 124.0;
		
		assertEquals(output, expected, 0.0000001);
	}
}
