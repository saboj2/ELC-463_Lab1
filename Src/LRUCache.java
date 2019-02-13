import java.util.BitSet;
/**
 * Write a description of class LRUCache here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class LRUCache extends Cache
{
    // TODO: Set up LRU Cache

    /**
     * Constructor for objects of class LRUCache
     * This class is a type of cache 
     */
    public LRUCache(int KN, int K)
    {
        // initialise instance variables
        super(KN, K);
    }
    //maybe move this to cache.java, it is probably common between both lru and fifo
    public void storeElement(BitSet element)
    {
        //Check all memory
        
        //if element exists in cache: hit do nothing
        //else check for opening
            //if opening store element
            //else look for last used and replace

        //return hit or miss and keep track in test?
    }
    
    public void resetRatios()
    {
        super.resetRatios();
    }
}
