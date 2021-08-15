package cs2321;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Tests for postfix expression evalutation
 * 
 * @author Caleb Jacobs (cajacobs)
 * Date last modified: 02/16/2021
 * Course: CS2321 Spring 2021
 */

public class PostfixExpressionTest {
	/**
	 * Test 1 using:
	 * Input string = "1 2 +"
	 * Expected = 3
	 */
	@Test
	public void testEvaluate1() {
		String input = "1 2 +";
		int expected = 3;
		int result = PostfixExpression.evaluate(input);
		
		if (expected != result) {
			fail("Expected " + expected + " but got " + result);
		}
	}
	
	/**
	 * Test 2 using:
	 * Input string = "13 4 *"
	 * Expected = 52
	 */
	@Test
	public void testEvaluate2() {
		String input = "13 4 *";
		int expected = 52;
		int result = PostfixExpression.evaluate(input);
		
		if (expected != result) {
			fail("Expected " + expected + " but got " + result);
		}
	}
	
	/**
	 * Test 3 using:
	 * Input string = "10 2 /"
	 * Expected = 5
	 */
	@Test
	public void testEvaluate3() {
		String input = "10 2 /";
		int expected = 5;
		int result = PostfixExpression.evaluate(input);
		
		if (expected != result) {
			fail("Expected " + expected + " but got " + result);
		}
	}
	
	/**
	 * Test 4 using:
	 * Input string = "1 2 -"
	 * Expected = -1
	 */
	@Test
	public void testEvaluate4() {
		String input = "1 2 -";
		int expected = -1;
		int result = PostfixExpression.evaluate(input);
		
		if (expected != result) {
			fail("Expected " + expected + " but got " + result);
		}
	}
	
	/**
	 * Test 5 using:
	 * Input string = "   4 20 5 + * 6 -     "
	 * Expected = 94
	 */
	@Test
	public void testEvaluate5() {
		String input = "   4 20 5 + * 6 -     ";
		int expected = 94;
		int result = PostfixExpression.evaluate(input);
		
		if (expected != result) {
			fail("Expected " + expected + " but got " + result);
		}
	}
	
	/**
	 * Test 6 using:
	 * Input string = ""
	 * Expected = 0
	 */
	@Test
	public void testEvaluate6() {
		String input = "";
		int expected = 0;
		int result = PostfixExpression.evaluate(input);
		
		if (expected != result) {
			fail("Expected " + expected + " but got " + result);
		}
	}
	
	/**
	 * Test 7 using:
	 * Input string = "188   4 20 5 +   * 6 - /"
	 * Expected = 2
	 */
	@Test
	public void testEvaluate7() {
		String input = "188   4 20 5 +   * 6 - /";
		int expected = 2;
		int result = PostfixExpression.evaluate(input);
		
		if (expected != result) {
			fail("Expected " + expected + " but got " + result);
		}
	}
	
	/**
	 * Test 8 using:
	 * Input string = "24 2 3 4 5 + - * /"
	 * Expected = -2
	 */
	@Test
	public void testEvaluate8() {
		String input = "24 2 3 4 5 + - * /";
		int expected = -2;
		int result = PostfixExpression.evaluate(input);
		
		if (expected != result) {
			fail("Expected " + expected + " but got " + result);
		}
	}
}
