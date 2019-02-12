import java.util.HashMap;
import java.util.BitSet;
/**
 * Write a description of class Cache here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Cache
{
    // instance variables - replace the example below with your own
    //Maybe these are what we are looking for, but maybe not...
    private HashMap rows;       //Map byte index to CacheValue Object, more important to get working than sets
    private HashMap cache;      //Map Set to Rows?

    /**
     * Constructor for objects of class Cache
     */
    public Cache(int KN, int K)
    {
        //N = KN/K is Number of sets
        //K is number of rows
        
        // initialise these maps
        rows = new HashMap(K);
        cache = new HashMap(KN/K);
        
        //TODO: math later to find size
        final int  addressLength = 24;
        
        int setLength = KN/K/2, indexLength = K/2, tagLength = 0;
        BitSet byteAddress = new BitSet(indexLength);
        BitSet setAddress = new BitSet(setLength);
        CacheValues cacheValue;
         
        //Populate Rows with 
        for(int j = 0; j < KN/K; j++)
        {
            rows.clear();
            for(int i = 0; i < K; i++)
            {
                //byteAddress = something;
                cacheValue = new CacheValues(tagLength);
                rows.put(byteAddress, cacheValue);
            }
            cache.put(setAddress, rows);
        }
    }
    
    public void resetRatios()
    {
        //Set all H's to misses and,
        //set number of hits to 0, number of misses to 0
    }
}
