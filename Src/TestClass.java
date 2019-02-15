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

        List<LRUCache> LRUTests = new ArrayList<LRUCache>();
        List<FIFOCache> FIFOTests = new ArrayList<FIFOCache>();
        LRUCache lruCache;
        for(int k = 64; k < 257; k*=4)
        {
            for(int i = 2; i < 17; i*=2)
            {
                System.out.println("Int k = " + k + "    Int i = " + i);
                lruCache = new LRUCache(k, i);
                LRUTests.add(lruCache);
            }
        }
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
        
        String trace1 = "C:\\Users\\babeh_000\\Desktop\\TRACE1.DAT";
        String trace2 = "C:\\Users\\babeh_000\\Desktop\\TRACE2.DAT";
        
        //String trace1 = "C:\\Users\\Jeffrey\\Documents\\2018-2019\\ELC 463\\ELC-463_Lab1\\Trace1.DAT";
        //String trace2 = "C:\\Users\\Jeffrey\\Documents\\2018-2019\\ELC 463\\ELC-463_Lab1\\Trace2.DAT";

        //Get store the trace files in memory and print the lists
        System.out.println("Importing trace files");
        mem1 = new DataHandler(trace1);
        //mem2 = new DataHandler(trace2);

        // TODO: Maybe pass the lists? 
        // Anyway this is method is used to perform the tests
        performTest(LRUTests);
    }

    public static String toHexString(byte[] bytes) {
        StringBuilder hexString = new StringBuilder();
    
        for (int i = 0; i < bytes.length; i++) {
            String hex = Integer.toHexString(0xFF & bytes[i]);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
    
        return hexString.toString();
    }
    
    /*
     * This method is used for the logic of test and result execution
     * For the LRU Cache tests
     */
    private static void performTest(List<LRUCache> list)
    {
        // Outer loop is for the different tyes of cahce
        // Inner loop is for the different tests 
        for(int i = 0; i < list.size(); i++)
        {
            // LRU Cache case
            // Perform test on trace 1, then do some cool result stuff
            System.out.println("On test: " + (i+1));
            storeMemory(list.get(i), mem1);

            System.out.println("Printing pie chart");
            handleResults(list.get(i));

            // Same as above but for trace 2
            //storeMemory(LRUTests.get(i), mem2);
            //handleResults(LRUTests.get(i));          
            
        }
    }
    
    /*
     * 
     */
    private static void storeMemory(LRUCache cache, DataHandler mem)
    {
        //Handels LRU Cache
        cache.resetRatios();
        int request = 1;
        List<byte[]> addressList = mem.getAddressList();
        BitSet bitString = new BitSet(24);
        for(byte[] address: addressList)
        {
            bitString = BitSet.valueOf(address);
            System.out.println("Count: " + request + "    Address: " + toHexString(bitString.toByteArray()));
            cache.storeElement(bitString,request);
            request++;
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

        //Pie pieChart = new Pie([hits, misses]);
    }
    
    private static void handleResults(FIFOCache cache)
    {
        //Handles FIFO results
    }
}
