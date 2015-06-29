package LongestCommonString;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Solution {

    String subStringSize(String x,String y) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0;i<x.length() && i <y.length();++i) {
            if (x.charAt(i) != y.charAt(i)) {
                break;
            }

            stringBuilder.append(x.charAt(i));
        }

        return stringBuilder.toString();
    }
    void longestCommonSubString(String r1,String r2) {

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(r1);
        stringBuilder.append("#");
        stringBuilder.append(r2);

        String combinedString = stringBuilder.toString();
        Suffix.Solution suffix = new Suffix.Solution();
        String [] suffixes = suffix.suffixSort(combinedString);

        String longestCommonSubString = new String();
        for (int i = 1;i<suffixes.length  ;++i) {
            String x = suffixes[i];
            String y = suffixes[i-1];
            String localSubString = subStringSize(x,y);
            if (localSubString.length() > longestCommonSubString.length()) {
                longestCommonSubString = localSubString;
            }


        }

        System.out.println("Longest Common Substring is " + longestCommonSubString);


    }
    public static void main(String[] args) throws IOException {

        String file1 = args[0];
        String file2 = args[1];

        String r1 = new String(Files.readAllBytes(Paths.get(file1)));
        String r2 = new String(Files.readAllBytes(Paths.get(file2)));

        new Solution().longestCommonSubString(r1,r2);


    }
}
