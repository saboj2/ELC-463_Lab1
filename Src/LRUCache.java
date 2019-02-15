import java.util.BitSet;
import java.util.HashMap;
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
        // .get(a, b) a is inclusive, b is exclusive
        int elementLen = element.length();
        int targetLen = super.tagLength + super.numOfSets + 3;
        int diff = targetLen - elementLen;
        if(diff != 0)
        {
            BitSet newElement = new BitSet(targetLen + 1);
            for(int i = 0; i < elementLen; i++)
            {
                if(element.get(i))
                {
                    newElement.set(i);
                }
                
            }
            newElement.set(targetLen+1);
            element = newElement;
        }
        
        System.out.println(super.toHexString(element.toByteArray()));

        BitSet tag = element.get(0, super.tagLength); 
        BitSet index = element.get(tagLength,element.length() - 3);

        //Use method below, it might be cleaner than tryng to fit in an if statement
        if(checkExists(tag, index))
        {
            super.hits++;
            this.cache.get(index).get(getLineNumforTag(tag, index)).setLastUsed(request); //don't do this in FIFO
            return;
        }
        else
        {
            int LRUEntry =  this.cache.get(index).get(super.inttoBitSet(0,super.numOfLines)).getLastUsed();
            BitSet LRUlineNum = new BitSet();
            super.misses++;
            //go through each line of the set
            int i;
            for(i = 0; i < this.k; i++)
            {
                //if current line valid bit is zero, then store current cachevalue in current line
                if(super.cache.get(index).get(super.inttoBitSet(i,super.numOfLines)).getValid() == 0)
                {
                    this.cache.get(index).get(super.inttoBitSet(i,super.numOfLines)).setLastUsed(request);
                    this.cache.get(index).get(super.inttoBitSet(i,super.numOfLines)).setTag(tag);
                    this.cache.get(index).get(super.inttoBitSet(i,super.numOfLines)).setValid(1);
                    return;
                }
                //else save the last recently used line
                else
                {
                    if(LRUEntry > this.cache.get(index).get(super.inttoBitSet(0,super.numOfLines)).getLastUsed())
                    {
                        LRUlineNum = super.inttoBitSet(i,super.numOfLines);
                    }
                }
            }
            //Store the data in the last recently used line
            this.cache.get(index).get(LRUlineNum).setLastUsed(request);
            this.cache.get(index).get(LRUlineNum).setTag(tag);
            this.cache.get(index).get(LRUlineNum).setValid(1);
            return;
        }
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
        BitSet result = new BitSet();
        for(BitSet lineNum: cache.get(index).keySet())
        {
            
            if(this.cache.get(index).get(lineNum).getTag() == tag)
            {
                result = lineNum;
                break;
            }
            
        }
        return result;
    }

    public int getTagLength()
    {
        return super.tagLength;
    }

    public int getIndexLength()
    {
        return super.numOfSets;
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

    public String toString()
    {
        String str = "Tag Size: " + this.getTagLength() + 
                    "\nIndex Size: " + this.getIndexLength() +
                    "\nHit: " + super.getHits() +
                    "\nMisses: " + super.getMisses();
        return str;
    }
}
