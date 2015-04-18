package FollowingInteger;




import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.TreeSet;

class ReturnValue {
    public ReturnValue(TreeSet<String> strings, TreeSet<Integer> integers) {
        this.strings = strings;
        this.integers = integers;
    }

    public TreeSet<Integer> getIntegers() {
        return integers;
    }

    public void setIntegers(TreeSet<Integer> integers) {
        this.integers = integers;
    }

    public TreeSet<String> getStrings() {
        return strings;
    }

    public void setStrings(TreeSet<String> strings) {
        this.strings = strings;
    }

    private TreeSet<String> strings;
    private TreeSet<Integer> integers;
}

public class Main {
    ReturnValue permutations(String x,int index) {
        ReturnValue returnValue = new ReturnValue(new TreeSet<String>(),new TreeSet<Integer>());
        char ch = x.charAt(index);
        if (index == x.length()-1) {
            returnValue.getStrings().add(String.valueOf(ch));
            returnValue.getIntegers().add(Integer.parseInt(String.valueOf(ch)));
            return returnValue;
        }



        ReturnValue permutationsPending = permutations(x,index+1);
        for (String value : permutationsPending.getStrings()) {
            for (int i = 0;i<=value.length();++i) {
                StringBuilder stringBuilder = new StringBuilder(value);
                stringBuilder.insert(i,ch);
                String finalValue = stringBuilder.toString();
                Integer integerValue = Integer.parseInt(finalValue);


                returnValue.getStrings().add(finalValue);
                returnValue.getIntegers().add(Integer.parseInt(finalValue));
            }
        }

        return returnValue;
    }

    int next(ReturnValue returnValue,String searchedElement) {
        Integer searchElementInt = Integer.parseInt(searchedElement);
        Integer nextValue = returnValue.getIntegers().higher(searchElementInt);
        if (nextValue == null) {
            //System.out.println("its null");
            //Its the highest

            Integer finalValue = null;
            for (String value : returnValue.getStrings()) {
                StringBuilder stringBuilder = new StringBuilder(value);
                stringBuilder.insert(1, '0');
                Integer temp = Integer.parseInt(stringBuilder.toString());
                if (temp > searchElementInt) {
                    finalValue = temp;
                    break;
                }
            }


            return finalValue;
        }

        return nextValue;

    }

    void handleLine(String line) {
        ReturnValue returnValue = permutations(line,0);
//        for (Integer val : returnValue.getIntegers()) {
//            System.out.println(" " + val);
//        }
        System.out.println(next(returnValue,line));

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
        //main.print();
        main.readFile(args[0]);
    }
//    public static void main(String args[]) {
//        Main m = new Main();
//        TreeSet<String> treeSet = m.permutations("115",0);
////        for (String x : treeSet) {
////            System.out.println(x);
////        }
//
//        System.out.println(m.next(treeSet,"511"));
//
//
//    }
}
