package PredictNumber;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;


public class Main {
    String m_s = "01121220";
    ArrayList<String> m_CachedResult;
    private static final String m_Fs = "12202001"; //01121220 => 12202001
    private static final String m_FFs = "20010112"; //12202001 => 20010112

    private static final String m_BasicSymbol = "s";
    private static final String m_FunctionSymbol = "fs";
    private static final String m_DoubleFunctionSymbol = "ffs";


    Main() {

    }

    char find(long lowerBase2,long higherBase2,long n) {

        //System.out.println("Lower base2 " + lowerBase2 + " Higher Base2 " + higherBase2 + " N = " + n);
        if (n <= 0) {
            return '0';
        }

        if (n == 1) {
            return '1';
        }

        long p = 1L << lowerBase2;
        //System.out.println("Lower base2 " + (n-p));
        //long distanceFromMid = n-(1<<lowerBase2)  ;
        long distanceFromMid = n-p;
        //System.out.println("Distance from mid " + distanceFromMid);
        if (distanceFromMid < 0) {
            //assert(false);
            //It has reduced further
            //System.out.println("Reduced Further");

            Double d = Math.log(n) / Math.log(2);
            int x = d.intValue();
            int y = x+1;
            return find(x,y,n);

        }

        //System.out.println("Distance from mid is " + distanceFromMid);
        char ch = find(lowerBase2-1,higherBase2-1,distanceFromMid);
//        char ch = find(lowerBase2-1,higherBase2-1,n/2);
        switch (ch) {
            case '0':
                return '1';
            case '1':
                return '2';
            case '2':
                return '0';
        }

        System.out.println("Invalid");
        assert(false);
        return 0;




    }

    String getStringFromSymbol(String symbol) {
        switch (symbol) {
            case m_BasicSymbol:
                return m_s;
            case m_FunctionSymbol:
                return m_Fs;
            case m_DoubleFunctionSymbol:
                return m_FFs;
        }


        assert(false);
        return "";
    }

    ArrayList<String> extend(ArrayList<String> arr) {
        int l = arr.size();
        for (int i = 0;i<l;++i) {
            //System.out.println("Inside extend");
            String temp = arr.get(i);
            switch (temp) {
                case m_BasicSymbol :
                    arr.add(m_FunctionSymbol);
                    break;
                case m_FunctionSymbol:
                    arr.add(m_DoubleFunctionSymbol);
                    break;
                case m_DoubleFunctionSymbol:
                    arr.add(m_BasicSymbol);
                    break;
                default:
                    assert(false);
            }
        }


        return arr;

    }




    char checkNumber(int n) {
        int count = m_s.length();
        if (n < count) {
            return m_s.charAt(n);
        }

        int index = n / 8;
        int charIndex = n % 8;

        if (m_CachedResult != null && index <= m_CachedResult.size()) {
            //System.out.println("Returning from cache");
            return getStringFromSymbol(m_CachedResult.get(index)).charAt(charIndex);
        }

        ArrayList<String> arr = new ArrayList<>();
        arr.add(m_BasicSymbol);

        while (true) {
            arr = extend(arr);
            count = count << 1; //

            if (count > n) {
                int t = 0;

                //System.out.println("Array size is " + arr.size());
                if (null == m_CachedResult) {
                    m_CachedResult = arr;
                }else {
                    m_CachedResult = arr;
                }
                return getStringFromSymbol(arr.get(index)).charAt(charIndex);
            }
        }



    }


    void readFile(String fileName) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String line;
            int i = 0;
            while ((line = br.readLine()) != null) {

                Long n = Long.parseLong(line);

                //System.out.println(find(n));
                if (n != 0) {
                    Double d = Math.log(n.doubleValue()) / Math.log(2);
                    long x = d.longValue();
                    long y = x+1;
                    //System.out.println("X= " + x + " Y = " + y + " " + find(x,y,n));
                    System.out.println(find(x,y,n.longValue()));
                    //System.out.println(y);
                }else {
                    System.out.println('0');
                }


            }
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String args[]) {
        Main m  = new Main();
        m.readFile(args[0]);
    }

}
