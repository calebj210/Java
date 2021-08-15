import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Recursion extends Application {
    public long factorial(long n) {
        return ( n != 0 ) ? n * factorial( n - 1 ) : 1;
    }

    public void start ( Stage stage ) {
        BorderPane pane = new BorderPane();

        HBox textBox = new HBox();
        textBox.setPadding( new Insets( 5,5,5,5 ) );

        TextField input = new TextField();
        TextField output = new TextField( "output" );

        textBox.getChildren().addAll( input, output );
        HBox.setHgrow( output, Priority.ALWAYS );
        pane.setCenter( textBox );

        input.setOnKeyPressed(event -> {
            if ( event.getCode() == KeyCode.ENTER ) {
                long n = Integer.parseInt( input.getText() );

                output.setText( Long.toString( factorial( n ) ) );
            }
        });


        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
    }
}
