import java.util.Scanner;

public class RectangleTest {
    int changeCounter = 0;

    public void run() {
        Rectangle rectangle = new Rectangle();
        rectangle.setHeight(10);
        rectangle.setWidth(40);
        System.out.println("width = " + rectangle.getWidth() + ", height = " + rectangle.getHeight());

        Square square = new Square();
        // Anonymous Subclass
        square.setOnLengthChanged(new MyLengthChangedHandler() {
            @Override
            public void lengthChanged(int oldLength, int newLength) {
                changeCounter++;
                System.out.println("" + changeCounter + ". Length was changed from " + oldLength + " to " + newLength);
            }
        });

        // Lambda Expression
        square.setOnLengthChanged((oldLength, newLength) ->  {
            changeCounter++;
            System.out.println("" + changeCounter + ". Length was changed from " + oldLength + " to " + newLength);
        });


        square.setHeight(10);
        square.setWidth(40);
        System.out.println("width = " + square.getWidth() + ", height = " + square .getHeight());
    }

    public static void main(String[] args) {
        RectangleTest test = new RectangleTest();
        test.run();
    }
}
