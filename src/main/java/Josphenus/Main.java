package Josphenus;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class Main {
    void iterate(int listSize,int m) {
        int arr[] = new int[listSize];
        for (int j = 0;j<listSize;++j) {
            arr[j] = j;
        }

        int alive = listSize;
        int index = -1;
        int indexForKilling = 1;
        while (alive > 0) {
            int temp = ++index;
            index = temp % listSize;


            if (arr[index] == -1) {
                //Already killed
                continue;
            }



            //++indexForKilling;

            if (indexForKilling == m) {
                --alive;//Kill
                arr[index] = -1;
                System.out.print(index);
                if (alive == 0) {
                    return;
                }
                System.out.print(" ");

                indexForKilling = 0;
                ++indexForKilling;
            }else {
                ++indexForKilling;
            }

        }
    }

    void handleLine(String line) {
        String [] arr = line.split(",");
        int lstSize = Integer.parseInt(arr[0]);
        int m = Integer.parseInt(arr[1]);
        iterate(lstSize,m);
        System.out.println();

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
//    public static void main(String args[]) {
//        Main m = new Main();
//        m.iterate(10,3);
//    }
}
