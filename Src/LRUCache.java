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
    
    public void storeElement(BitSet element)
    {
        // Break up element maybe?
        // Check all memory
        // if element exists in cache: hit do nothing
        // else check for opening
            // if opening store element
            // else look for last used and replace
    }
    
    public void resetRatios()
    {
        super.resetRatios();
    }
}
