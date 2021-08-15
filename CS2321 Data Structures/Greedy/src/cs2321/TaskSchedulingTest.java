package cs2321;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Tests for task scheduler
 * 
 * @author: Caleb Jacobs (cajacobs)
 * Date last modified: 03/15/2021
 * Course: CS2321 Spring 2021
 */

public class TaskSchedulingTest {
	/**
	 * Test empty list of tasks
	 */
	@Test
	public void emptyTaskTest() {
		int[][] input = {};
		int output = TaskScheduling.NumOfMachines(input);
		int expected = 0;
		
		assertEquals(output, expected);
	}
	
	/**
	 * Test null list of tasks
	 */
	@Test
	public void nullTaskTest() {
		int[][] input = null;
		int output = TaskScheduling.NumOfMachines(input);
		int expected = 0;
		
		assertEquals(output, expected);
	}

	/**
	 * Test one task
	 */
	@Test
	public void oneTaskTest() {
		int[][] input = {{1,10}};
		int output = TaskScheduling.NumOfMachines(input);
		int expected = 1;
		
		assertEquals(output, expected);
	}
	
	/**
	 * Test many identical tasks
	 */
	@Test
	public void manyIdenticalTasksTest() {
		int[][] input = {{1,10}, {1,10}, {1,10}, {1,10}, {1,10}};
		int output = TaskScheduling.NumOfMachines(input);
		int expected = 5;
		
		assertEquals(output, expected);
	}
	
	/**
	 * Test many twice identical tasks
	 */
	@Test
	public void manyTwiceIdenticalTasksTest() {
		int[][] input = {{1,10}, {1,10}, {1,10}, {1,10}, {1,10}, {0,1}, {0,1}, {0,1}, {0,1}, {0,1}};
		int output = TaskScheduling.NumOfMachines(input);
		int expected = 5;
		
		assertEquals(output, expected);
	}
	
	/**
	 * Test many tasks
	 */
	@Test
	public void manyTasksTest() {
		int[][] input = {{3,7}, {1,4}, {1,3}, {2,5}, {4,7}, {6,9}, {7,8}, {1,3}};
		int output = TaskScheduling.NumOfMachines(input);
		int expected = 4;
		
		assertEquals(output, expected);
	}
	
	/**
	 * Test many no-time tasks
	 */
	@Test
	public void manyNoTimeTasksTest() {
		int[][] input = {{1,1}, {1,1}, {1,1}, {1,1}, {1,1}};
		int output = TaskScheduling.NumOfMachines(input);
		int expected = 1;
		
		assertEquals(output, expected);
	}
}
