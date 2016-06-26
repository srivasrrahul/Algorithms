package LargestLexicographicRotation;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by Rahul on 4/28/15.
 */
class SubString {
    private int index;
    private String data;

    public SubString(int index, String data) {
        this.index = index;
        this.data = data;
    }

    public int getIndex() {
        return index;
    }

    public String getData() {
        return data;
    }

    public char getData(int j) {
        int length = data.length();
        int k = (index+j) % length;
        return data.charAt(k);
    }

}
public class TestClass {
    String findLargestLexicographicRotation(String lang) {
        StringBuilder stringBuilder = new StringBuilder(lang);
        stringBuilder.append(lang);
        String total = stringBuilder.toString();
        String largestRotation = lang;
        StringBuilder x = new StringBuilder(lang);
        for (int i = 1;i<lang.length();++i) {
            //String val = total.substring(i,i+lang.length());
            char ch = x.charAt(0);

            x = x.deleteCharAt(0);
            x = x.append(ch);
            String val = x.toString();

            if (largestRotation.compareTo(val) < 0) {
                largestRotation = val;
            }

        }

        return largestRotation;

    }
    void readFile(String fileName) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String line = br.readLine();
            System.out.println(findLargestLexicographicRotation(line));


//            while ((line = br.readLine()) != null) {
//
//
//            }
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String args[]) {

        TestClass m = new TestClass();
        m.readFile(null);
    }
}
