package Encircular;


public class Solution {
    class Point {
        private int x;
        private int y;

        public Point(int x, int y) {
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

            Point point = (Point) o;

            if (x != point.x) return false;
            if (y != point.y) return false;

            return true;
        }

        @Override
        public int hashCode() {
            int result = x;
            result = 31 * result + y;
            return result;
        }
    }

    interface IncrementPolicy {
        Point increment(Point currentPosition);
        IncrementPolicy turnLeft(Point currentPosition);
        IncrementPolicy turnRight(Point currentPosition);


    }



    class NorthMovement implements IncrementPolicy {

        @Override
        public Point increment(Point currentPosition) {
            return new Point(currentPosition.getX(),currentPosition.getY()+1);
        }

        @Override
        public IncrementPolicy turnLeft(Point currentPosition) {
            return new WestMovement();
        }

        @Override
        public IncrementPolicy turnRight(Point currentPosition) {
            return new EastMovement();
        }
    }

    class EastMovement implements IncrementPolicy {


        @Override
        public Point increment(Point currentPosition) {
            return new Point(currentPosition.getX()+1,currentPosition.getY());
        }


        @Override
        public IncrementPolicy turnLeft(Point currentPosition) {
            return new NorthMovement();
        }

        @Override
        public IncrementPolicy turnRight(Point currentPosition) {
            return new SouthMovement();
        }
    }

    class WestMovement implements IncrementPolicy {

        @Override
        public Point increment(Point currentPosition) {
            return new Point(currentPosition.getX()-1,currentPosition.getY());
        }

        @Override
        public IncrementPolicy turnLeft(Point currentPosition) {
            return new SouthMovement();
        }

        @Override
        public IncrementPolicy turnRight(Point currentPosition) {
            return new NorthMovement();
        }
    }

    class SouthMovement implements IncrementPolicy {

        @Override
        public Point increment(Point currentPosition) {
            return new Point(currentPosition.getX(),currentPosition.getY()-1);
        }

        @Override
        public IncrementPolicy turnLeft(Point currentPosition) {
            return new EastMovement();
        }

        @Override
        public IncrementPolicy turnRight(Point currentPosition) {
            return new WestMovement();
        }
    }

    static String doesCircleExists(String commands) {
        Solution solution = new Solution();
        Point point = solution.new Point(0,0);
        IncrementPolicy movement = solution.new NorthMovement();

        for (int j = 0;j<2550;++j) {
            for (int i = 0; i < commands.length(); ++i) {
                switch (commands.charAt(i)) {
                    case 'G':
                        point = movement.increment(point);
                        break;
                    case 'L':
                        movement = movement.turnLeft(point);
                        break;
                    case 'R':
                        movement = movement.turnRight(point);
                        break;
                }
            }
            if (point.getX() == 0 && point.getY()==0) {
                return "YES";
            }

        }

        if (point.getX() == 0 && point.getY()==0) {
            return "YES";
        }else {
            return "NO";
        }

    }


    public static void main(String[] args) {
        System.out.println(doesCircleExists("GLL"));
    }
}
