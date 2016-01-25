package StringRotation;

import java.io.BufferedReader;
import java.io.FileReader;

/**
 * Created by Rahul on 4/28/15.
 */
public class Main {
    boolean isRotation(String source,String dest) {
        if (source.length() != dest.length()) {
            return false;
        }

        StringBuilder stringBuilder = new StringBuilder(source);
        stringBuilder.append(source);
        return stringBuilder.toString().contains(dest);
    }
    void readFile(String fileName) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String line;


            while ((line = br.readLine()) != null) {
                String x[] = line.split(",");
                System.out.println(isRotation(x[0],x[1])?"True":"False");

            }
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String args[]) {

        Main m = new Main();
        m.readFile(args[0]);
    }
}
