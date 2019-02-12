import java.util.List;
import java.util.ArrayList;
import java.util.BitSet;

/**
 * Write a description of class TestClass here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class TestClass
{
    // instance variables - replace the example below with your own
    private static List<LRUCache> LRUTests;
    private static List<FIFOCache> FIFOTests;
    
    private static BitSet trace1Set;
    private static BitSet trace2Set;
    
    private static DataHandler mem1;
    private static DataHandler mem2;

    /**
     * Constructor for objects of class TestClass
     */
    public static void main(String[]args)
    {
        LRUTests.add(new LRUCache(64, 2));
        LRUTests.add(new LRUCache(64, 4));
        LRUTests.add(new LRUCache(64, 8));
        LRUTests.add(new LRUCache(64, 16));
        LRUTests.add(new LRUCache(256, 2));
        LRUTests.add(new LRUCache(256, 4));
        LRUTests.add(new LRUCache(256, 8));
        LRUTests.add(new LRUCache(256, 16));
        FIFOTests.add(new FIFOCache(64, 2));
        FIFOTests.add(new FIFOCache(64, 4));
        FIFOTests.add(new FIFOCache(64, 8));
        FIFOTests.add(new FIFOCache(64, 16));
        FIFOTests.add(new FIFOCache(256, 2));
        FIFOTests.add(new FIFOCache(256, 4));
        FIFOTests.add(new FIFOCache(256, 8));
        FIFOTests.add(new FIFOCache(256, 16));
        
        //Path to trace files
        String trace1 = "C:\\Users\\babeh_000\\Desktop\\TCNJ\\Senior\\2nd Semester\\CE2\\Trace1.DAT";
        String trace2 = "C:\\Users\\babeh_000\\Desktop\\TCNJ\\Senior\\2nd Semester\\CE2\\Trace1.DAT";
        
        mem1 = new DataHandler(trace1);
        mem2 = new DataHandler(trace2);
        mem1.printList();
        mem2.printList();
        performTest();
    }
    
    /*
     * This method performs handles the execution of the tests
     */
    private static void performTest()
    {
        for(int i = 0; i < 2; i++)
        {
            for(int j = 0; j < FIFOTests.size(); j++)
            {
                if(i ==0)
                {
                    storeMemory(LRUTests.get(j), mem1);
                    handleResults(LRUTests.get(j));
                    storeMemory(LRUTests.get(j), mem2);
                    handleResults(LRUTests.get(j));
                }
                else
                {
                    //FIFO
                    storeMemory(FIFOTests.get(j), mem1);
                    handleResults(FIFOTests.get(j));
                    storeMemory(FIFOTests.get(j), mem2);
                    handleResults(FIFOTests.get(j));
                }
            }
        }
    }
    
    private static void storeMemory(LRUCache cache, DataHandler mem)
    {
        //Handels LRU Cache
        cache.resetRatios();
        List<byte[]> addressList = mem.getAddressList();
        for(byte[] address: addressList)
        {
            BitSet bitString = BitSet.valueOf(address);
            cache.storeElement(bitString);
        }
    }
    
    private static void storeMemory(FIFOCache cache, DataHandler mem)
    {
        //Handels FIFO Cache
    }
    
    private static void handleResults(LRUCache cache)
    {
        //Handles LRU results
    }
    
    private static void handleResults(FIFOCache cache)
    {
        //Handles FIFO results
    }
}
