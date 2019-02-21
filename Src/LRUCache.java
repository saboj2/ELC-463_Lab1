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
    public int shit = 0;
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
        else
        {
            //System.out.println(tag);
            //System.out.println(index);
            //System.out.println(request);
            //Store miss in history
            if(request == 1) { super.history[request-1] = 0; }
            else
            {
                super.history[request-1] = super.history[request-2];
            }

            //int LRUEntry =  this.cache.get(index).get(super.intToString(0,super.numOfLines)).getLastUsed();
            String lastUsed = getLastUsed(this.cache.get(index));
            String LRUlineNum = "";
            super.misses++;
            //go through each line of the set, K is number of sets
            int i;
            for(i = 0; i < this.k; i++)
            {
                LRUlineNum = super.intToString(i,super.numOfLines);
                //if current line valid bit is zero, then store current cachevalue in current line
                if(super.cache.get(index).get(LRUlineNum).getValid() == 0)
                {
                    this.cache.get(index).get(LRUlineNum).setLastUsed(request);
                    this.cache.get(index).get(LRUlineNum).setTag(tag);
                    this.cache.get(index).get(LRUlineNum).setValid(1);
                    return;
                }
                //else save the last recently used line
                else
                {
                    if(LRUlineNum == lastUsed)
                    {
                        
                        //Store the data in the last recently used line
                        this.cache.get(index).get(LRUlineNum).setLastUsed(request);
                    }
                    this.cache.get(index).get(LRUlineNum).setTag(tag);
                    this.cache.get(index).get(LRUlineNum).setValid(1);
                }
            }
            return;
        }
    }

    private String getLastUsed(HashMap<String, CacheValues> map)
    {
        int used = 0;
        String res = "";
        for(String set:map.keySet())
        {
            if(map.get(set).getLastUsed() > used)
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
        for(String lineNum:temp.keySet())
        {
            cacheTag = this.cache.get(index).get(lineNum).getTag();
            if(cacheTag.equals(tag))
            {
                return true;
            }
            else
            {
                //System.out.println("SET: " + lineNum);
                //System.out.println("    TARGET: " + tag);
                //System.out.println("    In cache: " + cacheTag);
            }
            if(prevTag.equals(cacheTag)) { this.shit++; }
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
