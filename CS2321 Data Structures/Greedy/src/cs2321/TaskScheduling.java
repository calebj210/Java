package cs2321;

import net.datastructures.*;

/**
 * Task scheduler for specifying the minimum number of machines required
 * to schedule a list of tasks
 * 
 * @author: Caleb Jacobs (cajacobs)
 * Date last modified: 03/15/2021
 * Course: CS2321 Spring 2021
 */

public class TaskScheduling {
	/**
	 * Goal: Perform all the tasks using a minimum number of machines. 
	 * 
	 *       
	 * @param tasks tasks[i][0] is start time for task i
	 *              tasks[i][1] is end time for task i
	 * @return The minimum number or machines
	 */
   public static int NumOfMachines(int[][] tasks) {
	   
	   // Handle null task list and empty task list
	   if (tasks == null || tasks.length == 0) {
		   return 0;
	   }
	   
	   // Populate priority queue with tasks
	   HeapAPQ<Integer,Integer> Q1 = new HeapAPQ<Integer,Integer>();
	   for (int i = 0; i < tasks.length; i++) {
		   Q1.insert(tasks[i][0], tasks[i][1]);
	   }

	   // Adaptive priority queue of current scheduled machines and their tasks
	   HeapAPQ<Integer, String> Q2 = new HeapAPQ<Integer, String>();
	   Q2.insert(Q1.removeMin().getValue(), "");
	   
	   int m = 1;				// Number of machines
	   
	   // Begin scheduling tasks
	   while (!Q1.isEmpty()) {
		   Entry<Integer, Integer> tmp = Q1.removeMin();	// Task to schedule
		   Entry<Integer, String> machine = Q2.min();		// Current to schedule on
		   
		   // Decide if task can be scheduled or if we need another machine
		   if (machine.getKey() <= tmp.getKey()) {
			   Q2.replaceKey(machine, tmp.getValue());
		   } else {
			   m++;
			   Q2.insert(tmp.getValue(), "");
		   }
	   }
	   
	   return m;
   }
}
