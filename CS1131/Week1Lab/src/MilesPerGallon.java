/**
 * This program computes the miles per gallon given the miles travelled and the amount of gas used from the user.
 *
 * @author Jake Chunn, Caleb Jacobs, Brenda Wilson
 * CS1131 Fall 2020
 * L03
 * Last Modified: 9/1/2020
 */

import java.util.Scanner;
public class MilesPerGallon {
    public static void main( String [ ] args ) {
        Scanner input = new Scanner( System.in );
        System.out.print( "How many miles were driven? " );
        double miles = input.nextDouble();
        System.out.print( "How many gallons of petrol were consumed?");
        double gallons = input.nextDouble();
        double mpg = miles / gallons;
        System.out.println("Miles-per-gallon = " + mpg);
    }
}
