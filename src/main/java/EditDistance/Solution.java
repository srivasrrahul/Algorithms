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

    int editDistance(String source,int i,String dest,int j) {
        //System.out.println(i +  "  " + j);
        if (i == 0 && j == 0) {
            if (source.charAt(i) == dest.charAt(j)) {
                return 0;
            }else {
                return 1;
            }
        }

        if (i == 0) {
            //remove
            return 1 + editDistance(source,i,dest,j-1);
        }

        if (j == 0) {
            //remove
            return 1 + editDistance(source,i-1,dest,j);
        }

        char c1 = source.charAt(i);
        char c2 = dest.charAt(j);
        int minCost = Integer.MAX_VALUE;
        int cost1 = Integer.MAX_VALUE;
        if (c1 == c2) {
            cost1 = 0 + editDistance(source,i-1,dest,j-1);
        }else {
            //Change
            System.out.println("Place 1");
            cost1 = 1 + editDistance(source,i-1,dest,j-1);
        }

        minCost = cost1;

        int cost2 = 1 + editDistance(source,i-1,dest,j);
        if (cost2 < minCost) {
            minCost = cost2;
        }

        int cost3 = 1 + editDistance(source,i,dest,j-1);
        if (cost3 < minCost) {
            minCost = cost3;
        }

        return minCost;


    }

    public static void main(String[] args) {
        Solution  solution = new Solution();
        System.out.println(solution.editDistance("exponential","exponential".length()-1,"polynomial","polynomial".length()-1));
    }
}
