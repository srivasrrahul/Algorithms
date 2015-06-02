package ConsecutivePrimes;

import java.io.BufferedReader;
import java.io.FileReader;
import java.math.BigInteger;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;


public class Main {
    boolean isPrime(int n) {
        //System.out.println("Inside prime " + n);

        if (n == 2) {
            return true;
        }

        if ((n % 2) == 0) {
            return false;
        }

        BigInteger bigInteger = BigInteger.valueOf(n);
        if (bigInteger.isProbablePrime(8)) {
            int x = (int)Math.sqrt(n);
            for (int i = 2;i<=x;++i) {
                if ((n % i) == 0) {

                    return false;
                }
            }


            return true;
        }

        return false;
    }
    boolean isValid(String x,int index) {
        for (int i = 0;i<x.length()-1;++i) {
            String currentVal = String.valueOf(x.charAt(i));
            int currentValInt = Integer.parseInt(currentVal);
            int nextValInt = Integer.parseInt(String.valueOf(x.charAt(i+1)));

            if (isPrime(currentValInt + nextValInt) == false) {
                return false;
            }
        }

        if (index == x.length()) {
            int first = Integer.parseInt(String.valueOf(x.charAt(0)));
            int last = Integer.parseInt(String.valueOf(x.charAt(x.length() - 1)));

            if (isPrime(first + last) == false) {
                return false;
            }
        }

        return true;
    }

    List<String> removeDuplicates(List<String> lst) {
        HashSet<String> stringHashSet  = new HashSet<>();
        List<String> sol = new LinkedList<>();
        ListIterator<String> stringListIterator = lst.listIterator();
        while (stringListIterator.hasNext()) {
            String val = stringListIterator.next();
            boolean found = false;
            for (String x : stringHashSet) {
                if (x.contains(val)) {
                    found = true;
                    break;
                }
            }

            if (found == false) {
                sol.add(val);
                stringHashSet.add(val + val);
            }else {

            }
        }




        return sol;
    }
    List<String> getPermutation(String string,int index) {

        if (index == string.length()-1) {
            LinkedList<String> arrayList = new LinkedList<>();
            arrayList.add(String.valueOf(string.charAt(string.length()-1)));
            return arrayList;
        }



        List<String> lst = getPermutation(string,index+1);
        String lastIndex = String.valueOf(string.charAt(index));
        int lastIndexInt = Integer.parseInt(lastIndex);
        List<String> solution = new LinkedList<>();
        for (String val : lst) {
            //System.out.println("Val is " + val);
            for (int i = 0;i<val.length();++i) {
                StringBuilder stringBuilder = new StringBuilder(val);
                stringBuilder.insert(i,lastIndexInt);
                String updatedStr = stringBuilder.toString();
                //System.out.println("Updated string is " + updatedStr);
                if (isValid(updatedStr,index)) {
                    //System.out.println("Its valid");
                    solution.add(updatedStr);
                }


            }

            //For last value
            StringBuilder stringBuilder = new StringBuilder(val);
            stringBuilder.append(lastIndexInt);
            String updatedStr = stringBuilder.toString();
            //System.out.println("Updated string is " + updatedStr);
            if (isValid(updatedStr,index)) {
                //System.out.println("Its valid");
                solution.add(stringBuilder.toString());
            }



        }

        return solution;


    }
    int handleLine(int n) {
        return 0;
    }

    String getString(int n) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 1;i<=n;++i) {
            stringBuilder.append(i);
        }

        return stringBuilder.toString();
    }

    void readFile(String fileName) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String line;


            while ((line = br.readLine()) != null) {
                int n = Integer.parseInt(line);
                //System.out.println(n);
                List<String> solution = getPermutation(getString(n),0);
                //System.out.println("Solution is");
//                for (String x : solution) {
//                    System.out.println(x);
//                }
                //System.out.println("Removing duplicates");
                solution = removeDuplicates(solution);
                //System.out.println("Solution is");
//                for (String x : solution) {
//                    System.out.println(x);
//                }
                System.out.println(solution.size());

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
