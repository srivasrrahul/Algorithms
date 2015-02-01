package IPChecksum;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.BitSet;

public class Main {

    void printResult(ArrayList<Short> ipHeader,int result) {
        for (int i = 0;i<10;++i) {
            System.out.printf("%02x ", ipHeader.get(i));
        }

        //Now print result
        System.out.printf("%02x",(result & 0XFF00)>>8);
        System.out.printf(" %02x ",result & 0XFF);



        for (int i = 12;i<20;++i) {
            System.out.printf("%02x", ipHeader.get(i));
            if (i != 19) {
                System.out.print(" ");
            }
        }

        System.out.println();

    }
    void createCheckSum(ArrayList<Short> message) {
        int  x = 0;
        for (int i = 0;i<10;) {
            int y = message.get(i);
            y = (y<<8);

            y = y & 0X0000FFFF;
            //System.out.printf("After anding %04X\n",y);
            int z =  message.get(i+1);
            z = z & 0X000000FF;
            //System.out.printf("%04X\n",z);
            y = y + z;
            //System.out.printf("%04X\n",y);

            i += 2;
            x += y;


        }

        for (int i = 12;i<19;) {
            int y = message.get(i);
            y = (y<<8);

            y = y & 0X0000FFFF;
            //System.out.printf("After anding %04X\n",y);
            int z =  message.get(i+1);
            z = z & 0X000000FF;
            //System.out.printf("%04X\n",z);
            y = y + z;
            //System.out.printf("%04X\n",y);

            i += 2;
            x += y;
        }


        BitSet bitSet = BitSet.valueOf(new long[]{x});
//        bitSet.toByteArray();
        byte [] bytes = bitSet.toByteArray();
        byte [] sumOnly = new byte[2];
        sumOnly[0] = bytes[0];
        sumOnly[1] = bytes[1];
        byte carry = bytes[2];

        BitSet bitSet1 = BitSet.valueOf(sumOnly);
        long y [] = bitSet1.toLongArray();

        int z = (int)y[0];

        z = z + carry; //Add carry
        int result = z & 0X0000FFFF; //Mask first 2 bytes
        result = ~(result); //Complement it
        result = result & 0X0000FFFF; //Now mask again to make the result
        //System.out.printf("%02X",result);

        //Now print result
        printResult(message,result);

    }

    ArrayList<Short> convertIPAdddressToDottedNotation(String ipAddress) {
        String [] arr = ipAddress.split("\\.");
        ArrayList<Short> arrayList = new ArrayList<>();
        for (int i = 0;i<arr.length;++i) {
            arrayList.add(Short.parseShort(arr[i]));
        }

        return arrayList;
    }

    void handleLine(String line) {

        String ipMessage[] = line.split(" ");
        ArrayList<Short> newSourceIPAddress = convertIPAdddressToDottedNotation(ipMessage[0]);
        ArrayList<Short> newDestinationIPAddress = convertIPAdddressToDottedNotation(ipMessage[1]);

        ArrayList<Short> ipHeader = new ArrayList<>();
        for (int i = 2;i<22;++i) {
            ipHeader.add(Short.parseShort(ipMessage[i], 16));
        }


        //Replace source IP Address
        ipHeader.add(12, newSourceIPAddress.get(0));
        ipHeader.add(13, newSourceIPAddress.get(1));
        ipHeader.add(14, newSourceIPAddress.get(2));
        ipHeader.add(15, newSourceIPAddress.get(3));

        //Replace destination IP Address
        ipHeader.add(16, newDestinationIPAddress.get(0));
        ipHeader.add(17, newDestinationIPAddress.get(1));
        ipHeader.add(18, newDestinationIPAddress.get(2));
        ipHeader.add(19, newDestinationIPAddress.get(3));
        createCheckSum(ipHeader);





    }
    void readFile(String fileName) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                handleLine(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String args[]) {
        Main main = new Main();
        main.readFile(args[0]);
    }


//    public static void main(String[] args) {
//        Main m =  new Main();
//        ArrayList<Byte> arrayList = new ArrayList<>();
//        arrayList.addAll(Arrays.asList(
//                (byte)0X45,(byte)0X00,
//                (byte)0X00,(byte)0X73,
//                (byte)0X00,(byte)0X00,
//                (byte)0X40,(byte)0X00,
//                (byte)0X40,(byte)0X11,
//                (byte)0XB8,(byte)0X61,
//                (byte)0XC0,(byte)0XA8,
//                (byte)0X00,(byte)0X01,
//                (byte)0XC0,(byte)0XA8,
//                (byte)0X00,(byte)0XC7));
//        m.createCheckSum(arrayList);
//    }
}
