/***    This program allows a user to input data to create*  a bar graph for bridge lengths.*  Date Last Modified: 02/19/2018*    Ian Smith*  CS1121*    JavaFX Exercise 10*/

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Random;

public class BarGrapher extends Application {
	public void start ( Stage stage1 ) {
		Pane root = createRootPane ( );
		Scene scene1 = new Scene ( root );
		stage1.setScene ( scene1 );
		stage1.setTitle ( "Bridges" );
		stage1.show ( );
	}

	private Pane createRootPane ( ) {
		TextField labelField = new TextField ( "" );
		TextField valueField = new TextField ( "" );
		Text title = new Text ( "Bridges" );
		Text[] exx = { new Text ( ".0" ), new Text ( ".500" ),
		               new Text ( ".1000" ), new Text ( ".1500" ),
		               new Text ( ".2000" ), new Text ( ".2500" ),
		               new Text ( ".3000" ), new Text ( ".3500" ),
		               new Text ( ".4000" ), new Text ( ".4500" ),
		               new Text ( "Length (Feet)" ) };
		Bar ex = new Bar ( exx );
		title.setFont ( Font.font ( 20 ) );
		Button addButton = new Button ( "Add" );
		Button removeLastButton = new Button ( "Remove Last" );
		BarChart chart = new BarChart ( );
		addButton.setOnAction ( event -> chart.append ( labelField.getText ( ),
		                                                Double.parseDouble (
				                                                  valueField
							                                                 .getText ( ) ) ) );
		removeLastButton.setOnAction ( event -> chart.removeLast ( ) );
		return new VBox ( title, new Label ( "Name of Bridge:" ), labelField,
		                  new Label ( "Length in Feet:" ), valueField, addButton,
		                  removeLastButton, ex, chart );
	}

	public class BarChart extends Pane {
		BarChart ( ) {
			final int PANE_WIDTH = 600;
			final int PANE_HEIGHT = 400;
			setMinSize ( PANE_WIDTH, PANE_HEIGHT );
		}

		void append ( String label, double value ) {
			final int GAP = 5;
			int bars = getChildren ( ).size ( );
			Bar bar1 = new Bar ( label, ( value / 10 ) );
			bar1.relocate ( 0, ( Bar.HEIGHT + GAP ) * bars );
			getChildren ( ).add ( bar1 );
		}

		void removeLast ( ) {
			int bars = getChildren ( ).size ( );
			if ( bars > 0 ) {getChildren ( ).remove ( bars - 1 );}
		}
	}

	public class Bar extends Pane {
		public static final int HEIGHT = 15;

		Bar ( String label, double value ) {
			Random rand1 = new Random ( );
			Random rand2 = new Random ( );
			Random rand3 = new Random ( );
			Rectangle barRectangle = new Rectangle ( 0, 0, value, HEIGHT );
			barRectangle.setFill (
					  Color.rgb ( rand1.nextInt ( 256 ), rand2.nextInt ( 256 ),
					              rand3.nextInt ( 256 ) ) );
			barRectangle.setStroke ( Color.BLACK );
			Text barText = new Text ( label );
			barText.relocate ( 0, 0 );
			barText.setStroke ( Color.BLACK );
			barText.setFill ( Color.WHITE );
			getChildren ( ).addAll ( barRectangle, barText );
		}

		Bar ( Text[] label ) {
			Rectangle barRectangle = new Rectangle ( 0, 0, 500, HEIGHT );
			for ( int i = 0; i < 10; i++ ) {
				label[ i ].relocate ( 50 * i, 0 );
				label[ i ].setStroke ( Color.WHITE );
				label[ i ].setFill ( Color.WHITE );
			}
			label[ 10 ].relocate ( 510, 0 );
			label[ 10 ].setFill ( Color.BLACK );
			getChildren ( )
					  .addAll ( barRectangle, label[ 0 ], label[ 1 ], label[ 2 ],
					            label[ 3 ], label[ 4 ], label[ 5 ], label[ 6 ],
					            label[ 7 ], label[ 8 ], label[ 9 ], label[ 10 ] );
		}
	}
}