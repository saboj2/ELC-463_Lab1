import java.util.HashMap;
import java.util.Set;
/*
 * This class is used to create a LRU Cache object
 * It contains the storage implementation that differs it from FIFO
 */
public class LRUCache extends Cache
{
    /**
     * Constructor for objects of class LRUCache
     * This class is a type of cache 
     */
    public LRUCache(int KN, int K)
    {
        // Call Parent constructor
        super(KN, K);
    }

    /*
     * This method takes the address as element and the iteration as request
     */
    public void storeElement(String element, int request)
    {        
        //Break up address into its tag and index
        String tag = element.substring(0, super.tagLength);
        String index = element.substring(super.tagLength, element.length() - 3);
        
        if(super.checkExists(tag, index)) //Found in caches
        {
            //Update the request on a hit so it is the most recent request
            this.cache.get(index).get(super.getLineNumforTag(tag, index)).setLastUsed(request);
            this.hits++;
        }
        else //Not in cache
        {
            this.misses++;
            boolean stored = false;
            int i = 0;
            String set;
            while(i < super.numOfSets && !stored) //Loop through
            {
                set = super.intToString(i,super.numOfSets);
                if(this.cache.get(index).get(set).getValid()==0) //only store if valid is 0, meaning it is empty
                {
                    //
                    this.cache.get(index).get(set).setTag(tag);
                    this.cache.get(index).get(set).setLastUsed(request);
                    this.cache.get(index).get(set).setValid(1);
                    stored = true;
                }
                i++;
            }

            //Get the first used string based on the sets for the given index, and the amount of sets K
            if(!stored)
            {
                set = super.getUsedFirst(this.cache.get(index));
                this.cache.get(index).get(set).setTag(tag);
                this.cache.get(index).get(set).setLastUsed(request);
                this.cache.get(index).get(set).setValid(1);
            }

        }
    }

    /*
     * Resets hits and misses
     */
    public void reset()
    {
        super.reset();
    }

    /*
     * This method returns a new results object
     */
    public Results getResults()
    {
        return new Results(super.getHits(), super.getMisses());
    }
}
