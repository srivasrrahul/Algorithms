package PeakTraffic;

import java.io.*;
import java.util.*;




//class Friend implements Comparable<Friend>{
//
//    public Friend(String name,HashMap<Integer,Group> globalGroups) {
//        this.name = name;
//        this.uniqueId = IdGen.getNewId();
//        Group selfGroup = new Group();
//        selfGroup.addFriend(this);
//        group.add(selfGroup);
//        globalGroups.put(selfGroup.getUniqueId(), selfGroup);
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//
//        Friend friend = (Friend) o;
//
//        if (uniqueId != friend.uniqueId) return false;
//
//        return true;
//    }
//
//    @Override
//    public int hashCode() {
//        return uniqueId;
//    }
//
//    @Override
//    public String toString() {
//        return "Friend{" +
//                "name='" + name + '\'' +
//                '}';
//    }
//
//    public String getName() {
//        return name;
//    }
//
//
//
//
//
//    public void addGroup(Group group) {
//        this.group.add(group);
//    }
//
//    void addOneWayFriend(Friend friend) {
//        oneWayFriends.add(friend);
//    }
//
//    boolean isOnewayFriend(Friend friend) {
//        return oneWayFriends.contains(friend.getUniqueId());
//    }
//
//    boolean isOnewayFriend(int friendId) {
//        return oneWayFriends.contains(friendId);
//    }
//
//    private String name;
//    private HashSet<Friend> oneWayFriends = new HashSet<>();
//    private HashSet<Group> group = new HashSet<>();
//
//    @Override
//    public int compareTo(Friend o) {
//        return this.getName().compareTo(o.getName());
//    }
//
//    private int uniqueId = 0;
//
//
//    public int getUniqueId() {
//        return uniqueId;
//    }
//
//    public void setUniqueId(int uniqueId) {
//        this.uniqueId = uniqueId;
//    }
//}
//
//class Group implements Comparable<Group>{
//    private HashMap<Integer,Friend> group = new HashMap<>();
//    private int uniqueId = 0;
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//
//        Group group = (Group) o;
//
//        if (uniqueId != group.uniqueId) return false;
//
//        return true;
//    }
//
//    @Override
//    public int hashCode() {
//        return uniqueId;
//    }
//
//    @Override
//    public String toString() {
//        StringBuilder stringBuilder = new StringBuilder();
//        for (Friend friend : group.values()) {
//            stringBuilder.append(friend.toString());
//        }
//
//        return stringBuilder.toString();
//    }
//
//    Group() {
//        uniqueId = IdGen.getNewId();
//    }
//
////    void validate(String fileName) {
////        for (Friend sourceFriend : getGroup()) {
////            for (Friend taretFriend : getGroup()) {
////                if (sourceFriend == taretFriend) {
////                    continue;
////                }
////
////                boolean validate = false;
////                try {
////                    BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
////                    String line;
////                    while ((line = bufferedReader.readLine()) != null) {
////                        String x[] = line.split("\t");
////                        if (x[1].equals(sourceFriend.getName()) && x[2].equals(taretFriend.getName())) {
////                            System.out.println("Found mapping " +  sourceFriend.getName() + " " + taretFriend.getName());
////                            System.out.println(line);
////                            validate = true;
////                            break;
////                        }
////                    }
////
////                    if (validate == false) {
////                        System.out.println("=========Its a failure==========");
////                        System.exit(-1);
////                    }
////                } catch (FileNotFoundException e) {
////                    e.printStackTrace();
////                } catch (IOException e) {
////                    e.printStackTrace();
////                }
////            }
////        }
////    }
//    void addFriend(Friend friend) {
//        group.put(friend.getUniqueId(), friend);
//        friend.addGroup(this);
//    }
//
//    void removeFriend(Friend friend) {
//        group.remove(friend);
//    }
//
//    boolean findFriend(Friend friend) {
//        return group.containsKey(friend.getUniqueId());
//    }
//
//    boolean canBeAddedToGroup(Friend friend) {
//
////        if (group.contains(friend)) {
////            return true;
////        }
//
//        for (Integer groupFriendId : group.keySet()) {
//            if (!friend.isOnewayFriend(groupFriendId)) {
//                return false;
//            }
//
//            if (!group.get(groupFriendId).isOnewayFriend(friend.getUniqueId())) {
//                return false;
//            }
//
//
//        }
//
//        return true;
//    }
//
//    HashMap<Integer,Friend> getGroup() {
//        return group;
//    }
//
//    TreeSet<Friend> getSortedFriendSet() {
//        TreeSet<Friend> treeSet = new TreeSet<>();
//        for (Map.Entry<Integer,Friend> entry : getGroup().entrySet()) {
//            treeSet.add(entry.getValue());
//        }
//
//        return treeSet;
//    }
//    @Override
//    public int compareTo(Group o) {
//        //Make whole String
//        StringBuilder stringSelfBuilder = new StringBuilder();
//
//        for (Friend friend : getSortedFriendSet()) {
//            stringSelfBuilder.append(friend.getName());
//        }
//
//        StringBuilder stringThatBuilder = new StringBuilder();
//        for (Friend friend : o.getSortedFriendSet()) {
//            stringThatBuilder.append(friend.getName());
//        }
//
//        return stringSelfBuilder.toString().compareTo(stringThatBuilder.toString());
//    }
//
//    void mergeGroups(Group g) {
//        //addedGroups.add(g);
//        idNewAddedGroups.add(g.getUniqueId());
//    }
//
//    boolean isAlreadyMerged(Group g) {
//        return  idNewAddedGroups.contains(g.getUniqueId());
//    }
//
//
//    void setPrint(boolean x) {
//        this.print = x;
//    }
//    boolean getPrint() {
//        return print;
//    }
//
//
//    //private TreeSet<Group> addedGroups = new TreeSet<>();
//    private boolean print = true;
//    private HashSet<Integer> idNewAddedGroups = new HashSet<>();
//
//
//    public int getUniqueId() {
//        return uniqueId;
//    }
//
//    public void setUniqueId(int uniqueId) {
//        this.uniqueId = uniqueId;
//    }
//}

//class TrafficAnalysis {
//    //Assumption both are in different group and groups are valid
//    private HashMap<Integer,Group> groups = new HashMap<>();
//
//
//    Group mergeGroup(Group group1,Group group2) {
//        Group mergedGroup = new Group();
//
//        for (Friend friend : group2.getGroup().values()) {
//            mergedGroup.addFriend(friend);
//            friend.addGroup(mergedGroup);
//        }
//
//        for (Friend friend : group1.getGroup().values()) {
//            mergedGroup.addFriend(friend);
//            friend.addGroup(mergedGroup);
//        }
//
//        return mergedGroup;
//
//    }
//
//    boolean manipulateGroup(Friend sourceFriend,Friend destinationFriend,HashMap<String,Friend> persons) {
//        //System.out.println("Manipulating group " + sourceFriend.getName() + " " + destinationFriend.getName());
//
//        //Check for one way friendShip
//        sourceFriend.addOneWayFriend(destinationFriend);
//
//        return false;
//
//    }
//
//    //Is this a new finding or old finding only
//    boolean iterate(String edge,HashMap<String,Friend> persons) {
//
//        //System.out.println(edge);
//        String x[] = edge.split("\t");
//
//        String sourceFriendName = x[1];
//        String targetFriendName = x[2];
//
//        Friend sourceFriend = persons.get(sourceFriendName);
//        if (sourceFriend == null) {
//             sourceFriend = new Friend(sourceFriendName,groups);
//            persons.put(sourceFriendName,sourceFriend);
//        }
//
//        Friend targetFriend = persons.get(targetFriendName);
//        if (targetFriend == null) {
//            targetFriend = new Friend(targetFriendName,groups);
//            persons.put(targetFriendName,targetFriend);
//        }
//
//        return manipulateGroup(sourceFriend,targetFriend,persons);
//
//
//
//    }
//
//    void print() {
//        TreeSet<Group> treeSet = new TreeSet<>();
//        for (Group group : groups.values()) {
//            if (group.getGroup().size() >= 3 && group.getPrint()) {
//                treeSet.add(group);
//            }
//        }
//
//        for (Group group : treeSet) {
//
//            int i = 0;
//
//
//
//            for (Friend friend : group.getSortedFriendSet()) {
//                System.out.print(friend.getName() + ((i == group.getGroup().size()-1)?"":", "));
//                ++i;
//            }
//
//            System.out.println();
//        }
//
////        for (Group group : groups.values()) {
////            if (group.getGroup().size() < 3) {
////                continue;
////            }
////
////
////            if (group.getPrint() == false) {
////                continue;
////            }
////
////
////
////            int i = 0;
////
////
////
////            for (Friend friend : group.getGroup()) {
////                System.out.print(friend.getName() + ((i == group.getGroup().size()-1)?"":", "));
////                ++i;
////            }
////
////            System.out.println();
////
////        }
//    }
//
//    Group checkGroupIfItCanBeMerged(Group sourceGroup) {
//       for (Group targetGroup : groups.values()) {
//           if (sourceGroup == targetGroup) {
//               continue;
//           }
//
//           boolean canThisGroupBeMerged = true;
//           for (Friend sourceFriend : sourceGroup.getGroup().values()) {
//               if (!targetGroup.canBeAddedToGroup(sourceFriend)) {
//                   canThisGroupBeMerged = false;
//                   break;
//               }
//
//
//           }
//
//           if (canThisGroupBeMerged) {
//               return targetGroup;
//           }
//
//       }
//
//       return null;
//    }
//
//
//    void massageGroups() {
//
//
//        boolean mergePending = true;
//        while (mergePending == true) {
//            mergePending = false;
//            for (Group group : groups.values()) {
//                Group targetGroup = checkGroupIfItCanBeMerged(group);
//                if (targetGroup != null && !group.isAlreadyMerged(targetGroup)) {
//                    //System.out.println("Massaged successfully before" + targetGroup.getGroup().toString() + "  " + group.toString());
//                    Group mergedGroup = mergeGroup(targetGroup, group);
//                    //groups.remove(group);
//                    //groups.remove(targetGroup);
//                    group.mergeGroups(targetGroup);
//                    group.mergeGroups(mergedGroup);
//                    targetGroup.mergeGroups(group);
//
//                    mergedGroup.mergeGroups(targetGroup);
//                    mergedGroup.mergeGroups(group);
//                    targetGroup.setPrint(false);
//                    group.setPrint(false);
//                    groups.put(mergedGroup.getUniqueId(),mergedGroup);
//
//                    mergePending = true;
//                    break;
//                }
//
//            }
//
//
//        }
//    }
//}

class Friend implements Comparable<Friend>{
    public Friend(String name) {
        this.name = name;
        //this.addOneWayFriend(this); //Self relatationship

    }




    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Friend friend = (Friend) o;

        if (!name.equals(friend.name)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    void addOneWayFriend(Friend friend) {
        //System.out.println("Source friend " + this.toString() + " Destination Friend " + friend.toString());
        oneWayFriend.add(friend);
    }

    boolean isOneWayFriend(Friend friend) {
        return oneWayFriend.contains(friend);
    }

    public static boolean areTwoFriendsElibileForGroup(Friend f1,Friend f2) {
        return f1.isOneWayFriend(f2)  && f2.isOneWayFriend(f1);
    }

    @Override
    public String toString() {
        return "Friend{" +
                "name='" + name + '\'' +
                '}';
    }

    HashSet<Friend> getOneWayFriends() {
        return oneWayFriend;
    }

    private String name;
    private HashSet<Friend> oneWayFriend = new HashSet<>();

    @Override
    public int compareTo(Friend o) {
        return getName().compareTo(o.getName());
    }
    //private HashSet<Group> groups = new HashSet<>(); //One belongs to how many groups
}


class Group implements Comparable<Group> {
    private HashSet<Friend> privateGroup = new HashSet<>(); //All of these talks
    HashSet<Friend> getFriendsInGroup() {
        return privateGroup;
    }

    private List<Friend> sortedLst;
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Friend friend : privateGroup) {
            stringBuilder.append(friend.toString());
        }

        return stringBuilder.toString();
    }

    String getString() {
        sortedLst = new ArrayList<>(getFriendsInGroup());
        Collections.sort(sortedLst);
        StringBuilder stringBuilder = new StringBuilder();
        for (Friend friend: sortedLst) {
            stringBuilder.append(friend.getName());
        }

        return stringBuilder.toString();
    }

    List<Friend> getSortedLst() {
        return sortedLst;
    }

    boolean isOkToAddFriend(Friend friend) {
       return true;
    }

    void addFriend(Friend friend) {
        privateGroup.add(friend);
    }




    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Group group = (Group) o;

        if (!privateGroup.equals(group.privateGroup)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return privateGroup.hashCode();
    }



    public static boolean areTwoGroupsElibileForMerging(Group g1,Group g2) {
        for (Friend group1Friend : g1.getFriendsInGroup()) {
            for (Friend group2Friend : g2.getFriendsInGroup()) {
//                if (group1Friend == group2Friend) {
//                    continue; //Same reference
//                }
                if (!Friend.areTwoFriendsElibileForGroup(group1Friend,group2Friend)) {
                    return false;
                }
            }
        }

        return true;
    }

    public static Group mergeTwoGroups(Group g1,Group g2) {
        Group mergedGroup = new Group();

        for (Friend friend : g1.getFriendsInGroup()) {
            mergedGroup.addFriend(friend);
        }

        for (Friend friend : g2.getFriendsInGroup()) {
            mergedGroup.addFriend(friend);
        }

        return mergedGroup;
    }

    @Override
    public int compareTo(Group o) {
        return getString().compareTo(o.getString());
    }
}

class TrafficAnalysis {

    HashMap<String,Friend> totalFriends = new HashMap<>();
    HashSet<Group> groups = new HashSet<>();
    void formElementaryGroups() {
        //System.out.println("Forming elementary groups");
        for (Map.Entry<String,Friend> entry : totalFriends.entrySet()) {
            Group group = new Group();
            group.addFriend(entry.getValue());
            groups.add(group);
        }

        //System.out.println("Formed elementary groups size = " + groups.size());
    }



    Group findAlreadyConsistedElements(HashSet<Group> groups,Friend f1,Friend f2) {
        for (Group group : groups) {
            if (group.getFriendsInGroup().contains(f1) && group.getFriendsInGroup().contains(f2)) {
                return group;
            }
        }

        return null;

    }

    Group findValidConsistedElements(HashSet<Group> groups,Friend f1,Friend f2) {
        for (Group group : groups) {
            //Check if f1 and f2 are valid for entering to this group
            for (Friend groupFriend : group.getFriendsInGroup()) {
                if (Friend.areTwoFriendsElibileForGroup(groupFriend,f1) && Friend.areTwoFriendsElibileForGroup(groupFriend,f2)) {
                    return group;
                }
            }
        }

        return null;
    }
    void mergeGroups() {
        //Now we have one member group for all
        //Lets merge it
        int totalSize = groups.size();
        HashSet<Group> associatedGroup = new HashSet<>();
        for (Group group : groups) {

            for (Friend sourceFriend : group.getFriendsInGroup()) {
                //System.out.println("Inside source friend " + sourceFriend.toString() + " " + sourceFriend.getOneWayFriends().size());
                HashSet<Group> sourceFriendGroup = new HashSet<>();
                for (Friend destinationFriend : sourceFriend.getOneWayFriends()) {
                    //System.out.println("Inside destination friend " + destinationFriend.toString());
//                    if (sourceFriend == destinationFriend) {
//                        continue;
//                    }

                    if (Friend.areTwoFriendsElibileForGroup(sourceFriend,destinationFriend)) {
                       //Check if any groups were already created because of it
                        //System.out.println("Both are eligible " + sourceFriend.toString() + "  " + destinationFriend.toString());
                        Group alreadyAddedGroup = findAlreadyConsistedElements(sourceFriendGroup,sourceFriend,destinationFriend);

                        if (alreadyAddedGroup != null) {
                            //No need just continue
                            //System.out.println("Both are added ");
                            continue;
                        }

                        //Check if any valid group is present
                        Group validGroup = findValidConsistedElements(sourceFriendGroup,sourceFriend,destinationFriend);

                        if (validGroup != null) {
                            //System.out.println("Both are not added but valid is present " + validGroup.toString());
                            validGroup.addFriend(sourceFriend);
                            validGroup.addFriend(destinationFriend);
                            continue;
                        }

                        //Now create a new group and add it
                        //System.out.println("Creating a new group");
                        Group newGroup = new Group();
                        newGroup.addFriend(sourceFriend);
                        newGroup.addFriend(destinationFriend);
                        sourceFriendGroup.add(newGroup);

                    }
                }

                associatedGroup.addAll(sourceFriendGroup);

            }
        }

        groups = associatedGroup;
        sort();
        //print();
//        for (HashSet<Group> group: associatedGroup) {
//            print(group);
//        }
        //print(associatedGroup);


    }

    void sort() {

        List<Group> groups1 = new ArrayList<>();
        for (Group group : groups) {
            if (group.getFriendsInGroup().size() >= 3) {
                groups1.add(group);
            }
        }

        Collections.sort(groups1);

        int j = 0;
        for (Group group : groups1) {

            List<Friend> friends = group.getSortedLst();
            int i = 0;
            for (Friend friend : friends) {
                System.out.print(friend.getName() + ((i == friends.size()-1)?"":", "));
                ++i;
            }

            if (j != groups1.size()-1) {
                System.out.println();
            }

            ++j;


        }



    }
    void print() {
        for (Group group : groups) {
            for (Friend friend : group.getFriendsInGroup()) {
                System.out.print(friend.getName() +  ",");
            }

            System.out.println();
        }
    }

    void print(HashSet<Group> groups) {
        for (Group group : groups) {
            for (Friend friend : group.getFriendsInGroup()) {
                System.out.print(friend.getName() +  ",");
            }

            System.out.println();
        }
    }
    public void iterate(String line) {
        String x[] = line.split("\t");

        String sourceFriendName = x[1];
        String targetFriendName = x[2];

//        System.out.println("source friend name " + sourceFriendName + " " + targetFriendName + "  " + totalFriends.size());
//        for (Map.Entry<String,Friend> entry : totalFriends.entrySet()) {
//            System.out.println(entry.getKey() + " " + entry.getValue().toString());
//        }
        Friend sourceFriend = null;
        if (totalFriends.containsKey(sourceFriendName)) {
            //System.out.println("source friend  found " + sourceFriendName);
            sourceFriend = totalFriends.get(sourceFriendName);
            //System.out.println("source friend after searc " + sourceFriend);
        }else {
            //System.out.println("source friend not found " + sourceFriendName);
            sourceFriend = new Friend(sourceFriendName);
            totalFriends.put(sourceFriendName,sourceFriend);
        }

        Friend targetFriend = null;

        if (totalFriends.containsKey(targetFriendName)) {
            //System.out.println("target friend  found " + targetFriendName);
            targetFriend = totalFriends.get(targetFriendName);
        }else {
            //System.out.println("target friend  not found " + targetFriendName);
            targetFriend = new Friend(targetFriendName);
            //System.out.println("target friend  adding found " + targetFriend);
            totalFriends.put(targetFriendName,targetFriend);
        }

        sourceFriend.addOneWayFriend(targetFriend);



    }
}

public class Main {



    void readFile(String fileName) {

        TrafficAnalysis trafficAnalysis = new TrafficAnalysis();

        try {

            FileInputStream fileInputStream = new FileInputStream(fileName);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                   trafficAnalysis.iterate(line);

            }




        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        trafficAnalysis.formElementaryGroups();
        trafficAnalysis.mergeGroups();
        //trafficAnalysis.print();
    }

    public static void main(String args[]) {
        Main main = new Main();
        //main.print();
        main.readFile(args[0]);

    }


}
