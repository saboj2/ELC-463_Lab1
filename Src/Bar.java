import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.chart.*;
import javafx.scene.Group;

public class Bar extends Application
{
    static int[][] hist;
 
    @Override public void start(Stage stage) {
        stage.setTitle("Bar Chart");
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        final BarChart<String,Number> bc = 
            new BarChart<>(xAxis,yAxis);
        bc.setTitle("Tests vs Hits and Misses");
        xAxis.setLabel("Test Cases");       
        yAxis.setLabel("Value");

        XYChart.Series misses = new XYChart.Series();
        misses.setName("Misses");
        XYChart.Series hits = new XYChart.Series();
           hits.setName("Hits");       
        for(int i = 0; i < hist.length; i++)
        {
            hits.getData().add(new XYChart.Data(("Test " + (i+1)), hist[i][0]));
            
            misses.getData().add(new XYChart.Data(("Test " + (i+1)), hist[i][1]));
            
        }
        bc.getData().addAll(hits, misses);
        Scene scene  = new Scene(bc,800,600);
        
        stage.setScene(scene);
        stage.show();
    }
 
    public static void main(int[][] args) {
        hist = args;
        Application.launch();
    }
}