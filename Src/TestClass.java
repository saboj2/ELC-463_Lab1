import java.util.List;
import java.util.ArrayList;
import java.util.BitSet;

/**
 * This class is used to run the different test cases
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class TestClass
{
    // Lists for the different types of tests
    // We want to run 8 tests per each which is why I used a list
    private static List<LRUCache> LRUTests;
    private static List<FIFOCache> FIFOTests;
    
    //These might not be used
    private static BitSet trace1Set;
    private static BitSet trace2Set;
    
    // These classes are used to wrap the traces in an object
    private static DataHandler mem1;
    private static DataHandler mem2;

    /**
     * Constructor for objects of class TestClass
     */
    public static void main(String[]args)
    {
        //Add the different tests to be performed
        System.out.println("Creating different caches");
        LRUTests.add(new LRUCache(64, 2));
        LRUTests.add(new LRUCache(64, 4));
        LRUTests.add(new LRUCache(64, 8));
        LRUTests.add(new LRUCache(64, 16));
        //LRUTests.add(new LRUCache(256, 2));
        //LRUTests.add(new LRUCache(256, 4));
        //LRUTests.add(new LRUCache(256, 8));
        //LRUTests.add(new LRUCache(256, 16));
        //FIFOTests.add(new FIFOCache(64, 2));
        //FIFOTests.add(new FIFOCache(64, 4));
        //FIFOTests.add(new FIFOCache(64, 8));
        //FIFOTests.add(new FIFOCache(64, 16));
        //FIFOTests.add(new FIFOCache(256, 2));
        //FIFOTests.add(new FIFOCache(256, 4));
        //FIFOTests.add(new FIFOCache(256, 8));
        //FIFOTests.add(new FIFOCache(256, 16));
        System.out.println("Finished!");
        //Path to trace files
        String trace1 = "C:\\Users\\babeh_000\\Desktop\\TCNJ\\Senior\\2nd Semester\\CE2\\Trace1.DAT";
        String trace2 = "C:\\Users\\babeh_000\\Desktop\\TCNJ\\Senior\\2nd Semester\\CE2\\Trace1.DAT";
        
        //Get store the trace files in memory and print the lists
        System.out.println("Importing trace files");
        mem1 = new DataHandler(trace1);
        mem2 = new DataHandler(trace2);
        System.out.println("Finished!");

        // TODO: Maybe pass the lists? 
        // Anyway this is method is used to perform the tests
        performTest();
    }
    
    /*
     * This method is used for the logic of test and result execution
     */
    private static void performTest()
    {
        // Outer loop is for the different tyes of cahce
        // Inner loop is for the different tests
        for(int i = 0; i < 2; i++)
        {
            for(int j = 0; j < FIFOTests.size(); j++)
            {
                if(i ==0)
                {
                    // LRU Cache case
                    // Perform test on trace 1, then do some cool result stuff
                    System.out.println("On tesst: " + (j+1));
                    storeMemory(LRUTests.get(j), mem1);

                    System.out.println("Printing pie chart");
                    handleResults(LRUTests.get(j));

                    // Same as above but for trace 2
                    //storeMemory(LRUTests.get(j), mem2);
                    //handleResults(LRUTests.get(j));
                }
                else
                {
                    //FIFO- Same implementation as above
                    /*storeMemory(FIFOTests.get(j), mem1);
                    handleResults(FIFOTests.get(j));

                    storeMemory(FIFOTests.get(j), mem2);
                    handleResults(FIFOTests.get(j));*/
                }
            }
        }
    }
    
    /*
     * 
     */
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
        //Get LRU results
        Results results = cache.getResults();
        int misses = results.getMisses();
        int hits = results.getHits();
        int total = hits + misses;
        double hitRatio = results.getHitRatio();
        double missRatio = results.getMissRatio();

        Pie pieChart = new Pie([hits, misses]);
    }
    
    private static void handleResults(FIFOCache cache)
    {
        //Handles FIFO results
    }
}
