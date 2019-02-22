import java.util.HashMap;
import java.lang.Math;

/*
 * This is the parent class for LRUCache class and FIFOCache class
 * It is used to store all the attributes and methods shared 
 */
public class Cache
{
    protected HashMap<String, CacheValues> line;       //Map byte index to CacheValue Object, more important to get working than sets
    protected HashMap<String, HashMap<String, CacheValues>> cache;      //Map Set to Rows?
    protected int hits;         // Because these are inherited they need to be protected not private
    protected int misses;       // Because these are inherited they need to be protected not private
    protected int tagLength; 
    protected String index;
    protected int numOfSets;
    protected int indexLength;
    protected int k; 

    /*
     * Constructor for objects of class Cache
     */
    public Cache(int KN, int K)
    {
        
        // initialise these maps
        this.cache = new HashMap<String, HashMap<String, CacheValues>>(KN/K);
        
        final int  addressLength = 24;
        this.k = K;

        //Set the lengths of sets,index and tags
        numOfSets = logBase2(KN/K);
        indexLength = logBase2(K); 
        tagLength = addressLength - indexLength - 3; //3 for offset, 8 address loaded in
        index = "" ;
        String lineNum = "";
        CacheValues cacheValue;
         
        //Populate Rows with 
        for(int j = 0; j < K; j++)
        {
            line = new HashMap<String, CacheValues>(K); // problems with using the same object?
            for(int i = 0; i < KN/K; ++i)
            {
                lineNum = intToString(i,numOfSets);
                cacheValue = new CacheValues(tagLength);
                line.put(lineNum, cacheValue); //add line to set
            }
            index = intToString(j,indexLength);
            cache.put(index, line); // add set to cache
        }
    }
    
    /*
     * Get total number of hits
     */
    public int getHits()
    {
        return this.hits;
    }

    /*
     * Get total number of misses
     */
    public int getMisses()
    {
        return this.misses;
    }

    /*
     * This method takes an integer and returns the log base 2 of that int
     */
    public static int logBase2(int val)
    {
        val /=2;
        int bits = 1;
        while (val > 1)
        {
            bits++;
            val/=2;
        }
        return bits; 
    }
    
    /*
        * This method takes a HashMAp as its paramater and finds which set was the last used
        * It then returns the set in the method above to be updated with a new tag value
        */
    protected String getUsedFirst(HashMap<String, CacheValues> map)
    {
        int used = 60000; //Set to amount of addresses in the trace file;
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

    /*
        * This method checks all sets for the tag based on the given nidex
        */
    protected boolean checkExists(String tag, String index)
    {
        String cacheTag;
        boolean result = false;
        for(String lineNum:this.cache.get(index).keySet())  //Loop through the sets for the given index
        {
            cacheTag = this.cache.get(index).get(lineNum).getTag(); //Get the tag in the set
            if(cacheTag.equals(tag))
            {
                result = true;
                break;
            }
            else { } //Do nothing
        }
        return result;
    }

    /*
        * This method searches for the set the tag is located in
        * This is always going to return a value because the check for
        * if it exists in the cache is already done
        */
    protected String getLineNumforTag(String tag, String index)
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

    /*
     * This method resets the hits and misses so that the second test runs
     * as if the first never happened and the results are correct
     */
    protected void reset()
    {
        this.misses = 0;
        this.hits = 0;
    }

    /*
     * This method is used to create the different index keys for the hasmap
     * It takes the row num asnum and the amount of rows as length
     * The return is the String of what the number should be
     */
    public static String intToString(int num, int length) 
    {
        String result = "";
        for(int i = 1; i <= length; i++)
        {
            if(num % 2 != 0)
            {
                result = result + "1";
            }
            else
            {
                result = result + "0";
            }
            num = num / 2;
        }
        return result;
    }
}