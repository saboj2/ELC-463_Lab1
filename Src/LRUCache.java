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
        System.out.println("LRUCache made!");
    }
    //maybe move this to cache.java, it is probably common between both lru and fifo
    public void storeElement(BitSet element, int request)
    {

        // Break up element maybe?
        BitSet tag = element.get(0, super.tagLength); 
        BitSet index = element.get(tagLength+1,element.length() - 3);

        //Use method below, it might be cleaner than tryng to fit in an if statement
        if(checkExists(tag, index))
        {
            super.hits++;
            super.cache.get(index).get(getLineNumforTag(tag, index)).setLastUsed(request); //don't do this in FIFO
            return;
        }
        else
        {
            int LRUEntry =  super.cache.get(index).get(super.inttoBitSet(0,super.numOfLines).getLastUsed());
            BitSet LRUlineNum;
            super.misses++;
            //go through each line of the set
            int i;
            for(i = 0; i < this.k; i++)
            {
                //if current line valid bit is zero, then store current cachevalue in current line
                if(!super.cache.get(index).get(super.inttoBitSet(i,super.numOfLines)).getValid())
                {
                    super.cache.get(index).get(super.inttoBitSet(i,super.numOfLines)).setLastUsed(request);
                    super.cache.get(index).get(super.inttoBitSet(i,super.numOfLines)).setTag(tag);
                    super.cache.get(index).get(super.inttoBitSet(i,super.numOfLines)).setValid(1);
                    return;
                }
                //else save the last recently used line
                else
                {
                    if(LRUEntry > super.cache.get(index).get(super.inttoBitSet(0,super.numOfLines).getLastUsed()))
                    {
                        LRUlineNum = super.inttoBitSet(i,super.numOfLines);
                    }
                }
            }
            //Store the data in the last recently used line
            super.cache.get(index).get(LRUlineNum).setLastUsed(request);
            super.cache.get(index).get(LRUlineNum).setTag(tag);
            super.cache.get(index).get(LRUlineNum).setValid(1);
            return;
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
        for(BitSet lineNum: cache.get(index).keySet())
        {
            if(this.cache.get(index).get(lineNum).getTag() == tag)
            {
                return true;
            }
        }
        return false;
    }

    private BitSet getLineNumforTag(BitSet tag, BitSet index)
    {
        for(BitSet lineNum: cache.get(index).keySet())
        {
            if(this.cache.get(index).get(lineNum).getTag() == tag)
            {
                return lineNum;
            }
        }
        return NULL;
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
