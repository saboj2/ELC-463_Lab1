import java.util.BitSet;
import java.util.HashMap;
import java.util.Set;
import java.io.File;
import java.io.FileWriter;
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
            //Store hit in history
            if(request == 0)
            {
                super.history[request-1]++;
            }
            else
            {
                super.history[request-1] = super.history[request-2] +1;
            }
            super.hits++;
            //if youre storing the last request above, this line probably isnt useful anymore
            this.cache.get(index).get(getLineNumforTag(tag, index)).setLastUsed(request); //don't do this in FIFO
            return;
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

    private String getUsedFirst(HashMap<String, CacheValues> map)
    {
        int used = 60000; //temp;
        String res = "";
        for(String set:map.keySet())
        {
            if(map.get(set).getLastUsed() < used)
            {
                used = map.get(set).getLastUsed();
                res = set;
            }
        }
        return res;
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
        int total = 0;
        for(String ind:super.cache.keySet())
        {
            info = info +  "Count: " + count + " , Index: " + ind + "\n";
            count++;
            subCount = 0;
            for(String set:super.cache.get(ind).keySet())
            {
                CacheValues value = super.cache.get(ind).get(set);
                info = info +  "    Subcount: " + subCount + " , Tag: " + value.getTag() + "\n";
                subCount++;
                total++;
            }
        }
        info = info + "TOTAL BLOCKS: " + total + "\n";
        System.out.println(info);
    }

    // So we have the index and tag above, now we want to check lines for the index and tag
    // Lines is the cache, it is a hashmap of BitSets and CacheValues.
    // CacheValues is and object of the BitSet and H/M
    private boolean checkExists(String tag, String index)
    {
        //super.lines.get(index).contains(tag).getTag()
        HashMap<String, CacheValues> temp = super.cache.get(index);
        String cacheTag, prevTag = "";
        boolean result = false;
        for(String lineNum:temp.keySet())
        {
            cacheTag = this.cache.get(index).get(lineNum).getTag();
            if(cacheTag.equals(tag))
            {
                result = true;
                break;
            }
            else
            {
                String msg = "\nSET: " + lineNum +
                            "\n\n    TARGET: " + tag +
                            "\n\n    In cache: " + cacheTag;
            }
            prevTag = cacheTag;
        }
        return result;
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
