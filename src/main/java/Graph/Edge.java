package Graph;

/**
 * Created by Rahul on 11/20/15.
 */
public class Edge implements Comparable<Edge>{
    private int sourceIndex;
    private int destinationIndex;
    private double weight = 0;

    public Edge(int sourceIndex, int destinationIndex, double weight) {
        this.sourceIndex = sourceIndex;
        this.destinationIndex = destinationIndex;
        this.weight = weight;
    }

    public Edge(int sourceIndex, int destinationIndex) {
        this.sourceIndex = sourceIndex;
        this.destinationIndex = destinationIndex;
    }

    public int getSourceIndex() {
        return sourceIndex;
    }

    public int getDestinationIndex() {
        return destinationIndex;
    }

    int other(int p) {
        return sourceIndex == p?destinationIndex:sourceIndex;
    }

    public double getWeight() {
        return weight;
    }

    @Override
    public int compareTo(Edge o) {
        return Double.compare(weight,o.getWeight());
    }

    @Override
    public String toString() {
        return "Edge{" +
                "sourceIndex=" + sourceIndex +
                ", destinationIndex=" + destinationIndex +
                ", weight=" + weight +
                '}';
    }
}
