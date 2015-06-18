package Suffix;


import java.util.Arrays;

public class Solution {
    String [] getSuffix(String string) {
        String suffixes[] = new String[string.length()];
        //suffixes[0] = new String();
        for (int i = 0;i<string.length();++i) {
            String x = string.substring(i);
            //System.out.println("Adding suffix " + x);
            suffixes[i] = x;

        }

        return suffixes;
    }

    String[] suffixSort(String string) {
        String [] suffixes = getSuffix(string);
        Arrays.sort(suffixes);

        return suffixes;
    }

    String findRepeatedSubString(String x,String y) {
        //System.out.println("Finding in " + x + "  " + y);
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0;i<x.length() && i < y.length();++i) {
            if (x.charAt(i) == y.charAt(i)) {
                stringBuilder.append(x.charAt(i));
            }else {
                break;
            }
        }

        return stringBuilder.toString();
    }
    String largestRepeatedString(String string) {
        String [] sortedSuffixes = suffixSort(string);
        String largestRepeatedString = new String();
        for (int i = 0;i<(sortedSuffixes.length-1);++i) {
            String localMax = findRepeatedSubString(sortedSuffixes[i],sortedSuffixes[i+1]);
            if (localMax.length() > largestRepeatedString.length()) {
                largestRepeatedString = localMax;
            }
        }

        return largestRepeatedString;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.largestRepeatedString("BANANA"));
    }


}
