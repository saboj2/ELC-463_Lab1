import java.util.Random;
import javafx.stage.Stage;
import javafx.application.Application;

public class TestGraphs
{
    private static int[] hits = new int[1];
    private static int[] misses = new int[1];
    private static int[][] history = new int[1][60000];

    public static void main(String[]args)
    {
        //Init sitff
        int[][] chartArgs = new int[1][2];
        Random rng = new Random();
        int num;
        for(int i = 0; i < history.length; i++)
        {
            for(int j = 0; j < history[i].length; j++)
            {
                if(j < 64)
                {
                    history[i][j] = 0;
                }
                else
                {
                    num = rng.nextInt(100)+1;
                    history[i][j] = history[i][j-1];
                    if(num >10)
                    {
                        history[i][j] = history[i][j]+1;
                    }
                }

            }
            hits[i] = history[i][history[i].length-1];
            misses[i] = history[i].length -history[i][history[i].length-1];
            chartArgs[i][0] = hits[i];
            chartArgs[i][1] = misses[i];
        }
        //Pie.main(chartArgs);
        //Bar.main(chartArgs);
        Line.main(history[0]);
    }
}