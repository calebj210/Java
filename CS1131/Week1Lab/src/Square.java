/**
 * This program creates a square using a turtle.
 *
 * @author Jake Chunn, Caleb Jacobs, Brenda Wilson
 * CS1131 Fall 2020
 * L03
 * Last Modified: 9/1/2020
 */

import java.awt.Color;
public class Square {
    /**
     * This method uses the Logo.jar package to draw a square with a turtle.
     *
     * @param args - ignored
     */
    public static void main(String[] args) {
        Turtle t = new Turtle ( );

        t.setPenWidth(5);
        t.setPenColor(Color.BLACK);
        t.penDown();
        t.forward(100);
        t.turn(90);
        t.forward(100);
        t.turn(90);
        t.forward(100);
        t.turn(90);
        t.forward(100);
        t.turn(90);
    }
}
