package LongestRepeatedSubString;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

//Correct solve is suffix tree but dimensions are small here
public class Main {
    int getIsSubString(String source,int index,String needle) {
        return source.indexOf(needle,index);
    }

    boolean ifAllSpace(String x) {
        for (Character character : x.toCharArray()) {
            if (character != ' ') {
                return false;
            }
        }

        return true;
    }
    String getRepeatedSubString(String source,int index) {
        String largestString = "";
        for (int i = index;i<source.length();++i) {
            String extractString = source.substring(index,i);
            //System.out.println("Source is " + extractString + " index "  + i + " " + getIsSubString(source,i,extractString));
            if (getIsSubString(source,i,extractString) >= 0) {
                //System.out.println(largestString.length() + " " + extractString.length()) ;
                if (largestString.length() < extractString.length() && !ifAllSpace(extractString)) {
                    //System.out.println("Updating larget string to " + extractString);
                    largestString = extractString;
                }
            }
        }

        //System.out.println("Retunring largest string " + largestString);

        return largestString;
    }

    void getTotalRepeatedString(String source) {
        String globalMax = "";
        for (int i = 0;i<source.length();++i) {
            String largestString = getRepeatedSubString(source,i);
            if (globalMax.length() < largestString.length() && !ifAllSpace(largestString) ) {
                globalMax = largestString;
            }
        }

        //System.out.println(globalMax);
        boolean validString = false;
        for (Character character : globalMax.toCharArray()) {
            if (character != ' ') {
                validString = true;

                break;
            }
        }
        System.out.println((validString?globalMax:"NONE"));
    }


    void handleLine(String line) {

        getTotalRepeatedString(line);

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
