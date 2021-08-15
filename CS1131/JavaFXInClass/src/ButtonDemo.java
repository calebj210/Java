import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class ButtonDemo extends Application {
    int counter = 0;

    // A bit heavy handed to handle an event (button click)
    public class MyEventHandler implements EventHandler {
        public void handle(Event event) {
            System.out.println(((Button) (event.getSource())).getText() +
                    ": Clicked! " + (++counter));
        }
    }

    public void start(Stage stage) {
        BorderPane pane = new BorderPane();
        Button button = new Button("Click Me");

//        button.setOnAction(new EventHandler<ActionEvent>() {
//          // A little better for handling events
//            @Override
//            public void handle(ActionEvent event) {
//                System.out.println(((Button) (event.getSource())).getText() +
//                        ": Clicked! " + (++counter));
//            }
//        });

        // Best way to handle an event
        button.setOnAction(event -> {
                System.out.println(((Button) (event.getSource())).getText() +
                    ": Clicked! " + (++counter));
            }
        );

        pane.setCenter(button);
        Scene scene = new Scene(pane, 640, 480);
        stage.setScene(scene);
        stage.show();
    }
}
