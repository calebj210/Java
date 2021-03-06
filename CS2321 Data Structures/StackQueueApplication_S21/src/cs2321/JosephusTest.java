package cs2321;

import net.datastructures.List;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Tests for Josephus' game.
 * 
 * @author Caleb Jacobs (cajacobs)
 * Date last modified: 02/16/2021
 * Course: CS2321 Spring 2021
 */

public class JosephusTest {
	/**
	 * Convert string array into a List<String> type
	 * 
	 * @param in - string array to be converted
	 * @return - List<String> of string array
	 */
	private List<String> makeList(String[] in) {
		ArrayList<String> list = new ArrayList<String>();
		
		for (String i : in) {
			list.addLast(i);
		}
		
		return list;
	}
	
	/**
	 * Test 1 using:
	 * Input array = {A,B,C,D,E,F,G}
	 * Input int = 3
	 * Expected = List<String> of {C,F,B,G,E,A,D}
	 */
	@Test
	public void testOrder1() {
		String[] inputString = {"A", "B", "C", "D", "E", "F", "G"};
		int inputInt = 3;
		String[] expectedString = {"C", "F", "B", "G", "E", "A", "D"};
		List<String> expected = makeList(expectedString);
		List<String> result = Josephus.order(inputString, inputInt);
		
		for (int i = 0; i < inputString.length; i++) {
			String r = result.remove(0);
			String e = expected.remove(0);
			if (!r.equals(e)) {
				fail("Result " + r +" does not equal " + e);
			}
		}
		if (!result.isEmpty()) {
			fail("More information than expected in List");
		}
	}

	/**
	 * Test 2 using:
	 * Input array = {A,B,C,D,E,F,G}
	 * Input int = 1
	 * Expected = List<String> of {A,B,C,D,E,F,G}
	 */
	@Test
	public void testOrder2() {
		String[] inputString = {"A", "B", "C", "D", "E", "F", "G"};
		int inputInt = 1;
		String[] expectedString = {"A", "B", "C", "D", "E", "F", "G"};
		List<String> expected = makeList(expectedString);
		List<String> result = Josephus.order(inputString, inputInt);
		
		for (int i = 0; i < inputString.length; i++) {
			String r = result.remove(0);
			String e = expected.remove(0);
			if (!r.equals(e)) {
				fail("Result " + r +" does not equal " + e);
			}
		}
		if (!result.isEmpty()) {
			fail("More information than expected in List");
		}
	}

	/**
	 * Test 3 using:
	 * Input array = null
	 * Input int = 3
	 * Expected = List<String> of {}
	 */
	@Test
	public void testOrder3() {
		String[] inputString = null;
		int inputInt = 3;
		List<String> result = Josephus.order(inputString, inputInt);
		
		if (!result.isEmpty()) {
			fail("Result is not an empty list but should be.");
		}
	}

	/**
	 * Test 4 using:
	 * Input array = {A}
	 * Input int = 10
	 * Expected = List<String> of {A}
	 */
	@Test
	public void testOrder4() {
		String[] inputString = {"A"};
		int inputInt = 10;
		String[] expectedString = {"A"};
		List<String> expected = makeList(expectedString);
		List<String> result = Josephus.order(inputString, inputInt);
		
		for (int i = 0; i < inputString.length; i++) {
			String r = result.remove(0);
			String e = expected.remove(0);
			if (!r.equals(e)) {
				fail("Result " + r +" does not equal " + e);
			}
		}
		if (!result.isEmpty()) {
			fail("More information than expected in List");
		}
	}
	
	/**
	 * Test 5 using:
	 * Input array = {}
	 * Input int = 10
	 * Expected = List<String> of {}
	 */
	@Test
	public void testOrder5() {
		String[] inputString = {};
		int inputInt = 10;
		List<String> result = Josephus.order(inputString, inputInt);
		
		if (!result.isEmpty()) {
			fail("More information than expected in List");
		}
	}

}

