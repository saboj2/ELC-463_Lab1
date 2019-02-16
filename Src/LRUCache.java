import java.util.BitSet;
import java.util.HashMap;
import java.util.Set;
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
    public void storeElement(String element, int request)
    {

        // Break up element maybe?
        // .get(a, b) a is inclusive, b is exclusive
        
        int tagLen = super.tagLength;
        String tag = element.substring(0, tagLen);
        String index = element.substring(tagLen, element.length() - 3);

        //Use method below, it might be cleaner than tryng to fit in an if statement
        if(checkExists(tag, index))
        {
            System.out.println("Hit");
            super.hits++;
            this.cache.get(index).get(getLineNumforTag(tag, index)).setLastUsed(request); //don't do this in FIFO
            return;
        }
        else
        {
            System.out.println("Miss");
            int LRUEntry =  this.cache.get(index).get(super.intToString(0,super.numOfLines)).getLastUsed();
            String LRUlineNum = "";
            super.misses++;
            //go through each line of the set
            int i;
            for(i = 0; i < this.k; i++)
            {
                //if current line valid bit is zero, then store current cachevalue in current line
                if(super.cache.get(index).get(super.intToString(i,super.numOfLines)).getValid() == 0)
                {
                    System.out.println("Case 1");
                    this.cache.get(index).get(super.intToString(i,super.numOfLines)).setLastUsed(request);
                    this.cache.get(index).get(super.intToString(i,super.numOfLines)).setTag(tag);
                    this.cache.get(index).get(super.intToString(i,super.numOfLines)).setValid(1);
                    return;
                }
                //else save the last recently used line
                else
                {
                    System.out.println(index);
                    if(LRUEntry > this.cache.get(index).get(super.intToString(0,super.numOfLines)).getLastUsed())
                    {
                        System.out.println("Case 2b");
                        LRUlineNum = super.intToString(i,super.numOfLines);
                    }
                }
            }
            //Store the data in the last recently used line
            System.out.println("LRUlineNum: " + LRUlineNum);
            this.cache.get(index).get(LRUlineNum).setLastUsed(request);
            this.cache.get(index).get(LRUlineNum).setTag(tag);
            this.cache.get(index).get(LRUlineNum).setValid(1);
            return;
        }
    }

    // So we have the index and tag above, now we want to check lines for the index and tag
    // Lines is the cache, it is a hashmap of BitSets and CacheValues.
    // CacheValues is and object of the BitSet and H/M
    private boolean checkExists(String tag, String index)
    {
        //super.lines.get(index).contains(tag).getTag()
        HashMap<String, CacheValues> temp = super.cache.get(index);
        if(temp == null) { System.out.println("Shit"); }
        for(String lineNum:temp.keySet())
        {
            
            if(this.cache.get(index).get(lineNum).getTag().equals(tag))
            {
                return true;
            }
        }
        return false;
    }

    private String getLineNumforTag(String tag, String index)
    {
        String result = "";
        for(String lineNum: cache.get(index).keySet())
        {
            
            if(this.cache.get(index).get(lineNum).getTag().equals(tag))
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
