package MatrixRotate;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
    ArrayList<ArrayList<Character>> rotate(ArrayList<ArrayList<Character>> source) {
        ArrayList<ArrayList<Character>> target = new ArrayList<>();
        int j = source.size();
        for (int i = 0;i<source.size();++i,--j) {
            target.add(new ArrayList<Character>());
        }
        for (int i =0;i<source.size();++i) {
            ArrayList<Character> currentRow = source.get(i);
            //Collections.reverse(currentRow);
            for (int k = 0;k<source.size();++k) {
                ArrayList<Character> targetCol = target.get(k);
                if (targetCol.size() == 0) {
                    //Only init
                    for (int m = 0;m<source.size();++m) {
                        targetCol.add('0');
                    }



                }

                targetCol.add(j,currentRow.get(k));
            }

        }

        return target;
    }
    void print(ArrayList<ArrayList<Character>> arrayLists) {
        int k = arrayLists.size()*arrayLists.size();
        int count = 1;
        for (int i = 0;i<arrayLists.size();++i) {
            for (int j = 0;j<arrayLists.size();++j) {
                System.out.print(arrayLists.get(i).get(j) + ((count == k)?"":" "));
                ++count;
            }



        }
    }

    void handleLine(String line) {

        String x[] = line.split(" ");
        double size = Math.sqrt(x.length);
        int count = 0;

        ArrayList<ArrayList<Character>> arrayLists = new ArrayList<>();
        for (int i = 0;i<size;++i) {
            ArrayList<Character> arrayList = new ArrayList<>();
            for (int j = 0;j<size;++j) {
                arrayList.add(Character.valueOf(x[count].charAt(0)));
                ++count;

            }

            arrayLists.add(arrayList);
        }

        ArrayList<ArrayList<Character>> rotated = rotate(arrayLists);
        print(rotated);

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


}
