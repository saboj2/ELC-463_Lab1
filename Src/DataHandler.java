import java.io.ObjectInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

/*
 * This class is used to read the trace files into memory
 */
public class DataHandler
{
    private List<byte[]> addressList; 

    /*
     * This creates a List based on the file found in the path
     */
    public DataHandler(String path)
    {
        addressList = new ArrayList<>();
        // These trace files are 180KB long, hopefully mem can handle that
        try
        {
            //Read the file
            FileInputStream fis = new FileInputStream(path); 
            DataInputStream dis = new DataInputStream(fis);
            int length = dis.available();
            byte[] buff = new byte[length];
            dis.readFully(buff);
            
            try
            {
                byte[] traceValue = new byte[1];
                for(int i = 0; i < length; i+=3) 
                {
                    byte[] memAddress = new byte[3];
                    //Handle if the cache sizes do not workout
                    if(i + 2 < length)
                    {
                        traceValue[0] = buff[i+2];
                        System.arraycopy(traceValue, 0, memAddress, 0, 1);
                        traceValue[0] = buff[i+1];
                        System.arraycopy(traceValue, 0, memAddress, 1, 1);
                        traceValue[0] = buff[i];
                        System.arraycopy(traceValue, 0, memAddress, 2, 1);
                    }
                    else if(i + 1 < length && i + 2 >= length)
                    {
                        traceValue[0] = (byte)0x00&0xFF;
                        System.arraycopy(traceValue, 0, memAddress, 0, 1);
                        traceValue[0] = buff[i+1];
                        System.arraycopy(traceValue, 0, memAddress, 1, 1);
                        traceValue[0] = buff[i];
                        System.arraycopy(traceValue, 0, memAddress, 2, 1);
                    }
                    else
                    {
                        traceValue[0] = (byte)0x00&0xFF;
                        System.arraycopy(traceValue, 0, memAddress, 0, 1);
                        System.arraycopy(traceValue, 0, memAddress, 1, 1);
                        traceValue[0] = buff[i];
                        System.arraycopy(traceValue, 0, memAddress, 2, 1);
                    }
                    this.addressList.add(memAddress);
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
    
    /*
     * This method returns the addressList
     */
    public List<byte[]> getAddressList()
    {
       return addressList;
    }
}
