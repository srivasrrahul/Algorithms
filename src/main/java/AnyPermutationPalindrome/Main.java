package AnyPermutationPalindrome;

import java.util.HashMap;

/**
 * Created by Rahul on 11/19/15.
 */
public class Main {
    boolean checkPermutation(String val) {
        HashMap<Character,Integer> hashMap = new HashMap<>();
        for (int i = 0;i<val.length();++i) {
            if (hashMap.containsKey(val.charAt(i))) {
                int count = hashMap.get(val.charAt(i));
                hashMap.put(val.charAt(i), ++count);
            }else {
                hashMap.put(val.charAt(i), 1);
            }
        }

        int oddCount = 0;

        for (Character c: hashMap.keySet()) {
            if ((hashMap.get(c) % 2) == 0) {
                //Even np
                continue;
            }else {
                ++oddCount;
            }
        }

        if (oddCount < 2) {
            return true;
        }else {
            return false;
        }
    }

    public static void main(String[] args) {
        Main main = new Main();
        System.out.println(main.checkPermutation("civil"));
    }
}
