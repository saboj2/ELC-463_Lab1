import java.util.HashMap;
import java.util.BitSet;
import java.util.Math;
/**
 * Write a description of class Cache here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Cache
{
    // instance variables - replace the example below with your own
    //Maybe these are what we are looking for, but maybe not...
    private HashMap lines;       //Map byte index to CacheValue Object, more important to get working than sets
    private HashMap cache;      //Map Set to Rows?
    private int hits;
    private int misses;

    /**
     * Constructor for objects of class Cache
     */
    public Cache(int KN, int K)
    {
        //N = KN/K is Number of sets
        //K is number of rows
        
        // initialise these maps
        //might want to rename rows
        HashMap lines = new HashMap(K);
        HashMap cache = new HashMap(KN/K);
        
        //TODO: math later to find size
        final int  addressLength = 24;
        
        int numOfSets = logBase2(KN/K), numOfLines = logBase2(K), tagLength = addressLength - indexlength - 3; //3 for offset, 8 address loaded in
        //maybe not use bit sets? cant do nice math on them. Maybe only for dividing up the bits into tag,etc
        BitSet index = new BitSet(numOfSets);
        BitSet lineNum = new BitSet(numOfLines);
        CacheValues cacheValue;
         
        //Populate Rows with 
        for(int j = 0; j < KN/K; j++)
        {
            lines.clear(); // problems with using the same object?
            for(int i = 0; i < K; i++)
            {
                lineNum = inttoBitSet(i,numOfLines);

                cacheValue = new CacheValues(tagLength);
                lines.put(lineNum, cacheValue); //add line to set
            }
            index = inttoBitSet(j,numOfSets);
            cache.put(index, lines); // add set to cache
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

    public void setHits(hits)
    {
        this.hits = hits;
    }

    public void setMisses(misses)
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
        int bits = 0;
        while (val >>= 1)
        {
            bits++;
        }
        return bits;
    }
    
    public static int bitSettoInt(BitSet binSet)
    {
        int result = 0;
        for(int i = 0; i < binSet.length(); i++)
        {
            result += binSet.get(i) * Math.pow(2,binSet.length()-i)
        }
        return result;
    }

    public static BitSet inttoBitSet(int num, int length) 
    {
        BitSet result = new BitSet();
        for(int i = 0; i < length; i++)
        {
            if(num % 2 == 0)
            {
                result.set(length - i,0);
            }
            else
            {
                result.set(length - i,1);
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
