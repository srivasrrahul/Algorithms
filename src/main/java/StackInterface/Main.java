package StackInterface;


import java.io.BufferedReader;
import java.io.FileReader;




public class Main {

    void readFile(String fileName) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String line;

            while ((line = br.readLine()) != null) {

                String arr[] = line.split(" ");
                boolean printOrNot = true;

                for (int i = arr.length - 1;i>=0;--i) {
                    if (printOrNot) {

                        System.out.print(Integer.parseInt(arr[i]));
                        if (!(i == 0 || i == 1)) {
                            System.out.print(' ');
                        }
                        printOrNot = false;
                    }else {
                        printOrNot = true;
                    }

                }

                System.out.println();


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
