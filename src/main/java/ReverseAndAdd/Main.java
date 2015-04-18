package ReverseAndAdd;



import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

class Tuple {
    private long x;
    private long y;

    Tuple(long x, long y) {
        this.x = x;
        this.y = y;
    }

    public long getX() {
        return x;
    }

    public void setX(long x) {
        this.x = x;
    }

    public long getY() {
        return y;
    }

    public void setY(long y) {
        this.y = y;
    }
}

public class Main {

    private static final int MAX_ITER = 10000;


    String reverse(String x) {

        StringBuilder stringBuilder = new StringBuilder(x);
        String reversedString = stringBuilder.reverse().toString();
        //System.out.println(reversedString);
        return reversedString;
    }

    Tuple reverseAndAdd(long n,int iter,int maxIter) {
        if (iter > maxIter) {
            throw null;
        }

        String y = new Long(n).toString();
        String x = reverse(new Long(n).toString());
        if (x.equals(y)) {
            //System.out.println("Its a palindrome " + (x+n) );
            return new Tuple(iter,Long.parseLong(x));
        }

        return reverseAndAdd(Long.parseLong(x) + n,iter+1,maxIter);

    }

    void readFile(String fileName) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                Long val = Long.parseLong(line);
                Tuple tuple = reverseAndAdd(val,0,MAX_ITER);
                System.out.println(tuple.getX() + " " + tuple.getY());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String args[]) {
        Main main = new Main();
        main.readFile(args[0]); //No exception handling here
//        try {
//            Tuple t = main.reverseAndAdd(195,0,100);
//            System.out.println(t.getX() + " " + t.getY());
//        } catch (InvalidArgumentException e) {
//            e.printStackTrace();
//        }
    }
}
