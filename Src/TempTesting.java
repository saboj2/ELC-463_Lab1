//I just wanted to test some stuff :(

import java.util.List;
import java.util.ArrayList;
import java.util.BitSet;

public class TempTesting 
{
    public static void main(String[]args)
    {
        BitSet test = new BitSet(5);
        test = Cache.inttoBitSet(10,6);
        System.out.println(test);
    }
}