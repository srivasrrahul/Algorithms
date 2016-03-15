package ConsecutivePrimes;

import java.io.BufferedReader;
import java.io.FileReader;
import java.math.BigInteger;
import java.util.*;

class RotatedPerm{
    private ArrayList<String> data;

    public RotatedPerm(ArrayList<String> data) {
        this.data = data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RotatedPerm that = (RotatedPerm) o;

        List<String> current = new LinkedList<>(this.data);
        current.addAll(data);

        for (int i = 0;i<current.size();++i) {
            int j = 0;
            for (int k = i;j<that.data.size() && k < current.size();++j,++k) {
                if (Objects.equals(current.get(k),that.data.get(j)) == false)  {
                    break;
                }else {
                    continue;
                }
            }

            if (j == that.data.size()) {
                //It means full loop
                return true;
            }
        }

        return false;

    }

    @Override
    public int hashCode() {
        return 17;
    }
}

public class Main {
    private HashMap<Integer,Boolean> primeLst = new HashMap<>();
    private HashSet<Integer> primes = new HashSet<>();
    void createPrimeCode(int n) {
        for (int i = 1;i<=n;++i) {
            for (int j = i+1;j<=n;++j) {
                if (isPrime(i+j)) {
                    primes.add(i+j);
                }
            }
        }
    }
    boolean isPrime(int n) {
        //System.out.println("Inside prime " + n);

        if (n == 2) {
            return true;
        }

        if ((n % 2) == 0) {
            return false;
        }

        if (primeLst.containsKey(n)) {
            System.out.println("cache hit");
            return primeLst.get(n);
        }
        BigInteger bigInteger = BigInteger.valueOf(n);
        if (bigInteger.isProbablePrime(8)) {
            int x = (int)Math.sqrt(n);
            for (int i = 2;i<=x;++i) {
                if ((n % i) == 0) {
                    primeLst.put(n,false);
                    return false;
                }
            }

            primeLst.put(n,true);
            return true;
        }

        primeLst.put(n,false);
        return false;
    }
    boolean isValid(List<String> lst) {

        for (int i = 0;i<lst.size();++i) {
            if (i > 0) {
                int current = Integer.parseInt(lst.get(i));
                int updatedSum = current + Integer.parseInt(lst.get(i-1));
                if (false == isPrime(updatedSum)) {
                    return false;
                }
            }


        }

        int first = Integer.valueOf(lst.get(0));
        int last = Integer.valueOf(lst.get(lst.size() - 1));
        if (isPrime(first+last) == false) {
            return false;
        }

        return true;
    }

    int removeDuplicates(ArrayList<ArrayList<String>> lst) {
        HashSet<RotatedPerm> rotatedPermHashSet = new HashSet<>();
        for (ArrayList<String> val : lst) {
            if (isValid(val)) {
                //System.out.println("valid is " + val);
                rotatedPermHashSet.add(new RotatedPerm(val));
            }
        }
        return rotatedPermHashSet.size();


    }

    LinkedList<String> insertAt(LinkedList<String> lst,int i,String val) {

        LinkedList<String> clonedLst = new LinkedList<>(lst);
        clonedLst.add(i,val);
        return clonedLst;


    }


    ArrayList<ArrayList<String>> getSolution(ArrayList<String> list,HashSet<String> overallSet,int i,int n) {
        System.out.println(list);
        if (i == n) {
            int x = Integer.parseInt(list.get(i - 1));
            int y = Integer.parseInt(list.get(0));
            if (isPrime(x+y)) {
                ArrayList<ArrayList<String>> solution  = new ArrayList<>();
                solution.add(list);
                return solution;
            }else {
                return new ArrayList<>();
            }
        }

        ArrayList<ArrayList<String>> solution = new ArrayList<>();
        if (i == 0) {
            for (String val : overallSet) {
                HashSet<String> pendingSet = new HashSet<>(overallSet);
                pendingSet.remove(val);
                ArrayList<String> pendingLst = new ArrayList<>(list);
                pendingLst.add(val);
                ArrayList<ArrayList<String>> solve = getSolution(pendingLst,pendingSet,i+1,n);
                solution.addAll(solve);
            }
        }else {

            for (String val : overallSet) {
                int last = Integer.parseInt(list.get(i-1));
                int current = Integer.parseInt(val);
                if (isPrime(last+current)) {
                    HashSet<String> pendingSet = new HashSet<>(overallSet);
                    pendingSet.remove(val);
                    ArrayList<String> pendingLst = new ArrayList<>(list);
                    pendingLst.add(val);
                    ArrayList<ArrayList<String>> solve = getSolution(pendingLst,pendingSet,i+1,n);
                    solution.addAll(solve);
                }

            }


        }

        return solution;
    }
    int handleLine(int n) {
        return 0;
    }

    List<String> getString(int n) {
        List<String> intLst = new ArrayList<>();
        for (int i = 1;i<=n;++i) {
            intLst.add(String.valueOf(i));
        }

        return intLst;
    }

    void readFile(String fileName) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String line;


            while ((line = br.readLine()) != null) {
                int n = Integer.parseInt(line);
                //System.out.println(getString(n));
                HashSet<String> stringHashSet  = new HashSet<>();
                ArrayList<String> list = new ArrayList<>();
                for (int i = 0;i<n;++i) {
                    //list.add("");
                    stringHashSet.add(String.valueOf(i+1));
                }
                ArrayList<ArrayList<String>> solution = getSolution(list,stringHashSet, 0,n);
                //System.out.println("Sol size is " + solution.size());
                System.out.println(removeDuplicates(solution));
//                for (LinkedList<String> val : solution) {
//                    System.out.println(val);
//                }


                //System.out.println("Removing duplicates");
                //solution = removeDuplicates(solution);
                //System.out.println("Solution is");
//                for (String x : solution) {
//                    System.out.println(x);
//                }
                //System.out.println(solution.size());

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
