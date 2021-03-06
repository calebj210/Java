package cs2321;

import net.datastructures.List;

/**
 * Class for playing Josephus' game.
 * 
 * @author Caleb Jacobs (cajacobs)
 * Date last modified: 02/16/2021
 * Course: CS2321 Spring 2021
 */

public class Josephus{
	/**
	 * Given a list of strings and a k-value, compute the result of playing Josephus' game. 
	 * The game is described below:
	 * 
	 * All persons sit in a circle. When we go around the circle, initially starting
	 * from the first person, then the second person, then the third... 
	 * we count 1,2,3,.., k-1. The next person, that is the k-th person is out. 
	 * Then we restart the counting from the next person, go around, the k-th person 
	 * is out. Keep going the same way, when there is only one person left, she/he 
	 * is the winner. 
	 *  
	 * @parameter persons - an array of string which contains all player names.
	 * @parameter k - an integer specifying the k-th person will be kicked out of the game
	 * @return - return a list in the order when the players were out of the game. 
	 *           The last one in the list is the winner.  
	 */
	public static List<String> order(String[] persons, int k ) {
		// Return an empty list if no people were entered
		if (persons == null || persons.length == 0) {
			return new ArrayList<String>(0);
		}
		// Return null if k-value is negative because game is undefined
		if (k <= 0) {
			System.out.printf("k must be a positive-nonzero integer");
			return null;
		}
		
		CircularArrayQueue<String> Q = new CircularArrayQueue<String>(persons.length);  // Queue to store people
		ArrayList<String> list = new ArrayList<String>(persons.length);                 // Solution/ result list
		
		// FIll queue with people
		for (String person : persons) {
			Q.enqueue(person);
		}
		
		int cnt = 1;  						// Counter for k-value
		
		// Begin playing the game
		while(!Q.isEmpty()) {
			if (cnt % k == 0) {
				list.addLast(Q.dequeue());  // Remove player from the game
			} else {
				Q.enqueue(Q.dequeue());     // Move to next player
			}
			
			cnt++;
		}
		
		return list;
	}
}
