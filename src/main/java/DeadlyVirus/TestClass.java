package DeadlyVirus;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;


public class TestClass {

    boolean ifAllSave(ArrayList<Integer> medicines,ArrayList<Integer> persons) {
        Collections.sort(medicines);
        Collections.sort(persons);

        for (int i = 0;i<medicines.size();++i) {
            if (medicines.get(i) < persons.get(i)) {
                return false;
            }
        }

        return true;
    }
    public static void main(String[] args) throws IOException {
        //long t1 = System.currentTimeMillis();
        TestClass testClass = new TestClass();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();

        line = br.readLine();
        ArrayList<Integer> vaccines = new ArrayList<>();
        String arr[] = line.split(" ");
        for (int i = 0;i<arr.length;++i) {
            vaccines.add(Integer.parseInt(arr[i]));
        }

        line = br.readLine();
        ArrayList<Integer> persons = new ArrayList<>();
        arr = line.split(" ");
        for (int i = 0;i<arr.length;++i) {
            persons.add(Integer.parseInt(arr[i]));
        }

        System.out.println(testClass.ifAllSave(vaccines,persons)?"Yes":"No");


    }
}
