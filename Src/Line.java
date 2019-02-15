import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.chart.*;
import javafx.scene.chart.XYChart;
import javafx.scene.Group;
import javafx.scene.layout.FlowPane;

public class Line extends Application
{
    static int hist[];
    @Override public void start(Stage stage) {
        stage.setTitle("LComparison of Hit Ratios Over time");
        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();
         xAxis.setLabel("Iteration");
         yAxis.setLabel("Hit Ratio");
        final LineChart<Number,Number> lc = 
                new LineChart<Number,Number>(xAxis,yAxis);
       
        lc.setTitle("Comparison of Hit Ratios over time");

        XYChart.Series series;         
        series = new XYChart.Series();
        for(int i = 0; i < hist.length - 1; i++)
        {
            double ratio = (double)hist[i]/(double)i;
            series.getData().add(new XYChart.Data(i, ratio));
        }
        lc.getData().add(series);
        

        //Pie
        final int hits = hist[hist.length - 1];
        final int misses = hist.length - hits;
        ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList(
                new PieChart.Data("Hits", hits),
                new PieChart.Data("Misses", misses));
        final PieChart pie = new PieChart(pieChartData);
        pie.setTitle("Imported Fruits");

        // Add Charts to scene and stage
        FlowPane root = new FlowPane();
        root.getChildren().addAll(lc, pie);        
        Scene scene  = new Scene(root,1920,1080);
       
        stage.setScene(scene);
        stage.show();
    }
 
 
    public static void main(int[] args) {
        hist = args;
        launch();
    }
}