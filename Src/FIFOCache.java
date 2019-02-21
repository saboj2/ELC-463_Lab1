
import java.util.HashMap;
import java.util.Set;
/**
 * Write a description of class FIFO here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class FIFOCache extends Cache
{
    // TODO Set up FIFO Class
    public int shit = 0;
    /**
     * Constructor for objects of class FIFO
     */
    public FIFOCache(int KN, int K)
    {
        // initialise instance variables
        super(KN, K);
    }

    public void storeElement(String element, int request)
    {
        
        int tagLen = super.tagLength;
        String tag = element.substring(0, tagLen);
        String index = element.substring(tagLen, element.length() - 3);

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

            //Update value in que
            //this.cache.get(index).get(getLineNumforTag(tag, index)).setLastUsed(request); //don't do this in FIFO we dont want to keep track of most recent

            this.hits++;
        }
        else //Not in cache
        {
            this.misses++;
            boolean stored = false;
            int i = 0;
            String set;
            while(i < super.numOfLines && !stored) //Loop through
            {
                set = super.intToString(i,super.numOfLines);
                if(this.cache.get(index).get(set).getValid()==0) //
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
