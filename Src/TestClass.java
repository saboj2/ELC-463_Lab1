import java.util.List;
import java.util.ArrayList;
import java.util.BitSet;

/**
 * This class is used to run the different test cases
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class TestClass {
    // Lists for the different types of tests
    // We want to run 8 tests per each which is why I used a list

    // These classes are used to wrap the traces in an object
    private static DataHandler mem1;
    private static DataHandler mem2;

    /**
     * Constructor for objects of class TestClass
     */
    public static void main(String[] args) {
        // Add the different tests to be performed
        List<LRUCache> LRUTests = new ArrayList<LRUCache>();
        List<FIFOCache> FIFOTests = new ArrayList<FIFOCache>();
        LRUCache lruCache;
        for (int k = 64; k < 65; k *= 4) {
            for (int i = 2; i < 3; i *= 2) {
                System.out.println("Int k = " + k + "    Int i = " + i);
                lruCache = new LRUCache(k, i);
                LRUTests.add(lruCache);
            }
        }
        // FIFOTests.add(new FIFOCache(64, 2));
        // FIFOTests.add(new FIFOCache(64, 4));
        // FIFOTests.add(new FIFOCache(64, 8));
        // FIFOTests.add(new FIFOCache(64, 16));
        // FIFOTests.add(new FIFOCache(256, 2));
        // FIFOTests.add(new FIFOCache(256, 4));
        // FIFOTests.add(new FIFOCache(256, 8));
        // FIFOTests.add(new FIFOCache(256, 16));
        System.out.println("Finished!");
        // Path to trace files

        String trace1 = "C:\\Users\\babeh_000\\Desktop\\TRACE1.DAT";
        String trace2 = "C:\\Users\\babeh_000\\Desktop\\TRACE2.DAT";

        // String trace1 = "C:\\Users\\Jeffrey\\Documents\\2018-2019\\ELC
        // 463\\ELC-463_Lab1\\Trace1.DAT";
        // String trace2 = "C:\\Users\\Jeffrey\\Documents\\2018-2019\\ELC
        // 463\\ELC-463_Lab1\\Trace2.DAT";

        // Get store the trace files in memory and print the lists
        List<String> addressList = getAdresses(trace1);

        // mem2 = new DataHandler(trace2);

        // TODO: Maybe pass the lists?
        // Anyway this is method is used to perform the tests
        performTest(LRUTests, addressList);
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

    private static List<String> getAdresses(String trace) {
        DataHandler mem = new DataHandler(trace);
        List<byte[]> addressList = mem.getAddressList();
        List<String> results = new ArrayList<String>();
        int count = 0;
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
            count++;
        }
        return results;
    }

    /*
     * This method is used for the logic of test and result execution For the LRU
     * Cache tests
     */
    private static void performTest(List<LRUCache> list, List<String> addressList) {
        // Outer loop is for the different tyes of cahce
        // Inner loop is for the different tests
        int[][] results = new int[list.size()][addressList.size()];
        for (int i = 0; i < list.size(); i++) {
            // LRU Cache case
            // Perform test on trace 1, then do some cool result stuff
            System.out.println("On test: " + (i + 1));
            storeMemory(list.get(i), addressList);

            //System.out.println("Printing pie chart");
            results[i] = handleResults(list.get(i));
        }
        printLineBarGraphs(results);
    }

    /*
     * 
     */
    private static void storeMemory(LRUCache cache, List<String> addressList) {
        // Handels LRU Cache
        cache.resetRatios();
        cache.initHistory(addressList.size());
        int request = 1;
        for (String address : addressList) {
            cache.storeElement(address, request);
            request++;
        }
        cache.printCacheInfo();
    }

    private static void storeMemory(FIFOCache cache, DataHandler mem) {
        // Handels FIFO Cache
    }

    private static int[] handleResults(LRUCache cache) {
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
        // Pie pieChart = new Pie([hits, misses]);
        return results.getHistory();
    }

    private static void handleResults(FIFOCache cache) {
        // Handles FIFO results
    }

    private static void printLineBarGraphs(int[][] res)
    {
        int[] test = new int[res[0].length];
        for(int i = 0 ; i < res[0].length; i++)
        {
            test[i] = res[0][i];
        }
        System.out.println("HERE");
        Line.main(test);
    }
}
