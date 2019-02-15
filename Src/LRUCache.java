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

        // Break up element maybe?
        BitSet tag = element.get(0, super.tagLength); 
        BitSet index = element.get(tagLength+1,element.length() - 3);

        //Use method below, it might be cleaner than tryng to fit in an if statement
        if(checkExists(tag, index))
        {
            super.hits++;
        }
        else
        {
            super.misses++;
            
        }
        // Check all memory
        // if element exists in cache: hit do nothing
            //numRatio++;
        // else check for opening
            //numMisses++;
            // if opening store element
            // else look for last used and replace
    }

    // So we have the index and tag above, now we want to check lines for the index and tag
    // Lines is the cache, it is a hashmap of BitSets and CacheValues.
    // CacheValues is and object of the BitSet and H/M
    private boolean checkExists(BitSet tag, BitSet index)
    {
        //super.lines.get(index).contains(tag).getTag()
        boolean res = false;
        return res;
    }

    public int getTagLength()
    {
        return super.tagLength;
    }

    public Results getResults()
    {
        //return new Results(super.getHits(), super.getMisses());
        return new Results(54000, 6000);

    }
    
    public void resetRatios()
    {
        super.resetRatios();
    }
}
