package SmartIDE;

import java.io.IOException;
import java.util.Scanner;


interface ParsingState {

}

//Correct solve is by regular expression but that requires copying from source code which is not ethical
public class Solution {
    String removeBlockComments(String inputBuffer) {
        StringBuilder stringBuilder = new StringBuilder();
        return "";
    }

    String removeSingleLineComment(String x) {
        StringBuilder stringBuilder = new StringBuilder();
        boolean keeOnAdding = false;
        for (int i = 0;i<x.length();++i) {
            if (i < x.length()-1 && x.charAt(i) == '/' && x.charAt(i+1) == '/') {

                stringBuilder.append(x.charAt(i));

                keeOnAdding = true;
            }
            else {
                if (keeOnAdding == false) {

                    //stringBuilder.append(x.charAt(i));
                } else {
                    stringBuilder.append(x.charAt(i));

                }
            }
        }

        return stringBuilder.toString();
    }

    String extractMultiLineComments(String buffer) {
        StringBuilder stringBuilder = new StringBuilder();

        boolean keepnOnAdding = false;
        boolean alreadyParsedState = false;
        for (int i = 0;i<buffer.length();++i) {
            if (i < buffer.length()-1 && buffer.charAt(i) == '/' && buffer.charAt(i+1) == '*') {
                stringBuilder.append(buffer.charAt(i));
                keepnOnAdding = true;
            }else {
                if (i < buffer.length()-1 && buffer.charAt(i) == '/' && buffer.charAt(i+1) == '/') {
                    stringBuilder.append(buffer.charAt(i));
                    alreadyParsedState = true;
                }
                //Don't handle mix comments
                else if (i < buffer.length()-1 && buffer.charAt(i) == '*' && buffer.charAt(i+1) == '/') {
                    stringBuilder.append(buffer.charAt(i));
                    stringBuilder.append(buffer.charAt(i+1));
                    keepnOnAdding = false;
                }

                else if (keepnOnAdding) {
                    stringBuilder.append(buffer.charAt(i));
                }

                else if (alreadyParsedState == true) {
                    stringBuilder.append(buffer.charAt(i));
                }

                if ((buffer.charAt(i) == '\r' || buffer.charAt(i) == '\n') && alreadyParsedState == true) {
                    keepnOnAdding = false;
                }


            }



        }
        return stringBuilder.toString();
    }

    public static void main(String[] args) throws IOException {
        Solution m = new Solution();
        Scanner in = new Scanner(System.in);
        //BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String line;
        StringBuilder totalBuffer = new StringBuilder();


        while ((line = in.nextLine()) != null) {
            String updatedLine = m.removeSingleLineComment(line);
            //System.out.println(updatedLine);
            totalBuffer.append(updatedLine);
            if (false == in.hasNextLine()) {
                //System.out.println("test");
                break;
            }
        }

        System.out.println(m.extractMultiLineComments(totalBuffer.toString()));
        //String totalSource = totalBuffer.toString();
        //String arr [] = totalSource.split("(?:/\\*(?:[^*]|(?:\\*+[^*/]))*\\*+/)|(?://.*)");
        //System.out.println(arr[0]);





    }
}
