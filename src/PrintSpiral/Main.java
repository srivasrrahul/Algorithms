package PrintSpiral;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import java.util.HashSet;

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

    Tuple getLeft(int rowSize,int colSize) {
        if (y == 0) {
            return null;
        }

        return new Tuple(x,y-1);
    }

    Tuple getRight(int rowSize,int colSize) {
        if (y == colSize-1) {
            return null;
        }
        return new Tuple(x,y+1);
    }

    @Override
    public String toString() {
        return "Tuple{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    Tuple getDown(int rowSize,int colSize) {
        if (x == rowSize-1) {
            return null;
        }

        return new Tuple(x+1,y);
    }


    Tuple getUp(int rowSize,int colSize) {
        if (x == 0) {
            return null;
        }

        return new Tuple(x-1,y);
    }

}


//Code is repeated and can be objectified but its trivial to do it
public class Main {


    void printTuple(Tuple t,ArrayList<ArrayList<String>> members) {
        System.out.print(members.get(t.getX()).get(t.getY()) + " ");
    }

    void printRight(ArrayList<ArrayList<String>> members,Tuple currentTuple,HashSet<Tuple> tuplesPrinted,int pending) {

        if (pending <= 0) {
            return;
        }

        int rowSize = members.size();
        int colSize = members.get(0).size();

        if (pending == 1) {
            System.out.print(members.get(currentTuple.getX()).get(currentTuple.getY()));
            return;
        }else {
            //System.out.print(members.get(currentTuple.getX()).get(currentTuple.getY()) + " ");
            printTuple(currentTuple,members);
            --pending;
            //Adding current tuple
            //System.out.println(currentTuple.toString());
            tuplesPrinted.add(currentTuple);
        }

        Tuple nextTuple = currentTuple.getRight(rowSize,colSize);

        if (nextTuple == null || tuplesPrinted.contains(nextTuple)) {
            nextTuple = currentTuple;
        }else {
            while (nextTuple != null && !tuplesPrinted.contains(nextTuple)) {
                printTuple(nextTuple, members);
                --pending;
                tuplesPrinted.add(nextTuple);
                Tuple temp = nextTuple.getRight(rowSize, colSize);
                if (null == temp || tuplesPrinted.contains(temp)) {
                    break;
                }
                nextTuple = temp;
            }
        }



        printDown(members,nextTuple.getDown(rowSize, colSize),tuplesPrinted,pending);
    }

    void printDown(ArrayList<ArrayList<String>> members,Tuple currentTuple,HashSet<Tuple> tuplesPrinted,int pending) {
        //System.out.println("Inside down");
        if (pending <= 0) {
            return;
        }

        int rowSize = members.size();
        int colSize = members.get(0).size();


        if (pending == 1) {
            System.out.print(members.get(currentTuple.getX()).get(currentTuple.getY()));
            return;
        }else {
            //System.out.print(members.get(currentTuple.getX()).get(currentTuple.getY()) + " ");
            printTuple(currentTuple,members);
            --pending;
            tuplesPrinted.add(currentTuple);
            //System.out.println(currentTuple.toString());
        }



        Tuple nextTuple = currentTuple.getDown(rowSize, colSize);

        if (nextTuple == null || tuplesPrinted.contains(nextTuple)) {
            nextTuple = currentTuple;
        }else {
            while (nextTuple != null && !tuplesPrinted.contains(nextTuple)) {
                //System.out.println("Inside left");
                printTuple(nextTuple, members);
                --pending;
                tuplesPrinted.add(nextTuple);
                Tuple temp = nextTuple.getDown(rowSize, colSize);
                if (null == temp || tuplesPrinted.contains(temp)) {
                    break;
                }
                nextTuple = temp;
            }
        }



        printLeft(members, nextTuple.getLeft(rowSize, colSize), tuplesPrinted, pending);
    }

    void printLeft(ArrayList<ArrayList<String>> members,Tuple currentTuple,HashSet<Tuple> tuplesPrinted,int pending) {
        //System.out.println("Inside left");
        //System.out.println("Inside left");
        if (pending <= 0) {
            return;
        }

        int rowSize = members.size();
        int colSize = members.get(0).size();


        if (pending == 1) {
            System.out.print(members.get(currentTuple.getX()).get(currentTuple.getY()));
            return;
        }else {
            //System.out.print(members.get(currentTuple.getX()).get(currentTuple.getY()) + " ");
            printTuple(currentTuple,members);
            --pending;
            tuplesPrinted.add(currentTuple);
            //System.out.println(currentTuple.toString());
        }

        Tuple nextTuple = currentTuple.getLeft(rowSize, colSize);
        if (nextTuple == null || tuplesPrinted.contains(nextTuple)) {
            nextTuple = currentTuple;
        }else {
            while (nextTuple != null && !tuplesPrinted.contains(nextTuple)) {
                printTuple(nextTuple, members);
                --pending;
                tuplesPrinted.add(nextTuple);
                Tuple temp = nextTuple.getLeft(rowSize, colSize);
                if (null == temp || tuplesPrinted.contains(temp)) {
                    break;
                }
                nextTuple = temp;
            }
        }





        //System.out.println("Before printing up");
        printUp(members, nextTuple.getUp(rowSize, colSize), tuplesPrinted, pending);
    }

    void printUp(ArrayList<ArrayList<String>> members,Tuple currentTuple,HashSet<Tuple> tuplesPrinted,int pending) {
        if (pending <= 0) {
            return;
        }
        //System.out.println("Inside up");
        int rowSize = members.size();
        int colSize = members.get(0).size();


        if (pending == 1) {
            System.out.print(members.get(currentTuple.getX()).get(currentTuple.getY()));
            return;
        }

        else {
            //System.out.print(members.get(currentTuple.getX()).get(currentTuple.getY()) + " ");
            printTuple(currentTuple,members);
            --pending;
            tuplesPrinted.add(currentTuple);
            //System.out.println(currentTuple.toString());
        }


        Tuple nextTuple = currentTuple.getUp(rowSize, colSize);

        if (nextTuple == null || tuplesPrinted.contains(nextTuple)) {
            nextTuple = currentTuple;
        }else {
            while (nextTuple != null && !tuplesPrinted.contains(nextTuple)) {
                //System.out.println("Inside up1");
                printTuple(nextTuple, members);
                --pending;
                tuplesPrinted.add(nextTuple);
                Tuple temp = nextTuple.getUp(rowSize, colSize);
                //System.out.println("Next in up " + temp.toString() + tuplesPrinted.contains(temp));
                if (null == temp || tuplesPrinted.contains(temp)) {

                    break;
                }
                nextTuple = temp;
            }
        }






        printRight(members, nextTuple.getRight(rowSize, colSize), tuplesPrinted, pending);
    }


    void printArray(ArrayList<ArrayList<String>> members) {
        HashSet<Tuple> tuplePrinted = new HashSet<>();
        int pending = members.size() * members.get(0).size();

        Tuple currentTuple = new Tuple(0,0);
        printRight(members,currentTuple,tuplePrinted,pending);





        //Last print without " "

    }

    void handleLine(String line) {
        //System.out.println(line);
        String arr[] = line.split(";");
        int x = Integer.parseInt(arr[0]);
        int y = Integer.parseInt((arr[1]));

        ArrayList<ArrayList<String>> arrayLists = new ArrayList<>();
        for (int i = 0;i<x;++i) {
            arrayLists.add(new ArrayList<String>());
        }

        String strings[] = arr[2].split(" ");
        int k = 0;
        for (int i = 0;i<x;++i) {
            for (int j = 0;j<y;++j) {
                arrayLists.get(i).add(strings[k]);
                ++k;
            }
        }

        printArray(arrayLists);
        System.out.println();
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

//    public static void main(String args[]) {
//        ArrayList<ArrayList<String >> arr = new ArrayList<>();
//        arr.add(new ArrayList<String>());
//        arr.add(new ArrayList<String>());
//        arr.add(new ArrayList<String>());
//
//        Collections.addAll(arr.get(0),"1","2","3");
//        Collections.addAll(arr.get(1),"4","5","6");
//        Collections.addAll(arr.get(2),"7","8","9");
//
//
//        Main m = new Main();
//        m.printArray(arr);
//
//    }
}
