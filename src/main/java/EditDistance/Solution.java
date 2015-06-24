package EditDistance;

import java.util.HashMap;

/**
 * Created by rasrivastava on 6/24/15.
 */

class Node {
    private String s1;
    private String s2;

    public Node(String s1, String s2) {
        this.s1 = s1;
        this.s2 = s2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Node node = (Node) o;

        if (s1 != null ? !s1.equals(node.s1) : node.s1 != null) return false;
        if (s2 != null ? !s2.equals(node.s2) : node.s2 != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = s1 != null ? s1.hashCode() : 0;
        result = 31 * result + (s2 != null ? s2.hashCode() : 0);
        return result;
    }
}
public class Solution {

    private HashMap<Node,Integer> cache = new HashMap<>();

    int editDistance(String source,String dest) {

        if (source.equals(dest)) {
            return 0;
        }

        if (source.length() == 0 && dest.length()== 0) {
            return 0;
        }

        if ((source.length() == 1 || dest.length() == 1) && source.equals(dest) == false) {
            return -1;
        }

        Node node = new Node(source,dest);
        if (cache.containsKey(node)) {
            return cache.get(node);
        }
        //System.out.println(source + "  " + dest);
        //Do following logic for each from source to dest
        //for 1 to M of source
        int minValue = Integer.MAX_VALUE;
        int val1 = editDistance(source.substring(0,source.length()-1),dest);
        if (val1 != -1) {
            minValue = val1;
        }

        int val2 = editDistance(source,dest.substring(0,dest.length()-1));

        if (val2 != -1) {
            if (val2 < minValue) {
                minValue = val2;
            }
        }

        int diffCost = 0;
        if (source.charAt(source.length()-1) == dest.charAt(dest.length()-1)) {
            diffCost = 1;
        }

        int val3 = diffCost + editDistance(source.substring(0,source.length()-1),dest.substring(0,dest.length()-1));

        if (val3 != -1) {
            if (val3 < minValue) {
                minValue = val3;
            }
        }

        cache.put(node,minValue+1);
        return minValue+1;
    }

    public static void main(String[] args) {
        Solution  solution = new Solution();
        System.out.println(solution.editDistance("snowy","sunny"));
    }
}
