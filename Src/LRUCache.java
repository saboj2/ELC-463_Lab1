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
        //System.out.println();
        // Break up element maybe?
        // .get(a, b) a is inclusive, b is exclusive
        
        int tagLen = super.tagLength;
        String tag = element.substring(0, tagLen);
        String index = element.substring(tagLen, element.length() - 3);
        //Use method below, it might be cleaner than tryng to fit in an if statement
        if(checkExists(tag, index))
        {
            
            if(request == 0)
            {
                super.history[request-1]++;
            }
            else
            {
                super.history[request-1] = super.history[request-2] +1;
            }
            super.hits++;
            this.cache.get(index).get(getLineNumforTag(tag, index)).setLastUsed(request); //don't do this in FIFO
            return;
        }
        else
        {
            //System.out.println(tag);
            //System.out.println(index);
            //System.out.println(request);
            if(request == 1) { super.history[request-1] = 0; }
            else
            {
                super.history[request-1] = super.history[request-2];
            }
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
                    this.cache.get(index).get(super.intToString(i,super.numOfLines)).setLastUsed(request);
                    this.cache.get(index).get(super.intToString(i,super.numOfLines)).setTag(tag);
                    this.cache.get(index).get(super.intToString(i,super.numOfLines)).setValid(1);
                    return;
                }
                //else save the last recently used line
                else
                {
                    LRUlineNum = super.intToString(i,super.numOfLines);
                    if(LRUEntry > this.cache.get(index).get(super.intToString(0,super.numOfLines)).getLastUsed())
                    {
                        
                        //Store the data in the last recently used line
                        this.cache.get(index).get(LRUlineNum).setTag(tag);
                    }
                    this.cache.get(index).get(LRUlineNum).setLastUsed(request);
                    this.cache.get(index).get(LRUlineNum).setValid(1);
                }
            }
            return;
        }
    }
    public void initHistry(int size)
    {
        super.initHistory(size);
    }

    public void printCacheInfo()
    {
        String info = "";
        int count = 0;
        int subCount;
        for(String key:super.cache.keySet())
        {
            info = info +  "Count: " + count + " , Index: " + key + "\n";
            count++;
            subCount = 0;
            for(String val:super.cache.get(key).keySet())
            {
                CacheValues value = super.cache.get(key).get(val);
                info = info +  "    Subcount: " + subCount + " , Tag: " + value.getTag() + "\n";
                subCount++;
            }
        }
        
        System.out.println(info);
    }

    // So we have the index and tag above, now we want to check lines for the index and tag
    // Lines is the cache, it is a hashmap of BitSets and CacheValues.
    // CacheValues is and object of the BitSet and H/M
    private boolean checkExists(String tag, String index)
    {
        //super.lines.get(index).contains(tag).getTag()
        HashMap<String, CacheValues> temp = super.cache.get(index);
        for(String lineNum:temp.keySet())
        {
            
            if(this.cache.get(index).get(lineNum).getTag().equals(tag))
            {
                return true;
            }
            else
            {
                System.out.println("In cache: " + this.cache.get(index).get(lineNum).getTag());
                System.out.println("Storing:  " + tag);
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
        return new Results(super.getHits(), super.getMisses(), super.history);
        //return new Results(54000, 6000);

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
