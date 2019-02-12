import java.util.HashMap;
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
        // initialise these maps
        rows = new HashMap(K);
        cache = new HashMap(KN/K);
        
        //TODO: math later to find size
        int setLength = 0, indexLength = 0, tagLength = 0;
        byte[] byteAddress = new byte[indexLength];
        byte[] setAddress = new byte[setLength];
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
}
