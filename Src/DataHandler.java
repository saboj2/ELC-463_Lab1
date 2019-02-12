import java.io.ObjectInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
/**
 * Write a description of class DatHandler here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class DataHandler
{
    // instance variables - replace the example below with your own
    private List<byte[]> death; //

    /**
     * Constructor for objects of class DatHandler
     */
    public DataHandler(String path)
    {
        death = new ArrayList<>();
        // These trace files are 180KB long, hopefully mem can handle that
        try
        {
            FileInputStream fis = new FileInputStream(path); 
            DataInputStream dis = new DataInputStream(fis);
            int length = dis.available();
            byte[] buff = new byte[length];
            dis.readFully(buff);
            
            try
            {
                byte[] traceValue = new byte[1];
                int Count = 0;
                for(int i = 0; i < length; i+=3)
                {
                    //Do maths
                    byte[] memAddress = new byte[3];
                    if(i + 2 < length)
                    {
                        traceValue[0] = buff[i+2];
                        System.arraycopy(traceValue, 0, memAddress, 0, 1);
                        traceValue[0] = buff[i+1];
                        System.arraycopy(traceValue, 0, memAddress, 1, 1);
                        traceValue[0] = buff[i];
                        System.arraycopy(traceValue, 0, memAddress, 2, 1);
                        Count++;
                    }
                    else if(i + 1 < length && i + 2 >= length)
                    {
                        traceValue[0] = (byte)0x00&0xFF;
                        System.arraycopy(traceValue, 0, memAddress, 0, 1);
                        traceValue[0] = buff[i+1];
                        System.arraycopy(traceValue, 0, memAddress, 1, 1);
                        traceValue[0] = buff[i];
                        System.arraycopy(traceValue, 0, memAddress, 2, 1);
                        Count++;
                    }
                    else
                    {
                        traceValue[0] = (byte)0x00&0xFF;
                        System.arraycopy(traceValue, 0, memAddress, 0, 1);
                        System.arraycopy(traceValue, 0, memAddress, 1, 1);
                        traceValue[0] = buff[i];
                        System.arraycopy(traceValue, 0, memAddress, 2, 1);
                        Count++;
                    }
                    this.death.add(memAddress);
                }
            }
            catch(Exception e)
            {
                System.out.println(e);
            }
        }
        catch(Exception err)
        {
            System.out.println("File not Found");
        }
    }
    
    public static String toHexString(byte[] bytes) {
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
    
    public List<byte[]> getAddressesTop(int n)
    {
        List<byte[]> subList;
        if(n < death.size())
        {
            subList = death.subList(0, n);
            return subList;
        }
        else
        {
            return getAddressList();
        }
    }
    
    public List<byte[]> getAddressList()
    {
       return death;
    }
    
    public void printTop(int n)
    {
        
        System.out.println("Printing " + n + " addresses");
        for(int i = 1; i < n - 1; i+=3)
        {
            if(i + 1 < n)
            {
                System.out.println(toHexString(death.get(i-1)) + ", " + toHexString(death.get(i)) + ", " + toHexString(death.get(i + 1)));
            }
            else if(i < n && i+1 >=n)
            {
                System.out.println(toHexString(death.get(i-1)) + ", " + toHexString(death.get(i)));
            }
            else
            {
                System.out.println(toHexString(death.get(i-1)));
            }
        }
        System.out.println("End of list");
    }
    
    public void printList()
    {
        printTop(death.size());
    }
}
