import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Read and display graphics from specified graphics file
 *
 * @author Caleb Jacobs
 * Date Last Modified: 10/18/2020
 *
 * CS1131 Fall 2020
 * Lab Section L03
 */

public class Week7Program extends Application {
    private int width = 640;                                    // Width of stage
    private int height = 480;                                   // Height of stage
    private Color strokeColor = Color.rgb(127,244,16); // Stroke color
    private Color fillColor = Color.BLACK;                      // Fill color
    /**
     * Use a given file
     *
     * @param stage: Default stage to setup
     */
    public void start( Stage stage ) {
        // Initialize Group
        Group root = new Group();

        // Initialize scene and set background color
        Scene scene = new Scene(root, 400, 300);
        scene.setFill(Color.BLACK);

        // Load file into buffer
        String filename = getParameters().getRaw().get(0);
        File file = new File(filename);

        // Initialize Shape parameters
        Line line;                           // Temporary line storage
        Circle circle;                       // Temporary circle storage
        Rectangle rectangle;                 // Temporary rectangle storage
        Text text;                           // Temporary text storage
        Arc arc;                             // Temporary arc storage
        Ellipse ellipse;                     // Temporary ellipse storage
        Polygon polygon;                     // Temporary polygon storage
        double x1,y1,radX,radY,theta,length; // Positions, radii, angle, and length
        double x2 = 0.0;                     // Last x-coordinate used
        double y2 = 0.0;                     // Last y-coordinate used
        int r,g,b;                           // RGB color codes
        String message;                      // Text-shape text storage
        boolean end = true;                  // End sequence indicator

        // Begin population stage with graphic file information
        try(Scanner scan = new Scanner(file)) {
            while(end && scan.hasNext()) {
                String command = scan.next();
                switch(command.toUpperCase()) {
                    case "SIZE":
                        width = scan.nextInt();
                        height = scan.nextInt();
                        break;
                    case "LINE":
                        x1 = scan.nextDouble();
                        y1 = scan.nextDouble();
                        x2 = scan.nextDouble();
                        y2 = scan.nextDouble();

                        line = new Line(x1,y1,x2,y2);
                        line.setStroke(strokeColor);

                        root.getChildren().add(line);
                        break;
                    case "CIRCLE":
                        x2 = scan.nextDouble();
                        y2 = scan.nextDouble();
                        radX = scan.nextDouble();

                        circle = new Circle(x2,y2,radX);
                        circle.setStroke(strokeColor);
                        circle.setFill(fillColor);

                        root.getChildren().add(circle);
                        break;
                    case "RECTANGLE":
                        x1 = scan.nextDouble();
                        y1 = scan.nextDouble();
                        x2 = scan.nextDouble();
                        y2 = scan.nextDouble();

                        rectangle = new Rectangle(x1,y1,x2,y2);
                        rectangle.setStroke(strokeColor);
                        rectangle.setFill(fillColor);

                        root.getChildren().add(rectangle);
                        break;
                    case "TEXT":
                        x2 = scan.nextDouble();
                        y2 = scan.nextDouble();
                        message = scan.nextLine();

                        text = new Text(x2,y2,message);
                        text.setStroke(strokeColor);

                        root.getChildren().add(text);
                        break;
                    case "LINETO":
                        x1 = x2;
                        y1 = y2;

                        x2 = scan.nextDouble();
                        y2 = scan.nextDouble();

                        line = new Line(x1,y1,x2,y2);
                        line.setStroke(strokeColor);

                        root.getChildren().add(line);
                        break;
                    case "ARC":
                        x2 = scan.nextDouble();
                        y2 = scan.nextDouble();
                        radX = scan.nextDouble();
                        radY = scan.nextDouble();
                        theta = scan.nextDouble();
                        length = scan.nextDouble();

                        arc = new Arc(x2,y2,radX,radY,theta,length);
                        arc.setStroke(strokeColor);
                        arc.setFill(fillColor);
                        arc.setType(ArcType.ROUND);

                        root.getChildren().add(arc);
                        break;
                    case "ELLIPSE":
                        x2 = scan.nextDouble();
                        y2 = scan.nextDouble();
                        radX = scan.nextDouble();
                        radY = scan.nextDouble();

                        ellipse = new Ellipse(x2,y2,radX,radY);
                        ellipse.setStroke(strokeColor);
                        ellipse.setFill(fillColor);

                        root.getChildren().add(ellipse);

                        break;
                    case "POLYGON":
                        polygon = new Polygon();
                        while (scan.hasNextDouble()) {
                            x2 = scan.nextDouble();
                            y2 = scan.nextDouble();

                            polygon.getPoints().addAll(x2,y2);
                        }

                        polygon.setStroke(strokeColor);
                        polygon.setFill(fillColor);

                        root.getChildren().add(polygon);
                        break;
                    case "SETSTROKE":
                        r = scan.nextInt();
                        g = scan.nextInt();
                        b = scan.nextInt();

                        strokeColor = Color.rgb(r,g,b);
                        break;
                    case "SETFILL":
                        r = scan.nextInt();
                        g = scan.nextInt();
                        b = scan.nextInt();

                        fillColor = Color.rgb(r,g,b);
                        break;
                    case "END":
                        end = false;
                        break;
                    default:
                        if (scan.hasNextLine()) {
                            scan.nextLine();
                        }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // Set final stage settings and display the stage
        stage.setWidth(width);
        stage.setHeight(height);
        stage.setTitle("My JavaFX Application");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Driver for starting the stage
     *
     * @param args string array containing the graphics file path
     */
    public static void main(String[] args) {
        launch(args);
    }
}
