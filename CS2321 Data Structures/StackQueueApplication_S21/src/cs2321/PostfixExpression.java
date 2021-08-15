package cs2321;

/**
 * Postfix integer arithmetic evaluator
 * 
 * @author Caleb Jacobs (cajacobs)
 * Date last modified: 02/16/2021
 * Course: CS2321 Spring 2021
 */

public class PostfixExpression {
	
	/**
	 * Evaluate a postfix expression. 
	 * Postfix expression notation has operands first, following by the operations.
	 * For example:
	 *    13 5 *           is same as 13 * 5 
	 *    4 20 5 + * 6 -   is same as 4 * (20 + 5) - 6  
	 * 
	 * @param exp - The postfix expression
	 * @return - the result of the expression
	 */
	public static int evaluate(String exp) {
		DLLStack<Integer> stack = new DLLStack<Integer>();    // Stack to store intermediate answer
		String[] e = exp.split(" ");                          // Operand and operator array
		int a, b;                                             // Intermediate storage for results
		
		// Begin reading and evaluating postfix expression
		for (int i = 0; i < e.length; i++) {
			switch (e[i]) {
				case "":
					break;
				case "+":
					a = stack.pop();
					b = stack.pop();
					stack.push(a + b);
					break;
				case "-":
					a = stack.pop();
					b = stack.pop();
					stack.push(b - a);
					break;
				case "*":
					a = stack.pop();
					b = stack.pop();
					stack.push(a * b);
					break;
				case "/":
					a = stack.pop();
					b = stack.pop();
					stack.push(b / a);
					break;
				default:
					stack.push(Integer.parseInt(e[i]));
					break;
			}
			
		}
		
		// Return the final result or return 0 if postfix expression was empty space
		if (stack.isEmpty()) {
			return 0;
		} else {
			return stack.pop();
		}
	}
				
	
}
