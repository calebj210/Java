import java.util.Scanner; 

/**
 * This program computes the perimeter of a triangle
 * 
 * Date Last Modified: 08/31/2020
 * @author Caleb Jacobs
 *
 * CS1131, Fall 2020
 * Lab Section 3
 */

public class TrianglePerimeter {
    /**
     * Compute the perimeter of a valid triangle
     *
     * @param input Object to get user input from System.in
     * @param side1 The first side length of a triangle
     * @param side2 The second side length of a triangle
     * @param side3 The third side length of a trianlge 
     */
    
    public static void main(String[] args) {
	Scanner input = new Scanner(System.in);           // Object for grabbing numbers from System.in
	
	System.out.print("Enter 3 side lengths: "); 	  // Prompt user to enter 3 lengths

	double side1 = input.nextDouble();                // Declare and get first side length
	double side2 = input.nextDouble();                // Declare and get second side length
	double side3 = input.nextDouble();                // Declare and get third side length

	/* Check if any of the sides are zero or negative */
	if ((side1 <= 0) || (side2 <= 0) || (side3 <= 0)) {
	    /* Prompt user that at least of side lengths was zero or negative */
	    System.out.println("At least one of the given side lengths was zero or negative.\n"
			       + "Can't compute perimeter.");

	    System.exit(0); 	// Exit due to negative or zero side length
	}
	
	/* Test if the sides make up a real triangle */
	if ((side1 + side2 > side3)
	        && (side1 + side3 > side2)
	        && (side2 + side3 > side1)) {
	    /* Compute and display the permiter of the triangle */
	    System.out.println("The given side lengths make up a triangle with a perimeter of "
			       + (side1 + side2 + side3) + " units.");
	} else {
	    /* Prompt user that the sides can't make up a triangle */
	    System.out.println("A triangle can not be constructed from the given side lengths.\n"
			       + "Can't compute perimeter."); // 
	}
    }
}
