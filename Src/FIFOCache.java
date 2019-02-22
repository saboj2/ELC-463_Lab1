
import java.util.HashMap;
import java.util.Set;
import java.io.File;
import java.io.FileWriter;
/*
 * This class is used to create a FIFO Cache object
 * It contains the storage implementation that differs it from LRU
 */
public class FIFOCache extends Cache
{
    /**
     * Constructor for objects of class FIFO
     */
    public FIFOCache(int KN, int K)
    {
        // Call parent's constuctor
        super(KN, K);
    }

    /*
     * This method takes the address as element and the iteration as request
     */
    public void storeElement(String element, int request)
    {
        String tag = element.substring(0, super.tagLength);
        String index = element.substring(super.tagLength, element.length() - 3);
        if(super.checkExists(tag, index))
        {
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
                if(this.cache.get(index).get(set).getValid()==0) //only store if valid is 0
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
                set = getUsedFirst(this.cache.get(index));
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
     * This method returns a new Results object
     */
    public Results getResults()
    {
        return new Results(super.getHits(), super.getMisses());

    }
}
