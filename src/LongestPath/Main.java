package LongestPath;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

public class Main {
    HashSet<Character> traverse(ArrayList<ArrayList<Character>> arrayLists,int i,int j,HashSet<Character> visited) {
        if (i < 0 || i > arrayLists.size()-1) {
            return visited;
        }

        if (j < 0 || j > arrayLists.size()-1) {
            return visited;
        }

        char element = arrayLists.get(i).get(j);
        if (visited.contains(element)) {
            return visited;
        }

        visited.add(element);

        HashSet<Character> maxSet;

        HashSet<Character> updatedSet1 = new HashSet<>(visited);
        HashSet<Character> leftSet = traverse(arrayLists,i,j-1,updatedSet1);
        maxSet = leftSet;

        HashSet<Character> updatedSet2 = new HashSet<>(visited);
        HashSet<Character> rightSet = traverse(arrayLists,i,j+1,updatedSet2);

        if (rightSet.size() > maxSet.size()) {
            maxSet = rightSet;
        }

        HashSet<Character> updatedSet3 = new HashSet<>(visited);
        HashSet<Character>  upSet = traverse(arrayLists,i-1,j,updatedSet3);

        if (upSet.size() > maxSet.size()) {
            maxSet = upSet;
        }

        HashSet<Character> updatedSet4 = new HashSet<>(visited);
        HashSet<Character>  downSet = traverse(arrayLists,i+1,j,updatedSet4);

        if (downSet.size() > maxSet.size()) {
            maxSet = downSet;
        }

        return maxSet;

    }

    void handleLine(String line) {

        int x = (int)Math.sqrt(line.length());
        ArrayList<ArrayList<Character>> arrayLists = new ArrayList<>();
        int count = 0;
        for (int i = 0;i<x;++i) {
            ArrayList<Character> arrayList = new ArrayList<>();
            for (int j = 0;j<x;++j) {
                arrayList.add(line.charAt(count));
                ++count;
            }

            //System.out.println(Arrays.asList(arrayList));
            arrayLists.add(arrayList);
        }

        HashSet<Character> max = new HashSet<>();
        for (int i = 0;i<x;++i) {
            for (int j = 0;j<x;++j) {
                HashSet<Character> visited = new HashSet<>();
                HashSet<Character>  result = traverse(arrayLists,i,j,visited);
                if (result.size() > max.size()) {
                    max = result;
                }
            }
        }

        System.out.println(max.size());


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


}
