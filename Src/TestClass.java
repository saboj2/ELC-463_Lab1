
/**
 * Write a description of class TestClass here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class TestClass
{
    // instance variables - replace the example below with your own
    private int x;

    /**
     * Constructor for objects of class TestClass
     */
    public TestClass()
    {
        LRUCache lru1 = new LRUCache(64, 2);
        LRUCache lru2 = new LRUCache(64, 4);
        LRUCache lru3 = new LRUCache(64, 8);
        LRUCache lru4 = new LRUCache(64, 16);
        LRUCache lru5 = new LRUCache(256, 2);
        LRUCache lru6 = new LRUCache(256, 4);
        LRUCache lru7 = new LRUCache(256, 8);
        LRUCache lru8 = new LRUCache(256, 16);
        FIFOCache fifo1 = new FIFOCache(64, 2);
        FIFOCache fifo2 = new FIFOCache(64, 4);
        FIFOCache fifo3 = new FIFOCache(64, 8);
        FIFOCache fifo4 = new FIFOCache(64, 16);
        FIFOCache fifo5 = new FIFOCache(256, 2);
        FIFOCache fifo6 = new FIFOCache(256, 4);
        FIFOCache fifo7 = new FIFOCache(256, 8);
        FIFOCache fifo8 = new FIFOCache(256, 16);
        
        //Path to trace files
        String trace1 = "C:\\Users\\babeh_000\\Desktop\\TCNJ\\Senior\\2nd Semester\\CE2\\Trace1.DAT";
        String trace2 = "C:\\Users\\babeh_000\\Desktop\\TCNJ\\Senior\\2nd Semester\\CE2\\Trace1.DAT";
        
        DatHandler mem1 = new DatHandler(trace1);
        DatHandler mem2 = new DatHandler(trace2);
    }

    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public int sampleMethod(int y)
    {
        // put your code here
        return x + y;
    }
}
