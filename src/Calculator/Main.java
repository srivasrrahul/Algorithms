package Calculator;

import java.util.*;


//Not complete yet
import static java.util.Collections.replaceAll;
import static java.util.Collections.reverse;
import static java.util.Collections.swap;

class MutableInt {
    private int x;

    MutableInt(int x) {
        this.x = x;
    }

    public int getInt() {
        return x;
    }

    public void setInt(int x) {
        this.x = x;
    }
}

class Expr {
    int getPriority(String x) {
        switch (x) {
            case "+":
            case "-":
                return 0;
            case "*":
            case "/":
                return 1;
            case "^":
                return 2;
            case "(":
                return -1;

            default:
                assert (false);
                return -1;
        }

    }


    String joinString(List<String> strings) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String string : strings) {
            stringBuilder.append(string);
            stringBuilder.append(" ");
        }

        return stringBuilder.toString();
    }

    String getFirstOperator(List<String> prefixLst) {
        System.out.println("First is " + prefixLst.get(1));
        return prefixLst.get(1);
    }

    List<String> joinPrefixString(List<String> prefixLst,Stack<String> exprStack,Stack<String> exprLst) {
        System.out.println("Inside join exprLst " + Arrays.toString(exprLst.toArray()));
        if (prefixLst.size() == 0 || (getPriority(getFirstOperator(prefixLst)) <= getPriority(exprStack.peek())) ) {

            System.out.println("Before invoking " + Arrays.toString(exprLst.toArray()));
            String t1 = exprLst.pop();

            //System.out.println("t1 => "  + t1);
            String t2 = exprLst.pop();
            String operand = exprStack.pop();
            List<String> expr = new LinkedList<>();
            if (prefixLst.size() != 0) {
                for (String data : prefixLst) {
                    expr.add(data);
                }

                expr.add(0,exprStack.pop());
                expr.add(0,"(");
                expr.add(")");
            }

            expr.add("(");
            expr.add(operand);
            expr.add(t2);
            expr.add(t1);
            expr.add(")");


            System.out.println("Inside join exprLst cond1 " + Arrays.toString(exprLst.toArray()));

            return expr;
        }else {
            String t1 = exprLst.pop();

            String operand = exprStack.pop();
            List<String> expr = new LinkedList<>();
            expr.add("(");
            expr.add(operand);

            //expr.add(t2);
            for (String data : prefixLst) {
                expr.add(data);
            }

            expr.add(t1);
            expr.add(")");
            return expr;
        }
    }

    List<String> prefix(String expr) {
        Stack<String> exprStack = new Stack<>();
        String[] finalExpr = expr.split("(?<=[-+*/^()])|(?=[-+*/^()])");
        Stack<String> exprList = new Stack<>();
        List<String> prefixLst = new LinkedList<>();
        for (String token : finalExpr) {
            System.out.println("Encountered token " + token);
            switch (token) {

                case "+":
                case "-":
                    System.out.println("Before invoking join prefixLst " + Arrays.toString(prefixLst.toArray()));
                    System.out.println("Before invoking join exprStack " + Arrays.toString(exprStack.toArray()));
                    System.out.println("Before invoking join exprLst " + Arrays.toString(exprList.toArray()));
//                    if (!exprStack.empty()) {
//                        int topPriority = getPriority(exprStack.peek());
//                        int currentPriority = getPriority(token);
//                        if (currentPriority <= topPriority) {
//
//                            prefixLst = joinPrefixString(prefixLst,exprStack,exprList);
//
//                        }
//
//
//                    }

                    exprStack.push(token);
                    break;
                case "*":
                case "/":
//                    System.out.println("Before invoking join prefixLst " + Arrays.toString(prefixLst.toArray()));
//                    System.out.println("Before invoking join exprStack " + Arrays.toString(exprStack.toArray()));
//                    System.out.println("Before invoking join exprLst " + Arrays.toString(exprList.toArray()));
//                    if (!exprStack.empty()) {
//                        int topPriority = getPriority(exprStack.peek());
//                        int currentPriority = getPriority(token);
//                        if (currentPriority <= topPriority) {
//
//                            prefixLst = joinPrefixString(prefixLst,exprStack,exprList);
//
//                        }
//
//
//                    }

                    exprStack.add(token);
                    break;
                case "^":
//                    if (!exprStack.empty()) {
//                        int topPriority = getPriority(exprStack.peek());
//                        int currentPriority = getPriority(token);
//                        if (currentPriority <= topPriority) {
//
//                            prefixLst = joinPrefixString(prefixLst,exprStack,exprList);
//
//                        }
//
//
//                    }

                    exprStack.add(token);
                    break;
                case "(":
                    exprStack.add(token);
                    break;
                case ")":
                    String lastOperand = exprStack.peek();

                    while (lastOperand != "(") {

                        prefixLst = joinPrefixString(prefixLst,exprStack,exprList);
                        if (exprStack.empty()) {
                            break;
                        }
                        lastOperand = exprStack.peek();
                    }
                default:
                    exprList.add(token);


            }
        }

        System.out.println("===");
        for (String str : exprList) {
            System.out.println(str);
        }
        System.out.println("===");
        while (!exprStack.empty()) {
            System.out.println("Before invoking join prefixLst " + Arrays.toString(prefixLst.toArray()));
            System.out.println("Before invoking join exprStack " + Arrays.toString(exprStack.toArray()));
            System.out.println("Before invoking join exprLst " + Arrays.toString(exprList.toArray()));
            prefixLst = joinPrefixString(prefixLst,exprStack,exprList);
        }

        return  prefixLst;
    }


    double eval(List<String> prefixExpr,MutableInt index) {
        if (index.getInt() > prefixExpr.size()) {
            return 0.0;
        }

        String currentToken = prefixExpr.get(index.getInt());
        System.out.println("Current token is " + currentToken);
        switch (currentToken) {
            case "+":
                index.setInt(index.getInt()+1);
                double left =  eval(prefixExpr,index);
                //index.setInt(index.getInt()+1);
                double right = eval(prefixExpr,index);
                return left + right;
            case "-":
                index.setInt(index.getInt()+1);
                double left1 =  eval(prefixExpr,index);
                //index.setInt(index.getInt()+1);
                double right1 = eval(prefixExpr,index);
                return left1 - right1;
            case "*":
                index.setInt(index.getInt()+1);
                double left2 =  eval(prefixExpr,index);
                //index.setInt(index.getInt()+1);
                double right2 = eval(prefixExpr,index);
                return left2 * right2;
            case "/":
                index.setInt(index.getInt()+1);
                double left3 =  eval(prefixExpr,index);
                //index.setInt(index.getInt()+1);
                double right3 = eval(prefixExpr,index);
                return left3 / right3;
            case "^":
                index.setInt(index.getInt()+1);
                double left4 =  eval(prefixExpr,index);
                //index.setInt(index.getInt()+1);
                double right4 = eval(prefixExpr,index);
                return Math.pow(left4,right4);

            case "(":
                index.setInt(index.getInt()+1);
                return eval(prefixExpr,index);
            case ")":
                index.setInt(index.getInt()+1);
                return eval(prefixExpr,index);
            default:
                index.setInt(index.getInt()+1);
                //System.out.println("Current token " + Double.parseDouble(currentToken));
                return Double.parseDouble(currentToken);

        }



    }
}
public class Main {
    public static void main(String args[]) {
        Expr expr = new Expr();
        List<String> prefix = expr.prefix("1+2-3*4*5");
        System.out.println("=====Prefix====");
        for (String str : prefix) {
            System.out.print(str + " ");
        }
        System.out.println();
        System.out.println("=====Prefix====");
        //System.out.println(expr.eval(prefix,new MutableInt(0)));

    }
}
