package Kattis;

/**
 * Created by rasrivastava on 1/2/17.
 */

public class Reverse {
    public String reverse(String str) {
        if (str.length() <= 1) {
            return str;
        }else {
            Character firtChar = str.charAt(0);
            Character lastLastChar = str.charAt(str.length()-1);
            String pending = reverse(str.substring(1,str.length()-1));
            return lastLastChar.toString() + pending + firtChar.toString();
//            StringBuilder totalString = new StringBuilder();
//            totalString.append(lastLastChar).append(pending).append(firtChar);
//            return totalString.toString();

        }
    }

//    public static void main(String[] args) {
//        System.out.println(new Reverse().reverse("hello world"));
//    }
}
