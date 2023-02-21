import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.transform.Scale;
import javafx.stage.Stage;

import java.util.List;

/**
 * JavaFX application to plot elevations of a GPS track, for
 * the Advanced task of COMP1721 Coursework 1.
 *
 * @author Ungku Amer Iskandar sc22uaib
 */
public class PlotApplication extends Application {
  // OPTIONAL: Implement the elevation plotting application here

  public static void main(String[] args) {
    launch(args[0]);
  }

  public void start(Stage primaryStage) throws Exception {
    Track newTrack = new Track();
    final Parameters param = getParameters();
    final List<String> parameters = param.getRaw();
    final String filename = parameters.get(0);
    newTrack.readFile(filename);
    double currDistance = 0;

    // Axis definition
    NumberAxis yAxis = new NumberAxis(0, 75, 5);
    yAxis.setLabel("Elevation (m)");

    NumberAxis xAxis = new NumberAxis(0, 2000, 100);
    xAxis.setLabel("Distance (m)");

    LineChart elevPlot = new LineChart(xAxis, yAxis);

    // Data preparation
    XYChart.Series series = new XYChart.Series();
    series.setName(filename);
    for (int i = 0; i < newTrack.size()-1; i++) {
      Point currPoint = newTrack.get(i);
      Point nextPoint = newTrack.get(i+1);

      currDistance += Point.greatCircleDistance(currPoint, nextPoint);

      series.getData().add(new XYChart.Data(currDistance, newTrack.get(i).getElevation()));
    }

    // Data setting
    elevPlot.getData().add(series);
    elevPlot.setTitle("Elevation Plot");
    elevPlot.setCreateSymbols(false);

    Group root = new Group(elevPlot);
    Scene scene = new Scene(root, 550, 400);
    primaryStage.setTitle("Elevation Plot");
    primaryStage.setScene(scene);
    primaryStage.show();
  }

}
