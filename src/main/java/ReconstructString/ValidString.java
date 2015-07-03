package ReconstructString;

import java.util.HashMap;
import java.util.LinkedList;

class Tuple {
    private int x;
    private int y;

    public Tuple(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tuple tuple = (Tuple) o;

        if (x != tuple.x) return false;
        if (y != tuple.y) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }
}

public class ValidString {
    static boolean isValidWord(String x) {
        if (x.equals("it")) {
            return true;
        }

        if (x.equals("was")) {
            return true;
        }

        if (x.equals("the")) {
            return true;
        }

        if (x.equals("best")) {
            return true;
        }

        if (x.equals("of")) {
            return true;
        }

        if (x.equals("times")) {
            return true;
        }

        if (x.equals("hello")) {
            return true;
        }

        if (x.equals("world")) {
            return true;
        }

        return false;
    }

    static HashMap<Tuple,LinkedList<Tuple>> validWordCache = new HashMap<>();
    static LinkedList<Tuple> validString(String s,int x,int y) {
        //System.out.println(s.substring(x,y) + " " + x + " " + y);

        if (x > y) {
            return null;
        }

        Tuple pair = new Tuple(x,y);

        LinkedList<Tuple> list = new LinkedList<>();
        if (isValidWord(s.substring(x, y))) {
            list.add(pair);
            return list;
        }



        //Single byte substring
        if (x + 1 == y) {
            list.add(pair);
            return isValidWord(s.substring(x,y))?list:null;
        }



        if (validWordCache.containsKey(pair)) {

            return validWordCache.get(pair);
        }



        //Add space
        for (int i = x+1;i<y;++i) {
            LinkedList<Tuple> lhs = validString(s,x,i);
            LinkedList<Tuple> rhs = validString(s,i,y);
            if (lhs != null && rhs != null) {
                lhs.addAll(rhs);
                validWordCache.put(pair,lhs);
                //lhs.addLast(pair);

                return lhs;
            }

        }

        validWordCache.put(pair,null);
        return null;
    }

    public static void main(String[] args) {
        //System.out.println(validString("helloworld", 0, "helloworld".length()));
        //System.out.println(validString("itwasthebestoftimes", 0,"itwasthebestoftimes".length()));
//        for (Map.Entry<Tuple,Boolean> entry : validWordCache.entrySet()) {
//            if (entry.getValue()) {
//                System.out.println("itwasthebestoftimes".substring(entry.getKey().getX(),entry.getKey().getY()));
//            }
//        }

        String s = "itwasthebestoftimes";
        LinkedList<Tuple> lst = validString(s, 0,s.length());
        for (Tuple tuple : lst) {
            System.out.println(tuple.getX() + " " + tuple.getY() + s.substring(tuple.getX(),tuple.getY()));
        }
    }
}
