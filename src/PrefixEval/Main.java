package PrefixEval;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


class Result {
    private int result;
    private int index;

    Result(int index) {
        this.index = index;
    }
    Result(int result,int index) {
        this.result = result;
        this.index = index;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}

class ResultStr {
    private String result;
    private int index;

    ResultStr(int index) {
        this.index = index;
    }
    ResultStr(String  result,int index) {
        this.result = result;
        this.index = index;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
public class Main {

    ResultStr extractNextTillNext(String string,int index) {
        StringBuilder stringBuilder = new StringBuilder();
        while (string.charAt(index) == ' ') {
            ++index;
        }

        while (string.charAt(index) != ' ') {
            stringBuilder.append(string.charAt(index));
            ++index;
        }

        return new ResultStr(stringBuilder.toString(),index);


    }
    Result eval(String expr,int index) {
//        if (index >= expr.size()) {
//            return new Result(0,index);
//        }

        //System.out.println("List is " + index);

        if (expr.charAt(index) == ' ') {
            ResultStr result = extractNextTillNext(expr, index);
            index = result.getIndex();
        }

        String currentVal = Character.toString(expr.charAt(index));
        ++index;
        switch (currentVal) {
            case "+":
                //System.out.println("Inside + ");
                Result leftResult = eval(expr,index);
                index = leftResult.getIndex();
                Result rightResult = eval(expr,index);
                return new Result(leftResult.getResult() + rightResult.getResult(),
                        rightResult.getIndex());
            case "*":
                //System.out.println("Inside * ");
                Result leftResult1 = eval(expr,index);
                index = leftResult1.getIndex();
                Result rightResult1 = eval(expr,index);
                return new Result(leftResult1.getResult() * rightResult1.getResult(),
                        rightResult1.getIndex());
            case "/":
                Result leftResult2 = eval(expr,index);
                index = leftResult2.getIndex();
                Result rightResult2 = eval(expr,index);
                return new Result(leftResult2.getResult() / rightResult2.getResult(),
                        rightResult2.getIndex());
            default:
                return new Result(Integer.parseInt(currentVal),index);

        }


    }
    Result eval(String [] expr,int index) {
//        if (index >= expr.size()) {
//            return new Result(0,index);
//        }

        //System.out.println("List is " + index);

        String currentVal = expr[index];
        ++index;
        switch (currentVal) {
            case "+":
                //System.out.println("Inside + ");
                Result leftResult = eval(expr,index);
                index = leftResult.getIndex();
                Result rightResult = eval(expr,index);
                return new Result(leftResult.getResult() + rightResult.getResult(),
                        rightResult.getIndex());
            case "*":
                //System.out.println("Inside * ");
                Result leftResult1 = eval(expr,index);
                index = leftResult1.getIndex();
                Result rightResult1 = eval(expr,index);
                return new Result(leftResult1.getResult() * rightResult1.getResult(),
                        rightResult1.getIndex());
            case "/":
                Result leftResult2 = eval(expr,index);
                index = leftResult2.getIndex();
                Result rightResult2 = eval(expr,index);
                return new Result(leftResult2.getResult() / rightResult2.getResult(),
                        rightResult2.getIndex());
            default:
                return new Result(Integer.parseInt(currentVal),index);

        }


    }


    void handleLine(String l) {
//        String[] lst = l.split(" ");
//        //System.out.println(Arrays.asList(lst));
//        //List<String> arrLst = Arrays.asList(lst);
//        Result result = eval(lst,0);
        //System.out.println(result.getResult());
        System.out.println(eval(l,0));
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

//    public static void main(String args[]) {
//
//        Main m = new Main();
//        m.handleLine("+ * 2 3 4");
//
//    }
}
