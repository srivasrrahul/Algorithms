package ConsecutivePrimes;

import java.io.BufferedReader;
import java.io.FileReader;
import java.math.BigInteger;
import java.util.*;


class RotatedPerm{
    private ArrayList<Integer> data;
    List<Integer> current;

    public RotatedPerm(ArrayList<Integer> data) {

        this.data = data;
        current = new ArrayList<>(this.data);
        current.addAll(data);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;



        RotatedPerm that = (RotatedPerm) o;

        if (this.data.size() != ((RotatedPerm) o).data.size()) {
            return false;
        }


        if (Collections.indexOfSubList(current,that.data) != -1) {
            return true;
        }

        //System.out.println("test failed");
        return false;

    }

    @Override
    public int hashCode() {
        return data.size();
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
            //System.out.println("cache hit");
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
    boolean isValid(List<Integer> lst) {

        for (int i = 0;i<lst.size();++i) {
            if (i > 0) {
                int current = lst.get(i);
                int updatedSum = current + lst.get(i - 1);
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

    int removeDuplicates(ArrayList<ArrayList<Integer>> lst) {
        HashSet<RotatedPerm> rotatedPermHashSet = new HashSet<>();
        for (ArrayList<Integer> val : lst) {
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
        //System.out.println(list);
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

    HashSet<Integer> createValidPrimes(int n) {
        HashSet<Integer> primeSet = new HashSet<>();
        for (int i = 1;i<=n;++i) {
            for (int j = i+1;j<=n;++j) {
                if (isPrime(i+j)) {
                    primeSet.add(i+j);
                }
            }
        }

        return primeSet;
    }

    ArrayList<ArrayList<Integer>> getSolve(ArrayList<Integer> list,HashSet<Integer> overallSet,int i,int n,
                                          HashSet<Integer> validPrimes,HashMap<RotatedPerm,ArrayList<ArrayList<Integer>>> cache) {
        System.out.println(list);
        //System.out.println(i);
        if (i == n) {
//            int x = list.get(i - 1);
//            int y = list.get(0);
            //if (validPrimes.contains(x + y)) {
                ArrayList<ArrayList<Integer>> solution  = new ArrayList<>();
                solution.add(list);
                return solution;
//            }else {
//                return new ArrayList<>();
//            }
        }

        if (overallSet.size() == 0) {
            //System.out.println("Problem");
            ArrayList<ArrayList<Integer>> solution  = new ArrayList<>();

            return solution;
        }

        RotatedPerm rotatedPerm = new RotatedPerm(list);

        if (cache.containsKey(rotatedPerm)) {
            ArrayList<ArrayList<Integer>> val = new ArrayList<>(cache.get(rotatedPerm));
            System.out.println("cache hit");
            return val;
        }
        ArrayList<ArrayList<Integer>> solution = new ArrayList<>();
        if (i == 0) {

            for (int val : overallSet) {

                for (int val1 : overallSet) {

                    if (val != val1 && validPrimes.contains(val+val1)) {
                        //System.out.println(val  + " " + val1 );
                        HashSet<Integer> pendingSet = new HashSet<>(overallSet);
                        pendingSet.remove(val);
                        pendingSet.remove(val1);
                        ArrayList<Integer> pendingLst = new ArrayList<>(list);
                        pendingLst.add(val);
                        ArrayList<ArrayList<Integer>> solve = getSolve(pendingLst, pendingSet, i + 1, n-1, validPrimes,cache);
                        for (ArrayList<Integer> lst : solve) {
                            //System.out.println("Returned list " + list);
                            lst.add(val1);
                        }

                        System.out.println("Size is " + solve.size());
                        solution.addAll(solve);
                    }
                }
//                pendingSet.remove(val);
//                ArrayList<Integer> pendingLst = new ArrayList<>(list);
//                pendingLst.add(val);
//                ArrayList<ArrayList<Integer>> solve = getSolve(pendingLst, pendingSet, i + 1, n, validPrimes,cache);
//                solution.addAll(solve);
            }
        }else {

            for (int val : overallSet) {
                int last = list.get(i - 1);
                int current = val;
                if (validPrimes.contains(last+current)) {
                    HashSet<Integer> pendingSet = new HashSet<>(overallSet);
                    pendingSet.remove(val);
                    ArrayList<Integer> pendingLst = new ArrayList<>(list);
                    pendingLst.add(val);
                    ArrayList<ArrayList<Integer>> solve = getSolve(pendingLst, pendingSet, i + 1, n, validPrimes,cache);
                    solution.addAll(solve);
                }

            }


        }

//        if (solution.size() == 0) {
//            System.out.println("problem 1");
//        }
        //cache.put(rotatedPerm,solution);
        return solution;


    }

//    ArrayList<ArrayList<Integer>> getSolutionUsingDC(ArrayList<Integer> validIntegerSet,HashSet<Integer> validPrimes) {
//        if (validIntegerSet.size() == 2) {
//            int sum = validIntegerSet.get(0) + validIntegerSet.get(1);
//            if (validPrimes.contains(sum)) {
//                ArrayList<ArrayList<Integer>> solution = new ArrayList<>();
//                solution.add(validIntegerSet);
//                return solution;
//            }
//        }
//    }

//    ArrayList<ArrayList<Integer>> getSolution1(int n) {
//        System.out.println(n);
//        if (n == 2) {
//            ArrayList<ArrayList<Integer>> arrayLists = new ArrayList<>();
//            ArrayList<Integer> val = new ArrayList<>();
//            val.add(1);
//            val.add(2);
//            arrayLists.add(val);
//            return arrayLists;
//        }
//
//        ArrayList<ArrayList<Integer>> solution  = getSolution1(n-1);
//        ArrayList<ArrayList<Integer>> solutions  = new ArrayList<>();
//        for (ArrayList<Integer> val : solution) {
//            for (int i = 0;i<=val.size();++i) {
//                ArrayList<Integer> data = new ArrayList<>(val);
//                data.add(i,n);
//                if (i == 0) {
//                    int s = data.get(0) + data.get(data.size()-1);
//                    int s1 = data.get(0) + data.get(1);
//                    if (isPrime(s) && isPrime(s1)) {
//                        solutions.add(data);
//                    }
//                }else {
//                    if (i < data.size()-1) {
//                        int s = data.get(i) + data.get(i - 1);
//                        int s1 = data.get(i) + data.get(i + 1);
//                        if (isPrime(s) && isPrime(s1)) {
//                            solutions.add(data);
//                        }
//                    }else {
//                        int s = data.get(i) + data.get(i-1);
//                        int s1 = data.get(i) + data.get(0);
//                        if (isPrime(s) && isPrime(s1)) {
//                            solutions.add(data);
//                        }
//                    }
//                }
//            }
//        }
//
//        return solutions;
//
//
//    }
    void readFile(String fileName) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String line;


            while ((line = br.readLine()) != null) {
                int n = Integer.parseInt(line);
                //System.out.println(getString(n));
                HashSet<Integer> stringHashSet  = new HashSet<>();
                ArrayList<Integer> list = new ArrayList<>();
                for (int i = 0;i<n;++i) {
                    //list.add("");
                    stringHashSet.add(i+1);
                }

                HashSet<Integer> primeSet = createValidPrimes(n);
                HashMap<RotatedPerm,ArrayList<ArrayList<Integer>>> cache = new HashMap<>();
                ArrayList<ArrayList<Integer>> solution = getSolve(list, stringHashSet, 0, n, primeSet,cache);
                //ArrayList<ArrayList<Integer>> solution1 = getSolution1(n);
                System.out.println("Sol size is " + solution.size());
                //System.out.println(solution1.size());
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
        ArrayList<String> v1 = new ArrayList<>(Arrays.asList("1","2","3","4"));
        ArrayList<String> v2 = new ArrayList<>(Arrays.asList("2","3","4","1"));

        //RotatedPerm rotatedPerm1 = new RotatedPerm(v1);
        //RotatedPerm rotatedPerm2 = new RotatedPerm(v2);
        //System.out.println(rotatedPerm1.equals(rotatedPerm2));
        m.readFile(args[0]);
    }
}
