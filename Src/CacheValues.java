/*
 * This class wwraps the value stored in the cache as an object with attributs
 */
public class CacheValues
{
    // 
    private String tag;
    private int valid;
    private int lastUsed; //while looping through each address hit, store iterate as reference for LRU/FIFO

    /**
     * Constructor for objects of class CacheValues
     */
    public CacheValues(int K)
    {
        // initialise instance variables
        tag = "";
        valid = 0;
        lastUsed = 0;
    }
    
    /*
     * Get if the block has been used (1 if used 0 if not used)
     */
    public int getValid()
    {
        return valid;
    }
    
    /*
     * Get the blocks tag
     */
    public String getTag()
    {
        return tag;
    }

    /*
     * Get when the cache block was used
     */
    public int getLastUsed()
    {
        return lastUsed;
    }
    
    /*
     * Set the valid variable
     */
    public void setValid(int val)
    {
        valid = val;
    }
    
    /*
     * Update the cache's stored tag
     */
    public void setTag(String val)
    {
        tag = val;
    }

    /*
     * Update the lastUsed variable
     */
    public void setLastUsed(int i)
    {
        lastUsed = i;
    }
}
