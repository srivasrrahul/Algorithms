package IdealRandom;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

class Fraction {
    private int numerator;
    private int denominator;

    public Fraction(int numerator, int denominator) {
        this.numerator = numerator;
        this.denominator = denominator;
    }

    public int getNumerator() {
        return numerator;
    }

    public void setNumerator(int numerator) {
        this.numerator = numerator;
    }

    public int getDenominator() {
        return denominator;
    }

    public void setDenominator(int denominator) {
        this.denominator = denominator;
    }


    Fraction add(Fraction rhs) {

        int newDenominator = (getDenominator() * rhs.getDenominator())/BigInteger.valueOf(getDenominator()).gcd(BigInteger.valueOf(rhs.getDenominator())).intValue();
        //System.out.println(newDenominator + " " + rhs.getDenominator() + " " + getDenominator());
        int newNumerator = (newDenominator / getDenominator()) * getNumerator() +
                (newDenominator / rhs.getDenominator()) * rhs.getNumerator();

        return new Fraction(newNumerator,newDenominator);
    }

    Fraction substract(Fraction rhs) {
        int newDenominator = (getDenominator() * rhs.getDenominator())/BigInteger.valueOf(getDenominator()).gcd(BigInteger.valueOf(rhs.getDenominator())).intValue();
        int newNumerator = (newDenominator / getDenominator()) * getNumerator() -
                (newDenominator / rhs.getDenominator()) * rhs.getNumerator();

        return new Fraction(newNumerator,newDenominator);
    }

    Fraction divide(Fraction rhs) {
        int newNumerator = getNumerator() * rhs.getDenominator();
        int newDenominator = getDenominator() * rhs.getNumerator();

        int gcd = BigInteger.valueOf(newNumerator).gcd(BigInteger.valueOf(newDenominator)).intValue();
        return new Fraction(newNumerator / gcd,newDenominator / gcd);
    }

    @Override
    public String toString() {
        return "Fraction{" +
                "numerator=" + numerator +
                ", denominator=" + denominator +
                '}';
    }
}
public class TestClass {



    String getArea(int a,int b,int c) {


        int xIntersection = c;
        int yIntersection = c;

        int deltaX = 0;
        if (xIntersection > a) {
            //Extra area will come in this trinagle
             deltaX = (xIntersection - a);
        }

        int deltaY  = 0;
        if (yIntersection > b) {
            deltaY = (yIntersection-b);
        }

        //double totalDelta = ((deltaX * deltaX)/2.0) + ((deltaY+deltaY)/2.0);
        Fraction xDelta = new Fraction(deltaX*deltaX,2);
        Fraction yDelta = new Fraction(deltaY*deltaY,2);

        Fraction totalDelta = xDelta.add(yDelta);

        //System.out.println(totalDelta.toString());

        Fraction totalArea = new Fraction(a * b,1);

        //System.out.println(totalArea.toString());

        Fraction totalIntersectionArea = new Fraction(xIntersection * yIntersection,2);

        //System.out.println(totalIntersectionArea.toString());

        Fraction coveredArea = totalIntersectionArea.substract(totalDelta);

        //System.out.println(coveredArea.toString());

        Fraction netCoveredArea  = coveredArea.divide(totalArea);


        System.out.println(netCoveredArea.getNumerator() + "/" + netCoveredArea.getDenominator());
        return "";

    }


    public static void main(String[] args) throws IOException {
        //long t1 = System.currentTimeMillis();
        TestClass testClass = new TestClass();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();

        String [] arr = line.split(" ");
        int a = Integer.parseInt(arr[0]);
        int b = Integer.parseInt(arr[1]);
        int c = Integer.parseInt(arr[2]);
        testClass.getArea(a,b,c);






    }
}
