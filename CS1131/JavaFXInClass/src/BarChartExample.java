import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class BarChartExample extends Application {
    public void start(Stage stage) {
        Group pane = new Group();

        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("x Axis Label");
        xAxis.setTickLabelsVisible(false);
        yAxis.setLabel("y Axis Label");

        BarChart<String,Number> barChart =
                new BarChart<String,Number>(xAxis,yAxis);
        barChart.setTitle("My Chart");

        XYChart.Series series1 = new XYChart.Series();
        series1.setName("DataSet 1 Name");
        series1.getData().add(new XYChart.Data("Thing 1", 419));
        series1.getData().add(new XYChart.Data("Thing 2", 185));
        series1.getData().add(new XYChart.Data("Thing 3", 892));
        series1.getData().add(new XYChart.Data("Thing 4", 725));
        barChart.getData().addAll(series1);

        XYChart.Series series2 = new XYChart.Series();
        series2.setName("DataSet 2 Name");
        series2.getData().add(new XYChart.Data("Larry 1", 123));
        series2.getData().add(new XYChart.Data("Curly 2", 456));
        series2.getData().add(new XYChart.Data("Moe 3", 342));
        series2.getData().add(new XYChart.Data("Schemp 4", 198));
        barChart.getData().addAll(series2);

        barChart.setOnMousePressed((MouseEvent event) -> {
            Point2D mouseSceneCoords =
                    new Point2D(event.getSceneX(), event.getSceneY());
            String x = xAxis.getValueForDisplay(xAxis.sceneToLocal(mouseSceneCoords).getX());
            Number y = yAxis.getValueForDisplay(yAxis.sceneToLocal(mouseSceneCoords).getY());
            System.out.println(
                    (x == null) ? "" :
                            String.format("(%s, %f)", x, y));
        });

        for(XYChart.Series<String, Number> series : barChart.getData()) {
            for(XYChart.Data<String, Number> data : series.getData()) {
                Tooltip t = new Tooltip(
                        String.format("(%s: %s, %d)", series.getName(),
                                data.getXValue(),
                                data.getYValue()));
                Tooltip.install(data.getNode(), t);
            }
        }

//        boolean checkboxIsSelected = true;
//        for(XYChart.Series<String, Number> series : barChart.getData()) {
//            series.getNode().setVisible(checkboxIsSelected);
//        }

        pane.getChildren().add(barChart);
        Scene scene = new Scene(pane, 800,600);
        stage.setScene(scene);
        stage.show();
    }
}
