package Bit;

//public class Solution {
//    long factorial(int n) {
//        int number = 1;
//        for (int i = 1;i<=n;++i) {
//            number = number*i;
//        }
//
//        return number;
//    }
//
//    int combination(int n ,int r,int f) {
//        if (r == 0) {
//            return 1;
//        }
//
//        if (r == 1) {
//            return n;
//        }
//
//        if (r == n) {
//            return 1;
//        }
//
//        long rFactorial = factorial(r);
//        long nRFactorial = factorial(n-r);
//
//        return (int)(f/(rFactorial*nRFactorial));
//
//    }
//    public ArrayList<Integer> getRow(int A) {
//        ArrayList<Integer> arrList = new ArrayList<>();
//        int f = (int)factorial(A);
//        for (int i = 0;i<=A;++i) {
//            int comb = combination(A, i, f);
//            arrList.add(comb);
//        }
//
//        return arrList;
//    }
//
//    public static void main(String[] args) {
//        Solution solution = new Solution();
//        ArrayList<Integer> arrayList = solution.getRow(0);
//        for (Integer x: arrayList) {
//            System.out.println(x);
//        }
//
//    }
//}


import java.util.ArrayList;

public class Solution {

    public ArrayList<Integer> plusOne(ArrayList<Integer> A) {
        int j = 0;
        while (A.size()  > 1 && A.get(0) == 0) {
            A.remove(0);
        }



        int carry = 0;
        int y = A.get(A.size()-1);
        if (y + 1 > 9) {
            carry = 1;
        }else {
            A.set(A.size()-1,y+1);
            return A;
        }


        for (int i = A.size()-1;i>=0;--i) {
            int x = A.get(i);
            if (carry == 0) {
                break;
            }else {
                if (x + 1 > 9) {
                    A.set(i,0);
                    carry = 1;
                }else {
                    A.set(i,x+1);
                    return A;
                }
            }
        }

        if (carry == 1) {
            A.add(0,1);
        }


        return A;
//        int exponent = A.size()-1;
//        BigInteger result = BigInteger.ZERO;
//
//        for (Integer x : A) {
//            //result += (Math.pow(10,exponent) * x);)
//
//            //BigInteger val = BigInteger.valueOf((long)(Math.pow(10,exponent)) * x);
//            BigInteger val = BigInteger.ONE;
//            val = val.multiply(BigInteger.valueOf(x));
//
//            BigInteger v1 = BigInteger.valueOf(10);
//            v1 = v1.pow(exponent);
//
//            val = val.multiply(v1);
//            result = result.add(val);
//            --exponent;
//        }
//
//        result = result.add(BigInteger.ONE);
//
//        String string = String.valueOf(result);
//        ArrayList<Integer> returnValue = new ArrayList<>();
//        for (char y : string.toCharArray()) {
//            returnValue.add(y - '0');
//        }
//
//        return returnValue;

    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        ArrayList<Integer> arrayList = new ArrayList<>();
        arrayList.add(0);
        arrayList.add(0);
        //arrayList.add(9);
        ArrayList<Integer> x = solution.plusOne(arrayList);
        for (Integer y : x) {
            System.out.println(y);
        }
    }
}
