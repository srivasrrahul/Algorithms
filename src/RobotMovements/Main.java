package RobotMovements;

import java.util.ArrayList;

public class Main {
    boolean isValidCoordinate(int x,int y) {
        if (x <0 || x > 3) {
            return false;
        }

        if (y < 0 || y > 3) {
            return false;
        }

        return true;
    }

    ArrayList<ArrayList<Integer>> selfClone( ArrayList<ArrayList<Integer>> x) {
        ArrayList<ArrayList<Integer>> clonedObj = new ArrayList<>();
        for (ArrayList<Integer> arr : x) {
            ArrayList<Integer> cloneArr = (ArrayList<Integer>) arr.clone();
            clonedObj.add(cloneArr);
        }

        return clonedObj;
    }

    void printArray(int x[][]) {
        System.out.println("===========");
        for (int i = 0;i<3;++i) {
            for (int j = 0;j<3;++j) {
                System.out.print(x[i][j] + " ");
            }

            System.out.println();
        }
        System.out.println("===========");
    }
    int printNumberOfPaths(int x,int y,ArrayList<ArrayList<Integer>> arrayList) {
        //System.out.println("Pos1;");
        if (x == 3 && y == 3) {
            //System.out.println("Reached destination " + x + " " + y);
            arrayList.get(x).set(y,1);
            return 1;
        }

        //Check validity
        //If not valid return -1
        boolean validCoordinate = isValidCoordinate(x,y);
        if (validCoordinate == false) {
            return 0;
        }

        //System.out.println("Pos2;");

        //If already in path
        if (arrayList.get(x).get(y) == 1) {
            //System.out.println("Its already touched " + x + " " + y);
            return 0;
        }


        //Mark current as 1
        arrayList.get(x).set(y,1);
        int totalPaths = 0;
        //System.out.println("X = " + x + " Y = " + y + " last set " + arrayList.get(x).get(y));
        //printArray(path);
        //System.out.println("===========");
//        for (int i = 3;i>=0;--i) {
//            for (int j = 0;j<=3;++j) {
//                System.out.print(arrayList.get(i).get(j) + " ");
//            }
//
//            System.out.println();
//        }
//        System.out.println("===========");

        //Move Left
        ArrayList<ArrayList<Integer>> copy1 = selfClone(arrayList);


        int c1 = printNumberOfPaths(x-1,y,copy1);
        if (c1 != 0) {
            totalPaths += c1;
        }

        //Move right
        ArrayList<ArrayList<Integer>> copy2 = selfClone(arrayList);
        int c2 = printNumberOfPaths(x+1,y,copy2);
        if (c2 != 0) {
            totalPaths+=c2;
        }

        //Move up
        ArrayList<ArrayList<Integer>> copy3 = selfClone(arrayList);
        int c3 = printNumberOfPaths(x,y-1,copy3);
        if (c3 != 0) {
            totalPaths+=c3;
        }

        //Move down
        ArrayList<ArrayList<Integer>> copy4 = selfClone(arrayList);
        int c4 = printNumberOfPaths(x,y+1,copy4);
        if (c4 != 0) {
            totalPaths +=c4;
        }

        //Now out of the paths encountered
        //System.out.println("Total Paths = " + totalPaths);
//        if (totalPaths == 1) {
//            //It means no addition
//            //Lets leave it here
//            return 0;
//        }
        return totalPaths;

    }

    public static void main(String []args) {
        Main m = new Main();
        ArrayList<ArrayList<Integer>> arrayList = new ArrayList<>();
        for (int i = 0;i<4;++i) {
            arrayList.add(new ArrayList<Integer>(4));
        }

        for (int i = 0;i<4;++i) {
            ArrayList<Integer> arrayList1 = arrayList.get(i);
            for (int j = 0;j<4;++j) {
                arrayList1.add(0);
            }
        }

        //path[0][3] = 1; //already in first path
        System.out.println(m.printNumberOfPaths(0,0,arrayList));

//        ArrayList<Integer> a1 = new ArrayList<>();
//        ArrayList<Integer> a2 = (ArrayList<Integer>) a1.clone();
//        a2.add(0);
//        System.out.println(a1.size());
    }
}
