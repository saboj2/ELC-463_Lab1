import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.chart.*;
import javafx.scene.Group;

public class Pie extends Application
{
    private int hits;
    private int misses;

    @Override public void start(Stage stage) {
        Scene scene = new Scene(new Group());
        stage.setTitle("Hits vs Misses");
        stage.setWidth(500);
        stage.setHeight(500);ObservableList<PieChart.Data> pieChartData =
            FXCollections.observableArrayList(
            new PieChart.Data("Hits", hits),
            new PieChart.Data("Misses", misses));
        final PieChart chart = new PieChart(pieChartData);
        chart.setTitle("Hits vs Misses");

        ((Group) scene.getRoot()).getChildren().add(chart);
        stage.setScene(scene);
        stage.show();
    }

    public Pie(int h, int m) {
        hits = h;
        misses = m;
    }
    
}