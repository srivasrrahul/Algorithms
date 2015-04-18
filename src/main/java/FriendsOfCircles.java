import java.util.ArrayList;
import java.util.HashMap;

public class FriendsOfCircles {


    static HashMap<Integer,Boolean> bfs(String [] friends,int sourceNode) {
        //Lets use 0,1,2,3 actual friends are i+1
        int length = friends.length;
        HashMap<Integer,Boolean> discoveredSetOfFriends = new HashMap<>();
        for (int i = 0;i<length;++i) {
            discoveredSetOfFriends.put(i,false);
        }

        discoveredSetOfFriends.put(sourceNode,true); //Since we are starting from 1

        ArrayList<ArrayList<Integer>> layers = new ArrayList<>();

        ArrayList<Integer> initialLayer = new ArrayList<>();
        initialLayer.add(sourceNode);

        layers.add(initialLayer);

        ArrayList<Integer> tree = new ArrayList<>();
        tree.add(sourceNode);
        int layerCounter = 0;

        while (layers.get(layerCounter).size() > 0) {
            ArrayList<Integer> nextLayer = new ArrayList<>();
            for (Integer uIndex : layers.get(layerCounter)) {
                String nodeLst = friends[uIndex];
                for (int j = 0;j<nodeLst.length();++j) {
                    char u = nodeLst.charAt(j);
                    if (u == 'Y' && (discoveredSetOfFriends.get(j) == false)) {
                        //Its a friend
                        discoveredSetOfFriends.put(j,true);
                        tree.add(j);
                        nextLayer.add(j);
                    }
                }
            }


            layers.add(nextLayer);
            ++layerCounter;
        }

        return discoveredSetOfFriends;

    }
    static int friendCircles(String [] friends) {
        HashMap<Integer,Boolean> friendsFromFirst = bfs(friends,0);
        int groups = 1;
        for (int i = 1;i<friends.length;++i) {
            if (friendsFromFirst.get(i) == false) {
                //NOt a friend
                ++groups;
                HashMap<Integer,Boolean> friendsFromCurrent = bfs(friends,i);
                for (Integer x : friendsFromCurrent.keySet()) {
                    if (friendsFromCurrent.get(x) == true) {
                        friendsFromFirst.put(x,true);
                    }
                }
            }
        }

        return groups;
    }

    public static void main(String[] args) {
        String [] strings = new String[] {"YNNNN","NYNNN","NNYNN","NNNYN","NNNNY"};
        System.out.println(friendCircles(strings));
    }
}
