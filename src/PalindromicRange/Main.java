package PalindromicRange;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class Main {
    boolean isPalindrome(int x) {

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(x);
        String xStr = stringBuilder.toString();
        String revStr = stringBuilder.reverse().toString();
        //System.out.println("Checking palindrome " + x + " result " + xStr.equals(revStr));
        return  xStr.equals(revStr);
    }

    HashMap<Integer,Boolean> checkPalindromicRange(int x,int y) {
        HashMap<Integer,Boolean> hashMap = new HashMap<>();
        for (int i = x ;i<=y;++i) {
           if (isPalindrome(i)) {
                hashMap.put(i,true);
           }else {
               hashMap.put(i,false);
           }
        }

        return hashMap;
    }

    int getPalindromicNumbers(int x ,int y,HashMap<Integer,Boolean> hashMap) {
        int palindromes = 0;
        for (int i = x;i<=y;++i) {
            if (hashMap.get(i) == true) {
                ++palindromes;
            }
        }

        return palindromes;
    }

    int getEvenPalindromes(int x,int y) {
        int evenRange = 0;
        HashMap<Integer,Boolean> hashMap = checkPalindromicRange(x,y);
        for (int i = x;i<=y;++i) {

            for (int j = i;j<=y;++j) {
                //System.out.println("i = " + i + " = j " + j);
                int countPalindromes = getPalindromicNumbers(i,j,hashMap);
                //System.out.println("i = " + i + " = j " + j + "  Palindromes " + countPalindromes);
                if (countPalindromes % 2 == 0) {
                    ++evenRange;
                }
            }
        }

        return evenRange;
    }

    void handleLine(String line) {

        String [] arr = line.split(" ");
        System.out.println(getEvenPalindromes(Integer.parseInt(arr[0]),Integer.parseInt(arr[1])));

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
}
