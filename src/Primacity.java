import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.*;

class PrimeSieve implements Runnable {

    private final int low;
    private final int high;

    PrimeSieve(int low,int high) {
        this.low = low;
        this.high = high;
        thread = new Thread(this,"test");
        thread.start();

    }

    boolean isPrime(int n) {
        if ((n % 2) == 0) {
            return false;
        }

        BigInteger bigInteger = BigInteger.valueOf(n);

        if (bigInteger.isProbablePrime(8)) {
            for (int i = 3;i<n;i+=2) {
                if ((n%i) == 0) {
                    return false;
                }
            }
        }else {
            return false;
        }

        return true;
    }

    private Thread thread;
    private HashSet<Integer> primeNumbers = new HashSet<>();
    @Override
    public void run() {
        for (int i = low;i<=high;++i) {
            if (isPrime(i)) {
                primeNumbers.add(i);
            }
        }
    }

    Set<Integer> getPrimes() {
        return primeNumbers;
    }

    Thread getThread() {
        return thread;
    }
}
class PrimesDB {
    private HashSet<Integer> primeNumbers = new HashSet<>();
    private int maxPrime = 2;
    PrimesDB() {
        primeNumbers.add(2);
    }

    Set<Integer> getIntegers() {
        return primeNumbers;
    }

    boolean isPrime(int n) {
        if ((n % 2) == 0) {
            return false;
        }

        BigInteger bigInteger = BigInteger.valueOf(n);

        int x = (int)Math.sqrt(n);
        if (bigInteger.isProbablePrime(8)) {
            for (int i = 3;i<=x;i+=2) {
                if ((n%i) == 0) {
                    return false;
                }
            }
        }else {
            return false;
        }

        primeNumbers.add(n);
        return true;
    }
    void extendDB(int i) {
//        for (int j = 3;j<=i;++j) {
//            isPrime(j);
//        }

        PrimeSieve primeSieve[] = new PrimeSieve[THREADS];
        int eachThreadJob = (i-2)/THREADS;
        int low = 2;

        for (int j = 0;j<primeSieve.length;++j) {
            if (j == primeSieve.length-1) {
                System.out.println("Creating sieve for " + low + " " + i);
                primeSieve[j] = new PrimeSieve(low,i);
            }else {
                System.out.println("Creating sieve for " + low + " " + (low+eachThreadJob));
                primeSieve[j] = new PrimeSieve(low,low + eachThreadJob);
                low = low + eachThreadJob;
            }
        }

        for (int j = 0;j<primeSieve.length;++j) {
            try {
                primeSieve[j].getThread().join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        //Now merge all the sets
        for (int j = 0;j<primeSieve.length;++j) {
            primeNumbers.addAll(primeSieve[j].getPrimes());
        }

    }

    private static final int THREADS = 8;

}

class PrimeFactors implements Runnable{
    PrimeFactors(PrimesDB primesDB,int a,int b) {
        this.primesDB = primesDB;
        this.low = a;
        this.high = b;
        thread = new Thread(this,"test");
        thread.start();

    }

    Thread getThread() {
        return thread;
    }

    Thread thread;
    private PrimesDB primesDB;
    private int low;
    private int high;

    private HashMap<Integer,Integer> primacity = new HashMap<>();

    @Override
    public void run() {
        for (int i = low;i<=high;++i) {
            int samplePrimacity = 0;
            for (int prime : primesDB.getIntegers()) {
                if (prime > i) {
                    break;
                }

                if ((i % prime) == 0) {
                    ++samplePrimacity;
                }
            }

            primacity.put(i,samplePrimacity);
        }
    }

    HashMap<Integer,Integer> getResult() {
        return primacity;
    }
}
public class Primacity {





    public static void main(String[] args) {
        Primacity primacity = new Primacity();
        //System.out.println(primacity.checkPrimacityOfNumbers(1000000, 1000000, 1));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;
        try {
            line = br.readLine();
            int testCases = Integer.parseInt(line);
            int maxRange = 0;
            int minRange = 2;
            ArrayList<String> strings = new ArrayList<>();
            for (int i = 0;i<testCases;++i) {
                line = br.readLine();
                strings.add(line);
                String data [] = line.split(" ");
                //int max = Integer.parseInt(data[2]);
                //primacity.updatePrimesDB(max);
                int localMax = Integer.parseInt(data[1]);
                if (localMax > maxRange) {
                    maxRange = localMax;
                }

                int localMin = Integer.parseInt(data[0]);
                if (localMin <= minRange) {
                    minRange = localMin;
                }
//                System.out.println("Case #" + (i+1) + ": " +  primacity.checkPrimacityOfNumbers(Integer.parseInt(data[0]),
//                                                                     Integer.parseInt(data[1]),
//                                                                     Integer.parseInt(data[2])));
            }

            System.out.println(minRange);
            System.out.println(maxRange);
            PrimesDB primesDB = new PrimesDB();
            primesDB.extendDB(maxRange);
            System.out.println("Extended DB");

            //Now we have primes
            //Spawn 4 threads
            PrimeFactors primeFactors[ ] = new PrimeFactors[THREADS];

            int eachThreadJob = (maxRange - minRange)/THREADS;
            for (int i = 0;i<THREADS;++i) {
                System.out.println("MinRange = " + minRange + " maxRange " + (minRange + eachThreadJob));
                if (i == THREADS -1) {
                    //For last encompass everything
                    primeFactors[i] = new PrimeFactors(primesDB, minRange, maxRange);
                }else {
                    primeFactors[i] = new PrimeFactors(primesDB, minRange, minRange + eachThreadJob);
                }
                minRange = minRange + eachThreadJob;
            }

            for (int i = 0;i<THREADS;++i) {
                primeFactors[i].getThread().join();
            }

            System.out.println("Finished threads now iterated");
            for (int i = 0;i<strings.size();++i) {
                System.out.println("Iterating for i = " + i);
                String [] data = strings.get(i).split(" ");
                int low = Integer.parseInt(data[0]);
                int high = Integer.parseInt(data[1]);
                int givenPrimacity = Integer.parseInt(data[2]);
                int calcPrimacity = 0;
                for (int k = low;k<=high;++k) {
                    if (primeFactors[0].getResult().containsKey(k)) {
                        if (primeFactors[0].getResult().get(k) == givenPrimacity) {
                            ++calcPrimacity;
                        }
                    }

                    if (primeFactors[1].getResult().containsKey(k)) {
                        if (primeFactors[1].getResult().get(k) == givenPrimacity) {
                            ++calcPrimacity;
                        }
                    }

                    if (primeFactors[2].getResult().containsKey(k)) {
                        if (primeFactors[2].getResult().get(k) == givenPrimacity) {
                            ++calcPrimacity;
                        }
                    }

                    if (primeFactors[3].getResult().containsKey(k)) {
                        if (primeFactors[3].getResult().get(k) == givenPrimacity) {
                            ++calcPrimacity;
                        }
                    }
                }

                System.out.println(calcPrimacity);

            }


        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static final int THREADS = 4;
}
