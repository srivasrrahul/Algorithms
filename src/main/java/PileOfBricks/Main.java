package PileOfBricks;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


class Pair{
    private int x;
    private int y;
    Pair(int x ,int y) {
        this.x = x;
        this.y = y;
    }

    int getX() {
        return x;
    }

    void setX(int x) {
        this.x = x;
    }

    int getY() {
        return y;
    }

    void setY(int y) {
        this.y = y;
    }
}

interface IDistanceCalc {
    double getDistance(Pair p1,Pair p2);
}



class Point {
    private int x;
    private int y;
    private int z;
    Point(int x ,int y ,int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    Point() {
        x = 0;
        y = 0;
        z = 0;

    }

    Pair projectZ() {
        return new Pair(x,y);
    }

    Pair projectY() {
        return new Pair(x,z);
    }

    Pair projectX() {
        return new Pair(y,z);
    }

}

class Brick {
    private Point p1;
    private Point p2;

    Brick(Point p1,Point p2) {
        this.p1 = p1;
        this.p2 = p2;
    }

    //Gives the minimum distance required for hole
    boolean minDist(IDistanceCalc distanceCalc,double distance) {
        //Project Z
        Pair p1Z = p1.projectZ();
        Pair p2Z = p2.projectZ();

        double d1 = distanceCalc.getDistance(p1Z,p2Z);

        if (Double.compare(d1,distance) <= 0) {
            return true;
        }

        //Project Y
        Pair p1Y = p1.projectY();
        Pair p2Y = p2.projectY();

        double d2 = distanceCalc.getDistance(p1Y,p2Y);
        if (Double.compare(d2,distance) <= 0) {
            return true;
        }

        //Project X
        Pair p1X = p1.projectX();
        Pair p2X = p2.projectX();

        double d3 = distanceCalc.getDistance(p1X,p2X);

        if (Double.compare(d3,distance) <= 0) {
            return true;
        }

        return false;

    }
}

class DistanceCalc implements IDistanceCalc {

    @Override
    public double getDistance(Pair p1, Pair p2) {
        return Math.sqrt(Math.pow(p1.getX() - p2.getX(),2)+ Math.pow(p1.getY()-p2.getY(),2));
    }
}




public class Main {
    //[4,3] => Pair(4,3)
    Pair getPair(String str) {
        str = str.replace("[","");
        str = str.replace("]","");
        String arr [] = str.split(",");
        return new Pair(Integer.parseInt(arr[0]),Integer.parseInt(arr[1]));
    }

    //[1,4,3] => Point(1,4,3)
    Point getPoint(String str) {
        str = str.replace("[","");
        str = str.replace("]","");
        String arr [] = str.split(",");
        return new Point(Integer.parseInt(arr[0]),Integer.parseInt(arr[1]),Integer.parseInt(arr[2]));
    }

    void readFile(String fileName) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
            String line;
            DistanceCalc d = new DistanceCalc();

            while ((line = bufferedReader.readLine()) != null) {
                //System.out.println(line);
                String [] lineLst = line.split("\\|");
                //System.out.println(Arrays.toString(lineLst));
                String hole = lineLst[0];
                String [] holeCoordinates = hole.split(" ");
                //System.out.println(Arrays.toString(holeCoordinates));

                String holeL = holeCoordinates[0];
                String holeR = holeCoordinates[1];

                Pair p1Hole = getPair(holeL);
                Pair p2Hole = getPair(holeR);


                int loopIndex = 0;

                double holeLength = d.getDistance(p1Hole, p2Hole);
                for (String s : lineLst[1].split(";")) {
                    //System.out.println(s);
                     //Now a three dimensional point
                    s = s.replace("(","");
                    s = s.replace(")","");

                    String arr[] = s.split(" ");
                    //System.out.println(Arrays.toString(arr));
                    int index = Integer.parseInt(arr[0]);

                    Point p1L = getPoint(arr[1]);
                    Point p2L = getPoint(arr[2]);

                    Brick b = new Brick(p1L,p2L);


                    //How to compare two decimals??
                    if (b.minDist(d,holeLength)) {
                    //if (b.minDist(d) <= holeLength) {

                        if (loopIndex > 0) {
                            System.out.print(",");
                        }

                        System.out.print(index);


                        ++loopIndex;
                    }




                }
                if (loopIndex == 0) {
                    System.out.println("-");
                }else {

                    System.out.println("");
                }

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String args[]) {
        String fileName = args[0];
        Main m = new Main();
        m.readFile(fileName);

//        Brick b = new Brick(new Point(8,-1,3),new Point(0,5,4));
//        DistanceCalc d = new DistanceCalc();
//        Pair holeL = new Pair(-4,-5);
//        Pair holeR = new Pair(-5,-3);
//
//        System.out.println(b.minDist(d) + " " + d.getDistance(holeL,holeR));
    }
}
