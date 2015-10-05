package ExpressionParenthesis;

//Let us define a multiplication operation on three symbols a, b, c according to the following table;
//thus ab = b, ba = c, and so on. Notice that the multiplication operation defined by the table is neither associative
//nor commutative.Find an efficient algorithm that examines a string of these symbols, say bbbbac, and decides whether
//or not it is possible to parenthesize the string in such a way that the value of the resulting expression is a.
//For example, on input bbbbac your algorithm should return yes because ((b(bb))(ba))c = a.



import java.util.HashMap;
import java.util.HashSet;

class Tuple {
    private String x;
    private String y;

    public Tuple(String x, String y) {
        this.x = x;
        this.y = y;
    }

    public String getX() {
        return x;
    }

    public String getY() {
        return y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tuple tuple = (Tuple) o;

        if (x != null ? !x.equals(tuple.x) : tuple.x != null) return false;
        if (y != null ? !y.equals(tuple.y) : tuple.y != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = x != null ? x.hashCode() : 0;
        result = 31 * result + (y != null ? y.hashCode() : 0);
        return result;
    }
}

class Index {
    private int x;
    private int y;

    public Index(int x, int y) {
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

        Index index = (Index) o;

        if (x != index.x) return false;
        if (y != index.y) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }
}
//a a = b, a b = b, a c = a
//b a = c, b b = b, b c = a
//c a = a, c b = c, c c = c
public class Solution {
    HashMap<Tuple,String> decisionTable = new HashMap<>();
    HashMap<Index,HashSet<String>> cache = new HashMap<>();
    Solution() {
        decisionTable.put(new Tuple("a","a"),"b");
        decisionTable.put(new Tuple("a","b"),"b");
        decisionTable.put(new Tuple("a","c"),"a");

        decisionTable.put(new Tuple("b","a"),"c");
        decisionTable.put(new Tuple("b","b"),"b");
        decisionTable.put(new Tuple("b","c"),"a");

        decisionTable.put(new Tuple("c","a"),"a");
        decisionTable.put(new Tuple("c","b"),"c");
        decisionTable.put(new Tuple("c","c"),"c");
    }

    HashSet<String> combine(String s,int x,int y) {
        if (x == y) {
            HashSet<String> val = new HashSet<>();
            val.add(String.valueOf(s.charAt(x)));
            return val;
        }


        Index index = new Index(x,y);
        if (cache.containsKey(index)) {
            return cache.get(index);
        }

        System.out.println(x + "  " + y);
        HashSet<String> returnValue = new HashSet<>();
        //System.out.println("Entering");
        for (int i = x;i<y;++i) {
            HashSet<String> val1 = combine(s,x,i);
            HashSet<String> val2 = combine(s,i+1,y);
            for (String v  : val1) {
                for (String v1 : val2) {
                    //System.out.println("Multiplying " + v + " " + v1);
                    returnValue.add(decisionTable.get(new Tuple(v, v1)));
                }
            }
        }

        //System.out.println("Leaving");

        cache.put(index,returnValue);
        return returnValue;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String val = "bbbbac";
        HashSet<String> value = solution.combine(val,0,val.length()-1);
        for (String v : value) {
            System.out.println(v);
        }
    }
}
