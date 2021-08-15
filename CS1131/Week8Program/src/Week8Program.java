import java.util.List;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.concurrent.Worker.State;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.scene.layout.HBox;

/**
 * The main class for Week8Program. Week8Program constructs the JavaFX window and
 * handles interactions with the dynamic components contained therein.
 *
 * @author Caleb Jacobs
 * Date Last Modified: 10/4/2020
 *
 * Lab Section: L03
 * CS1131 Fall 2020
 */
public class Week8Program extends Application {
    private WebView view = null;
	private WebEngine webEngine = null;
	private TextField statusbar = null;
	private TextField addressField = null;
	private final String helpScreen = "<HTML><HEAD><TITLE>Help Page</TITLE></HEAD></HTML><BODY>" +
			"Simple web browser<br><br>" +
			"Use the address bar to change websites.<br>" +
			"Use the back and forward buttons to revisit a site.<br><br>" +
			"Author: Caleb Jacobs<br>" +
			"Course: CS1131<br>" +
			"Lab Section: L03</BODY>";

	/**
	 * Retrieves the value of a command line argument specified by the index.
	 *
	 * @param index - position of the argument in the args list.
	 * @return The value of the command line argument.
	 */
	private String getParameter( int index ) {
		Parameters params = getParameters();
		List<String> parameters = params.getRaw();
		return !parameters.isEmpty() ? parameters.get(index) : "";
	}

	/**
	 * Creates a WebView which handles mouse and some keyboard events, and
	 * manages scrolling automatically, so there's no need to put it into a ScrollPane.
	 * The associated WebEngine is created automatically at construction time.
	 *
	 * @return browser - a WebView container for the WebEngine.
	 */
	private WebView makeHtmlView( ) {
		view = new WebView();
		webEngine = view.getEngine();

		webEngine.setOnStatusChanged(event -> {
			statusbar.setText(event.getData());
		});

		webEngine.getLoadWorker().stateProperty().addListener(
				new ChangeListener<State>() {
					public void changed(ObservableValue ov, State oldState, State newState) {
						if (newState == State.SUCCEEDED) {
							addressField.setText(webEngine.getLocation());
						}
					}
				});
		return view;
	}

	/**
	 * Generates the status bar layout and text field.
	 *
	 * @return statusbarPane - the HBox layout that contains the statusbar.
	 */
	private HBox makeStatusBar( ) {
		HBox statusbarPane = new HBox();
		statusbarPane.setPadding(new Insets(5, 4, 5, 4));
		statusbarPane.setSpacing(10);
		statusbarPane.setStyle("-fx-background-color: #77bb16;");
		statusbar = new TextField();
		HBox.setHgrow(statusbar, Priority.ALWAYS);
		statusbarPane.getChildren().addAll(statusbar);
		return statusbarPane;
	}

	/**
	 * Generate the address bar for navigating from webpage to webpage.
	 *
	 * @return addressBar - the address bar for displaying.
	 */
	public HBox makeAddressBar( ) {
		HBox addressBar = new HBox();
		addressBar.setPadding(new Insets(7.5,5,7.5,5));

		Button backButton = new Button("<");
		backButton.setOnAction(event -> {
			if (webEngine.getHistory().getCurrentIndex() > 0) {
				webEngine.getHistory().go(-1);
			}
		});

		Button forwardButton = new Button(">");
		forwardButton.setOnAction(actionEvent -> {
			if (webEngine.getHistory().getCurrentIndex() <
					webEngine.getHistory().getEntries().size() - 1) {
				webEngine.getHistory().go(1);
			}
		});

		addressField = new TextField();
		HBox.setHgrow(addressField, Priority.ALWAYS);
		addressField.setOnAction((ActionEvent event) -> {
			String url = addressField.getText();
			if (!url.startsWith("http://") && !url.startsWith("file://")) {
				url = "http://" + url;
			}
			webEngine.load(url);
		});

		Button helpButton = new Button("?");
		helpButton.setOnAction(actionEvent -> {
			webEngine.loadContent(helpScreen);
		});

		// Add buttons and text fields to the address bar
		addressBar.getChildren().addAll(backButton,forwardButton,addressField,helpButton);

		return addressBar;
	}

	/**
	 * The main entry point for all JavaFX applications. The start method is
	 * called after the init method has returned, and after the system is ready
	 * for the application to begin running.
	 *
	 * NOTE: This method is called on the JavaFX Application Thread.
	 *
	 * @param stage - the primary stage for this application, onto which
	 * the application scene can be set.
	 */
	@Override
	public void start(Stage stage) {
		BorderPane pane = new BorderPane();

		// Set top of borderpane
		pane.setTop(makeAddressBar());

		// Set center of borderpane
		pane.setCenter(makeHtmlView());
		String arg = getParameter(0);
		if (arg == null || arg.isEmpty()) {
			webEngine.loadContent(helpScreen);
		} else {
			webEngine.load(arg);
		}

		// Set bottom of borderpane
		pane.setBottom(makeStatusBar());

		Scene scene = new Scene(pane, 1024, 768);
		stage.setTitle("CS 1131: Web Browser");
		stage.titleProperty().bind(webEngine.titleProperty());
		stage.setScene(scene);
		stage.show();

	}

	/**
	 * The main( ) method is ignored in JavaFX applications.
	 * main( ) serves only as fallback in case the application is launched
	 * as a regular Java application, e.g., in IDEs with limited FX
	 * support.
	 *
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
