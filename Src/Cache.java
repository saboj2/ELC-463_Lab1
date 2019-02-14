import java.util.HashMap;
import java.util.BitSet;
import java.lang.Math;

public class Cache
{
    //Maybe these are what we are looking for, but maybe not...
    protected HashMap line;       //Map byte index to CacheValue Object, more important to get working than sets
    protected HashMap cache;      //Map Set to Rows?
    protected int hits;         // Because these are inherited they need to be protected not private
    protected int misses;       // Because these are inherited they need to be protected not private
    protected int tagLength; 
    protected int indexLength;
    protected BitSet index;

    /**
     * Constructor for objects of class Cache
     */
    public Cache(int KN, int K)
    {
        //N = KN/K is Number of sets
        //K is number of rows
        
        // initialise these maps
        //might want to rename rows
        cache = new HashMap<BitSet, HashMap<BitSet, CacheValues>>(KN/K);
        
        //TODO: math later to find size
        final int  addressLength = 24;
        indexLength = 0; //TODO: Must fix this for bellow
        tagLength = addressLength - indexLength - 3; //3 for offset, 8 address loaded in

        int numOfSets = logBase2(KN/K), numOfLines = logBase2(K); 
        //maybe not use bit sets? cant do nice math on them. Maybe only for dividing up the bits into tag,etc
        System.out.println(numOfSets);
        System.out.println(numOfLines);
        index = new BitSet(numOfSets);
        BitSet lineNum = new BitSet(numOfLines);
        CacheValues cacheValue;
         
        //Populate Rows with 
        for(int j = 0; j < KN/K; j++)
        {
            line = new HashMap(K); // problems with using the same object?
            for(int i = 0; i < K; i++)
            {
                lineNum = inttoBitSet(i,numOfLines);

                System.out.println(toHexString(lineNum.toByteArray()));
                cacheValue = new CacheValues(tagLength);
                line.put(lineNum, cacheValue); //add line to set
            }
            index = inttoBitSet(j,numOfSets);
            System.out.println("Adding the line and index value");
            cache.put(index, line); // add set to cache
            System.out.println("Added");
        }
        // TODO: We need to fix the line storing because we are storing nulls
        for(BitSet index:cache.keySet())
        {
            System.out.println(bitSettoInt(index));
        }
        System.out.println("Finished");
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

    public int logBase2(int val)
    {
        int bits = 1;
        while (val > 1)
        {
            bits++;
            val/=2;
        }
        return bits;
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
    
    public static int bitSettoInt(BitSet binSet)
    {
        int result = 0;
        for(int i = 0; i < binSet.length(); i++)
        {
            // So apparently this is an array of booleans
            // Good to know but the val update is necessary
            int val = 0;
            if(binSet.get(i))
            {
                val = 1;
            }

            result += val * Math.pow(2,(binSet.length()-i-1));
        }
        return result;
    }

    /*
     * This method is used to create the different index keys for the hasmap
     * It takes the row num asnum and the amount of rows as length
     * The return is the BitSet of what the number should be
     */
    public static BitSet inttoBitSet(int num, int length) 
    {
        BitSet result = new BitSet(length);
        for(int i = 1; i < length; i++)
        {
            // Based on docs, we only need to do set on vals that are 1!
            if(num % 2 != 0)
            {
                result.set(length - i);
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
