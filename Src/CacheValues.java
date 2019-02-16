import java.util.BitSet;
/**
 * Write a description of class CacheValues here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class CacheValues
{
    // instance variables - replace the example below with your own
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
    
    public int getValid()
    {
        return valid;
    }
    
    public String getTag()
    {
        return tag;
    }

    public int getLastUsed()
    {
        return lastUsed;
    }
    
    public void setValid(int val)
    {
        valid = val;
    }
    
    public void setTag(String val)
    {
        tag = val;
    }

    public void setLastUsed(int i)
    {
        lastUsed = i;
    }
    
    public void printValues()
    {
        String validStr;
        if(valid == 1)
            validStr = "Y";
        else
            validStr = "N";
        
        System.out.println("Tag: " + tag +
                         "\nValue: " + validStr);
    }

}
