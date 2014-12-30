package ValidParenthesis;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Stack;

/**
 * Created by Rahul on 11/16/14.
 * Code is licensed solely to Rahul
 */
public class Main {
    boolean validParenthesis(String str) {
        Stack<Character> parseStack = new Stack<>();
        try {

            for (char c : str.toCharArray()) {
                switch (c) {
                    case '(':
                        parseStack.push(c);
                        break;
                    case '[':
                        parseStack.push(c);
                        break;
                    case '{':
                        parseStack.push(c);
                        break;
                    case ')':
                        char top = parseStack.pop();
                        if (top != '(') {
                            return false;
                        }
                        break;
                    case ']':
                        char top1 = parseStack.pop();
                        if (top1 != '[') {
                            return false;
                        }
                        break;
                    case '}':
                        char top2 = parseStack.pop();
                        if (top2 != '{') {
                            return false;
                        }
                        break;
                }


            }
        }catch (Exception e) {
            return false;
        }

        if (parseStack.size() > 0) {
            return false;
        }
        return true;
    }

    void readFile(String fileName) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String line;

            while ((line = br.readLine()) != null) {

                System.out.println(validParenthesis(line) ? "True" : "False");


            }
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }



    public static void main(String args[]) {
        Main m = new Main();
        m.readFile(args[0]);
    }
}
