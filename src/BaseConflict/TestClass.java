package BaseConflict;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class TestClass {

    long findSampleSum(String data[],int base) {
        long sampleSum = 0;
        for (int j = 0;j<data.length;++j) {
            sampleSum += Long.parseLong(data[j],base);
        }

        return sampleSum;
    }
    long findBaseBinarySearch(String data[],long givenTotal,int lowBase,int highBase) {
        if (lowBase >= highBase) {
            return lowBase;
        }

        int mid = lowBase + (highBase-lowBase)/2;

        long midSum = findSampleSum(data,mid);

        if (midSum < givenTotal) {
            return findBaseBinarySearch(data,givenTotal,mid+1,highBase);
        }else {
            if (midSum > givenTotal) {
                return findBaseBinarySearch(data,givenTotal,lowBase,mid);
            }else {
                return mid;
            }
        }


    }
    long findBase(String data[],int minBase,long total) {
        //System.out.println("Base is " + minBase);
        for (int i = minBase;i<=16;++i) {
            long sampleSum = 0;
            sampleSum= findSampleSum(data,i);

            if (sampleSum == total) {
                return i;
            }
        }

        return 0;

        //return findBaseBinarySearch(data,total,minBase,16);
    }
    long detectBase(String x,Long total) {
        String data[] = x.split(" ");
        int maxDigit = 0;
        for (String individualStr : data) {
            for (int i = 0;i<individualStr.length();++i) {
                if (individualStr.charAt(i) >= '0' && individualStr.charAt(i) <= '9') {
                    int charInt = individualStr.charAt(i) - '0';
                    //System.out.println(charInt);
                    if (charInt > maxDigit) {
                        maxDigit = charInt;
                    }
                }else {
                    //a-10,b-11,c-12,d-13,e-14,f-15
                    switch (individualStr.charAt(i)) {
                        case 'a':
                            if (maxDigit < 10) {
                                maxDigit = 10;
                            }
                            break;
                        case 'b':
                            if (maxDigit < 11) {
                                maxDigit = 11;
                            }
                            break;
                        case 'c':
                            if (maxDigit < 12) {
                                maxDigit = 12;
                            }
                            break;

                        case 'd':
                            if (maxDigit < 13) {
                                maxDigit = 13;
                            }
                            break;

                        case 'e':
                            if (maxDigit < 14) {
                                maxDigit = 14;
                            }
                            break;

                        case 'f':
                            if (maxDigit < 15) {
                                maxDigit = 15;
                            }
                            break;

                    }
                }
            }
        }

        //Max Digit is the base
        return findBase(data,maxDigit+1,total);
    }

    public static void main(String[] args) throws IOException {
        TestClass testClass = new TestClass();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //String line = br.readLine(); //Its a watse
        String data = br.readLine();
        data = data.toLowerCase();
        Long x = Long.parseLong(br.readLine());
        System.out.println(testClass.detectBase(data,x));



    }
}
