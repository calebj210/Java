import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Midterm2Practice extends Application {
    @Override // Override the start method in the Application class
    public void start(Stage primaryStage) {
        HBox pane = new HBox(5);
        Image usIcon = new Image("http://www.cs.armstrong.edu/liang/image/usIcon.gif");
        Button bt1 = new Button("Button1", new ImageView(usIcon));
        Button bt2 = new Button("Button2", new ImageView(usIcon));
        pane.getChildren().addAll(bt1, bt2);

        Scene scene = new Scene(pane, 200, 250);
        primaryStage.setTitle("Test"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage
    }

    /**
     * The main method is only needed for the IDE with limited JavaFX
     * support. Not needed for running from the command line.
     */
    public static void main(String[] args) {
        launch(args);
    }
}