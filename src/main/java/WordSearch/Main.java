package WordSearch;



import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;


class Tuple {
    private int x;
    private int y;

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

    public Tuple(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Main {

    HashMap<Character,ArrayList<Tuple>> characterArrayListHashMap = new HashMap<>();
    ArrayList<ArrayList<Character>> board = new ArrayList<>();

    ArrayList<ArrayList<Tuple>> graph = new ArrayList<>();

    Main() {
        ArrayList<Character> line1 = new ArrayList<>();
        Collections.addAll(line1, 'A', 'B', 'C', 'E');

        ArrayList<Character> line2 = new ArrayList<>();
        Collections.addAll(line2, 'S', 'F', 'C', 'S');

        ArrayList<Character> line3 = new ArrayList<>();
        Collections.addAll(line3, 'A', 'D', 'E', 'E');

        board.add(line1);
        board.add(line2);
        board.add(line3);

        //line3.clear();

        ArrayList<Tuple> tuples = new ArrayList<>();
        Collections.addAll(tuples,(new Tuple(0,0)),new Tuple(2,0));

        characterArrayListHashMap.put('A',tuples);

        ArrayList<Tuple> tuples1 = new ArrayList<>();
        Collections.addAll(tuples1,(new Tuple(0,1)));
        characterArrayListHashMap.put('B',tuples1);

        ArrayList<Tuple> tuples2 = new ArrayList<>();
        Collections.addAll(tuples2,(new Tuple(0,2)),new Tuple(1,2));
        characterArrayListHashMap.put('C',tuples2);

        ArrayList<Tuple> tuples3 = new ArrayList<>();
        Collections.addAll(tuples3,(new Tuple(0,3)),new Tuple(2,2),new Tuple(2,3));
        characterArrayListHashMap.put('E',tuples3);

        ArrayList<Tuple> tuples4 = new ArrayList<>();
        Collections.addAll(tuples4, (new Tuple(1, 0)), new Tuple(1, 3));
        characterArrayListHashMap.put('S', tuples4);

        ArrayList<Tuple> tuples5 = new ArrayList<>();
        Collections.addAll(tuples5,(new Tuple(2,1)));
        characterArrayListHashMap.put('D',tuples5);

        ArrayList<Tuple> tuples6 = new ArrayList<>();
        Collections.addAll(tuples6,(new Tuple(1,1)));
        characterArrayListHashMap.put('F',tuples6);

        graph.add(new ArrayList<Tuple>());
        for (int i = 0;i<3;++i) {
            graph.add(new ArrayList<Tuple>());
            for (int j = 0;j<4;++j) {
                graph.get(0).add(new Tuple(i,j));
            }

        }




    }

    void print() {
        for (ArrayList<Character> line : board) {
            for (Character character : line) {
                System.out.print(character + " ");
            }

            System.out.println();
        }
    }

    ArrayList<Tuple> getNeighbours(Tuple node) {
        ArrayList<Tuple> neighBours = new ArrayList<>();
        int x = node.getX();
        int y = node.getY();

        //Add left
        if (y != 0) {
            neighBours.add(new Tuple(x,y-1));
        }

        //Add right
        if (y != 3) {
            neighBours.add(new Tuple(x,y+1));
        }

        //Add up
        if (x != 0) {
            neighBours.add(new Tuple(x-1,y));
        }

        //Add below
        if (x != 2) {
            neighBours.add(new Tuple(x+1,y));
        }

        return neighBours;
    }
    boolean match(String str,Tuple startIndex) {

        //Do a breadth first search with startIndex
        //System.out.println("========Handling index======== " + startIndex.getX() + " , " + startIndex.getY());
        HashSet<Tuple> discovered = new HashSet<>();
        discovered.add(startIndex);
        ArrayList<ArrayList<Tuple>> layer = new ArrayList<>();

        int i = 0;
        int stringIndex = 1;
        layer.add(new ArrayList<Tuple>());
        layer.get(i).add(startIndex);
        while (layer.get(i).size() > 0) {
            //Add next layer
            //System.out.println("Inside layer i " + i);
            if (stringIndex ==str.length()) {
                //All are covered
                return true;
            }
            boolean found = false;
            layer.add(new ArrayList<Tuple>());
            for (Tuple u : layer.get(i)) {
                for (Tuple v : getNeighbours(u)) {
                    //System.out.println("Neighbours of u " + u.getX() + " , " + u.getY() + " " + board.get(v.getX()).get(v.getY()) + "  " + str.charAt(stringIndex));
                    if (!discovered.contains(v)) {

                        if (str.charAt(stringIndex) == board.get(v.getX()).get(v.getY())) {
                            found = true;
                            discovered.add(v);
                            layer.get(i+1).add(v);
                            //break;
                        }

                    }
                }
            }

            if (found == true) {
                ++stringIndex;
            }

            ++i;
        }
        return false;
    }

    void handleLine(String line) {
        //System.out.println("Handling " + line);
        Character ch = line.charAt(0);
        if (!characterArrayListHashMap.containsKey(ch)) {
            System.out.println("Wasted line " + line + " False");
            //System.out.println("False");
            return;
        }

        boolean lastValue = false;
        for (Tuple tuple:characterArrayListHashMap.get(ch)) {
            lastValue = match(line, tuple);
            if (lastValue) {
                break;
            }
        }

        //System.out.println(line + " " + (lastValue?"True":"False"));
        System.out.println(lastValue?"True":"False");

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
        //main.print();
        main.readFile(args[0]);
    }
}
