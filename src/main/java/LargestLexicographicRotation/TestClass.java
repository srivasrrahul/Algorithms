package LargestLexicographicRotation;

import java.io.BufferedReader;
import java.io.FileReader;

/**
 * Created by rasrivastava on 4/28/15.
 */
public class TestClass {
    void readFile(String fileName) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String line;


            while ((line = br.readLine()) != null) {


            }
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String args[]) {

        TestClass m = new TestClass();
        m.readFile(args[0]);
    }
}
