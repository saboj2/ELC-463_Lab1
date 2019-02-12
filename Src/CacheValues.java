
/**
 * Write a description of class CacheValues here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class CacheValues
{
    // instance variables - replace the example below with your own
    private byte[] tag;
    private int valid;

    /**
     * Constructor for objects of class CacheValues
     */
    public CacheValues(int K)
    {
        // initialise instance variables
        tag = new byte[K];
        valid = 0;
    }
    
    public int getValid()
    {
        return valid;
    }
    
    public byte[] getTag()
    {
        return tag;
    }
    
    public void setValid(int val)
    {
        valid = val;
    }
    
    public void setTag(byte[] val)
    {
        tag = val;
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
