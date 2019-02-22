import java.util.List;
import java.util.ArrayList;
import java.util.BitSet;

/*
 * This class is used to run the different test cases,
 * initiate the different cache objects and print the results
 * 
 */
public class TestClass {
    /*
     * Constructor for objects of class TestClass
     */
    public static void main(String[] args) {

        // Add the different tests to be performed
        List<LRUCache> LRUTests = new ArrayList<LRUCache>();
        List<FIFOCache> FIFOTests = new ArrayList<FIFOCache>();
        LRUCache lruCache;
        for (int k = 64; k < 257; k *= 4) {
            for (int i = 2; i < 17; i *= 2) {
                System.out.println("Int k = " + k + "    Int i = " + i);
                lruCache = new LRUCache(k, i);
                LRUTests.add(lruCache);
            }
        }
        FIFOCache fifoCache;
        for (int k = 64; k < 257; k *= 4) {
            for (int i = 2; i < 17; i *= 2) {
                System.out.println("Int k = " + k + "    Int i = " + i);
                fifoCache = new FIFOCache(k, i);
                FIFOTests.add(fifoCache);
            }
        }

        // Path to trace files
        String trace1 = "C:\\Users\\babeh_000\\Desktop\\TRACE1.DAT";
        String trace2 = "C:\\Users\\babeh_000\\Desktop\\TRACE2.DAT";
        // Get store the trace files in memory and print the lists
        List<String> addressList1 = getAdresses(trace1);
        List<String> addressList2 = getAdresses(trace2);
        
        // Perform the tests
        System.out.println("Performing LRU Test on Trace 1");
        performLRUTest(LRUTests, addressList1);
        System.out.println("\nPerforming LRU Test on Trace 2");
        performLRUTest(LRUTests, addressList2);
        System.out.println("\n\nPerforming FIFO Test on Trace 1");
        performFIFOTest(FIFOTests, addressList1);
        System.out.println("\nPerforming FIFO Test on Trace 2");
        performFIFOTest(FIFOTests, addressList2);
    }

    /*
     * This method accepts a Sring which is the path to the trace
     * It returns a list of addresses as Strings
     */
    private static List<String> getAdresses(String trace) {
        DataHandler mem = new DataHandler(trace);
        List<byte[]> addressList = mem.getAddressList();
        List<String> results = new ArrayList<String>();
        String str;
        for (byte[] address : addressList) {
            str = "";
            BitSet bits = BitSet.valueOf(address);
            for (int i = 0; i < 24; i++) {
                if(bits.get(i)) {
                    str = str+"1";
                }
                else{
                    str = str+"0";
                }
            }
            results.add(str);
        }
        return results;
    }

    /*
     * This method loops through the different cache's and passes the cache object 
     * and the address list to the storeElement method below
     */
    private static void performLRUTest(List<LRUCache> list, List<String> addressList) {
        // Loop through different caches
        for (int i = 0; i < list.size(); i++) {
            // LRU Cache case
            // Perform test on trace 1, then do some cool result stuff
            System.out.println("On test: " + (i + 1));
            storeLRUMemory(list.get(i), addressList);
            
            //Get the results
            handleResults(list.get(i));
        }
    }

    /*
     * Same functionality as above but for FIFO Cache
     */
    private static void performFIFOTest(List<FIFOCache> list, List<String> addressList) {
        // Loop through different caches
        for (int i = 0; i < list.size(); i++) {
            // FIFO Cache case
            System.out.println("On test: " + (i + 1));
            storeFIFOMemory(list.get(i), addressList);
            
            handleResults(list.get(i));
        }
    }

    /*
     * This method loops through the address list and uses the LRU Cache object's
     * storeElement method to store the address
     */
    private static void storeLRUMemory(LRUCache cache, List<String> addressList) {
        // Handels LRU Cache
        cache.reset();
        int request = 1;
        for (String address : addressList) {
            cache.storeElement(address, request);
            request++;
        }
    }

    /*
     * Same as above but for FIFO Cache
     */
    private static void storeFIFOMemory(FIFOCache cache,List<String> addressList) {
        // Handels FIFO Cache      
        cache.reset();               
        int request = 1;
        for (String address : addressList) {
            cache.storeElement(address, request);
            request++;
        }
    }

    /*
     * Print the results for the given cache object
     */
    private static void handleResults(LRUCache cache) {
        // Get LRU results
        Results results = cache.getResults();
        int misses = results.getMisses();
        int hits = results.getHits();
        int total = hits + misses;
        double hitRatio = (double)hits/(double)total;
        double missRatio = (double)misses/(double)total;

        System.out.println("TOTAL MISSES: " + misses +
                         "\nTOTAL HITS: " + hits +
                         "\nHIT RATIO: " + hitRatio +
                         "\nMISS RATIO: " + missRatio);
    }

    /*
     * Print the results for the given cache object
     */
    private static void handleResults(FIFOCache cache) {
        // Handles FIFO results
        Results results = cache.getResults();
        int misses = results.getMisses();
        int hits = results.getHits();
        int total = hits + misses;
        double hitRatio = (double)hits/(double)total;
        double missRatio = (double)misses/(double)total;

        System.out.println("TOTAL MISSES: " + misses +
                         "\nTOTAL HITS: " + hits +
                         "\nHIT RATIO: " + hitRatio +
                         "\nMISS RATIO: " + missRatio);
    }
}
