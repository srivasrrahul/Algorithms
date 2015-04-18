package StringList;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Main {
    Set<String> getStringList(HashSet<Character> uniqueChars, int length) {
        if (length == 1) {
            Set<String> stringHashSet = new TreeSet<>();
            for (Character character : uniqueChars) {
                stringHashSet.add(character.toString());
            }

            return stringHashSet;
        }

        Set<String> stringList = getStringList(uniqueChars, length - 1);

        Set<String> updatedStringLst = new TreeSet<>();
        for (String string : stringList) {
            for (Character uniqueChar : uniqueChars) {
                for (int j = 0;j<=string.length();++j) {
                    //Character uniqueChar = uniqueChars.get(i);
                    //Insert character at index j
                    StringBuilder stringBuilder = new StringBuilder(string);
                    stringBuilder.insert(j,uniqueChar);
                    updatedStringLst.add(stringBuilder.toString());
                }
            }
        }

        return updatedStringLst;


    }

    HashSet<Character> getUniqueChars(String line) {
        HashSet<Character> hashSet = new HashSet<>();
        for (Character character : line.toCharArray()) {
            hashSet.add(character);
        }

        return hashSet;
    }


    void handleLine(String line) {

        String [] data = line.split(",");
        HashSet<Character> uniqueChars = getUniqueChars(data[1]);
        Set<String> lst = getStringList(uniqueChars,Integer.parseInt(data[0]));

        int i = 0;
        for (String string : lst) {
            System.out.print(string + ((i < lst.size() - 1 )?",":""));
            ++i;
        }

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
//        ArrayList<Character> arrayList = new ArrayList<Character>();
//        arrayList.add('o');
//        arrayList.add('p');
//
//        Set<String> strings = m.getStringList(arrayList,3);
//        for (String string : strings) {
//            System.out.println(string);
//        }
//    }
}
