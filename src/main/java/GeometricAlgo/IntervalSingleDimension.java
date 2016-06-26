package GeometricAlgo;

/**
 * Created by Rahul on 3/17/16.
 */
public class IntervalSingleDimension implements Comparable<IntervalSingleDimension>{
    @Override
    public int compareTo(IntervalSingleDimension that) {
        if (this.low < that.low) {
            return -1;
        }

        if (this.low > that.low) {
            return 1;
        }

        //low is equal
        if (this.high < that.high) {
            return -1;
        }

        if (this.high > that.high) {
            return 1;
        }

        //both are equal
        return 0;
    }


    public boolean intersects(IntervalSingleDimension that) {
        if (that.high < this.low) {
            return false;
        }

        if (this.high < that.low) {
            return false;
        }

        return true;


    }


    boolean contains(int x) {
        return (x >= this.low && x <= this.high) ;


    }


    public int getLow() {
        return low;
    }

    public int getHigh() {
        return high;
    }

    @Override
    public String toString() {
        return "IntervalSingleDimension{" +
                "low=" + low +
                ", high=" + high +
                '}';
    }

    public IntervalSingleDimension(int low, int high) {
        this.low = low;
        this.high = high;
    }

    private int low;
    private int high;

}
