import java.io.ObjectInputStream;
import java.io.FileInputStream;
import java.util.List;
/**
 * Write a description of class DatHandler here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class DatHandler
{
    // instance variables - replace the example below with your own
    private List<byte[]> death; //

    /**
     * Constructor for objects of class DatHandler
     */
    public DatHandler(String path)
    {
        // These trace files are 180KB long, hopefully mem can handle that
        try
        {
            FileInputStream fis = new FileInputStream(path); 
        }
        catch(Exception err)
        {
            System.out.println("File not Found");
        }
    }
}
