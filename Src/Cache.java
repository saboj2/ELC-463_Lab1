import java.util.HashMap;
import java.util.BitSet;
import java.lang.Math;

public class Cache
{
    //Maybe these are what we are looking for, but maybe not...
    protected HashMap<String, CacheValues> line;       //Map byte index to CacheValue Object, more important to get working than sets
    protected HashMap<String, HashMap<String, CacheValues>> cache;      //Map Set to Rows?
    protected int hits;         // Because these are inherited they need to be protected not private
    protected int misses;       // Because these are inherited they need to be protected not private
    protected int[] history;
    protected int tagLength; 
    //protected int indexLength;
    protected String index;
    protected int numOfSets;
    protected int numOfLines;
    protected int k; 

    /**
     * Constructor for objects of class Cache
     */
    public Cache(int KN, int K)
    {
        //N = KN/K is Number of sets
        //K is number of rows
        
        // initialise these maps
        //might want to rename rows
        this.cache = new HashMap<String, HashMap<String, CacheValues>>(KN/K);
        
        //TODO: math later to find size
        final int  addressLength = 24;
        this.k = K;

        numOfSets = logBase2(KN/K);
        numOfLines = logBase2(K); 
        tagLength = addressLength - numOfSets - 3; //3 for offset, 8 address loaded in
        index = "" ;
        String lineNum = "";
        CacheValues cacheValue;
         
        //Populate Rows with 
        for(int j = 0; j < (KN/K); j++)
        {
            line = new HashMap<String, CacheValues>(K); // problems with using the same object?
            for(int i = 0; i < K; ++i)
            {
                lineNum = intToString(i,numOfLines);
                cacheValue = new CacheValues(tagLength);
                line.put(lineNum, cacheValue); //add line to set
            }
            index = intToString(j,numOfSets);
            cache.put(index, line); // add set to cache
        }
    }
    
    public int getHits()
    {
        return this.hits;
    }

    public int getMisses()
    {
        return this.misses;
    }

    public void setHits(int hits)
    {
        this.hits = hits;
    }

    public void initHistory(int size)
    {
        this.history = new int[size];
    }

    public void setMisses(int misses)
    {
        this.misses = misses;
    }

    public void resetRatios()
    {
        //Set all H's to misses and,
        //set number of hits to 0, number of misses to 0
        this.setMisses(0);
        this.setHits(0);
    }

    public static int logBase2(int val)
    {
        val -= 1;
        int bits = 1;
        while (val > 1)
        {
            bits++;
            val/=2;
        }
        return bits; //are we sure this does what we think?
    }

    public String toHexString(byte[] bytes) {
        StringBuilder hexString = new StringBuilder();
    
        for (int i = 0; i < bytes.length; i++) {
            String hex = Integer.toHexString(0xFF & bytes[i]);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
    
        return hexString.toString();
    }
    
    public static int boolToInt(String binSet)
    {
        int result = 0;
        for(int i = 0; i < binSet.length(); i++)
        {
            // So apparently this is an array of Strings
            // Good to know but the val update is necessary
            int val = binSet.charAt(i) - '0';

            result += val * Math.pow(2,(binSet.length()-i-1));
            //result += val * Math.pow(2,(binSet.length()-i));
        
        }
        return result;
    }

    /*
     * This method is used to create the different index keys for the hasmap
     * It takes the row num asnum and the amount of rows as length
     * The return is the BitSet of what the number should be
     */
    public static String intToString(int num, int length) 
    {
        String result = "";
        for(int i = 1; i <= length; i++)
        {
            // Based on docs, we only need to do set on vals that are 1!
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
/*
    _____________________________________________________________________________
   |                                   taglength | taglength + 1-addresslenth-4 | 3bits offset  |
    _____________________________________________________________________________     
*/  