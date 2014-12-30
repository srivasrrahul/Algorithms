package OverlapRectangle;

//Incomplete
class Rectangle {
    private int upperLeftX;
    private int upperLeftY;
    private int lowerRightX;
    private int lowerRightY;

    public Rectangle(int upperLeftX,int upperLeftY,int lowerRightX,int lowerRightY)  {
        this.upperLeftX = upperLeftX;
        this.upperLeftY = upperLeftY;
        this.lowerRightX = lowerRightX;
        this.lowerRightY = lowerRightY;
    }

    public int getUpperLeftX() {
        return upperLeftX;
    }

    public void setUpperLeftX(int upperLeftX) {
        this.upperLeftX = upperLeftX;
    }

    public int getUpperLeftY() {
        return upperLeftY;
    }

    public void setUpperLeftY(int upperLeftY) {
        this.upperLeftY = upperLeftY;
    }

    public int getLowerRightX() {
        return lowerRightX;
    }

    public void setLowerRightX(int lowerRightX) {
        this.lowerRightX = lowerRightX;
    }

    public int getLowerRightY() {
        return lowerRightY;
    }

    public void setLowerRightY(int lowerRightY) {
        this.lowerRightY = lowerRightY;
    }
}
public class Main {
    boolean isOverlap(Rectangle r1,Rectangle r2) {

        //Check horizontol overlap
        if (r1.getUpperLeftX() < r2.getUpperLeftX()) {
            if (r1.getLowerRightX() > r2.getUpperLeftX()) {
                return true;
            }
        }else {
            boolean check =  isOverlap(r2,r1);
            if (check) {
                return true;
            }
        }

        //Check vertical overlap
        if (r1.getLowerRightY() < r2.getLowerRightY()) {
            if (r1.getUpperLeftY() > r2.getUpperLeftY()) {
                return true;
            }
        }else {
            boolean check =  isOverlap(r2,r1);
            if (check) {
                return true;
            }
        }

        return false;
    }
}
