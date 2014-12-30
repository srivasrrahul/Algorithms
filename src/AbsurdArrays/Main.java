package AbsurdArrays;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    int[] getN(String l) {
        int [] arr = new int[2];
        StringBuilder stringBuilder = new StringBuilder();
        int index = 0;
        for (char ch : l.toCharArray()) {
            if (ch == ';') {
                //System.out.println("index for ; is " + index);
                arr[1] = index;
                break;
            }

            ++index;

            stringBuilder.append(ch);
        }

        arr[0] = Integer.parseInt(stringBuilder.toString());
        //System.out.println("N is " + arr[0]);
        return arr;
    }
    int findDuplicate(String l) {
        int [] arr = getN(l);
        int sum = 0;
        int sumInt = 0;
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = arr[1]+1;i<l.toCharArray().length;++i) {
            char ch = l.charAt(i);
            //System.out.println("Char is " + ch);
            if (ch == ',') {
                //System.out.println("Encountered ," + stringBuilder.toString());
                sumInt += Integer.parseInt(stringBuilder.toString());
                stringBuilder.setLength(0);
                continue;
            }else {
                //System.out.println("Adding ch " + ch);
                stringBuilder.append(ch);
            }
        }

        sumInt += Integer.parseInt(stringBuilder.toString());
        int n = arr[0];
        return sumInt - (((n-1)*(n-2))/2);

    }

    void handleLine(String line) {
        System.out.println(findDuplicate(line));

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
}
